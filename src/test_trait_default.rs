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


    fn add_number(number: &mut i32) -> i32 {
        *number += 3;
        *number
    }


    #[test]
    fn test_add_number() {
        let i = add_number(&mut 12);
        println!("{i}");
    }

}