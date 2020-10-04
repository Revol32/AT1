package TZ1.Logintest;

import TZ1.CucumberLogin;
import TZ1.Logintest.MainPage;

import io.cucumber.java.ru.Допустим;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class StepDefinitions {
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

    private MainPage mainPage;

    @Допустим("^зайдем на страницу \"(.+)\"$")
    public void openPage(String url) {
        mainPage = new MainPage (CucumberLogin.getDriver());
        mainPage.open(url);
    }

    @И("нажмем на кнопку \"Войти\"")
    public void clickLogin() {
        PageFactory.initElements(MainPage.driver, this);
        loginButton.click();
    }

    @И("^введем в поле логина \"(.+)\"$")
    public void inputLogin(String text) {
        loginField.sendKeys(text);
    }

    @И("^введем в поле пароля \"(.+)\"$")
    public void inputPass(String text) {
        passField.sendKeys(text);
    }

    @И("нажмем на кнопку авторизации")
    public void clickAuh() {
       auhButton.click();
    }


    @Тогда("^появилась внутрення станица, на ней должна быть кнопка \"Выйти\"")
    public void resultSuccess() {
        Assert.assertNotNull(logoutButton, "Облом");
    }

    @Тогда("^появилось сообшение об ошибке")
    public void resultFail() {
        Assert.assertNotNull(errorMassage, "Облом");
    }





}
