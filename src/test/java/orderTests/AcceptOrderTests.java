package orderTests;

import courierTests.CourierSteps;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class AcceptOrderTests {

    private OrderSteps orderSteps = new OrderSteps();
    private CourierSteps courierSteps = new CourierSteps();

    @Before
    public void createCourier() {
        courierSteps.createCourierIfDoesNotExist();
    }

    @Test
    @Description("Тест на успешное принятие заказа курьером")
    @Severity(SeverityLevel.BLOCKER)
    public void acceptOrder() {
        Integer idCourier = orderSteps.getCourierId();
        Integer orderId = orderSteps.getOrderId();
        Response acceptOrder = orderSteps.acceptOrder(orderId.toString() + "?", idCourier.toString());
        acceptOrder.then().statusCode(200);
    }

    @Test
    @Description("Проверка ответа при успешном принятии заказа курьером")
    @Severity(SeverityLevel.NORMAL)
    public void acceptOrderCheckResponse() {
        Integer idCourier = orderSteps.getCourierId();
        Integer orderId = orderSteps.getOrderId();
        Response acceptOrder = orderSteps.acceptOrder(orderId.toString() + "?", idCourier.toString());
        acceptOrder.then().body("ok", equalTo(true));
    }

    @Test
    @Description("Тест на принятие заказа с неверным ID курьера")
    @Severity(SeverityLevel.CRITICAL)
    public void acceptOrderWrongCourierId() {
        Integer orderId = orderSteps.getOrderId();
        Response acceptOrder = orderSteps.acceptOrder(orderId.toString() + "?", "0");
        acceptOrder.then().statusCode(404);
    }

    @Test
    @Description("Тест на принятие заказа с неверным ID заказа")
    @Severity(SeverityLevel.CRITICAL)
    public void acceptOrderWrongOrderId() {
        Integer idCourier = orderSteps.getCourierId();
        Response acceptOrder = orderSteps.acceptOrder("0?", idCourier.toString());
        acceptOrder.then().statusCode(404);
    }

    @Test
    @Description("Тест на принятие заказа без указания ID заказа и ID курьера")
    @Severity(SeverityLevel.NORMAL)
    public void acceptOrderWithoutOrderIdCourierId() {
        Response acceptOrder = orderSteps.acceptOrder("", "");
        acceptOrder.then().statusCode(400);
    }

    @Test
    @Description("Тест на принятие заказа без указания ID заказа")
    @Severity(SeverityLevel.NORMAL)
    public void acceptOrderWithoutOrderId() {
        Integer idCourier = orderSteps.getCourierId();
        Response acceptOrder = orderSteps.acceptOrder("", idCourier.toString());
        acceptOrder.then().statusCode(400);
    }

    @Test
    @Description("Тест на принятие заказа без указания ID курьера")
    @Severity(SeverityLevel.NORMAL)
    public void acceptOrderWithoutCourierId() {
        Integer orderId = orderSteps.getOrderId();
        Response acceptOrder = orderSteps.acceptOrder(orderId.toString() + "?", "");
        acceptOrder.then().statusCode(400);
    }

    @After
    public void deleteCourier() {
        courierSteps.deleteCourierIfExist();
    }
}