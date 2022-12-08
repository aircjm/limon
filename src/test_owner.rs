// 所有权只能被移动一次 移动了之后 变量就会变成未初始化的状态
// 1. 不能在循环中移动
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

#[test]
pub fn test_move_vec() {
    // vec的变量被move到方法后 变量会变成未定义状态
    let c = vec!["1".to_string(), "2".to_string()];

    move_vec(c);
    // error          ^ value borrowed here after move
    // print!("{:?}", c);
}
#[warn(unused_variables)]
fn move_vec(vec_str: Vec<String>) {
    println!("{:?}", vec_str);
}

#[test]
pub fn test_for_vec() {
    let mut v = Vec::new();
    for i in 101..106 {
        v.push(i.to_string());
    }
    // 从 vector 中取出随机的元素
    // let third = v[2]; // 错误:不能移动 Vec 的索引
    let third = v[2].clone(); // 错误:不能移动 Vec 的索引
    println!("{}", third);
}
