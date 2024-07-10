#[tokio::main]
async fn main() {
    println!("hello world!");
}

// #[tokio::main]
// async fn main() -> Result<(), ()> {
//     println!("hello world!");
//     Ok(())
// }


#[tokio::test]
// need return a result, because the ? operator can only be used in an async block that returns
// Result or Option.
async fn test_simple() -> Result<(), reqwest::Error> {
    let text = reqwest::get("https://www.rust-lang.org").await?.text().await?;

    println!("text is {}", text);

    Ok(())
}



#[tokio::test]
// need return a result, because the ? operator can only be used in an async block that returns
// Result or Option.
async fn test_json() -> Result<(), reqwest::Error> {
    // let text = reqwest::get("https://www.rust-lang.org").await?.json().await?;

    // println!("text is {}", text);

    Ok(())
}