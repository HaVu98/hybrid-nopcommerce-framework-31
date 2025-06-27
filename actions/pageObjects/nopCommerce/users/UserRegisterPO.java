package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserRegisterPageUI;

public class UserRegisterPO extends BasePage {
    private WebDriver driver;

    public UserRegisterPO(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToMaleRadio() {
        waitForElementClickable(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
        checkToCheckboxRadio(driver, UserRegisterPageUI.GENDER_MALE_RADIO);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void selectDayDropdown(String day) {
        waitForElementClickable(driver, UserRegisterPageUI.DAY_DROPDOWN);
        selectItemInDropdown(driver, UserRegisterPageUI.DAY_DROPDOWN, day);
    }

    public void selectMonthDropdown(String month) {
        waitForElementClickable(driver, UserRegisterPageUI.MONTH_DROPDOWN);
        selectItemInDropdown(driver, UserRegisterPageUI.MONTH_DROPDOWN, month);
    }

    public void selectYearDropdown(String year) {
        waitForElementClickable(driver, UserRegisterPageUI.YEAR_DROPDOWN);
        selectItemInDropdown(driver, UserRegisterPageUI.YEAR_DROPDOWN, year);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, UserRegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.EMAIL_TEXTBOX, emailAddress);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, UserRegisterPageUI.COMPANY_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver, UserRegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(driver, UserRegisterPageUI.REGISTER_BUTTON);
    }


    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }


    public UserLoginPageObject openLoginPage() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGIN_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGIN_LINK);
        return PageGenerator.getUserLoginPage(driver);
    }

    public UserHomePO clickToLogoutLink() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGOUT_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGOUT_LINK);
        return PageGenerator.getUserHomePage(driver);
    }

    public String getRegisterPageTitle() {
        waitForElementVisible(driver, UserRegisterPageUI.REGISTER_PAGE_TITLE);
        return getElementText(driver, UserRegisterPageUI.REGISTER_PAGE_TITLE);
    }
}

