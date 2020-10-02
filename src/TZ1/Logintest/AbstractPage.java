package TZ1.Logintest;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class AbstractPage {
    protected WebDriver driver;
    protected Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
