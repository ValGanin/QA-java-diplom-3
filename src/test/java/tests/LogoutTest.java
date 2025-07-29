package tests;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.PersonalAccountPage;
import utils.BrowserProvider;
import utils.DriverFactory;
import utils.UserData;

import java.time.Duration;

import static utils.ApiClient.createUser;
import static utils.ApiClient.deleteUser;

public class LogoutTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private UserData user;
    private String accessToken;
    private String browserName;
    private PersonalAccountPage personalAccountPage;

    @BeforeEach
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
        user = UserData.random();
        accessToken = createUser(user);
    }
    private void initDriver() {
        driver = DriverFactory.getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
    }
    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Выход из аккаунта")
    @Step("Выход из аккаунта")
    public void logoutAccountTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(user.getEmail());
        loginPage.getPasswordInput().sendKeys(user.getPassword());
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.getExitButton().click();

        loginPage = new LoginPage(driver);
        WebElement nameLoginPage = loginPage.getNameLoginPage();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(nameLoginPage));
        Assertions.assertTrue(nameLoginPage.isDisplayed());
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            if (accessToken!=null) deleteUser(accessToken);
        }
    }
}
