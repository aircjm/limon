use crate::lamp::Lamp;

pub struct Switcher<'a> {
    lamp: &'a mut Lamp
}

impl<'a> Switcher<'a> {
    pub fn new(lamp: &'a mut Lamp) -> Self {
        Switcher { lamp }
    }
    pub fn switch(&mut self) {
        if self.lamp.is_on() {
            self.lamp.switch_off()
        } else {
            self.lamp.switch_on()
        }
    }
}