package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
    public WebDriver driver;

    public UserLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public UserHomePO clickToLoginButton() {
        waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getUserHomePage(driver);
    }

    public UserHomePO loginToSystem(String emailAddress, String password) {
        enterToEmailTextbox(emailAddress);
        enterToPasswordTextbox(password);
        clickToLoginButton();
        return PageGenerator.getUserHomePage(driver);
    }
}

