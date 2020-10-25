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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static constants.Constants.SETPIN_PAGE_TITLE;
import static util.TestUtil.shortWait;

public class MessagesPage extends BasePageAction {
    protected Logger log;

    //1.a. Define page objects (PAGE OR) : using PageFactory Pattern
    @FindBy(css = "div.name.celo-elipsis.ng-tns-c130-5")
    WebElement btnQA;
    @FindBy(id = "celo-send-message-textarea")
    WebElement txtSendMsg;
    @FindBy(xpath = "//mat-icon[.='send']")
    WebElement btnSend;
    @FindBy(css = "span.arrowicon mat-icon.mat-icon.notranslate.mat-icon-no-color")
    WebElement btnDown;
    @FindBy(xpath = "//mat-icon[.='exit_to_app']")
    WebElement btnLogout;

    //1.b. Constructor of page class and initialize elements with driver
    public MessagesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        log = Logger.getLogger(IndexPage.class);

    }

    @Step("Going to login page")
    public void writeSendMessage(String messageVal){
        FluentWait<WebDriver> wait = new WebDriverWait(driver, 5).ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnQA));
        } catch (TimeoutException e) {
            System.out.print(e);
        }catch (NoSuchElementException e) {
            System.out.print(e);
        }
        btnQA.click();
        txtSendMsg.sendKeys(messageVal);
        btnSend.click();
        btnDown.click();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnLogout));
        } catch (TimeoutException e) {
            System.out.print(e);
        }catch (NoSuchElementException e) {
            System.out.print(e);
        }
        btnLogout.click();

    }
}
