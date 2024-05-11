#[test]
pub fn test_vec_capacity() {
    let vec1: Vec<String> = vec![];
    println!("{}", vec1.capacity());
    assert_eq!(vec1.capacity(), 0);
}



#[test]
pub fn base_vec_use_get() {
}


pub fn get_test_str_vec() -> Vec<String> {
    let mut vec:Vec<String> = Vec::new();

    vec.push("hello".to_string());
    vec.push("world".to_string());
    vec.push("hello".to_string());
    vec.push("rust".to_string());

    return vec;
}


#[test]
pub fn test_vec_print() {
    let c = get_test_str_vec();
    // println!("{}", c);
    println!("{:?}", c);
}



#[test]
pub fn test_push_capacity() {
    let mut vec:Vec<String> = Vec::new();
    println!("{}", vec.capacity());
    vec.push("hello".to_string());
    println!("{}", vec.capacity());
    vec.push("world".to_string());
    vec.push("hello".to_string());
    vec.push("rust".to_string());
    println!("{}", vec.capacity());
    vec.push("rust".to_string());
    println!("{}", vec.capacity());
}
