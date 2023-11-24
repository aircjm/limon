use serde::{Deserialize, Serialize};
use serde_json::Value;

#[derive(Serialize, Deserialize)]
pub struct Person {
    #[serde(rename= "fullName")]
    name: String,
    age: u8,
}

#[test]
pub fn test_serialize() {
    let person = Person { name: "test".to_string(), age: 12 };
    let str = serde_json::to_string(&person).unwrap();
    println!("person is {}", str);
}


#[test]
pub fn test_deserialize() {
    let person = Person { name: "test".to_string(), age: 12 };
    let str = serde_json::to_string(&person).unwrap();
    let x: Person = serde_json::from_str(str.as_str()).unwrap();
    println!("person is {}", x.name);
}

#[test]
pub fn test_serde_json_value() {


    let json_str = "{\"name\":\"John\",\"age\":30}";
    let v: Value = serde_json::from_str(json_str).unwrap();
}