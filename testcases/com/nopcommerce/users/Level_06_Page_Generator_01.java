package com.nopcommerce.users;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPageObject;
import pageObjects.nopCommerce.users.UserRegisterPO;

public class Level_06_Page_Generator_01 extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

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

    @Test
    public void User_01_Register() {
        homePage.openRegisterPage(); // page A: HomePage -> Register

        // Thể hiện trực tiếp trên Test Class
        // Vi phạm nguyên tắc thiết kế
        registerPage = new UserRegisterPO(driver); // page B: Register

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

        loginPage = new UserLoginPageObject(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = new UserHomePO(driver);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    public void User_03_MyAccount() {
        homePage.openCustomerInfoPage();

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

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private static final Logger log = LoggerFactory.getLogger(Level_06_Page_Generator_01.class);
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPO customerInfoPage;

    private String firstName, lastName, day, month, year, emailAddress, companyName, password;
}
