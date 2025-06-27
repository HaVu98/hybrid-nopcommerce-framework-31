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
import pageObjects.nopCommerce.admin.AdminDashboardPO;
import pageObjects.nopCommerce.admin.AdminLoginPO;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserHomePO;
import pageObjects.nopCommerce.users.UserLoginPageObject;
import pageObjects.nopCommerce.users.UserRegisterPO;

public class Level_09_Switch_Site_Url extends BaseTest {


    @Parameters({"browser", "userUrl", "adminUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String userUrl, String adminUrl) {
        userUrlValue = userUrl;
        adminUrlValue = adminUrl;

        driver = getBrowserDriver(browserName, userUrl);

        userHomePage = PageGenerator.getUserHomePage(driver);

        firstName = "John";
        lastName = "Philip";
        day = "29";
        month = "3";
        year = "1996";
        emailAddress = "john" + generateRandomNumber() + "@gmail.de";
        companyName = "Continental";
        password = "12345678";

        adminEmailAddress = "admin@yourstore.com";
        adminPassword = "admin";

        // Pre-Condition
        userRegisterPage = userHomePage.openRegisterPage();

        userRegisterPage.clickToMaleRadio();
        userRegisterPage.enterToFirstNameTextbox(firstName);
        userRegisterPage.enterToLastNameTextbox(lastName);
        userRegisterPage.selectDayDropdown(day);
        userRegisterPage.selectMonthDropdown(month);
        userRegisterPage.selectYearDropdown(year);
        userRegisterPage.enterToEmailTextbox(emailAddress);
        userRegisterPage.enterToCompanyTextbox(companyName);
        userRegisterPage.enterToPasswordTextbox(password);
        userRegisterPage.enterToConfirmPasswordTextbox(password);
        userRegisterPage.clickToRegisterButton();

        Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(),"Your registration completed");

        userHomePage = userRegisterPage.clickToLogoutLink();
    }

    @Test
    public void Role_01_User_Site_Admin_Site() {
        userLoginPage = userHomePage.openLoginPage();

        userHomePage = userLoginPage.loginToSystem(emailAddress,password);

        Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());

        // Step để order 1 product nào đó
        // ...

        // Qua trang Admin để verify/ appprove cái order ở trên với quyền Admin
        userHomePage.openPageUrl(driver, adminUrlValue);
        adminLoginPage = PageGenerator.getAdminLoginPage(driver);

        // Login vào trang Admin
        adminLoginPage.enterToEmailTextbox(adminEmailAddress);
        adminLoginPage.enterToPasswordTextbox(adminPassword);
        adminDashboardPage = adminLoginPage.clickToLoginButton();




        // Đã login trước đó rồi
        adminDashboardPage = PageGenerator.getAdminDashboardPage(driver);
    }

    public void Role_02_Admin_Site_Admin_Site(){
        // Vào trang Order / Customer / ...
        // ...
        adminDashboardPage.openPageUrl(driver,userUrlValue);
        userHomePage = PageGenerator.getUserHomePage(driver);

        // Action các step tiếp theo
        //

        userCustomerInfoPage = userHomePage.openCustomerInfoPage();

        Assert.assertTrue(userCustomerInfoPage.isGenderMaleSelected());
        Assert.assertEquals(userCustomerInfoPage.getFirstNameTextboxValue(),firstName);
        Assert.assertEquals(userCustomerInfoPage.getLastNameTextboxValue(),lastName);
        Assert.assertEquals(userCustomerInfoPage.getDayDropdownSelectedValue(),day);
        Assert.assertEquals(userCustomerInfoPage.getMonthDropdownSelectedValue(),month);
        Assert.assertEquals(userCustomerInfoPage.getYearNameTextboxValue(),year);
        Assert.assertEquals(userCustomerInfoPage.getEmailTextboxValue(),emailAddress);
        Assert.assertEquals(userCustomerInfoPage.getCompanyTextboxValue(),companyName);
    }

    public void User_03_MyAccount() {

    }

    public void User_04_Switch_Page() {

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private static final Logger log = LoggerFactory.getLogger(Level_09_Switch_Site_Url.class);
    private WebDriver driver;
    private UserHomePO userHomePage;
    private UserRegisterPO userRegisterPage;
    private UserLoginPageObject userLoginPage;
    private UserCustomerInfoPO userCustomerInfoPage;
    private AdminLoginPO adminLoginPage;
    private AdminDashboardPO adminDashboardPage;
    private String firstName, lastName, day, month, year, emailAddress, companyName, password;
    private String userUrlValue, adminUrlValue, adminEmailAddress, adminPassword;
}
