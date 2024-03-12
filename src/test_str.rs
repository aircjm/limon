

// Define a struct named User with a field name of type String
pub struct User {
    // when use str in struct, you can use String instance &str,because String use the lifetime of Struct.
    name: String
}

// Define a struct named RoleUser with a lifetime parameter 'a and a field name of type reference to str
pub struct RoleUser<'a> {
    name: &'a str
}

// Define a test function to demonstrate usage of str in struct
#[test]
pub fn test_str_in_struct () {

    // Create an instance of User struct with name field set to "kitty"
    let user = User {
        name: "kitty".to_string(),
    };
    // Print the name field of user struct
    println!("{}", user.name);

    // Create an instance of RoleUser struct with name field set to "admin"
    let role_user = RoleUser {
        name: "admin",
    };
    // Print the name field of role_user struct
    println!("{}", role_user.name);
}



#[test]
// Function to demonstrate different ways of creating and manipulating Strings
pub fn test_string_guide() {

    // Create a String from a &str using the to_string() method
    let to_string = "Learn Rust".to_string();
    println!("get String from &str.to_string() method ,result is {to_string}");

    // Replace a substring in the String using the replace() method
    let replace_string = to_string.replace("Learn", "Study");
    println!("String.replace() result is {replace_string}");

    // Create a String from a &str using the to_owned() method
    let owner_string = "Learn Rust String".to_owned();
    println!("get String from &str.to_owned() method ,result is {owner_string}");

    // Create a String using the String::from() method
    let from_string = String::from("Learn Rust from method");
    println!("get String from String::from() method ,result is {from_string}");
}

#[test]
// Function to demonstrate passing &str and String as parameters
pub fn test_str_param() {

    // Define a &str
    let str = "learn rust";
    print(str);
    // mismatched types expected reference `&std::string::String` found reference `&str`
    // print_String(str);

    // Create a String
    let string = "Learn Rust String".to_string();

    // Call the print function with &str parameter
    print(&string);
    // Call the print_string function with String parameter
    print_string(&string);
}

// Function to print a &str
pub fn print(str: &str) {
    println!("str result is {}", str);
}

// Function to print a String
pub fn print_string(str: &String) {
    println!("str result is {}", str);
}

#[test]
pub fn test_lowercase() {
    // lowercase
    assert_eq!("One".to_lowercase(), "one");

    // contains
    assert!("I like".contains("like"));

    // trim
    assert_eq!(" like".trim(), "like");

    // start with
    for word in "veni, vidi, vici".split(", ") {
        assert!(word.starts_with("v"));
    }
}

type IntList = Vec<u8>;

#[test]
pub fn test_type_alias() {
    let a = IntList::new();
    print!("{:?}", a);
}
