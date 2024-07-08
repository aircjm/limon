#[cfg(test)]
mod test_ts {
    use chrono::{DateTime, NaiveDate, NaiveDateTime, NaiveTime, Utc};
    use chrono::format::ParseError;


    #[test]
    fn test_parse() -> Result<(), ParseError> {
        let rfc2822 = DateTime::parse_from_rfc2822("Tue, 1 Jul 2003 10:52:37 +0200")?;
        println!("{}", rfc2822);

        let rfc3339 = DateTime::parse_from_rfc3339("1996-12-19T16:39:57-08:00")?;
        println!("{}", rfc3339);

        let custom = DateTime::parse_from_str("5.8.1994 8:00 am +0000", "%d.%m.%Y %H:%M %P %z")?;
        println!("{}", custom);

        let time_only = NaiveTime::parse_from_str("23:56:04", "%H:%M:%S")?;
        println!("{}", time_only);

        let date_only = NaiveDate::parse_from_str("2015-09-05", "%Y-%m-%d")?;
        println!("{}", date_only);

        let no_timezone = NaiveDateTime::parse_from_str("2015-09-05 23:56:04", "%Y-%m-%d %H:%M:%S")?;
        println!("{}", no_timezone);

        Ok(())
    }


    #[test]
    pub fn ts_convert() {

        let ts = chrono::Utc::now().timestamp();
        println!("ts is {ts}"); // 1720418656

        let ts = chrono::Utc::now().timestamp_millis();
        println!("ts is {ts}"); // 1720418715833

        let ts = chrono::Utc::now().timestamp_micros();
        println!("ts is {ts}"); // 1720418672567736


        let result = chrono::DateTime::from_timestamp_millis(1720418715833).expect("format exception");
        println!("Date time is {}", result.naive_utc().to_string());
        println!("Date time is {}", result.naive_local().format("%Y-%m-%d %H:%M:%S").to_string());

        let dt = NaiveDate::from_ymd_opt(2024, 1, 1)
            .unwrap()
            .and_hms_nano_opt(0, 0, 1, 0)
            .unwrap()
            .and_local_timezone(Utc)
            .unwrap();
        println!("from date is {}", dt.timestamp_millis());
        assert_eq!(dt.timestamp_millis(), 1704067201000);

        let ts_millis = Utc::now().timestamp_millis();
        println!("ts is {ts}"); // 1720418715833
    }


}
