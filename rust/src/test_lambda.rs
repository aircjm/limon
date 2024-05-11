

#[test]
pub fn test_simple() {
    let my_vec = vec![0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];


    let x = my_vec.clone().into_iter().map(|x|  vec![x]).collect::<Vec<Vec<i32>>>();
    println!("{:?}", x);


    my_vec.clone().into_iter().map(|x| {
        println!("{}", x);
        vec![x]
    }).for_each(|m|   {
        println!("m is {:?}", m);
    })

}




#[test]
pub fn test_lambda_fn() {

    let fn_i32 = |x:i32| println!("{}",x);
    fn_i32(12);

    let vec_i32 = vec![1,2,3,4,5,6];
    vec_i32.into_iter().for_each(fn_i32);

}

#[test]
pub fn test_lambda_filter() {


    let vec_i32 = vec![1,2,3,4,5,6,7,8,9,10,11,12];
    let i = vec_i32.into_iter()
        .filter(|item| *item < 6)
        .inspect(|f| println!("{}", *f))
        .collect::<Vec<i32>>();

    // dbg!("{:?}", i);

    println!("{:?}", i);
    println!("{:?}", i);

}




