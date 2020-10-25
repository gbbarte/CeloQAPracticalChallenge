package pages;

import base.BasePage;
import base.BasePageAction;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class IndexPage extends BasePageAction {
    protected Logger log;
    String testName;

    //1.a. Define page objects (PAGE OR) : using PageFactory Pattern
    @FindBy(id = "login")
    WebElement btnLogin;


    //1.b. Constructor of page class and initialize elements with driver
    public IndexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = Logger.getLogger(IndexPage.class);

    }
    //2. Page methods - Actions:
    @Step("Getting index page title step....")
    public String getPageTitle() {
        return driver.getTitle();
    }

    @Step("Verifying the login button is displayed and enabled")
    public boolean verifyLoginButton(){
        return btnLogin.isDisplayed() && btnLogin.isEnabled();
    }

    @Step("Going to login page")
    public LoginPage gotoLoginPage(){
        btnLogin.click();
        return new LoginPage(driver);
    }

}
