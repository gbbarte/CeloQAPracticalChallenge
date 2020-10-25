package testcases;

import base.BasePage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.IndexPage;
import pages.LoginPage;
import pages.MessagesPage;
import pages.SetPinPage;

import java.util.Properties;

import static constants.Constants.*;
import static util.TestUtil.*;

public class TC2_SetupPinAndSendMessage {
    public BasePage basePage;
    public WebDriver driver;
    public Properties prop;
    public IndexPage indexPage;
    public LoginPage loginPage;
    public SetPinPage setPinPage;
    public MessagesPage messagesPage;

    @BeforeMethod // this method will be executed before every @test method
    public void setUp() {
        basePage = new BasePage();
        prop = basePage.readPropertyFile();
        driver = basePage.initialization();
        driver.get(prop.getProperty("url"));
        indexPage = new IndexPage(driver);
        loginPage = new LoginPage(driver);
        setPinPage = new SetPinPage(driver);
        messagesPage = new MessagesPage(driver);
    }


    @DataProvider
    public Object[][] getLoginInfo() {
        Object tc1Data[][] = getTestData(TC1_SHEET_NAME);
        return tc1Data;
    }

    @Test(dataProvider = "getLoginInfo", priority = 1, description = "Verifying that user can set PIN, send message then logout")
    @Severity(SeverityLevel.NORMAL)
    @Description("Acceptance Criteria 1(AC2): User after logging, can can set PIN, send message then logout")
    @Story("Story Name: The user can set PIN, send message then logout")
    public void verifyingAcceptanceCriteria2(String email, String password, String pin, String message) {
        loginPage = indexPage.gotoLoginPage();
        setPinPage = loginPage.inputLoginData(email,password);
        messagesPage = setPinPage.setPIN(pin);
        messagesPage.writeSendMessage(message);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
