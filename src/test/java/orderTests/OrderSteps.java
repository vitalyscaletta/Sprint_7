package orderTests;

import courierTests.CourierSteps;
import courierTests.CourierTestData;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.example.ScooterServiceApi;

import java.util.List;

public class OrderSteps {

    ScooterServiceApi scooterApi = new ScooterServiceApi();
    CourierSteps courierSteps = new CourierSteps();

    @Step("Отправить PUT запрос, на принятие заказа курьером")
    public Response acceptOrder(String idOrder, String idCourier) {
        return scooterApi.doPut(idOrder, idCourier);
    }

    @Step("Получить id курьера")
    public Integer getCourierId() {
        Response postLogin = courierSteps.loginCourier(CourierTestData.bodyPost());
        return postLogin.jsonPath().getInt("id");
    }

    @Step("Получить id первого из 5 доступных заказов")
    public Integer getOrderId() {
        Response getFiveOrders = getFiveOrders();
        List<Integer> ordersList = getOrdersId(getFiveOrders);
        return ordersList.get(0);
    }

    @Step("Получить 5 доступных заказов")
    public Response getFiveOrders() {
        return scooterApi.doGet();
    }

    @Step("Получить id 5 доступных для взятия заказов")
    public List<Integer> getOrdersId(Response OrdersList) {
        JsonPath jsonPath = new JsonPath(OrdersList.body().asString());
        return jsonPath.getList("orders.id");
    }
}