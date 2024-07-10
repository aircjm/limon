fn main() {
    let elements = [1, 10, 5, 6, 2, 3, 8];
    let cap = 5;
    let mut left_behind = 0;

    let smaller_than_cap: Vec<_> = elements
        .iter()
        .filter(|&&a| {
            /* Complete the closure here */
        })
        .collect();

    println!("{:?}", smaller_than_cap);
    println!("{} elements left behind", left_behind);
}
