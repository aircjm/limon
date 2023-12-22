use std::mem::size_of;

enum Message {
    None,
    Email {
        x: String,
        y: String,
    },
    Write(String),
    ChangeColor(i32, i32, i32),
}

#[test]
fn test_enum_size() {
    println!("Message size is  {}", size_of::<Message>());
    println!("String size is  {}", size_of::<String>());
}