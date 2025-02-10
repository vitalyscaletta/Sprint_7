package courierTests;

import org.example.Courier;

public class CourierTestData {

    public static Courier bodyPost() {
        return new Courier("VAbovyan", "123456", "Vitaliy");
    }

    public static Courier notCompleteBodyPost() {
        return new Courier("VAbovyan", null, "Vitaliy");
    }

    public static Courier bodyPostWithNonExistentData() {
        return new Courier("VAbovyan", "123654789", "Vitaliy");
    }

    public static Courier bodyPostWithExistingLogin() {
        return new Courier("VAbovyan", "12345", "Vitaliy");
    }

    public static Courier bodyPostWithIncorrectData() {
        return new Courier("0{}%!{-=-", "!{-=-0{}%", "Vitaliy");
    }

    public static Courier notFullBodyPost() {
        return new Courier("VAbovyan", "", "Vitaliy");
    }
}