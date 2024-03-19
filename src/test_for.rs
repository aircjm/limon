use std::time::Duration;
use crate::test_str::print;

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
pub fn for_loop_break() {

    // break can't break parent loop
    loop {
        println!("first loop");
        let mut line = String::new();
        loop {
            println!("second loop");
            std::thread::sleep(Duration::from_secs(3));
            let b1 = std::io::stdin().read_line(&mut line).unwrap();
            if line.eq("") {  }
            break;
        }
    }


    loop {
        println!("first loop");
        loop {
            println!("second loop");
            std::thread::sleep(Duration::from_secs(3));
            break;
        }
    }


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


#[test]
pub fn list_inter() {

    let a = ["12","13","14","15"];

    for i in a {
        println!("{}", i);
    }
    for c in a {
        println!("{}", c);
    }

    println!("{:?}", a);

    a.into_iter().for_each(|x| {
        println!("{}", x.to_string().pop().unwrap());
    });

    println!("{:?}", a);


}



#[test]
fn test_for_step() {

    for i in (0..10).step_by(2) {
        println!("i: {i}");
    }

}