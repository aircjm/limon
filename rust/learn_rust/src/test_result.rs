#[test]
pub fn result_option() {
    let result = return_simple_ok(11);
    if result.is_ok() {
        println!("{}", result.unwrap());
    } else {
        println!("{}", result.unwrap_err());
    }

}


pub fn return_simple_ok(i: i32) -> Result<i32, String> {
    match i > 10 {
        true => {Ok(i)}
        false => {Err("is error".to_string())}
    }
}
