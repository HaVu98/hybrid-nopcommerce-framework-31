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

public class Level_08_Page_Navigation extends BaseTest {
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
        loginPage = homePage.openLoginPage();
        homePage = loginPage.loginToSystem(emailAddress,password);

        Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
    }

    public void User_03_MyAccount() {
        customerInfoPage = homePage.openCustomerInfoPage();

        Assert.assertTrue(customerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(customerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(customerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(customerInfoPage.getDayDropdownSelectedValue(),day);
        Assert.assertEquals(customerInfoPage.getMonthDropdownSelectedValue(),month);
        Assert.assertEquals(customerInfoPage.getYearNameTextboxValue(),year);
        Assert.assertEquals(customerInfoPage.getEmailTextboxValue(),emailAddress);
        Assert.assertEquals(customerInfoPage.getCompanyTextboxValue(),companyName);
    }

    public void User_04_Switch_Page() {
        // Customer Info -> Address
        addressPage = customerInfoPage.openAddressPage();
        // Add new Address

        // Address -> Reward Point
        rewardPointPage = addressPage.openRewardPointPage();

        // Reward Point -> Order
        orderPage = rewardPointPage.openOrderPage();

        // Order -> Address
        addressPage =orderPage.openAddressPage();

        // Address -> Customer Infor
        customerInfoPage = addressPage.openCustomerInfoPage();

        // Page A chuyển qua page B
        // Cái hàm để mở page B sẽ nằm trong page A
        // Cần khởi tạo page B lên và gán lại cho page B để chạy tiếp
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private static final Logger log = LoggerFactory.getLogger(Level_08_Page_Navigation.class);
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
