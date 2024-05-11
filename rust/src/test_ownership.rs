use std::{collections::HashMap, ops::Add, rc::Rc};


// ownership的原则
// 1. 每一个变量都有他的所有者
// 2. 每个变量有且只有一个所有者
// 3. 每个变量离开他的作用域就会被清理


// COPY和CLONE的trait


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

#[test]
pub fn test_rc() {
    let new = Rc::new(5);
    println!("{}", new); // 5
    let int = 15;
    println!("{}", new.add(int)); // 20

    let old = new.clone();
    println!("{}", old); // 5

    let new_str = Rc::new("hello ".to_string());
    // new_str.push_str("world"); // cannot borrow data in an `Rc` as mutable
    println!("{}", new_str);
}

type Table = HashMap<String, Vec<String>>;

#[test]
pub fn test_table_type() {
    let a: HashMap<String, Vec<String>> = HashMap::default();
    show(a);

}

fn show(table: Table) {
    for _ele in table {}
}
