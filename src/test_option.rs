#[test]
pub fn option_simple() {
    let mut  number_list:Vec<i32> = Vec::new();
    number_list.push(12);
    number_list.push(21);
    number_list.push(21);
    // if number list not have 5 length, it's error
    // take_fifth(number_list);

    let option = take_fifth_option(&number_list);
    match option {
        None => {
            println!("None");
        }
        Some(i) => {
            println!("{}", i);
        }
    }
    number_list.push(11);
    number_list.push(22);
    let option = take_fifth_option(&number_list);
    match option {
        None => {
            println!("None");
        }
        Some(i) => {
            println!("{}", i);
        }
    }
}


fn take_fifth(number_list: Vec<i32>) -> i32 {
    number_list[4]
}

fn take_fifth_option(number_list: &Vec<i32>) -> Option<i32> {
    if !number_list.is_empty() && number_list.len() > 4 {
        Some(number_list[4])
    } else {
        None
    }
}



#[test]
pub fn test_some_unwrap() {
    let mut  number_list:Vec<i32> = Vec::new();
    number_list.push(12);
    number_list.push(21);
    number_list.push(22);
    number_list.push(23);
    number_list.push(25);

    let option = take_fifth_option(&number_list).unwrap();

    assert_eq!(option, 25);
}


#[test]
pub fn test_none_unwrap() {
    let mut  number_list:Vec<i32> = Vec::new();
    number_list.push(12);
    number_list.push(21);

    // let option = take_fifth_option(&number_list).unwrap();
    // called `Option::unwrap()` on a `None` value
}



