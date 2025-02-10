package orderTests;

import org.example.Orders;

import java.util.Arrays;

public class OrdersTestData {

    public static Orders bodyPostWithBlack() {
        return new Orders("Vitaliy", "Abovyan", "Revolucionnay str. 101", 4, "+7 960 125 78 63", 5, "2025-01-10", "test", Arrays.asList("BLACK"));
    }

    public static Orders bodyPostWithGrey() {
        return new Orders("Vitaliy", "Abovyan", "Revolucionnay str. 101", 4, "+7 960 125 78 63", 5, "2025-01-10", "test", Arrays.asList("GREY"));
    }

    public static Orders bodyPostWithBothColors() {
        return new Orders("Vitaliy", "Abovyan", "Revolucionnay str. 101", 4, "+7 960 125 78 63", 5, "2025-01-10", "test", Arrays.asList("BLACK", "GREY"));
    }

    public static Orders bodyPostWithNonColors() {
        return new Orders("Vitaliy", "Abovyan", "Revolucionnay str. 101", 4, "+7 960 125 78 63", 5, "2025-01-10", "test", Arrays.asList());
    }

}