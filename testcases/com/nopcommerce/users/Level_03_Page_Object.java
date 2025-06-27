package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPageObject;
import pageObjects.nopCommerce.users.UserRegisterPO;

import java.time.Duration;

public class Level_03_Page_Object extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(Level_03_Page_Object.class);
    // Declare Variables
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPO customerInfoPage;

    private String firstName, lastName, day, month, year, emailAddress, companyName, password;

    // Pre-Condition
    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();

        // Mở URL lên -> qua Home Page
        driver.get("https://demo.nopcommerce.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        // Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new UserHomePO(driver);

        firstName = "John";
        lastName = "Philip";
        day = "29";
        month = "3";
        year = "1996";
        emailAddress = "john" + generateRandomNumber() + "@gmail.de";
        companyName = "Continental";
        password = "12345678";
    }

    // Testcases
    @Test
    public void User_01_Register() {
        // Action 1
        homePage.openRegisterPage();

        // Từ Home Page qua Register Page
        // Page đó được sinh ra và bắt đầu làm những action của page đó
        registerPage = new UserRegisterPO(driver);

        registerPage.clickToMaleRadio();

        registerPage.enterToFirstNameTextbox(firstName);
        registerPage.enterToLastNameTextbox(lastName);
        registerPage.selectDayDropdown(day);
        registerPage.selectMonthDropdown(month);
        registerPage.selectYearDropdown(year);
        registerPage.enterToEmailTextbox(emailAddress);
        registerPage.enterToCompanyTextbox(companyName);
        registerPage.enterToPasswordTextbox(password);
        registerPage.enterToConfirmPasswordTextbox(password);
        registerPage.clickToRegisterButton();

        Assert.assertEquals(registerPage.getRegisterSuccessMessage(),"Your registration completed");
    }

    public void User_02_Login(){
        registerPage.openLoginPage();

        // Từ Register Page qua Login Page
        // Page đó được sinh ra và bắt đầu làm những action của page đó
        loginPage = new UserLoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        // Từ Login Page qua Home Page
        // Page đó được sinh ra và bắt đầu làm những action của page đó
        homePage = new UserHomePO(driver);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    public void User_03_MyAccount() {
        homePage.openCustomerInfoPage();

        // Từ Home Page qua Customer Info Page
        // Page đó được sinh ra và bắt đầu làm những action của page đó

        customerInfoPage = new UserCustomerInfoPO(driver);
        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(),day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(),month);
        Assert.assertEquals(customerInfoPage.getYearNameTextboxValue(),year);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);



    }

    // Post-Condition
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
