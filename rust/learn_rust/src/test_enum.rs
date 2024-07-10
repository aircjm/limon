use std::mem::size_of;

// how to d
enum Message {
    None,
    Email {
        x: String,
        y: String,
    },
    SMS(String),
    PUSH(i32, i32, i32),
}


#[derive(Debug)]
enum Ordering {
    Less,
    Greater,
    Equal,
}


fn compare(n: i32, m: i32) -> Ordering {
    if n < m {
        Ordering::Less
    } else if n > m {
        Ordering::Greater
    } else {
        Ordering::Equal
    }
}


#[derive(Debug)]
struct Order {
    price: f64,
    ordering: Ordering,
}

impl Order {
    fn new(price: f64) -> Self {
        let ordering = {
            if price > 10.0 { Ordering::Greater } else if price < 10.0 {
                Ordering::Less
            } else {
                Ordering::Equal
            }
        };

        Order { price, ordering }
    }
}


#[test]
fn test_enum_size() {
    // enum size is design by its max type size
    println!("Message size is  {}", size_of::<Message>());
    println!("String size is  {}", size_of::<String>());
    println!("Ordering size is  {}", size_of::<Ordering>());


    // return enum
    println!("compare n,m result  is  {:?}", compare(12, 22));

    println!("new Order  is  {:?}", Order::new(9.0));
}