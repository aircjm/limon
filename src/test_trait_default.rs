mod default {

    #[derive(Default, Debug)]
    struct FoodSku {
        id: i64,
        name: String,
        age : i32,
        deleted: bool,
    }


    #[test]
    fn test_default() {

        let foodSku : FoodSku = Default::default();

        println!("{:?}", foodSku);

    }




}