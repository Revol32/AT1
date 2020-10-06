package TZ1.Logintest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;



public class DriverCr {

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;

    private DriverCr () {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            webDriverWait = new WebDriverWait((WebDriver) driver, 10L);


    }

    public static WebDriver getDriver () {
        if (driver == null)  new DriverCr();
        return driver;
    }

    public static WebDriverWait getWebDriverWait () {
        return webDriverWait;
    }


}
