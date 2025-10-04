package ru.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

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

    public MainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return new LoginPage(driver);
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
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(bunsTab.findElement(By.xpath("./..")), "class", "tab_tab_type_current__2BEPc"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isSaucesSectionActive() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(saucesTab.findElement(By.xpath("./..")), "class", "tab_tab_type_current__2BEPc"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isFillingsSectionActive() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.attributeContains(fillingsTab.findElement(By.xpath("./..")), "class", "tab_tab_type_current__2BEPc"));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}