pub fn add(left: usize, right: usize) -> usize {
    left + right
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn it_works() {
        let result = add(2, 2);
        assert_eq!(result, 4);
    }
}



#[cfg(test)]
mod uuid {
    use uuid::{Timestamp, Uuid};

    #[test]
    pub fn now_v7 () {
        let uuid_v7 = Uuid::now_v7();
        println!("uuid v7 is {}", uuid_v7.to_string());
        // uuid v7 is 01910832-55d6-7671-82e4-ea1071b1dd3c
    }

   #[test]
    pub fn new_v6 () {
        let uuid_v6 = Uuid::now_v6( &[1, 2, 3, 4, 5, 6]);
        println!("uuid v6 is {}", uuid_v6.to_string());
        // uuid v7 is 01910832-55d6-7671-82e4-ea1071b1dd3c
    }

}
