package TZ1.Logintest;

import TZ1.CucumberLogin;
import TZ1.Logintest.MainPage;

import io.cucumber.java.ru.*;
import org.openqa.selenium.Keys;
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



    private String rnd () {
        int rnd = (int) (Math.random() * 100000);
        return Integer.toString(rnd);
    }
    private MainPage mainPage;

    @Допустим("^зайдем на страницу \"(.+)\"$")
    public void openPage(String url) {
        mainPage = new MainPage (CucumberLogin.getDriver());
        PageFactory.initElements(MainPage.driver, this);
        mainPage.open(url);
    }

    @И("нажмем на кнопку \"Войти\"")
    public void clickLogin() {
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


    @Тогда("мы попадем в личный кабинет, и нажмем на кнопку \"Создать новый тест\"")
    public void clickNewTest() {
        createNewTest.click();
    }

    @И("введем название теста {string}")
    public void введемНазваниеТеста(String arg0) {
        nameTestField.sendKeys(arg0+rnd());
    }

    @Также("введем адрес тестируемого сайта {string}")
    public void введемАдресТестируемогоСайта(String arg0) {
        urlTestField.sendKeys(arg0);
    }

    @И("вводную информациб для тестеровшика {string}")
    public void вводнуюИнформацибДляТестеровшика(String arg0) {
        infoTestField.sendKeys(arg0);
    }

    @И("нажмем на кнопку \"К выбору аудитории\"")
    public void нажмемНаКнопку() {
        nextOneTestButton.click();
    }

    @Тогда("на странице аудитории где введем название сегмента аудитории {string}")
    public void наСтраницеАудиторииГдеВведемНазваниеСегментаАудитории(String arg0) {
        segmentNameField.sendKeys(arg0+rnd());
        usersCountField.sendKeys(Keys.BACK_SPACE,"1");
    }

    @Затем("нажмем на кнопку \"К заданиям\"")
    public void нажмемНаКнопку(String arg0) {
        tasksButton.click();
    }

    @Тогда("на странице задания мы введем описание задания {string}")
    public void наСтраницеЗаданияМыВведемОписаниеЗадания(String arg0) {
        tasksQuestionField.sendKeys(arg0+rnd());
    }

    @И("нажмем конпку Добавить")
    public void нажмемКонпкуДобавить() {
        submitTasksQuestionButton.click();
    }

    @Затем("после создания задания мы нажмем кнопку \"Проверка и запуск\"")
    public void послеСозданияЗаданияМыНажмемКнопку() {
        checkButton.click();
    }

    @И("проверим созаднную задачу на странице Проверка и запуск, и после нажмем \"Запустить тест\"")
    public void проверимСозадннуюЗадачуНаСтраницеПроверкаИЗапускИПослеНажмем() {
        startTestButton.click();
    }

    @Тогда("увидев страницу о удачном создании тесте, мы нажмеме на конопку возврата в личный кабинет")
    public void увидевСтраницуОУдачномСозданииТестеМыНажмемеНаКонопкуВозвратаВЛичныйКабинет() {
        returnTestListButton.click();
    }

    @Когда("мы увеидем список тестов, нажмем кнопку Выйти")
    public void мыУвеидемСписокТестовНажмемКнопкуВыйти() {
        logoutButton.click();
    }


}
