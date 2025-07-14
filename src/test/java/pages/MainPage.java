package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }
    private By personalAccountButton = By.xpath("//p[contains(text(),'Личный Кабинет')]");
    private By openAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private By createdOrderButton = By.xpath("//*[contains(text(), 'Оформить заказ')]");
    private By constructorName = By.xpath("//h1[text()=\"Соберите бургер\"]");
    private By bunsButton = By.xpath("//span[contains(text(), 'Булки')]");
    private By bunsName = By.xpath("//h2[text()='Булки']");
    private By saucesButton = By.xpath("//span[contains(text(), 'Соусы')]");
    private By saucesName = By.xpath("//h2[text()='Соусы']");
    private By toppingsButton = By.xpath("//span[contains(text(), 'Начинки')]");
    private By toppingsName = By.xpath("//h2[text()='Начинки']");



    @Step("Нажимается кнопка войти в аккаунт")
    public WebElement getOpenAccountButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(openAccountButton));
    }
    @Step("Нажимается кнопка оформить заказ")
    public WebElement getCreatedOrderButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(createdOrderButton));
    }
    @Step("Нажимается кнопка личный кабинет")
    public WebElement getPersonalAccountButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton));
    }
    @Step("Отображается наименование конструктора")
    public WebElement getConstructorName() {
        return wait.until(ExpectedConditions.elementToBeClickable(constructorName));
    }
    @Step("Нажимается раздел Булки")
    public WebElement getBunsButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(bunsButton));
    }
    @Step("Отображается наименование раздела Булки")
    public WebElement getBunsName() {
        return wait.until(ExpectedConditions.elementToBeClickable(bunsName));
    }
    @Step("Отображается наименование раздела Соусы")
    public WebElement getSaucesName() {
        return wait.until(ExpectedConditions.elementToBeClickable(saucesName));
    }
    @Step("Нажимается раздел Соусы")
    public WebElement getSaucesButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(saucesButton));
    }
    @Step("Отображается наименование раздела Начинки")
    public WebElement getToppingsName() {
        return wait.until(ExpectedConditions.elementToBeClickable(toppingsName));
    }
    @Step("Нажимается раздел Начинки")
    public WebElement getToppingsButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(toppingsButton));
    }
}