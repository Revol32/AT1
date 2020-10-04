package TZ1.Logintest;

import org.openqa.selenium.WebDriver;

public class MainPage extends AbstractPage {

    public static WebDriver driver;

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

}
