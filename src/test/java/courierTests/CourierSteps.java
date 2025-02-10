package courierTests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.Courier;
import org.example.Orders;
import org.example.ScooterServiceApi;

public class CourierSteps {

    private ScooterServiceApi scooterApi = new ScooterServiceApi();

    @Step("Проверка, что курьера не существует")
    public boolean checkingThatNoCourier() {
        return scooterApi.doPostLogin(CourierTestData.bodyPost()).getStatusCode() != 200;
    }

    @Step("Создать курьера")
    public Response createCourier() {
        return scooterApi.doPostCreate(CourierTestData.bodyPost());
    }

    @Step("Залогинить курьера")
    public Response loginCourier(Courier courier) {
        return scooterApi.doPostLogin(courier);
    }

    @Step("Залогинить курьера без пароля")
    public Response loginCourierNotFullData() {
        return scooterApi.doPostLogin(CourierTestData.notFullBodyPost());
    }

    @Step("Залогинить курьера с невалидными данными")
    public Response loginCourierIncorrectData() {
        return scooterApi.doPostLogin(CourierTestData.bodyPostWithIncorrectData());
    }

    @Step("Залогинить курьера с несуществующим логином")
    public Response loginCourierNonExistentData() {
        return scooterApi.doPostLogin(CourierTestData.bodyPostWithNonExistentData());
    }

    @Step("Залогинить курьера с паролем null")
    public Response loginCourierNotCompleteData() {
        return scooterApi.doPostLogin(CourierTestData.notCompleteBodyPost());
    }

    @Step("Создать курьера, если он не существует")
    public void createCourierIfDoesNotExist() {
        if (checkingThatNoCourier()) {
            createCourier();
        }
    }

    @Step("Удалить курьера, если он существует")
    public void deleteCourierIfExist() {
        if(!checkingThatNoCourier()) {
            Response postLogin = loginCourier(CourierTestData.bodyPost());
            deleteCourier(postLogin.jsonPath().getString("id"));
        }

    }

    @Step("Создать курьера с пустым паролем")
    public Response createCourierNotFullData() {
        return scooterApi.doPostCreate(CourierTestData.notFullBodyPost());
    }

    @Step("Создать курьера с паролем null")
    public Response createCourierNotCompleteData() {
        return scooterApi.doPostCreate(CourierTestData.notCompleteBodyPost());
    }

    @Step("Создать курьера с существующим логином")
    public Response createCourierWithExistingLogin() {
        return scooterApi.doPostCreate(CourierTestData.bodyPostWithExistingLogin());
    }

    @Step("Создать заказ")
    public Response createOrder(Orders order) {
        return scooterApi.doPostOrder(order);
    }

    @Step("Удалить курьера")
    public Response deleteCourier(String id) {
        return scooterApi.doDelete(id);
    }

}