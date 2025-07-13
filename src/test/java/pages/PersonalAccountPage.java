package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By exitButton = By.xpath("//button[text()='Выход']");
    private By сonstructorButton = By.xpath("//p[contains(text(),'Конструктор')]");
    private By logoButton = By.xpath("//div[@id='root']//header//nav//div");
    private By profileButton = By.xpath("//a[contains(text(),'Профиль')]");

    @Step("Нажимается кнопка выход")
    public WebElement getExitButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(exitButton));
    }
    @Step("Нажимается кнопка конструктор")
    public WebElement getConstructorButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(сonstructorButton));
    }
    @Step("Нажимается логотип Stellar Burgers")
    public WebElement getLogoButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(logoButton));
    }
    @Step("Нажимается кнопка профиль")
    public WebElement getProfileButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(profileButton));
    }
}