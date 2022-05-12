fn main() {
    println!("Hello, world!");
    let mut x = 5;
    println!("x  is : {}", x.to_string());
    x = 6;
    println!("new x  is : {}", x.to_string());


    const CONST_NUMBER: u128 = 100* 32*32;
    println!("const number is {}", CONST_NUMBER);

    println!("测试隐藏 Shadowing   begin");


    let y = 10;
    println!("y is  {}", y);
    let y = y+ 10;
    println!("y + 10 is : {}", y);

    {
        let y = y *3;
        println!("y * 3 is  {}", y);
    }

    println!("y is  {}", y);
    println!("测试隐藏 Shadowing   end");


    println!("---------------------------------------------------------");

    println!("测试mut绑定变量   begin");

    let mut m = 10;
    println!("y is  {}", m);
    m = m + 10;
    println!(" + 10 is : {}", m);

    {
        m = m *3;
        println!("m * 3 is  {}", m);
    }

    println!("m is  {}", m);
    println!("测试mut绑定变量   end");


    println!("---------------------------------------------------------");


    println!("测试string.len()   begin");

    let s =  "        ";
    println!("s len is : {}", s.len());
    println!("测试string.len()   end");

    println!("---------------------------------------------------------");


    let int_1 : u32 = 1200;

    let int_64 :u64 = 1222222222222222;
    println!("int_1 is:{}, int_64 is: {}", int_1, int_64);

    let float_num:f32  = 3.2;
    println!("float_num is: {}", float_num);


    let result = false;
    println!("result is: {}", result);

    let tuple:(i32, i32, u32, u64) = (1,2, 3,4);

    // println!("tuple is :{}", tuple.10)

}
