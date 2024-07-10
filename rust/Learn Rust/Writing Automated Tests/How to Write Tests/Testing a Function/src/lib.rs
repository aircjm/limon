pub fn is_even(num: i32) -> bool {
    num % 2 == 0
}

#[cfg(test)]
mod tests {
    use super::is_even;

    #[test]
    fn is_true_when_even() {
        assert!(is_even(/* TODO */));
    }

/* Write a test function called `is_false_when_odd` here and test for `is_even(5)` */
}
