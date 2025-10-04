package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    @FindBy(xpath = ".//h2[text()='Вход']")
    private WebElement loginHeader;

    @FindBy(xpath = ".//a[text()='Зарегистрироваться']")
    private WebElement registerLink;

    @FindBy(xpath = ".//label[text()='Email']/../input")
    private WebElement emailField;

    @FindBy(xpath = ".//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//button[text()='Войти']")
    private WebElement loginButton;

    @FindBy(xpath = ".//a[text()='Восстановить пароль']")
    private WebElement forgotPasswordLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(loginHeader));
    }

    @Step("Клик по ссылке 'Зарегистрироваться'")
    public RegisterPage clickRegisterLink() {
        registerLink.click();
        return new RegisterPage(driver);
    }

    @Step("Клик по ссылке 'Восстановить пароль'")
    public ForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ForgotPasswordPage(driver);
    }

    @Step("Авторизация пользователя с email: {email} и паролем: {password}")
    public MainPage login(String email, String password) {
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        loginButton.click();
        return new MainPage(driver);
    }

    public boolean isLoginHeaderVisible() {
        return loginHeader.isDisplayed();
    }
}