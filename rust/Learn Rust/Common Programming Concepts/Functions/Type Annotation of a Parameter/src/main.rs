fn main() {
    call_me(3);
}

fn call_me(num:/*insert the type annotation here*/) {
    for i in 0..num {
        println!("Ring! Call number {}", i + 1);
    }
}
