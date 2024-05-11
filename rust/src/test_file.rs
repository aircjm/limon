

use std::fs;
use std::io::Write;

fn create_write() -> std::io::Result<()> {
    let mut file = fs::File::create("myfil/ename.txt")?; // Create a file with this name.
    // CAREFUL! If you have a file with this name already,
    // it will delete everything in it.
    file.write_all(b"Let's put this in the file").unwrap();     // Don't forget the b in front of ". That's because files take bytes.
    Ok(())
}


#[test]
pub fn test_create_write() {
    create_write().expect("TODO: panic message");
}
