#[test]
pub fn test_owner() {
    let str_a = vec!["1".to_string(), "10".to_string(), "100".to_string()];

    let str_b = str_a;
    println!("{:?}", str_b);
    // str_a value borrowed here after move
    // println!("{:?}", str_a);

    let str_c = str_b.clone();
    println!("{:?}", str_c);
}

#[test]
pub fn test_move() {
    let mut m = "test".to_string();
    println!("{:?}", m);
    let t = m;
    println!("{:?}", t);
    // 如果定义的变量不是mut修饰的 就无法修改对应的值
    // t = "hello".to_string();

    // move掉所有权之后，可以重新给变量赋值
    m = "hello".to_string();
    println!("{:?}", m);
}
