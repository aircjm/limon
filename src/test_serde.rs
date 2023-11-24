use serde::{Deserialize, Serialize};
use serde_json::Value;

#[derive(Serialize, Deserialize)]
pub struct Person {
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
    let json_str = "{\"name\":\"Allen\",\"age\":40}";
    let v: Value = serde_json::from_str(json_str).unwrap();
}


#[test]
pub fn test_from_value() {
    let value: Value = serde_json::json!({"name": "John", "age": 30});
    let p: Person = serde_json::from_value(value).unwrap();
    println!("Name: {}, Age: {}", p.name, p.age);
}


#[test]
pub fn test_vec_serialize() {
    let person1 = Person {
        name: "1".to_string(),
        age: 1,
    };
    let person2 = Person {
        name: "2".to_string(),
        age: 2,
    };

    let vec1 = vec![person2, person1];
    println!("{}", serde_json::to_string(&vec1).unwrap());
}





