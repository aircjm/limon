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
        println!("{}", "Dog is eat")
    }

    fn sit(&self) {
        println!("{}", "Dog is sit")
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


mod trait_demo {


    #[test]
    pub fn test_demo_write() {
        println!("{}", "hello trait");
    }


    // use std::io::Result;
    // use std::io::Write;
    // pub fn say_hello<W: Write>(out: &mut W) ->  Result<()>{
    //     out.write_all(b"hello world\n")?;
    //     out.flush()
    // }
}


mod sheep_trait {
    struct Sheep {
        name: String,
        naked: bool,
    }

    // 定义一个trait

    trait Animal {
        fn new(name: String) -> Self;
        fn name(self) -> String;
    }


    // 实现trait


    impl Animal for Sheep {
        fn new(name: String) -> Self {
            Sheep {
                name,
                naked: false,
            }
        }

        fn name(self) -> String {
            self.name
        }
    }


    #[test]
    pub fn sheep_test() {
        let sheep: Sheep = Animal::new("".to_string());
        println!("{}, {}", sheep.name, sheep.naked)
    }
}


mod test_default_trait {


    pub trait Animal {
        fn name(&self) -> String {
            "an Animal".to_string()
        }
    }

    pub struct Dog {
        pub name: String,
        pub sex: i32,
    }

    impl Animal for Dog {
        fn name(&self) -> String {
            self.name()
        }
    }


    #[test]
    pub fn test_default_trait() {
        let x: Dog = Dog { name: "".to_string(), sex: 0 };
        println!("{}", x.name());
    }
}


mod test_trait {
    pub trait Jira {
        fn summary(self) -> String;
    }

    pub struct Issue {
        pub title: String,
        pub context: String,
    }

    impl Jira for Issue {
        fn summary(self) -> String {
            self.title
        }
    }


    #[test]
    pub fn test_jira() {
        let x = Issue {
            title: "hello".to_string(),
            context: "world".to_string(),
        };

        println!("{}", x.summary());
    }
}
