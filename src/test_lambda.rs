

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