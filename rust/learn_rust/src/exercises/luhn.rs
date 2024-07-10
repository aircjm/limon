// TODO: remove this when you're done with your implementation.
#![allow(unused_variables, dead_code)]
// 卢恩算法用于验证信用卡号。该算法将字符串作为输入内容，并执行以下操作来验证信用卡号：
//
//     忽略所有空格。拒绝少于两位的号码。
//
//     从右到左，将偶数位的数字乘二。对于数字“1234”，我们将“3”和“1”乘二；对于数字“98765”，将“6”和“8”乘二。
//
//     将一个数字乘二后，如果结果大于 9，则将每位数字相加。因此，将“7”乘二得“14”，然后“1 + 4 = 5”。
//
//     将所有未乘二和已乘二的数字相加。
//
//     如果总和以“0”结尾，则信用卡号有效。


pub fn luhn(cc_number: &str) -> bool {

    let replace_str = cc_number.replace(" ", "");
    println!("{}", replace_str);

    false
}





#[test]
fn test_non_digit_cc_number() {
    assert!(!luhn("foo"));
    assert!(!luhn("foo 0 0"));
}

#[test]
fn test_empty_cc_number() {
    assert!(!luhn(""));
    assert!(!luhn(" "));
    assert!(!luhn("  "));
    assert!(!luhn("    "));
}

#[test]
fn test_single_digit_cc_number() {
    assert!(!luhn("0"));
}

#[test]
fn test_two_digit_cc_number() {
    assert!(luhn(" 0 0 "));
}

#[test]
fn test_valid_cc_number() {
    assert!(luhn("4263 9826 4026 9299"));
    assert!(luhn("4539 3195 0343 6467"));
    assert!(luhn("7992 7398 713"));
}

#[test]
fn test_invalid_cc_number() {
    assert!(!luhn("4223 9826 4026 9299"));
    assert!(!luhn("4539 3195 0343 6476"));
    assert!(!luhn("8273 1232 7352 0569"));
}

#[allow(dead_code)]
fn main() {}