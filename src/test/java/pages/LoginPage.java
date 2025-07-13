package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriverWait wait;
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    private By registrationButton = By.xpath("//a[contains(text(),'Зарегистрироваться')]");
    private By loginInput = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    private By passwordInput = By.xpath("//input[@name='Пароль' and @type='password']");
    private By loginButton = By.xpath("//button[text()='Войти']");
    private By resetPasswordButton = By.xpath("//a[contains(text(),'Восстановить пароль')]");
    private By nameLoginPage = By.xpath("//h2[text()=\"Вход\"]");

    @Step("Нажимается кнопка Зарегистрироваться")
    public WebElement getNewRegistrationButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        return element;
    }
    @Step("Заполняется поле ввода для логина")
    public WebElement getLoginInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginInput));
    }
    @Step("Заполняется поле ввода для пароля")
    public WebElement getPasswordInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
    }
    @Step("Нажимается кнопка войти")
    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }
    @Step("Нажимается кнопка Восстановить пароль")
    public WebElement getResetPasswordButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(resetPasswordButton));
    }
    @Step("Отображается наименование страницы Вход")
    public WebElement getNameLoginPage() {
        return wait.until(ExpectedConditions.elementToBeClickable(nameLoginPage));
    }


}