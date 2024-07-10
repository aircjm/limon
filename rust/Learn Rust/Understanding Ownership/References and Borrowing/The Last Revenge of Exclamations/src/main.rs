fn main() {
    let mut hello = String::from("Hello");

    add_exclamation(/*TODO*/ hello);

    println!("{} is '{}'", "hello", hello);

    add_exclamation(/*TODO*/ hello);

    println!("{} is '{}'", "hello", hello);
}

fn add_exclamation(s: /*TODO*/ String) {
    s.push_str("!");
}
