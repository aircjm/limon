fn main() {
    let hello = String::from("Hello");

    let /*insert the necessary keyword here*/ hello1 = add_exclamation(/*TODO*/);

    println!("{} is `{}`", "hello", hello);

    hello1.push_str("!");

    println!("{} is `{}`", "hello1", hello1);
}

fn add_exclamation(s: String) -> String {
    let mut str = s;
    str.push_str("!");
    /*TODO*/
}
