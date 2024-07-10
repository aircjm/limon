#[test]
pub fn for_simple() {
    let mut a = 1;
    loop {
        println!("a is {}", a);
        a = a + 1;

        if a >= 10000 {
            break;
        }
    }
    assert_eq!(a, 10000);
}


#[test]
pub fn for_loop_break() {
    let mut a = 1;
    // break can't break parent loop
    loop {
        println!("first loop");
        loop {
            println!("second loop");
            a = a + 1;
            if a == 100 {
                break;
            }
        }

        a = a + 1;
        if a == 1000 {
            break;
        }
    }
}


#[test]
pub fn for_parent_loop_break() {
    let mut a = 1;
    // 在 Rust 中要实现在两层循环中跳出外层循环，可以使用标签（label）和'outer语法。
    // define loop, ant break the define loop
    'out: loop {
        println!("first loop");
        loop {
            println!("second loop");
            a = a + 1;
            if a == 100 {
                break 'out;
            }
        }
    }
}

#[test]
pub fn for_in() {
    for i in 0..1000 {
        println!("{}", i);
    }

    for i in 0..=1000 {
        println!("{i}");
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
pub fn array_map_into_inter() {
    let id_list = [1,2,3,4,5];
    // 需要对集合中的值进行修改或移出操作，应该使用.into_iter()方法获取拥有所有权的迭代器；
    let id_str_list: Vec<_> = id_list.into_iter().map(|x| {
       format!("id is {}", x)
    }).into_iter().collect();
    println!("{:?}", id_str_list);
}

// 我们使用.iter()方法来获取一个不可变引用的迭代器，并在闭包中使用map来生成新的字符串，然后使用collect()方法将结果收集到一个新的Vec中。
#[test]
pub fn array_map_inter() {
    let id_list = [1,2,3,4,5];
    // 如果只需要对集合中的值进行只读操作，应该使用.iter()方法获取不可变引用的迭代器。
    let id_str_list: Vec<_> = id_list.iter().map(|&x| {
        format!("id is {}", x)
    }).into_iter().collect();
    println!("{:?}", id_list);
    println!("{:?}", id_str_list);
}



#[test]
pub fn list_inter() {
    let a = ["12", "13", "14", "15"];

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