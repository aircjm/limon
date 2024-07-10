#[derive(Debug)]
pub struct Person {
    pub name: String,
    pub age: usize,
}

impl Default for Person {
    fn default() -> Person {
        Person {
            name: String::from("John"),
            age: 30,
        }
    }
}


impl From<&str> for Person {
    fn from(s: &str) -> Person {
        // Write your code here
    }
}

