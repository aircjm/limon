
#[test]
pub fn for_simple() {

    let mut a = 1;
    loop {
        println!("a is {}", a);
        a = a+1;

        if a >= 10000 {
            break;
        }
    }
    assert_eq!(a, 10000);
}


#[test]
pub fn for_in() {
  for i in 0..1000 {
       println!("{}", i);
    }
}


#[test]
pub fn for_vec() {
    let vec_list: Vec<String> = vec!["hello".to_string(), "world".to_string(), "github".to_string()];
    for x in vec_list {
        println!("{}", x);
        println!("{}", x);
    }
}


#[test]
pub fn vec_mut_inter() {
    let mut vec_list: Vec<String> = vec!["hello".to_string(), "world".to_string(), "github".to_string()];
    vec_list.iter_mut().for_each(|x| {
        x.push_str(" you");
        println!("{}", x);
    });


    println!("{:?}", vec_list);
}


