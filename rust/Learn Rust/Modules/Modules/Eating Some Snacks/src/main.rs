mod delicious_snacks {
    /*insert a modifier here to make the module accessible from outside*/ use self::fruits::PEAR as /*TODO*/;
    /*insert a modifier here to make the module accessible from outside*/ use self::veggies::CUCUMBER as /*TODO*/;

    mod fruits {
        pub const PEAR: &str = "Pear";
        pub const APPLE: &str = "Apple";
    }

    mod veggies {
        pub const CUCUMBER: &str = "Cucumber";
        pub const CARROT: &str = "Carrot";
    }
}

fn main() {
    println!("favorite snacks: {} and {}",
             delicious_snacks::fruit,
             delicious_snacks::veggie);
}
