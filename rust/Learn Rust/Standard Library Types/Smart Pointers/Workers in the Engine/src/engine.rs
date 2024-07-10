use crate::worker::Worker;
/* You may need to use something */

pub struct Engine {
    log: Vec<String>,
    workers: Vec<Worker>
}

impl Engine {
    pub fn new() -> Self {
        Engine {
            log: vec![],
            workers: vec![]
        }
    }

    pub fn add_worker(&mut self, id: usize) {
        self.workers.push(Worker::new(id, &self.log))
    }

    pub fn run(&self) {
        self.workers.iter().for_each(Worker::run);
    }

    pub fn print_log(&self) {
        for entry in &self.log {
            println!("{}", entry)
        }
    }
}