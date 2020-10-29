package TZ2;

import TZ2.bin.Users;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.annotations.Test;


import java.util.concurrent.TimeUnit;

public class Sencha {
    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\brizer\\IdeaProjects\\AT1\\src\\chromedriver.exe");
    }


    @FindBy(xpath = "//*[@class='x-button-el']")
    protected WebElement auhButton;

    private final String url = "https://examples.sencha.com/coworkee/#home";
    private final String urlapi = "https://examples.sencha.com/coworkee/api";

    private static WebDriver driver;
    private static WebDriverWait webDriverWait;

    public WebDriver drv () {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriverWait = new WebDriverWait(driver, 10L);
        return driver;
    }


    @Test
    public void xz () {
        String script =
                "const MockXMLHttpRequest = require('mock-xmlhttprequest');\n" +
                "const MockXhr = MockXMLHttpRequest.newMockXhr();\n" +
                "\n" +
                "MockXhr.onSend = (xhr) => {\n" +
                "  const responseHeaders = { 'Content-Type': 'application/json' };\n" +
                "  const response = '{ \"message\": \"Success!\" }';\n" +
                "  xhr.respond(200, responseHeaders, response);\n" +
                "};\n" +
                "\n" +
                "// Install in the global context so \"new XMLHttpRequest()\" uses the XMLHttpRequest mock\n" +
                "global.XMLHttpRequest = MockXhr;";
        driver = drv();
        PageFactory.initElements(driver, this);
        driver.get(url);
        auhButton.click();
        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript(script);
        }
    //    driver.quit();
    }


}
