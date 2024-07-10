use string_transformer::{/* TODO: Complete use declaration */};

fn main() {
    let output = transformer(vec![
        ("hello".into(), Command::Uppercase),
        (" all roads lead to rome! ".into(), Command::Trim),
        ("foo".into(), Command::Append(1)),
        ("bar".into(), Command::Append(5)),
    ]);

    println!("Transformer's results:\n{}", output.join("\n"))
}
