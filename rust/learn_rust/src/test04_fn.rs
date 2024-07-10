


#[derive(Debug)]
pub struct Point {
    x: i32,
    y: i32
}


#[derive(Debug,Clone, Copy)]
pub struct CopyPoint {
    x: i32,
    y: i32
}



pub fn simple_no_param() {
    let i = 10;
    println!("{i}");
}

pub fn simple_have_return() -> i32 {
    let i = 20;
    i
}

pub fn copy_simple(i: i32) {
    println!("{i}");
}


pub fn move_str(str: String) {
    println!("{str}");
}

// will move struct ownership but in the method can't modify
pub fn move_struct(point: Point) {
    println!("{}", point.x);
}


// will copy struct ownership but in the method can't modify
pub fn copy_trait_struct(point: CopyPoint) {
    println!("{}", point.x);
}

#[test]
pub fn test_fn () {
    simple_no_param();
    println!("{}", simple_have_return());

    // begin test copy i32
    let i = 12;
    copy_simple(i);
    println!("although have ownership {i}");
}

#[test]
pub fn test_move_string() {
    // begin test move String
    let str = "hello".to_string();
    move_str(str);
    // println!("although have ownership {str}"); // ^^^^^ value borrowed here after move
}


#[test]
pub fn test_move_struct() {
    // move struct
    let point = Point {
        x: 0,
        y: 0,
    };
    println!("{:?}", point);
    move_struct(point); // ----- value moved here
    // println!("{:?}", point);// ^^^^^ value borrowed here after move
}

#[test]
pub  fn test_copy_trait_struct() {
    println!("test copy_trait_struct");
    // method param use this copy struct,it will not move onwership
    let copy_point = CopyPoint {
        x: 0,
        y: 0,
    };
    println!("{:?}", copy_point);
    copy_trait_struct(copy_point);
    println!("{:?}", copy_point);
}