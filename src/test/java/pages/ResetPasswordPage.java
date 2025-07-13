package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ResetPasswordPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ResetPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By loginButton = By.xpath("//a[contains(text(),'Войти')]");

    @Step("Нажимается кнопка войти")
    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.elementToBeClickable(loginButton));
    }

}