package tests;
import io.qameta.allure.Step;
import java.time.Duration;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import pages.ResetPasswordPage;
import utils.BrowserProvider;
import utils.DriverFactory;
import utils.UserData;
import static utils.ApiClient.*;


public class LoginTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private UserData user;
    private String accessToken;
    private String browserName;
    private RegistrationPage registrationPage;
    private ResetPasswordPage resetPasswordPage;

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
    @DisplayName("Логин через личный кабинет")
    @Step("Логин через личный кабинет")
    public void loginWithLKTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(user.getEmail());
        loginPage.getPasswordInput().sendKeys(user.getPassword());
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebElement orderButton = mainPage.getCreatedOrderButton();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(orderButton));
        Assertions.assertTrue(orderButton.isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку войти в аккаунт")
    @Step("Логин через кнопку войти в аккаунт")
    public void loginWithEnterAccountTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getOpenAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(user.getEmail());
        loginPage.getPasswordInput().sendKeys(user.getPassword());
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebElement orderButton = mainPage.getCreatedOrderButton();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(orderButton));
        Assertions.assertTrue(orderButton.isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку в форме регистрации")
    @Step("Логин через кнопку в форме регистрации")
    public void loginWithRegistrationEnterTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getNewRegistrationButton().click();

        registrationPage = new RegistrationPage(driver);
        registrationPage.getEnterButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(user.getEmail());
        loginPage.getPasswordInput().sendKeys(user.getPassword());
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebElement orderButton = mainPage.getCreatedOrderButton();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(orderButton));
        Assertions.assertTrue(orderButton.isDisplayed());
    }

    @ParameterizedTest
    @ArgumentsSource(BrowserProvider.class)
    @DisplayName("Логин через кнопку в форме восстановления пароля")
    @Step("Логин через кнопку в форме восстановления пароля")
    public void loginWithRestorePasswordTest(String browser) {
        this.browserName = browser;
        initDriver();

        mainPage = new MainPage(driver);
        mainPage.getPersonalAccountButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getResetPasswordButton().click();

        resetPasswordPage = new ResetPasswordPage(driver);
        resetPasswordPage.getLoginButton().click();

        loginPage = new LoginPage(driver);
        loginPage.getLoginInput().sendKeys(user.getEmail());
        loginPage.getPasswordInput().sendKeys(user.getPassword());
        loginPage.getLoginButton().click();

        mainPage = new MainPage(driver);
        WebElement orderButton = mainPage.getCreatedOrderButton();
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOf(orderButton));
        Assertions.assertTrue(orderButton.isDisplayed());
    }

    @AfterEach
    public void tearDown() {
            if (driver != null) {
                driver.quit();
            }
        if (accessToken!=null) deleteUser(accessToken);

    }
}