package courierTests;

import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class DeleteCourierTests {

    private CourierSteps courierSteps = new CourierSteps();

    @Before
    public void createCourier() {
        courierSteps.createCourierIfDoesNotExist();
    }

    @Test
    @Description("Тест на удаление курьера с валидным ID")
    @Severity(SeverityLevel.BLOCKER)
    public void deleteCourierTest() {
        Response postLogin = courierSteps.loginCourier(CourierTestData.bodyPost());
        Response deleteCourier = courierSteps.deleteCourier(postLogin.jsonPath().getString("id"));
        deleteCourier.then().statusCode(200);
    }

    @Test
    @Description("Тест на удаление несуществующего курьера")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteNonExistentCourierTest() {
        Response deleteCourier = courierSteps.deleteCourier("0");
        deleteCourier.then().statusCode(404);
    }

    @Test
    @Description("Тест на удаление курьера без указания ID")
    @Severity(SeverityLevel.NORMAL)
    public void deleteCourierWithoutIdTest() {
        Response deleteCourier = courierSteps.deleteCourier("");
        deleteCourier.then().statusCode(404);
    }

    @Test
    @Description("Проверка ответа при удалении курьера")
    @Severity(SeverityLevel.NORMAL)
    public void deleteCourierCheckResponseTest() {
        courierSteps.createCourierIfDoesNotExist();
        Response postLogin = courierSteps.loginCourier(CourierTestData.bodyPost());
        Response deleteCourier = courierSteps.deleteCourier(postLogin.jsonPath().getString("id"));
        deleteCourier.then().body("ok", equalTo(true));
    }

    @Test
    @Description("Тест на удаление курьера с некорректным запросом")
    @Severity(SeverityLevel.CRITICAL)
    public void deleteCourierInvalidRequestTest() {
        Response deleteCourier = courierSteps.deleteCourier(null);
        deleteCourier.then().statusCode(500);
    }

    @After
    public void deleteCourier() {
        courierSteps.deleteCourierIfExist();
    }
}