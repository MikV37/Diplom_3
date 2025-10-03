package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgotPasswordPage {
    private final WebDriver driver;

    @FindBy(xpath = ".//a[text()='Войти']")
    private WebElement loginLink;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Клик по ссылке 'Войти' на странице восстановления пароля")
    public LoginPage clickLoginLink() {
        loginLink.click();
        return new LoginPage(driver);
    }
}
