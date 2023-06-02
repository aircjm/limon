#[test]
fn test_simple_struct() {
    println!("Hello, world!");

    let user = User {
        id : 10,
        username: String::from("test"),
        age: 18,
        address: String::from("18 号")
    };

    println!("{}", user.address)
}


pub struct User {
    id : u32,
    username : String,
    age : u16,
    address : String,
}
