package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;
import java.util.Random;

public class BaseTest {
    protected WebDriver driver;
    protected final Logger log;

    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }

        protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");

        }
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());
        switch (browserList) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                driver = new ChromeDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Browser name is not valid.");

        }
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        return driver;
    }

    protected int generateRandomNumber() {
        return new Random().nextInt(99999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean status = true;
        try {
            Assert.assertTrue(condition);
        } catch (Exception e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status ;
    }

    protected boolean verifyFail(boolean condition) {
        boolean status = true;
        try {
            Assert.assertFalse(condition);
        } catch (Exception e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status ;
    }

    protected boolean verifyEqual(Object actual, Object expected) {
        boolean status = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Exception e) {
            status = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return status ;
    }
}

