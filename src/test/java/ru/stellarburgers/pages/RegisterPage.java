package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    private final WebDriver driver;

    @FindBy(xpath = ".//label[text()='Имя']/../input")
    private WebElement nameField;

    @FindBy(xpath = ".//label[text()='Email']/../input")
    private WebElement emailField;

    @FindBy(xpath = ".//input[@type='password']")
    private WebElement passwordField;

    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private WebElement registerButton;

    @FindBy(xpath = ".//p[@class='input__error text_type_main-default']")
    private WebElement passwordErrorMessage;

    @FindBy(xpath = ".//a[text()='Войти']")
    private WebElement loginLink;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Заполнение формы регистрации: Имя={name}, Email={email}, Пароль={password}")
    public void fillRegistrationForm(String name, String email, String password) {
        nameField.sendKeys(name);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
    }

    @Step("Клик по кнопке 'Зарегистрироваться'")
    public void clickRegisterButton() {
        registerButton.click();
    }

    @Step("Клик по ссылке 'Войти'")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return new LoginPage(driver);
    }

    public boolean isPasswordErrorVisible() {
        return passwordErrorMessage.isDisplayed();
    }
}
