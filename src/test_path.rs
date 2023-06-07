use crate::test_path::path::test_path_sub2::path_fn_2;

#[cfg(test)]
mod path {

    // 没有pub关键字的都是私有的，无法外部访问
    //  mod test_path_sub {
    pub mod test_path_sub {

        pub fn path_fn()  {
        }
    }



    pub mod test_path_sub2 {
        pub fn path_fn_2 () {
            // 通过super关键字调用上层mod
            super::test_path_sub::path_fn();
        }
    }

}



#[test]
pub fn test_pt() {

    // crate表示root mod开始，可以直接使用同级mod进行访问
    crate::test_path::path::test_path_sub::path_fn();
    path::test_path_sub::path_fn();

    path_fn_2();


}
