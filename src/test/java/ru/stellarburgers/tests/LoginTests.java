package ru.stellarburgers.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.base.BaseTest;
import ru.stellarburgers.pages.*;

public class LoginTests extends BaseTest {

    private final String name = "TestUserLogin";
    private final String email = "testuser_login" + System.currentTimeMillis() + "@test.com";
    private final String password = "password123";

    private String accessToken;

    @Before
    public void prepareUser() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";
        User user = new User(email, password, name);

        Response response = RestAssured.given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post("/api/auth/register");

        Assert.assertEquals("Предусловие не выполнено: пользователь не создан", 200, response.statusCode());

        accessToken = response.jsonPath().getString("accessToken");
    }

    @Override
    @After
    public void tearDown() {
        if (accessToken != null && !accessToken.isEmpty()) {
            RestAssured.given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete("/api/auth/user")
                    .then()
                    .statusCode(202);
        }

        super.tearDown();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Проверка авторизации через кнопку 'Войти в аккаунт'")
    public void loginViaLoginButtonOnMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickLoginButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Проверка авторизации через кнопку 'Личный кабинет'")
    public void loginViaPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickPersonalAccountButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }


    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка авторизации со страницы регистрации")
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickPersonalAccountButton();

        RegisterPage registerPage = loginPage.clickRegisterLink();

        loginPage = registerPage.clickLoginLink();

        mainPage = loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }


    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации со страницы восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickPersonalAccountButton();

        ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPasswordLink();

        loginPage = forgotPasswordPage.clickLoginLink();

        mainPage = loginPage.login(email, password);

        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }
}