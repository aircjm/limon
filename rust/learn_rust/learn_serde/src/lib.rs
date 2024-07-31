use serde::{Deserialize, Serialize};

pub fn add(left: usize, right: usize) -> usize {
    left + right
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }
}


#[derive(Deserialize, Serialize)]
pub struct User {
    id: i64,
    name: String,
    create_at: Option<i64>
}

#[test]
fn test_serde() {
    let user = User {
        id: 0,
        name: "".to_string(),
        create_at: Some(1704067201000),
    };

    let json_str = serde_json::to_string_pretty(&user).expect("json to string error");
    println!("object to String :{json_str}");

    let json: User = serde_json::from_str(json_str.as_str()).unwrap();
    println!("user create_at is {}", json.create_at.unwrap());
    //
    //
    // let json_long = json_str.replace("1704067201000", "\"1704067201000"");
    //
    // let json: User = serde_json::from_str(json_long.as_str()).unwrap();
    // println!("user create_at is {}", json.create_at.unwrap());


    // Option<i64>  必须是可以解析的，如果传入一个string类型的数字，就会报错
    let long_str = r#"
    {
        "id": 123,
        "name": "John Doe",
        "create_at": 1704067201000
    }
    "#;


    let json: User = serde_json::from_str(long_str).unwrap();
    println!("user new create_at is {}", json.create_at.unwrap());



}

