package courierTests;

import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class LoginCourierTests {

    private CourierSteps courierSteps = new CourierSteps();

    @Before
    public void createCourier() {
        courierSteps.createCourierIfDoesNotExist();
    }

    @Test
    @Description("Тест на успешный вход курьера с валидными данными")
    @Severity(SeverityLevel.BLOCKER)
    public void loginCourierTest() {
        Response postLogin = courierSteps.loginCourier(CourierTestData.bodyPost());
        postLogin.then().statusCode(200);
    }

    @Test
    @Description("Тест на вход курьера с неполными данными")
    @Severity(SeverityLevel.NORMAL)
    public void loginCourierNotFullData() {
        Response postLogin = courierSteps.loginCourierNotFullData();
        postLogin.then().statusCode(400);
    }

    @Test
    @Description("Тест на вход курьера с некорректными данными")
    @Severity(SeverityLevel.NORMAL)
    public void loginCourierNotCompleteData() {
        Response postLogin = courierSteps.loginCourierNotCompleteData();
        postLogin.then().statusCode(504);
    }

    @Test
    @Description("Тест на вход курьера с неправильными данными")
    @Severity(SeverityLevel.CRITICAL)
    public void loginCourierIncorrectData() {
        Response postLogin = courierSteps.loginCourierIncorrectData();
        postLogin.then().statusCode(404);
    }

    @Test
    @Description("Тест на вход несуществующего курьера")
    @Severity(SeverityLevel.CRITICAL)
    public void loginNonExistentUser () {
        Response postLogin = courierSteps.loginCourierNonExistentData();
        postLogin.then().statusCode(404);
    }

    @Test
    @Description("Проверка ответа при входе курьера")
    @Severity(SeverityLevel.NORMAL)
    public void loginCourierCheckResponse() {
        Response postLogin = courierSteps.loginCourier(CourierTestData.bodyPost());
        postLogin.then().body("id", notNullValue());
    }

    @After
    public void deleteCourier() {
        courierSteps.deleteCourierIfExist();
    }
}