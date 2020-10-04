
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\chromedriver.exe");
    }

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10L);
    }

    @Test
    public void googleTest() {

        driver.get("https://www.google.com/");
//        driver.findElement(By.name("q"))
//        driver.findElement(By.cssSelector("[name=q]"));
//        WebElement searchInput = driver.findElement(By.xpath("//*[@name='q']"));
        WebElement searchInput = webDriverWait.until(driver -> driver.findElement(By.xpath("//*[@name='q']")));
        searchInput.sendKeys("performance lab");
        WebElement submitButton = driver.findElement(By.cssSelector("[name=btnK]"));
        submitButton.click();
        List<WebElement> searchResults = driver.findElements(By.cssSelector("#res .g"));
        Assert.assertTrue( searchResults.size() > 0, "Не найдено результатов поиска");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
