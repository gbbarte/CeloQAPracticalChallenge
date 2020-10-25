package listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import static constants.Constants.RETRY_LIMIT;

public class RetryAnalyzer implements IRetryAnalyzer{

    int counter = 0;
    int retryLimit = RETRY_LIMIT;

    public boolean retry(ITestResult iTestResult) {

        if(counter < retryLimit){
            counter++;
            return true;
        }
        return false;
    }
}
