package ru.stellarburgers.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.junit.Test;
import ru.stellarburgers.base.BaseTest;
import ru.stellarburgers.pages.MainPage;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверяем, что клик по табу 'Соусы' делает его активным.")
    public void shouldSwitchToSaucesSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickSaucesTab();
        Assert.assertTrue("Раздел 'Соусы' не стал активным после клика",
                mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверяем, что клик по табу 'Начинки' делает его активным.")
    public void shouldSwitchToFillingsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        Assert.assertTrue("Раздел 'Начинки' не стал активным после клика",
                mainPage.isFillingsSectionActive());
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверяем, что после перехода на другой раздел, можно вернуться в 'Булки'.")
    public void shouldSwitchToBunsSection() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickFillingsTab();
        mainPage.clickBunsTab();
        Assert.assertTrue("Раздел 'Булки' не стал активным после клика",
                mainPage.isBunsSectionActive());
    }
}
