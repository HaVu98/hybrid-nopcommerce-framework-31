package pageObjects.nopCommerce;

import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.admin.AdminDashboardPO;
import pageObjects.nopCommerce.admin.AdminLoginPO;
import pageObjects.nopCommerce.users.*;

public class PageGenerator {
    public static UserHomePO getUserHomePage(WebDriver driver) {
        return new UserHomePO(driver);
    }

    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    public static UserRegisterPO getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPO(driver);
    }

    public static UserCustomerInfoPO getUserCustomerPage(WebDriver driver) {
        return new UserCustomerInfoPO(driver);
    }

    public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
        return new UserAddressPageObject(driver);
    }

    public static UserOrderPO getUserOrderPage(WebDriver driver) {
        return new UserOrderPO(driver);
    }

    public static UserRewardPointPO getUserRewardPointPage(WebDriver driver) {
        return new UserRewardPointPO(driver);
    }

    public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPO(driver);
    }

    public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPO(driver) ;
    }
}
