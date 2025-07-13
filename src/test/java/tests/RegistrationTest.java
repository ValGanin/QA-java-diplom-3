package tests;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import utils.BrowserProvider;
import utils.DriverFactory;
import utils.UserData;
import static utils.ApiClient.*;

import java.time.Duration;

public class RegistrationTest {
    private UserData user;
    private WebDriver driver;
    private RegistrationPage registrationPage;
    private LoginPage loginPage;
    private MainPage mainPage;
    private String browserName;
    private String accessToken;


    @BeforeEach
    public void setUp() {
        user = UserData.random();
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    private void initDriver() {
        driver = DriverFactory.getDriver(browserName);
        driver.get("https://stellarburgers.nomoreparties.site");
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Регистрация с корректным паролем")
    @Step("Регистрация с корректным паролем")
    public void registrationCorrectTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getNewRegistrationButton().click();

        registrationPage = new RegistrationPage(driver);
        registrationPage.getLoginInput().sendKeys(user.getName());
        registrationPage.getEmailInput().sendKeys(user.getEmail());
        registrationPage.getPasswordInput().sendKeys(user.getPassword());
        registrationPage.getRegistrationButton().click();

        loginPage = new LoginPage(driver);
        WebElement nameLoginPage = loginPage.getNameLoginPage();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(nameLoginPage));
        Assertions.assertTrue(nameLoginPage.isDisplayed());

        accessToken = loginUser(user);
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Регистрация с некоррктным паролем")
    @Step("Регистрация с некоррктным паролем")
    public void registrationIncorrectPassTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getNewRegistrationButton().click();

        registrationPage = new RegistrationPage(driver);
        registrationPage.getLoginInput().sendKeys(user.getName());
        registrationPage.getEmailInput().sendKeys(user.getEmail());
        registrationPage.getPasswordInput().sendKeys("12345");
        registrationPage.getRegistrationButton().click();
        Assertions.assertTrue(registrationPage.getRegistrationError().isDisplayed());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        if (accessToken!=null) deleteUser(accessToken);
        }
    }
}