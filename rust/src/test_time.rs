// use std::time::Instant;
// use serde::{Deserialize, Serialize};
//
// #[test]
// pub fn time_new() {
//     let time = Instant::now();
//     println!("{:?}", time);
// }
//
//
// #[test]
// pub fn time_dur() {
//     let time1 = Instant::now();
//     let time2 = Instant::now();
//
//     let mut str = String::new();
//     for i in 0..100_000 {
//         str.push_str(i.to_string().as_str());
//     }
//     let time3 = Instant::now();
//
//     println!("{:?}", time2- time1);
//     println!("{:?}", time3- time1);
// }
//
//
//
// #[test]
// pub fn test_ts() {
//     // let model_result = ModelResult { ..Default::default() };
//     // println!("{}", serde_json::json!(model_result));
//     let json = json!({"content":null,"created_at":"","id":0,"title":null,"updated_at":0});
//
//     let result: TestModelResult = serde_json::from_value(json).unwrap();
//     println!("{}, {}", result.created_at.unwrap(), result.updated_at.unwrap());
// }
//
//
// #[derive(Clone, Debug, Serialize, Deserialize, Default)]
// pub struct TestModelResult {
//     #[serde(with = "ts_seconds_option")]
//     pub created_at: Option<DateTime<Utc>>,
//     #[serde(with = "ts_seconds_option")]
//     pub updated_at: Option<DateTime<Utc>>,
//     pub id: i32,
//     pub title: Option<String>,
//     pub content: Option<String>,
// }
//
//
//
//
//
