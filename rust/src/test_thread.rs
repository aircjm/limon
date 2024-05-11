use std::thread::spawn;

#[test]
fn test_spawn_one() {
    spawn_one();
}


#[test]
fn test_spawn_ten() {
    for _ in 0..10 {
        spawn_one();
    }
}



#[test]
fn test_spawn_param_ten() {
    for i in 0..10 {
        spawn_param(i);
    }
}


// 无参数异步线程
fn spawn_one() {
    std::thread::spawn(|| println!("{}", "helllo"));
}


// 异步线程中需要参数
fn spawn_param(i: i32) {
    i.to_string().push_str(" hello world");
    std::thread::spawn(move || println!("{}",i));
}




#[test]
fn test_join() {
    for i in 0..50 {
        let handle = spawn(move || {
            println!("{}", i);
        });

        handle.join().expect("TODO: panic message");
    }
}

#[test]
fn test_closure_fn() {
    let my_string = String::from("hello world");
    let my_closure = || println!("{}", my_string);
    my_closure();
    my_closure();
}


#[test]
fn test_closure_fn_mut() {
    let mut my_string = String::from("hello world");
    let mut my_closure = || {
        my_string.push_str(" now");
        println!("{}", my_string);
    };
    my_closure();
    my_closure();
}