package tests;

import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.BrowserProvider;
import utils.DriverFactory;

import static utils.ApiClient.deleteUser;


public class ConstructorSectionTest {
    private WebDriver driver;
    private MainPage mainPage;
    private String browserName;

    private void initDriver() {
        driver = DriverFactory.getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
    }
    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Булки")
    @Step("Переход к разделу Булки")
    public void openConstructorWithBunsTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getSaucesButton().click();
        mainPage.getBunsButton().click();
        Assertions.assertTrue(mainPage.getBunsName().isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Соусы")
    @Step("Переход к разделу Соусы")
    public void openConstructorWithSaucesTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getSaucesButton().click();
        Assertions.assertTrue(mainPage.getSaucesName().isDisplayed());
    }
    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Переход к разделу Начинки")
    @Step("Переход к разделу Начинки")
    public void openConstructorWithToppingsTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getToppingsButton().click();
        Assertions.assertTrue(mainPage.getToppingsName().isDisplayed());
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
