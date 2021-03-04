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
import pages.SetPinPage;
//import pages.KiwiSaverCalculatorPage;
//import pages.KiwiSaverPage;

import java.util.Properties;

import static constants.Constants.*;
import static util.TestUtil.getTestData;

//Glenn Barte
//bt2
public class TC1_LoginToApplication {
    public BasePage basePage;
    public WebDriver driver;
    public Properties prop;
    public IndexPage indexPage;
    public LoginPage loginPage;
    public SetPinPage setPinPage;


    @BeforeMethod // this method will be executed before every @test method
    public void setUp() {
        basePage = new BasePage();
        prop = basePage.readPropertyFile();
        driver = basePage.initialization();
        driver.get(prop.getProperty("url"));
        indexPage = new IndexPage(driver);
        loginPage = new LoginPage(driver);
        setPinPage = new SetPinPage(driver);
    }

    @Test(priority = 1, description = "Verifying index page title test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verifying the title of index Page")
    @Story("Story Name: To check index page title")
    public void verifyingIndexPageTitleTest() {
        String actualTitlePage = indexPage.getPageTitle();
        String expectedTitlePage = HOME_PAGE_TITLE;
        Assert.assertEquals(actualTitlePage, expectedTitlePage);

    }

    @Test(priority = 2, description = "Verifying login button test")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify that the login button is displayed and enabled on Index Page")
    @Story("Story Name: To login button")
    public void verifyLoginButtonTest() {
        Assert.assertTrue(indexPage.verifyLoginButton());
    }

    @Test(priority = 3, description = "Redirect to login page")
    @Severity(SeverityLevel.NORMAL)
    @Description("Test Case Description: Verify that the user goes to login page")
    @Story("Story Name: To check if the user redirects to login page")
    public void gotoLoginPageTest() {
        loginPage = indexPage.gotoLoginPage();
        String actualTitlePage = loginPage.getPageTitle();
        String expectedTitlePage = LOGIN_PAGE_TITLE;
        Assert.assertEquals(actualTitlePage, expectedTitlePage);

    }

    @DataProvider
    public Object[][] getLoginInfo() {
        Object tc1Data[][] = getTestData(TC1_SHEET_NAME);
        return tc1Data;
    }

    @Test(dataProvider = "getLoginInfo", priority = 4, description = "Verifying to Login to the application (TC1)")
    @Severity(SeverityLevel.NORMAL)
    @Description("Acceptance Criteria 1(AC1): User can login to the application using valid data that are read from an excel file ")
    @Story("Story Name: The user with valid credentials can login and redirect to setup pin page ")
    public void verifyingAcceptanceCriteria1(String email, String password, String pin, String message) {
        loginPage = indexPage.gotoLoginPage();
        setPinPage = loginPage.inputLoginData(email,password);
        String actualTitlePage = setPinPage.getPageTitle();
        String expectedTitlePage = SETPIN_PAGE_TITLE;
        Assert.assertEquals(actualTitlePage, expectedTitlePage);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}






