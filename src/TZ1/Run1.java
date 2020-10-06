package TZ1;


import TZ1.Logintest.*;
import org.openqa.selenium.*;
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
    private final String loginCustomer = "brizer@ultra-web.ru";
    private final String passtrue = "7EGDDl";
    private final String passtrueCustomer = "lp1wT2";
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

    @FindBy(xpath = "//*[@data-testid='Create a new test button']")
    protected WebElement createNewTest;
    @FindBy(xpath = "//*[@data-testid='Test name input']")
    protected WebElement nameTestField;
    @FindBy(xpath = "//*[@data-testid='Address site input']")
    protected WebElement urlTestField;
    @FindBy(xpath = "//*[@data-testid='Information textarea']")
    protected WebElement infoTestField;
    @FindBy(xpath = "//*[@data-testid='Submit button']")
    protected WebElement nextOneTestButton;
    @FindBy(xpath = "//*[@data-testid='Segment name input 0']")
    protected WebElement segmentNameField;
    @FindBy(xpath = "//*[@data-testid='Users count input']")
    protected WebElement usersCountField;
    @FindBy(xpath = "//*[@data-testid='Tasks button']")
    protected WebElement tasksButton;
    @FindBy(xpath = "//*[@data-testid='Tasks task question']")
    protected WebElement tasksQuestionField;
    @FindBy(xpath = "//*[@data-testid='Tasks submit task']")
    protected WebElement submitTasksQuestionButton;
    @FindBy(xpath = "//*[@data-testid='Check button']")
    protected WebElement checkButton;
    @FindBy(xpath = "//*[@data-testid='Checkout start button']")
    protected WebElement startTestButton;
    @FindBy(xpath = "//*[@class='sc-bxivhb iaxSpn']")
    protected WebElement returnTestListButton;

    public void LoginTry (String login, String pwd){
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


    public void TestLoginSuccess () {
        LoginTry(login, passtrue);
        Assert.assertNotNull(logoutButton, "Облом");
    }


    public void TestLoginFail () {

        LoginTry(login, pass);
        Assert.assertNotNull(errorMassage, "Облом");
    }

    @Test
    public  void TestTask () {
        int rnd = (int) (Math.random()*100000);
        String randomTestName = "Test "+rnd;
        LoginTry(loginCustomer, passtrueCustomer);
        createNewTest.click();
        nameTestField.sendKeys(randomTestName);
        urlTestField.sendKeys("test.uxcrowd.ru");
        infoTestField.sendKeys("Вводная информация для респондента");
        nextOneTestButton.click();
        segmentNameField.sendKeys(randomTestName);
        usersCountField.sendKeys(Keys.BACK_SPACE,"1");
        tasksButton.click();
        tasksQuestionField.sendKeys("Задание");
        submitTasksQuestionButton.click();
        checkButton.click();
        startTestButton.click();
        returnTestListButton.click();
        logoutButton.click();
    }


    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
