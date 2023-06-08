use std::fmt::{Display, Formatter};

trait Dog {
    fn eat(&self);

    fn sit(&self);
}


struct Animal {
    name: String,
    age: i32,
}


impl Dog for Animal {
    fn eat(&self) {
        todo!()
    }

    fn sit(&self) {
        todo!()
    }
}


impl Display for Animal {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        write!(f, "({}, {})", self.name, self.age)
    }
}


#[test]
pub fn test_display() {
    let animal = Animal {
        name: "".to_string(),
        age: 15,
    };

    println!("{}", animal);
}

