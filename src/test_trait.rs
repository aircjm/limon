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




    pub fn say_hello<W: Write>(out: &mut W) ->  Result<()>{
        out.write_all(b"hello world\n")?;
        out.flush()
    }





}
