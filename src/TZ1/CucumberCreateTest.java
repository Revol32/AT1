package TZ1;

import TZ1.Logintest.DriverCr;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


@CucumberOptions(
        features = "resources/CreateTest.feature",
        glue = "TZ1.Logintest"
)

public class CucumberCreateTest extends AbstractTestNGCucumberTests {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\chromedriver.exe");
    }

    protected static WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod
    public WebDriver setUp() {
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10L);
        return driver;*/
        driver = DriverCr.getDriver();
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
