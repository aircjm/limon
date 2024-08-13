use axum::{
    routing::{get, post},
    http::StatusCode,
    response::IntoResponse,
    Json, Router,
};
use serde::{Deserialize, Serialize};
use uuid::{Timestamp, Uuid};
static mut PTT_ENABLED: bool = false;
static mut PTT_KEYS: Vec<String> = Vec::new();

use device_query::{DeviceQuery, DeviceState, Keycode};
use std::{thread, thread::sleep, time::{Duration, Instant}};
use serde_json::json;

#[derive(serde::Serialize, serde::Deserialize, Clone)]
pub struct PTTEvent {
    pub state: bool,
}



pub fn start_hotkey_watcher() {
    let mut ptt_state = false;
    thread::spawn(move || {
        let device_state = DeviceState::new();
        loop {
            // if unsafe { !PTT_ENABLED } {
            //     thread::sleep(Duration::from_millis(20));
            //     continue;
            // }

            let ptt_keys = unsafe { PTT_KEYS.clone() };
            let keys: Vec<Keycode> = device_state.get_keys();

            // Recreate keys as a string vector
            let mut keys_str: Vec<String> = Vec::new();
            for key in keys {
                keys_str.push(key.to_string());
            }

            println!("keys_str {}", keys_str.join(",").to_string());

            // Check if held keys matches all PTT keys
            let mut ptt_held = true;

            for key in &ptt_keys {
                // if the key is "Control" or "Shift", match both L and R version
                if key == "Control"
                    && !keys_str.contains(&"LControl".to_string())
                    && !keys_str.contains(&"RControl".to_string())
                {
                    ptt_held = false;
                }

                if key == "Shift"
                    && !keys_str.contains(&"LShift".to_string())
                    && !keys_str.contains(&"RShift".to_string())
                {
                    ptt_held = false;
                }

                // If the key is a single regular character, make sure we are comparing an uppercase version of ptt_key
                if key.len() == 1 && !keys_str.contains(&key.to_uppercase()) {
                    ptt_held = false;
                }
            }

            // if ptt_held && !ptt_state {
            //     // Do PTT
            //     win
            //         .emit("ptt_toggle", PTTEvent { state: true })
            //         .unwrap_or_else(|_| log("Error sending PTT event!"));
            //     ptt_state = true;
            // } else if ptt_state && !ptt_held {
            //     // Stop PTT
            //     win
            //         .emit("ptt_toggle", PTTEvent { state: false })
            //         .unwrap_or_else(|_| log("Error sending PTT toggle event!"));
            //     ptt_state = false;
            // }

            // Small delay to reduce CPU usage
            thread::sleep(Duration::from_secs(5));
        }
    });
}

fn print_key() {
    let mut status = "init";
    let device_state = DeviceState::new();
    let mut start_time = Instant::now();
    println!("请按下F1开始计时");
    loop {
        if status == "started" {
            print!("\r{}", start_time.elapsed().as_millis());
        }
        let keys: Vec<Keycode> = device_state.get_keys();
        if keys.len() == 0 || keys[0] != Keycode::F1 {
            continue;
        }
        // println!("捕捉到输入{:}", keys[0]);
        match status {
            "init" => {
                status = "started";
                sleep(Duration::from_millis(100)); //不先睡一会的话，下一轮loop会立马捕捉到F1原因不明
                println!("请按下F1结束计时");
                start_time = Instant::now();
            }
            "started" => {
                println!("\n经过了：{}毫秒", start_time.elapsed().as_millis());
                return;
            }
            _ => return,
        }
    }
}

#[tokio::main]
async fn main() {
    // initialize tracing
    tracing_subscriber::fmt::init();

    // start_hotkey_watcher();

    // build our application with a route
    let app = Router::new()
        // `GET /` goes to `root`
        .route("/", get(root))
        .route("/health_check", get(health_check))
        .route("/test/uuid_new", get(test_uuid_new))
        // `POST /users` goes to `create_user`
        .route("/users", post(create_user));

    // run our app with hyper, listening globally on port 3000
    let listener = tokio::net::TcpListener::bind("0.0.0.0:8000").await.unwrap();
    axum::serve(listener, app.into_make_service()).await.unwrap();
}

// basic handler that responds with a static string
async fn root() -> &'static str {
    "Hello, World!"
}

async fn create_user(
    // this argument tells axum to parse the request body
    // as JSON into a `CreateUser` type
    Json(payload): Json<CreateUser>,
) -> impl IntoResponse {
    // insert your application logic here
    let user = User {
        id: 1337,
        username: payload.username,
    };

    // this will be converted into a JSON response
    // with a status code of `201 Created`
    (StatusCode::OK, Json(user))
}


async fn test_uuid_new(
    // this argument tells axum to parse the request body
    // as JSON into a `CreateUser` type
) -> impl IntoResponse {


    let uuid = Uuid::new_v4();

    // this will be converted into a JSON response
    // with a status code of `201 Created`
    (StatusCode::OK, Json(DemoResponse {
        id: uuid,
    }))
}

async fn health_check() -> impl IntoResponse {
    (StatusCode::OK)
}

// the input to our `create_user` handler
#[derive(Deserialize)]
struct CreateUser {
    username: String,
}

// the output to our `create_user` handler
#[derive(Serialize)]
struct User {
    id: u64,
    username: String,
}


#[derive(Serialize, Deserialize)]
struct DemoResponse {
    id: Uuid
}



#[cfg(test)]
mod tests {
    use super::*;
    use axum::{
        body::Body,
        http::{Request, Method},
    };
    use tower::ServiceExt; // for `oneshot` and `ready`

    #[tokio::test]
    async fn test_health_check() {
        // 创建一个路由器
        let app = Router::new().route("/health_check", get(health_check));

        // 创建一个请求
        let request = Request::builder()
            .method(Method::GET)
            .uri("/health_check")
            .body(Body::empty())
            .unwrap();

        // 发送请求并获取响应
        let response = app.oneshot(request).await.unwrap();

        // 断言响应状态码为200
        assert_eq!(response.status(), StatusCode::OK);
    }
}
