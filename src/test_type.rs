use std::any::type_name;

///
/// rust type类型使用实践
#[derive(Debug)]
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
    assert_eq!(git, 5.);
}
