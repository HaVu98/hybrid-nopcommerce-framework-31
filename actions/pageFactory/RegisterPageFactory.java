package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageFactory extends BasePage {
    private WebDriver driver;

    public RegisterPageFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "gender-male")
    private WebElement genderMaleRadio;

    @FindBy(id = "FirstName")
    private WebElement firstNameTextbox;

    @FindBy(id = "LastName")
    private WebElement lastNameTextbox;

    @FindBy(name = "DateOfBirthDay")
    private WebElement dayDropdown;

    @FindBy(name = "DateOfBirthMonth")
    private WebElement monthDropdown;

    @FindBy(name = "DateOfBirthYear")
    private WebElement yearDropdown;

    @FindBy(id = "Email")
    private WebElement emailTextbox;

    @FindBy(id = "Company")
    private WebElement companyTextbox;

    @FindBy(id = "Password")
    private WebElement passwordTextbox;

    @FindBy(id = "ConfirmPassword")
    private WebElement confirmPasswordTextbox;

    @FindBy(id = "register-button")
    private WebElement registerButton;

    @FindBy(id = "result")
    private WebElement registerSuccessMessage;

    @FindBy(id = "ico-login")
    private WebElement loginLink;

    public void clickToMaleRadio() {
        waitForElementClickable(driver, genderMaleRadio);
        clickToElement(genderMaleRadio);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitForElementVisible(driver, firstNameTextbox);
        sendKeyToElement(firstNameTextbox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitForElementVisible(driver, lastNameTextbox);
        sendKeyToElement(firstNameTextbox, lastName);
     }


    public void selectDayDropdown(String day) {
        waitForElementClickable(driver, dayDropdown);
        selectItemInDropdown(dayDropdown, day);
    }

    public void selectMonthDropdown(String month) {
        waitForElementClickable(driver, monthDropdown);
        selectItemInDropdown(monthDropdown, month);
    }

    public void selectYearDropdown(String year) {
        waitForElementClickable(driver, yearDropdown);
        selectItemInDropdown(yearDropdown, year);
    }

    public void enterToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendKeyToElement(emailTextbox,emailAddress);
    }

    public void enterToCompanyTextbox(String companyName) {
        waitForElementVisible(driver, companyTextbox);
        sendKeyToElement(companyTextbox,companyName);
    }

    public void enterToPasswordTextbox(String password) {
        waitForElementVisible(driver, passwordTextbox);
        sendKeyToElement(passwordTextbox,password);
    }

    public void enterToConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendKeyToElement(confirmPasswordTextbox,password);
    }

    public void clickToRegisterButton() {
        waitForElementClickable(driver, registerButton);
        clickToElement(registerButton);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(registerSuccessMessage);
    }

    public void clickToLoginLink() {
        waitForElementClickable(driver, loginLink);
        clickToElement(loginLink);
    }
}
