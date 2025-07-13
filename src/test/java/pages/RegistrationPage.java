package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    private By loginInput = By.xpath("//label[text()='Имя']/following-sibling::input[@name='name']");
    private By emailInput = By.xpath("//label[text()='Email']/following-sibling::input[@name='name']");
    private By passwordInput = By.xpath("//input[@name='Пароль' and @type='password']");
    private By registrationButton = By.xpath("//button[text()='Зарегистрироваться']");
    private By registrationError = By.xpath("//p[contains(text(),'Некорректный пароль')]");
    private By registrationNamePage = By.xpath("//h2[text()=\"Регистрация\"]");
    private By enterButton = By.xpath("//a[contains(text(),'Войти')]");

    @Step("Заполняется поле ввода Имя")
    public WebElement getLoginInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginInput));
    }
    @Step("Заполняется поле ввода Email")
    public WebElement getEmailInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(emailInput));
    }
    @Step("Заполняется поле ввода Пароль")
    public WebElement getPasswordInput() {
        return wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
    }
    @Step("Нажмается кнопка зарегистрироваться")
    public WebElement getRegistrationButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(registrationButton));
    }
    @Step("Отображается сообщение о некорректном пароле")
    public WebElement getRegistrationError() {
        return wait.until(ExpectedConditions.elementToBeClickable(registrationError));
    }
    @Step("Отображается наименование страницы Регистрация")
    public WebElement getRegistrationNamePage() {
        return wait.until(ExpectedConditions.elementToBeClickable(registrationNamePage));
    }
    @Step("Нажимается кнопка войти")
    public WebElement getEnterButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(enterButton));
    }

}