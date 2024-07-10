#[derive(Debug)]
enum Message {
    // TODO: implement the message variant types based on their usage below
}

#[derive(Debug)]
struct Point {
    x: u8,
    y: u8,
}

struct State {
    color: (u8, u8, u8),
    position: Point,
    quit: bool,
}

impl State {
    fn change_color(&mut self, color: (u8, u8, u8)) {
        self.color = color;
    }

    fn quit(&mut self) {
        self.quit = true;
    }

    fn echo(&self, s: String) {
        println!("{}", s);
    }

    fn move_position(&mut self, p: Point) {
        self.position = p;
    }

    fn process(&mut self, message: Message) {
        // TODO: create a match expression to process the different message variants
    }
}

fn test_match_message_call() -> State {
    let mut state = State {
        quit: false,
        position: Point { x: 0, y: 0 },
        color: (0, 0, 0),
    };
    state.process(Message::ChangeColor(255, 0, 255));
    state.process(Message::Echo(String::from("hello world")));
    state.process(Message::Move{ x: 10, y: 15 });
    state.process(Message::Quit);

    return state
}


fn main() {
    let state = test_match_message_call();
    println!("Color: {:?}", state.color);
    println!("Position: {:?}", state.position);
    if state.quit {
        println!("Quit");
    }
}