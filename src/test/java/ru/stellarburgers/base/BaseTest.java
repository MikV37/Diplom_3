package ru.stellarburgers.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseTest {

    protected WebDriver driver;

    @Before
    public void setUp() {

        String browser = System.getProperty("browser", "chrome");

        if ("yandex".equals(browser)) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");
            driver = new ChromeDriver();
            System.out.println("Запускаем тесты в Яндекс.Браузере");
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Запускаем тесты в Google Chrome");
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}