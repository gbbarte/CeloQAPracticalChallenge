package base;

import org.apache.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;

public class BasePageAction extends BasePage {


    protected final static Logger log = Logger.getLogger(BasePageAction.class);

    public void clickLink(WebElement element) {

        try {
            if (element.isDisplayed() && element.isEnabled())
                element.click();
        } catch (NoSuchElementException e) {
            throw new ElementNotVisibleException("Element not displayed in screen.");
        }
    }


    public void captureScreenshot(String testMethodName) throws Exception {
        String path = prop.getProperty("screenshotsPath");
        final String msg = "Capturing screenshot and saving at " + path;
        log.info(String.format(msg, path));
        try {
            final File srcFiler = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFiler, new File(path + testMethodName + "_"+ ".jpg"));
            } catch (final IOException e) {
                log.error("Error occurred while capturing screensshot..." + e);
            }
        } catch (final NoSuchSessionException e) {
            throw new Exception("Server Session has been stopped.", e);
        }
    }
}
