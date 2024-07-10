pub fn byte_counter<T/*Obtain the number of bytes (not characters) in the given argument
 Add the AsRef trait appropriately as a trait bound*/>(arg: T) -> usize {
    arg.as_ref().as_bytes().len()
}


pub fn char_counter<T/* Obtain the number of characters (not bytes) in the given argument
 Add the AsRef trait appropriately as a trait bound*/>(arg: T) -> usize {
    arg.as_ref().chars().count()
}



