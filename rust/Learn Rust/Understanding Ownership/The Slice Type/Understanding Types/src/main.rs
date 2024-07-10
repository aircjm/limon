fn main() {
    let a = [1, 2, 3, 4, 5];

    let nice_slice = &a[1..4];

    if nice_slice == [2, 3, 4] {
        println!("Nice slice!");
    } else {
        println!("Not quite what I was expecting... I see: {:?}", nice_slice);
    }

    println!("1 has type placeholder type");
    println!("1..4 has type placeholder type");
    println!("nice_slice == [2, 3, 4] has type placeholder type");
}
