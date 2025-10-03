package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.base.BaseTest;
import ru.stellarburgers.pages.LoginPage;
import ru.stellarburgers.pages.MainPage;
import ru.stellarburgers.pages.RegisterPage;

public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Description("Проверяем, что пользователь с корректными данными может успешно зарегистрироваться.")
    public void successfulRegistrationTest() {
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        String name = "TestUser";
        String email = "testuser" + System.currentTimeMillis() + "@test.com";
        String password = "password123";

        registerPage.fillRegistrationForm(name, email, password);
        registerPage.clickRegisterButton();

        LoginPage finalLoginPage = new LoginPage(driver);
        Assert.assertTrue("Не произошел переход на страницу входа после регистрации",
                finalLoginPage.isLoginHeaderVisible());
    }

    @Test
    @DisplayName("Ошибка при регистрации с некорректным паролем")
    @Description("Проверяем, что при вводе пароля короче 6 символов появляется ошибка.")
    public void registrationWithShortPasswordTest() {
        MainPage mainPage = new MainPage(driver);

        LoginPage loginPage = mainPage.clickPersonalAccountButton();
        RegisterPage registerPage = loginPage.clickRegisterLink();

        registerPage.fillRegistrationForm("TestUser", "email@test.com", "123");
        registerPage.clickRegisterButton();

        Assert.assertTrue("Сообщение об ошибке для некорректного пароля не отображается",
                registerPage.isPasswordErrorVisible());
    }
}