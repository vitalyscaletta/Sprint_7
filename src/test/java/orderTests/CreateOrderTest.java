package orderTests;

import courierTests.CourierSteps;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.example.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderTest {

    private Orders order;
    private CourierSteps courierSteps = new CourierSteps();

    public CreateOrderTest(Orders order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {OrdersTestData.bodyPostWithBlack()},
                {OrdersTestData.bodyPostWithGrey()},
                {OrdersTestData.bodyPostWithBothColors()},
                {OrdersTestData.bodyPostWithNonColors()}
        };
    }

    @Test
    @Description("Тест на создание заказа с параметрами: " + "${order}")
    @Severity(SeverityLevel.BLOCKER)
    public void createOrder() {
        Response createOrder = courierSteps.createOrder(order);
        createOrder.then().body("track", notNullValue());
        createOrder.then().statusCode(201);
    }
}