use std::collections::HashMap;

#[test]
pub fn hashmap_simple() {
    let mut hash_map: HashMap<String, String> = HashMap::new();
    hash_map.insert("hello".to_string(), "hello".to_string());
    hash_map.insert("github".to_string(), "nihao".to_string());
    println!("{:?}", hash_map);


    // hashmap的键值可以存储什么样的类型

    for x in hash_map {
        println!("{} , {}", x.0,x.1);
    }

}