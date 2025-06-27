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
import pageFactory.CustomerInfoPageFactory;
import pageFactory.HomePageFactory;
import pageFactory.LoginPageFactory;
import pageFactory.RegisterPageFactory;

public class Level_05_PageFactory extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        homePage = new HomePageFactory(driver);

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
        homePage.clickToRegisterLink();

        registerPage = new RegisterPageFactory(driver);

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
        registerPage.clickToLoginLink();

        loginPage = new LoginPageFactory(driver);

        loginPage.enterToEmailTextbox(emailAddress);
        loginPage.enterToPasswordTextbox(password);
        loginPage.clickToLoginButton();

        homePage = new HomePageFactory(driver);
        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    public void User_03_MyAccount() {
        homePage.clickToMyAccountLink();

        customerInfoPage = new CustomerInfoPageFactory(driver);
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

    private static final Logger log = LoggerFactory.getLogger(Level_05_PageFactory.class);
    private WebDriver driver;
    private HomePageFactory homePage;
    private RegisterPageFactory registerPage;
    private LoginPageFactory loginPage;
    private CustomerInfoPageFactory customerInfoPage;

    private String firstName, lastName, day, month, year, emailAddress, companyName, password;
}
