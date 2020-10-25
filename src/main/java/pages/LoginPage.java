package pages;

import base.BasePageAction;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.FluentWait;
import util.TestUtil;

import static constants.Constants.SETPIN_PAGE_TITLE;

public class LoginPage extends BasePageAction {
    protected Logger log;
    String testName;

    //1.a. Define page objects (PAGE OR) : using PageFactory Pattern
    @FindBy(id = "Username")
    WebElement inputUsername;
    @FindBy(id = "Password")
    WebElement inputPassword;
    @FindBy(name = "button")
    WebElement btnLogin;


    //1.b. Constructor of page class and initialize elements with driver
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = Logger.getLogger(IndexPage.class);

    }
    //2. Page methods - Actions:
    @Step("Getting login page title step....")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Verifying the login button is displayed and enabled")
    public SetPinPage inputLoginData(String emailVal, String passwordVal){
        inputUsername.sendKeys(emailVal);
        inputPassword.sendKeys(passwordVal);
        btnLogin.click();
        FluentWait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.titleIs(SETPIN_PAGE_TITLE));
        } catch (TimeoutException e) {
            System.out.print(e);
        }catch (NoSuchElementException e) {
            System.out.print(e);
        }
        return new SetPinPage(driver);
    }

}
