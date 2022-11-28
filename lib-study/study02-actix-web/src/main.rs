use std::{path::Path, collections::HashMap};
use serde::{Deserialize, Serialize};
use actix_web::{web, App, HttpRequest, HttpServer, Responder};
use config::{Config, File};
use std::fs;
use std::io::prelude::*;

async fn greet(req: HttpRequest) -> impl Responder {
    let name = req.match_info().get("name").unwrap_or("World");
    format!("Hello {}!", &name)
}

async fn hello(req: HttpRequest) -> impl Responder {
    let name = req.match_info().get("name").unwrap_or("World");
    format!("Hello {}!", &name)
}


#[derive(Deserialize, Serialize,Debug)]
pub struct Cfg {
    setting : String,
    git: i32,
    web: WebConfig
}


#[derive(Deserialize, Serialize,Debug)]
pub struct WebConfig {
    port: i32
}

#[actix_web::main]
async fn main() -> std::io::Result<()> {



    let settings_result = Config::builder().add_source(File::from(Path::new("conf/config.json")))
    .build();
   let settings = match settings_result {
    Ok(config) => config,
    Err(e) => {
        fs::create_dir_all("conf")?;
        let mut buffer = std::fs::File::create("conf/config.json")?;
        buffer.flush()?;
        !todo!("need new write config file");
        panic!("require config file");
    },
};

        // Print out our settings (as a HashMap)
        println!(
            "\n{:?} \n\n-----------",
            settings
                .try_deserialize::<Cfg>()
                .unwrap()
        );


    HttpServer::new(|| {
        App::new()
            .route("/", web::get().to(hello))
            .route("/{name}", web::get().to(greet))
    })
        .bind(("127.0.0.1", 8080))?
        .run()
        .await
}