// use std::fmt::{Debug, Formatter};
//
// #[test]
// pub fn test_box() {
//     // 元组
//     let a = Box::new((0.2, 0.3));
//     println!("{:?}", a);
//
//     let num = Box::new(10);
//     println!("{:?}", num);
//
//     // struct
//     let mut person = Box::new(Person {
//         id: 1,
//         name: Some("lisa".to_string()),
//     });
//     person.name = None;
//
//     println!("{:?}", person);
//
//     let mut list = Vec::new();
//
//     list.push(Person {
//         id: 1,
//         name: Some("lisa".to_string()),
//     });
//
//     list.push(Person {
//         id: 2,
//         name: Some("andy".to_string()),
//     });
//     println!("{:?}", list);
// }
//
// #[derive(Debug)]
// struct Person {
//     id: i32,
//     name: Option<String>,
// }
//
// impl Debug for Person {
//     fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
//         write!(f, "{} {}", self.id, self.name.unwrap())
//     }
// }
