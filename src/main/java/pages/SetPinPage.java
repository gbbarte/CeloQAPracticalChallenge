package pages;

import base.BasePage;
import base.BasePageAction;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetPinPage extends BasePageAction {
    protected Logger log;
    String testName;

    //1.a. Define page objects (PAGE OR) : using PageFactory Pattern
    @FindBy(name = "passcode")
    WebElement inputPinCode;
    @FindBy(name = "passcodeConfirm")
    WebElement inputPinCodeConfirm;
    @FindBy(css = "div.buttons.appear_anim_4.ng-star-inserted span.mat-button-wrapper")
    WebElement btnNext;


    //1.b. Constructor of page class and initialize elements with driver
    public SetPinPage(WebDriver driver) {
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
    public MessagesPage setPIN(String pinVal){
        inputPinCode.sendKeys(pinVal);
        inputPinCodeConfirm.sendKeys(pinVal);
        if (btnNext.isEnabled()) {
            btnNext.click();
        }
        return new MessagesPage(driver);
    }
}
