#[test]
pub fn test_box() {
    // 元组
    let a = Box::new((0.2, 0.3));
    println!("{:?}", a);

    let num = Box::new(10);
    println!("{:?}", num);

    // struct
    let mut person = Box::new(Person {
        id: 1,
        name: Some("lisa".to_string()),
    });
    person.name = None;

    println!("{:?}", person);
}

#[derive(Debug)]
struct Person {
    id: i32,
    name: Option<String>,
}
