fn main() {
    let hello = String::from("Hello");

    let mut hello1 = add_exclamation(/*TODO*/);

    println!("{} is `{}`", "hello", hello);

    hello1.push_str("!");

    println!("{} is `{}`", "hello1", hello1);
}

fn add_exclamation(s: /*TODO*/) -> String {
    let mut str = s.clone();
    str.push_str("!");
    str
}
