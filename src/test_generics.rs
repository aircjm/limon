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


#[derive(Default)]
pub struct GenericsPoint<T: Display, U: Display> {
    x: T,
    y: U
}


impl<T: Display, U: Display> GenericsPoint<T, U> {
    // pub fn x(&self) -> &T {
    //     println!("{}", self.x);
    //     &self.x
    // }
}


impl  GenericsPoint<i32, i32> {
    // pub fn y(&self) -> &i32 {
    //     println!("{}", self.y);
    //     &self.y
    // }
}


#[test]
pub fn point() {
    let mut point:GenericsPoint<i32, i32> = GenericsPoint::default();
    point.x = 100;

    let x1 = point.x;

    println!("{}", x1);
}
