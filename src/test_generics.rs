use std::fmt::{Display, Debug};

#[test]
pub fn test() {
    test_single(12);

    test_mulit(50, "string".to_string());
}


// 测试下如何使用范型
pub fn test_single<T: Display>(t: T) -> T {
    println!("{}", t);
    t
}


// 测试下如何在一个方法中使用多个范型
pub fn test_mulit<T: Display, U :Display +Debug>(t: T, u: U) -> T {
    println!("{}, u is :{:?}", t, u);
    t
}



#[test]
pub fn test_where(){
    use_test_where("hello".to_string(), "world".to_string());
}



pub fn use_test_where<T, U>(t: T, u: U) -> T
    where  T:Display,
           U:Display
{

    println!("{}", t);
    println!("{}", u);
    return t;
}


