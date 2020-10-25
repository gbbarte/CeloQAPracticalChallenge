package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchSessionException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import util.TestUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static constants.Constants.IMPLICIT_WAIT;
import static constants.Constants.PAGE_LOAD_TIMEOUT;

public class BasePage {

    public WebDriver driver;
    public static Properties prop;
    public Logger log;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public WebDriver initialization(){
        //The chromedriver used was for chrome browser version 85.0.4183.83
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        tdriver.set(driver);

        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }
    public Properties readPropertyFile(){
        //read the test date from the property file
        try {
            prop = new Properties();
            FileInputStream fileInputStream = new FileInputStream("./src/main/java/config/config.properties");
            prop.load(fileInputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return prop;
    }

    public void captureScreenshot(String testMethodName) throws Exception {
        String path = prop.getProperty("screenshotsPath");
        final String msg = "Capturing screenshot and saving at " + path;
        WebDriver driver = getDriver();
        try {
            final File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFiler, new File(path + testMethodName + ".jpg"));
            } catch (final IOException e) {
                log.error("Error occurred while capturing screensshot..." + e);
            }
        } catch (final NoSuchSessionException e) {
            throw new Exception("Server Session has been stopped.", e);
        }
    }

}
