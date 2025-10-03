package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.stellarburgers.base.BaseTest;
import ru.stellarburgers.pages.ForgotPasswordPage;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;

public class LoginTests extends BaseTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void prepareUser() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        userClient = new UserClient();

        user = User.getRandomUser();

        Response response = userClient.create(user);

        Assert.assertEquals("Предусловие не выполнено: пользователь не создан", 200, response.statusCode());
        accessToken = response.jsonPath().getString("accessToken");
    }

    @Override
    @After
    public void tearDown() {
        userClient.delete(accessToken);
        super.tearDown();
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginViaLoginButtonOnMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickLoginButton();
        mainPage = loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginViaPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        mainPage = loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginFromRegistrationPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();
        loginPage = registerPage.clickLoginLink();
        mainPage = loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginFromForgotPasswordPageTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        ForgotPasswordPage forgotPasswordPage = loginPage.clickForgotPasswordLink();
        loginPage = forgotPasswordPage.clickLoginLink();
        mainPage = loginPage.login(user.getEmail(), user.getPassword());
        Assert.assertTrue("Кнопка 'Оформить заказ' не видна, вход не выполнен",
                mainPage.isCreateOrderButtonVisible());
    }
}