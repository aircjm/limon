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
    use std::io::Write;
    use std::io::Result;

    #[test]
    pub fn test_demo_write() {
        println!("{}", "hello trait");
    }




    // pub fn say_hello<W: Write>(out: &mut W) ->  Result<()>{
    //     out.write_all(b"hello world\n")?;
    //     out.flush()
    // }
}



mod  sheep_trait {

    struct Sheep {
        name: String,
        naked: bool
    }

    // 定义一个trait

    trait Animal {
        fn new(name: String) -> Self;
        fn name(self) -> String;
    }


    // 实现trait


    impl Animal for Sheep{
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