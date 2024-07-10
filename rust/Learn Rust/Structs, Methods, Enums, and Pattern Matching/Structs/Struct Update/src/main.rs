#[derive(Debug)]
struct Order {
    name: String,
    year: u32,
    made_by_phone: bool,
    made_by_mobile: bool,
    made_by_email: bool,
    item_number: u32,
    count: u32,
}

fn create_order_template() -> Order {
    Order {
        name: String::from("Bob"),
        year: 2019,
        made_by_phone: false,
        made_by_mobile: false,
        made_by_email: true,
        item_number: 123,
        count: 0,
    }
}

fn your_order() -> Order {
    let order_template = create_order_template();
    let your_order = // TODO: Create your own order using the update syntax and template above!
    return your_order
}

fn main() {
    let x = your_order();
    println!("{:?}", x);
}


