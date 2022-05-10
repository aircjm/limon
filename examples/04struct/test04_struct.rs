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
    
    
    struct User {
        id : u32,
        username : String,
        age : u16,
        address : String,
    }
    
    
    

}