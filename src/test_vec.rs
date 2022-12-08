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
