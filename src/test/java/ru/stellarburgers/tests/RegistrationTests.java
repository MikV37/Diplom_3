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
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;

public class RegistrationTests extends BaseTest {

    private UserClient userClient;
    private User user;
    private String accessToken;

    @Before
    public void setUpClient() {
        RestAssured.baseURI = Endpoints.BASE_URL;
        userClient = new UserClient();
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            userClient.delete(accessToken);
        }
        super.tearDown();
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что пользователь с корректными данными может успешно зарегистрироваться.")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        user = User.getRandomUser();

        registerPage.fillRegistrationForm(user.getName(), user.getEmail(), user.getPassword());
        registerPage.clickRegisterButton();

        LoginPage finalLoginPage = new LoginPage(driver);
        Assert.assertTrue("Не произошел переход на страницу входа после регистрации",
                finalLoginPage.isLoginHeaderVisible());

        Response loginResponse = userClient.login(user);
        if(loginResponse.statusCode() == 200) {
            accessToken = loginResponse.jsonPath().getString("accessToken");
        }
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверяем, что при вводе пароля короче 6 символов появляется ошибка.")
    public void registrationWithShortPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        User randomUser = User.getRandomUser();
        registerPage.fillRegistrationForm(randomUser.getName(), randomUser.getEmail(), "12345");
        registerPage.clickRegisterButton();

        Assert.assertTrue("Сообщение об ошибке для некорректного пароля не отображается",
                registerPage.isPasswordErrorVisible());
    }
}