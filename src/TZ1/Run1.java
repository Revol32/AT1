package TZ1;


import TZ1.Logintest.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Run1 {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\chromedriver.exe");
    }

    private WebDriver driver;
    public WebDriverWait webDriverWait;
    private final String login = "brizer@inbox.ru";
    private final String pass = "";
    public String url = "https://test.uxcrowd.ru/";
    @FindBy(xpath = "//*[@data-testid='Login menu button']")
    protected WebElement loginButton;

    @FindBy(xpath = "//*[@data-testid='Email input']")
    protected WebElement loginField;

    @FindBy(xpath = "//*[@name='password']")
    protected WebElement passField;

    @FindBy(xpath = "//*[@data-testid='Login button']")
    protected WebElement auhButton;


    @BeforeMethod
    public WebDriver setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 30L);
        return driver;
    }

    @Test
    public void TestLogin () {
        webDriverWait =new WebDriverWait(driver,30L);
        MainPage mainPage = new MainPage(driver);
        mainPage.open(url);
        loginButton.click();
        loginField.sendKeys(login);
        passField.sendKeys(pass);
        auhButton.click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
