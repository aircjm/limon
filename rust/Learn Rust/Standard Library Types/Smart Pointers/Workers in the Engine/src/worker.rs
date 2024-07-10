/* You may need to use something */

pub struct Worker {
    id: usize,
    log: &Vec<String>
}

impl Worker {
    pub fn new(id: usize, log: &Vec<String>) -> Self {
        Worker { id, log }
    }

    pub fn run(&self) {
        self.log./* `push` requires a mutable reference */push(format!("Worker {} did some work", self.id))
    }
}