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
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.*;

public class Level_13_Verify extends BaseTest {
    @Parameters("browser")
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        homePage = PageGenerator.getUserHomePage(driver);

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
        registerPage = homePage.openRegisterPage();

        // Assert 01 ==> FAILED
        verifyEqual(registerPage.getRegisterPageTitle(),"REGISTER");


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

        // Assert 02
        verifyEqual(registerPage.getRegisterSuccessMessage(), "Your registration completed!!!");

        homePage = registerPage.clickToLogoutLink();
    }

    public void User_02_Login(){
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginToSystem(emailAddress,password);

        verifyTrue(homePage.isMyAccountLinkDisplayed());
    }

    public void User_03_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();

        verifyTrue(customerInfoPage.isGenderMaleSelected());
        verifyEqual(customerInfoPage.getFirstNameTextboxValue(),firstName);
        verifyEqual(customerInfoPage.getLastNameTextboxValue(),lastName);
        verifyEqual(customerInfoPage.getDayDropdownSelectedValue(),day);
        verifyEqual(customerInfoPage.getMonthDropdownSelectedValue(),month);
        verifyEqual(customerInfoPage.getYearNameTextboxValue(),year);
        verifyEqual(customerInfoPage.getEmailTextboxValue(),emailAddress);
        verifyEqual(customerInfoPage.getCompanyTextboxValue(),companyName);
    }

    // Only use for Level_08_Page_Navigation
    public void User_04_Dynamic_Page() {
        // Customer Info -> Address
        addressPage = (UserAddressPageObject) customerInfoPage.openSidebarLinkByPageName("Addresses");

        // Address -> Reward Point
        rewardPointPage = (UserRewardPointPO) addressPage.openSidebarLinkByPageName("Reward points");

        // Reward Point -> Order
        orderPage = (UserOrderPO) rewardPointPage.openSidebarLinkByPageName("Orders");

        // Order -> Address
        addressPage = (UserAddressPageObject) orderPage.openSidebarLinkByPageName("Addresses");

        // Address -> Customer Infor
        customerInfoPage = (UserCustomerInfoPO) addressPage.openSidebarLinkByPageName("Customer info");

        rewardPointPage = (UserRewardPointPO) customerInfoPage.openSidebarLinkByPageName("Reward points");

        addressPage = (UserAddressPageObject) rewardPointPage.openSidebarLinkByPageName("Addresses");
    }

    public void User_05_Dynamic_Page() {
        // Address -> Reward Point
        addressPage.openSidebarLinkByPageNames("Reward points");
        rewardPointPage = PageGenerator.getUserRewardPointPage(driver);

        // Reward Point -> Order
        rewardPointPage.openSidebarLinkByPageNames("Orders");
        orderPage = PageGenerator.getUserOrderPage(driver);

        // Order -> Address
        orderPage.openSidebarLinkByPageNames("Addresses");
        addressPage = PageGenerator.getUserAddressPage(driver);

        // Address -> Customer Infor
        addressPage.openSidebarLinkByPageNames("Customer info");
        customerInfoPage = PageGenerator.getUserCustomerPage(driver);

        customerInfoPage.openSidebarLinkByPageNames("Reward points");
        rewardPointPage = PageGenerator.getUserRewardPointPage(driver);

        rewardPointPage.openSidebarLinkByPageNames("Addresses");
        addressPage = PageGenerator.getUserAddressPage(driver);

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private static final Logger log = LoggerFactory.getLogger(Level_13_Verify.class);
    private WebDriver driver;
    private UserHomePO homePage;
    private UserRegisterPO registerPage;
    private UserLoginPageObject loginPage;
    private UserCustomerInfoPO customerInfoPage;
    private UserAddressPageObject addressPage;
    private UserOrderPO orderPage;
    private UserRewardPointPO rewardPointPage;
    private String firstName, lastName, day, month, year, emailAddress, companyName, password;
}
