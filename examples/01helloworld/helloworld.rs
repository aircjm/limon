fn main() {
    println!("{}", "hello world");
    test_push_str();
    create_phone_number(&[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]);

int_convert_str(10);

}

fn test_push_str() {
    let mut company = "hello".to_string();
    company.push_str("world");
    println!("{}", company)
}

fn create_phone_number(numbers: &[u8]) -> String {
    return "()".to_string();
}


fn int_convert_str(i : i32) -> String {
    return "String".to_string();
}