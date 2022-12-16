#[test]
pub fn test_vec_for() {
    // for遍历vec之后 vec的变量丢失所有权
    let str_a = vec!["1".to_string(), "10".to_string(), "100".to_string()];
    for a in str_a {
        println!("{}", a);
    }

    let str_b = vec!["1".to_string(), "10".to_string(), "100".to_string()];
    for mut b in str_b {
        b.push('!');
        println!("{}", b);
    }
}
struct Person {
    name: Option<String>,
    birth: i32,
}

#[test]
pub fn test_vec_field_take() {
    let mut composers = Vec::new();
    composers.push(Person {
        name: Some("Palestrina".to_string()),
        birth: 1525,
    });
    // 基本类型都是clone 不回丢失所有权
    let birth = composers[0].birth;
    assert_eq!(birth, composers[0].birth);

    // replace 调用移出了 composers[0].name 的值, 留下了一个 None, 并把原本的值的所有权传递给了调用者。
    let first_name = std::mem::replace(&mut composers[0].name, None);
    assert_eq!(first_name, Some("Palestrina".to_string()));
    assert_eq!(composers[0].name, None);

    // take函数实现了上面的方法
    let _ = std::mem::replace(&mut composers[0].name, Some("Palestrina".to_string()));
    let second_name = composers[0].name.take();
    assert_eq!(second_name, Some("Palestrina".to_string()));
    println!("{:?}", composers[0].name);
}
