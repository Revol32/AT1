package TZ1;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

@CucumberOptions(
        features = "resources/CreateTest.feature",
        glue = "TZ1.Logintest"
)

public class CucumberCreateTest extends AbstractTestNGCucumberTests {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\chromedriver.exe");
    }

    private static WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod
    public WebDriver setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10L);
        return driver;
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
