struct ColorClassicStruct {
    // Something goes here
}

struct ColorTupleStruct(/* Something goes here */);

fn classic_c_structs() -> ColorClassicStruct {
    let green = // Instantiate a classic c struct!
    return green
}

fn tuple_structs() -> ColorTupleStruct {
    let green = // Instantiate a tuple struct!
    return green
}

fn main() {
    let cl_str = classic_c_structs();
    println!("{}", "Classic Struct:");
    println!("Name: {}", cl_str.name);
    println!("Hex: {}", cl_str.hex);

    let tup_str = tuple_structs();
    println!("{}", "Tuple Struct:");
    println!("Name: {}", tup_str.0);
    println!("Hex: {}", tup_str.1);
}
