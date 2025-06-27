package com.nopcommerce.users;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_01_Repeat_Yourself {
    private WebDriver driver;

    private String firstName, lastName, day, month, year, emailAddress, companyName, password;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        firstName = "Thomas";
        lastName = "Muller";
        day = "10";
        month = "August";
        year = "1986";
        emailAddress = "thomasmuller" + generateRandomNumber() + "@gmail.de";
        companyName = "Bayern Murnich";
        password = "123456789";
    }

    @Test
    public void TC_O1_Register() {
        driver.findElement(By.cssSelector("a.ico-register")).click();
        driver.findElement(By.cssSelector("input#gender-male")).click();
        driver.findElement(By.cssSelector("input#FirstName")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#LastName")).sendKeys(lastName);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).selectByVisibleText(day);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).selectByVisibleText(year);
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Company")).sendKeys(companyName);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#ConfirmPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("button#register-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");


    }

    @Test
    public void TC_O2_Login() {
        driver.findElement(By.cssSelector("a.ico-login")).click();
        driver.findElement(By.cssSelector("input#Email")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#Password")).sendKeys(password);
        driver.findElement(By.cssSelector("button.login-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//a[@class='ico-account' and text()='My account']")).isDisplayed());
    }

    @Test
    public void TC_O3_MyAccount() {
        driver.findElement(By.cssSelector("a.ico-account")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("input#gender-male")).isSelected());

        Assert.assertEquals(driver.findElement(By.cssSelector("input#FirstName")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.cssSelector("input#LastName")).getAttribute("value"), lastName);

        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthDay']"))).getFirstSelectedOption().getText(), day);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(), month);
        Assert.assertEquals(new Select(driver.findElement(By.cssSelector("select[name='DateOfBirthYear']"))).getFirstSelectedOption().getText(), year);

        Assert.assertEquals(driver.findElement(By.cssSelector("input#Company")).getAttribute("value"), companyName);


    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
