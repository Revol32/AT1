package TZ1;


import TZ1.Logintest.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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
    private final String passtrue = "7EGDDl";
    private final String pass = "123412";
    public String url = "https://test.uxcrowd.ru/";
    @FindBy(xpath = "//*[@data-testid='Login menu button']")
    protected WebElement loginButton;

    @FindBy(xpath = "//*[@data-testid='Email input']")
    protected WebElement loginField;

    @FindBy(xpath = "//*[@name='password']")
    protected WebElement passField;

    @FindBy(xpath = "//*[@data-testid='Login button']")
    protected WebElement auhButton;

    @FindBy(xpath = "//*[@data-testid='Logout button']")
    protected WebElement logoutButton;

    @FindBy(xpath = "//*[@ng-show='authenticationError']")
    protected WebElement errorMassage;


    public void LoginTry (String pwd){
        MainPage mainPage = new MainPage(driver);
        PageFactory.initElements(driver, this);
        mainPage.open(url);
        loginButton.click();
        loginField.sendKeys(login);
        passField.sendKeys(pwd);
        auhButton.click();

    }

    @BeforeMethod
    public WebDriver setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10L);
        return driver;
    }

    @Test
    public void TestLogin () {
        LoginTry(passtrue);
        Assert.assertNotNull(logoutButton, "Облом");
        LoginTry(pass);
        Assert.assertNotNull(errorMassage, "Облом");

    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
