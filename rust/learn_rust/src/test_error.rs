use anyhow::Result;
use std::io::Error;
use serde_json::Value;

//
// pub fn get_cluster_info() -> Result<ClusterMap> {
//     let config = std::fs::read_to_string("cluster.json")?;
//     let map: ClusterMap = serde_json::from_str(&config)?;
//     Ok(map)
// }



#[test]
fn test_error() {
    let path = "/tmp/dat";  //文件路径
    match read_file(path) { //判断方法结果
        Ok(file) => { println!("{}", file) } //OK 代表读取到文件内容，正确打印文件内容
        Err(e) => { println!("{} {}", path, e) } //Err代表结果不存在，打印错误结果
    }
}

fn read_file(path: &str) -> Result<String,Error> { //Result作为结果返回值
    std::fs::read_to_string(path) //读取文件内容
}


#[test]
fn test_error_unwrap() {
    let path = "/tmp/dat";  //文件路径
    // process is panic
    read_file_unwrap(path);
}

fn read_file_unwrap(path: &str) -> String { //Result作为结果返回值
    std::fs::read_to_string(path).unwrap() //读取文件内容
}


#[test]
fn test_error_if_let()  {
    test_read_file_anyhow();
}


fn test_read_file_anyhow() -> Result<()> {
    let config = std::fs::read_to_string("cluster.json")?;
    let map: serde_json::Map<String, Value> = serde_json::from_str(&config)?;
    println!("cluster info: {:#?}", map);
    Ok(())
}
