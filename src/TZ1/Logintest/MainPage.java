package TZ1.Logintest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MainPage extends AbstractPage {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open(String url) {
        driver.get(url);
        return this;
    }

}
