#[cfg(test)]
mod test_if {
    #[test]
    pub fn test_if() {
        let a = 1;

        if a > 0 {
            println!("{}", a);
        }
    }


    #[test]
    pub fn test_if_else() {
        let a = 5;

        if a == 7 {
            println!("a is {}", a);
        } else if let 5 = 6 {
            println!("a is {}", a);
        } else {
            println!("{}", a);
        }
    }

    #[test]
    pub fn test_if_and() {
        let a = 10;

        if a > 5 && a == 10 {
            println!("a == 10, {}", a);
        } else {
            println!("{}", a);
        }
    }


    #[test]
    pub fn test_if_or() {
        let a = 10;

        if a > 5 || a == 10 {
            println!("a > 5, {}", a);
        } else {
            println!("{}", a);
        }
    }


    #[test]
    pub fn test_match() {
        let a = 10;
        match a {
            0 => println!("a is {}", a),
            _ => {
                println!("a is not 0, is {}", a);
            }
        }
    }


    #[test]
    pub fn test_match_return() {
        let a = 10;

        let b = match a {
            0 => 0,
            _ => { -1 }
        };
        println!("{}", b);
    }


    #[test]
    pub fn test_match_two_condition() {
        let a = 10;
        // if match two condition，only use the first one
        match a {
            a if a <= 50 => { println!("{a} <= 50") }
            // a if a > 6 => { println!("{a} > 6") }
            _ => {}
        }
    }

    #[test]
    pub fn test_match_if() {
        let a = 10;
        let b = match a {
            a if a <= 0 => 0,
            a if a >= 0 => a,
            _ => { -1 }
        };
        println!("{}", b);
    }
}
