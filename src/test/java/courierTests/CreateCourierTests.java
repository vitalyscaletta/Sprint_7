package courierTests;

import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTests {
    private CourierSteps courierSteps = new CourierSteps();

    @Before
    public void deleteCourierIfExist() {
        courierSteps.deleteCourierIfExist();
    }

    @Test
    @Description("Тест на создание курьера с валидными данными")
    @Severity(SeverityLevel.BLOCKER)
    public void createCourier() {
        Response createCourier = courierSteps.createCourier();
        createCourier.then().statusCode(201);
    }

    @Test
    @Description("Проверка ответа при создании курьера")
    @Severity(SeverityLevel.NORMAL)
    public void createCourierCheckResponse() {
        Response createCourier = courierSteps.createCourier();
        createCourier.then().body("ok", equalTo(true));
    }

    @Test
    @Description("Тест на попытку создания двух одинаковых курьеров")
    @Severity(SeverityLevel.CRITICAL)
    public void cantCreateTwoIdenticalCouriers() {
        courierSteps.createCourierIfDoesNotExist();
        Response createCourier = courierSteps.createCourier();
        createCourier.then().statusCode(409);
    }

    @Test
    @Description("Тест на создание курьера с неполными данными")
    @Severity(SeverityLevel.NORMAL)
    public void cantCreateCourierNotFullData() {
        Response createCourier = courierSteps.createCourierNotFullData();
        createCourier.then().statusCode(400);
    }

    @Test
    @Description("Тест на создание курьера с некорректными данными")
    @Severity(SeverityLevel.NORMAL)
    public void cantCreateCourierNotCompleteData() {
        Response createCourier = courierSteps.createCourierNotCompleteData();
        createCourier.then().statusCode(400);
    }

    @Test
    @Description("Тест на создание курьера с уже существующим логином")
    @Severity(SeverityLevel.CRITICAL)
    public void cantCreateCourierWithExistingLogin() {
        courierSteps.createCourierIfDoesNotExist();
        Response createCourier = courierSteps.createCourierWithExistingLogin();
        createCourier.then().statusCode(409);
    }

    @After
    public void deleteCourier() {
        courierSteps.deleteCourierIfExist();
    }
}