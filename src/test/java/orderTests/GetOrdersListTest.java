package orderTests;

import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GetOrdersListTest {

    private OrderSteps orderSteps = new OrderSteps();

    @Test
    @Description("Тест на получение списка заказов. Ожидается, что будет получено 5 заказов.")
    @Severity(SeverityLevel.NORMAL)
    public void getOrdersListTest() {
        Response getOrders = orderSteps.getFiveOrders();
        List<Integer> orders = orderSteps.getOrdersId(getOrders);
        Assert.assertEquals(5, orders.size());
    }
}