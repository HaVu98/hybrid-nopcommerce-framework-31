package com.nopcommerce.users;

import commons.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Random;

public class Level_02_BasePage_II_StaticMethod {
    private WebDriver driver;

    BasePage basePage; //Declare (khai báo đối tượng)

    private String emailAddress;


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        basePage = BasePage.getBasePage();

        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        emailAddress = "thomasmuller" + generateRandomNumber() + "@gmail.de";
    }

    @Test
    public void TC_O1_Register() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-register']");
        basePage.clickToElement(driver,"//a[@class='ico-register']");

        basePage.waitForElementClickable(driver,"//input[@id='gender-male']");
        basePage.clickToElement(driver,"//input[@id='gender-male']");

        basePage.sendKeyToElement(driver,"//input[@id=FirstName']","Thomas");
        basePage.sendKeyToElement(driver,"//input[@id=LastName']","Muller");

        basePage.selectItemInDropdown(driver,"sellect[@name='DateOfBirthDay']", "10");
        basePage.selectItemInDropdown(driver,"sellect[@name='DateOfBirthMonth']", "August");
        basePage.selectItemInDropdown(driver,"sellect[@name='DateOfBirthYear']", "1986");
        basePage.sendKeyToElement(driver,"//input[@id=Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id=Company']","Bayern Munich");
        basePage.sendKeyToElement(driver,"//input[@id=Password']","123456789");
        basePage.sendKeyToElement(driver,"//input[@id=ConfirmPassword']","123456789");

        basePage.waitForElementClickable(driver,"//button[@id='register-button']");
        basePage.clickToElement(driver,"//button[@id='register-button']");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='result']"), "Your registration completed");
        Assert.assertEquals(driver.findElement(By.cssSelector("div.result")).getText(), "Your registration completed");


    }

    @Test
    public void TC_O2_Login() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-login']");
        basePage.clickToElement(driver,"//a[@class='ico-login']");

        basePage.sendKeyToElement(driver,"//input[@id=Email']",emailAddress);
        basePage.sendKeyToElement(driver,"//input[@id=Password']","123456789");

        basePage.waitForElementClickable(driver,"//button[contains(@class='login-button')");
        basePage.clickToElement(driver,"//button[contains(@class='login-button')");

        Assert.assertTrue(basePage.isElementDisplayed(driver,"//a[@class='ico-account' and text()='My account']"));
    }

    @Test
    public void TC_O3_MyAccount() {
        basePage.waitForElementClickable(driver,"//a[@class='ico-account']");
        basePage.clickToElement(driver,"//a[@class='ico-account']");

        Assert.assertTrue(basePage.isElementSelected(driver,"//input[@id='gender-male'"));

        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='FirstName']","value"),"Thomas");
        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='LastName']","value"),"Muller");

        Assert.assertEquals(basePage.getSeclectedItemDropdown(driver,"sellect[@name='DateOfBirthDay']"),"10");
        Assert.assertEquals(basePage.getSeclectedItemDropdown(driver,"sellect[@name='DateOfBirthMonth']"),"August");
        Assert.assertEquals(basePage.getSeclectedItemDropdown(driver,"sellect[@name='DateOfBirthYear']"),"1986");

        Assert.assertEquals(basePage.getElementAttribute(driver,"//input[@id='Company']","value"),"Bayern Munich");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private int generateRandomNumber() {
        return new Random().nextInt(99999);
    }
}
