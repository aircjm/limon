use std::sync::mpsc;
use std::thread;
use std::time::Duration;

fn send_tx(numbers: Vec<u32>, tx: mpsc::Sender<u32>) {
    thread::spawn(move || {
        for val in numbers {
            println!("sending {:?}", val);
            /* TODO: Send value here */
            thread::sleep(Duration::from_secs(1));
        }
    });
}

fn main() {
    let (tx, rx) = mpsc::channel();

    send_tx(vec![2,4,6,8,10], /* TODO: pass the transmitting end */);
    send_tx(vec![1,3,5,7,9], /* TODO: pass the transmitting end */);

    let mut total_received: u32 = 0;
    let mut sum_received: u32 = 0;

    for received in /* What should you iterate over to receive all the numbers? */ {
        println!("Got: {}", received);
        total_received += 1;
        sum_received += received;
    }

    println!("total numbers received: {}", total_received);
    println!("sum received: {}", sum_received);
}