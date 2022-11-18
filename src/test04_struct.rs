#[cfg(test)]
mod test {

    #[test]
    fn test() {
        println!("Hello, world!");

        let user = User {
            id : 10,
            username: String::from("test"),
            age: 18,
            address: String::from("18 号")
        };

        println!("{}", user.address)
    }


    #[test]
    struct User {
        id : u32,
        username : String,
        age : u16,
        address : String,
    }





}

fn  main() {
    println!("hello");
    println!("{}", create_phone_number(&[1,2,3,4,5,6,7,8,9,0])); // returns "(123) 456-7890"
}



fn create_phone_number(numbers: &[u8]) -> String {
    let mut phone = "(".to_string();
    phone.push_str(&(numbers.get(0).unwrap().to_string()));

    return phone.to_string()
}