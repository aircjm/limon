use std::time::Instant;

#[test]
pub fn time_new() {
    let time = Instant::now();
    println!("{:?}", time);
}


#[test]
pub fn time_dur() {
    let time1 = Instant::now();
    let time2 = Instant::now();

    let mut str = String::new();
    for i in 0..100_000 {
        str.push_str(i.to_string().as_str());
    }
    let time3 = Instant::now();

    println!("{:?}", time2- time1);
    println!("{:?}", time3- time1);
}



