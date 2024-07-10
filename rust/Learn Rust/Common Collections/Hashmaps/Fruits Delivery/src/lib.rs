use std::collections::HashMap;

pub fn fruit_basket() -> HashMap<String, u32> {
    let mut basket = // Declare your hash map here

    // Two bananas are already given to you :)
    basket.insert(String::from("banana"), 2);
    // Put more fruits in your basket here!

    basket
}