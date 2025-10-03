package ru.stellarburgers.tests;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {

    @Step("Создание пользователя через API")
    public Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(Endpoints.REGISTER_PATH);
    }

    @Step("Логин пользователя через API")
    public Response login(User user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(Endpoints.LOGIN_PATH);
    }

    @Step("Удаление пользователя через API")
    public void delete(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(Endpoints.USER_PATH);
        }
    }
}
