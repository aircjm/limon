use std::io::Read;
use std::thread::{sleep, Thread};
use std::time::Duration;
use arboard::Clipboard;

fn main() {
    // let mut clipboard = Clipboard::new().unwrap();
    // println!("Clipboard text was: {}", clipboard.get_text().unwrap());
    //
    // let the_string = "Hello, world!";
    // clipboard.set_text(the_string).unwrap();
    // println!("But now the clipboard text should be: \"{}\"", the_string);

    loop {
        let mut clipboard = Clipboard::new().unwrap();
        let text = clipboard.get_text();
        if text.is_ok() {
            println!("{}", text.unwrap());
        }

        let image = clipboard.get_image();
        if image.is_ok() {
            // println!("{}", String::from(image.unwrap().bytes.bytes()));
            println!("have a image to deal");
        }
        sleep(Duration::new(2,0));
    }

}

