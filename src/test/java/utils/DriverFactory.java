package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class DriverFactory {
    public static WebDriver getDriver(String browserName) {
        switch (browserName.toUpperCase()) {
            case "CHROME":
                return new ChromeDriver();

            case "YANDEX":
                ChromeOptions options = new ChromeOptions();
                options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");

                File driverExecutable = new File("src/test/resources/yandexdriver");
                if (!driverExecutable.exists()) {
                    throw new IllegalStateException("YandexDriver not found at: " + driverExecutable.getAbsolutePath());
                }

                ChromeDriverService service = new ChromeDriverService.Builder()
                        .usingDriverExecutable(driverExecutable)
                        .usingAnyFreePort()
                        .build();

                return new ChromeDriver(service, options);

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }
    }
}