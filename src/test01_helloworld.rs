#[cfg(test)]
mod tests {
    
    /// 字符串拼接
    /// 两个字符串合并成一个
    #[test]
    fn test_push_str() {
        let mut company = "hello".to_string();
        company.push_str("world");
        println!("{}", company)
    }

    #[test]
    fn test_create_phone_number() {
        create_phone_number(&[1, 2, 3, 4, 5, 6, 7, 8, 9, 0]);
    }
    

    #[test]
    fn test_create_phone_number() {
        assert_eq!(String::new("10"), int_convert_str(10));
    }
}

fn create_phone_number(numbers: &[u8]) -> String {
    return ["{", "}"].concat();
}

/// 转化对应数字为字符串
fn int_convert_str(i: i32) -> String {
    return "String".to_string();
}