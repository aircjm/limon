use std::any::type_name;

/// rust type类型使用实践
#[derive(Debug,Default)]
pub struct Arguments {
    pub target: String,
    pub replacement: String,
    pub filename: String,
    pub output: String,
}

#[test]
pub fn print_usage() {
    eprintln!("123");
}

/*
对应类型的判断
*/
#[test]
pub fn test_type() {
    let type_str = "";
    type_print(type_str);
    let type_char = ' ';
    type_print(type_char);
}

fn type_print<T>(_: T) {
    println!("{:?}", { type_name::<T>() });
}

#[test]
pub fn test_type_bool() {
    println!("false as i8 is : {}", false as i8);
    println!("true as i8 is : {}", true as i8);
    assert_eq!(false as i8, 0);
    assert_eq!(true as i8, 1);

    let true_type = false;
    // todo rust中 bool类型占用的数据类型长度
    println!("{}", true_type);
}

#[test]
pub fn test_type_char() {
    // char 代表一个单独的 Unicode 字符,是一个 32 位的值
    assert_eq!('*' as i32, 42);

    println!("{}", 97 as char);

    assert_eq!(97 as char, 'a');
}

#[test]
pub fn test_type_str() {}

#[test]
pub fn test_int_add() {
    let option = 100_i32.checked_add(50);
    println!("{}", option.unwrap());

    let git = 5_f64.sqrt() * 5_f64.sqrt();
    // assert_eq!(git.sqrt(), 5.);

    // 5.000000000000001
    println!("{}", git);

}

// Rust 将调用它的值作为第一个参数传递给方法，该参数必须具有特殊名称 self，self 的类型可以省略，所以 self，&self 或者 &mut self 实际上是 self: Queue， self: &Queue， 或者 self: &mut Queue 的简写。
impl Arguments {

    pub fn new() -> Self {
        Arguments {
            target: "".to_string(),
            replacement: "".to_string(),
            filename: "".to_string(),
            output: "".to_string(),
        }
    }



    pub fn get_only_self(self) -> String {
        println!("{}", self.output);
        return self.filename
    }


    fn check(&self) -> bool {
        return self.replacement == "hello".to_string();
    }

    fn check_out(&self, out: String) -> bool {
        return self.replacement == out;
    }


    fn update_filename(&mut self, new_filename: &str) {
        self.filename= new_filename.to_string()
    }
}



#[test]
fn test_update_free_ownership() {
    let mut arg = Arguments {
        target: "".to_string(),
        replacement: "".to_string(),
        filename: "123".to_string(),
        output: "".to_string(),
    };
    let filename = arg.get_only_self();
    println!("{filename}");
    // value borrowed here after move
    println!("arg filename is {}", arg.filename);
}


#[test]
fn test_update_mut_self_param() {
    let mut arg = Arguments {
        target: "".to_string(),
        replacement: "".to_string(),
        filename: "".to_string(),
        output: "".to_string(),
    };
    arg.update_filename("update field");
    println!("{:?}", arg);
}


#[test]
pub fn function_new() {
    let arguments =  Arguments::new();
    println!("{:?}", arguments);

    assert_eq!(arguments.check(), false);
    assert_eq!(arguments.check_out("".to_string()), true);
}


