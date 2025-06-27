package pageObjects.nopCommerce.admin;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.admin.AdminLoginPageUI;

public class AdminLoginPO extends BasePage {
    WebDriver driver;

    public AdminLoginPO(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,AdminLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,AdminLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public AdminDashboardPO clickToLoginButton() {
        waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,AdminLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getAdminDashboardPage(driver);
    }
}
