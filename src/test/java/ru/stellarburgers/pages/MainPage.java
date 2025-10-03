package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage {
    private WebDriver driver;

    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private WebElement loginButton;

    @FindBy(xpath = ".//p[text()='Личный Кабинет']/..")
    private WebElement personalAccountButton;

    @FindBy(xpath = ".//button[text()='Оформить заказ']")
    private WebElement createOrderButton;

    @FindBy(xpath = ".//span[text()='Булки']")
    private WebElement bunsTab;

    @FindBy(xpath = ".//span[text()='Соусы']")
    private WebElement saucesTab;

    @FindBy(xpath = ".//span[text()='Начинки']")
    private WebElement fillingsTab;

    @FindBy(xpath = ".//h2[text()='Булки']")
    private WebElement bunsHeader;

    @FindBy(xpath = ".//h2[text()='Соусы']")
    private WebElement saucesHeader;

    @FindBy(xpath = ".//h2[text()='Начинки']")
    private WebElement fillingsHeader;


    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickLoginButton() {
        loginButton.click();
    }

    @Step("Клик по кнопке 'Личный кабинет'")
    public LoginPage clickPersonalAccountButton() {
        personalAccountButton.click();
        return new LoginPage(driver);
    }

    public boolean isCreateOrderButtonVisible() {
        return createOrderButton.isDisplayed();
    }

    @Step("Клик по вкладке 'Соусы'")
    public void clickSaucesTab() {
        saucesTab.click();
    }

    @Step("Клик по вкладке 'Начинки'")
    public void clickFillingsTab() {
        fillingsTab.click();
    }

    @Step("Клик по вкладке 'Булки'")
    public void clickBunsTab() {
        bunsTab.click();
    }

    public boolean isBunsSectionActive() {
        return bunsHeader.isDisplayed();
    }

    public boolean isSaucesSectionActive() {
        return saucesHeader.isDisplayed();
    }

    public boolean isFillingsSectionActive() {
        return fillingsHeader.isDisplayed();
    }
}