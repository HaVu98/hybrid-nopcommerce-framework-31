package assertVerify;

import commons.GlobalConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestNG_01_Hard_Assert {
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.get("http://live.techpanda.org/customer/account/login/");
    }

    @Test
    public void TC_01_Login_Empty_Email_Password() {
        System.out.println("Step 01 - Input to email textbox");
        driver.findElement(By.id("email")).sendKeys("");

        System.out.println("Step 02 - Input to password textbox");
        driver.findElement(By.id("pass")).sendKeys("");

        System.out.println("Step 03 - Click to Login button");
        driver.findElement(By.id("send2")).click();

        // First Pass (5)
        System.out.println("Step 05 - Verify error message displayed");
        assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");


        // Second Fail (10)
        System.out.println("Step 10 - Verify error message displayed");
        assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field");

        // Pass (45)
        System.out.println("Step 45 - Verify .....");
        assertTrue(driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed());

        // Third Fail (50)
        System.out.println("Step 50 - Verify .....");
        assertTrue(driver.findElement(By.xpath("//button[@id='send_failed_not_found']")).isDisplayed());

        // Pass (65)
        System.out.println("Step 65 - Verify .....");
        assertTrue(driver.findElement(By.xpath("//button[@id='send2']")).isDisplayed());

        // Fourth Fail (76)
        System.out.println("Step 76 - Verify .....");
        assertTrue(driver.findElement(By.xpath("//button[@id='send_failed_not_found']")).isDisplayed());
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
