fn main() {
    let message = String::from("Rust is great!");

    let c = get_last_char(/*TODO*/);
    println!("Last char is '{}'", c);

    uppercase_and_print(/*TODO*/);
}

fn get_last_char(/*TODO*/) -> char {
    msg.chars().last().unwrap()
}

fn uppercase_and_print(/*TODO*/) {
    msg = msg.to_uppercase();
    println!("The message is \"{}\"", msg);
}