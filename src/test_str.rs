#[test]
pub fn test_lowercase() {
    // lowercase
    assert!("One".to_lowercase() == "one");

    // contains
    assert!("I like".contains("like"));

    // trim
    assert!(" like".trim() == "like");

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
