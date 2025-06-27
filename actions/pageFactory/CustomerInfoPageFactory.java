package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerInfoPageFactory extends BasePage {
    private WebDriver driver;

    public CustomerInfoPageFactory(WebDriver driver) {
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

    public boolean isGenderMaleSelected() {
        waitForElementVisible(driver, genderMaleRadio);
        return isElementSelected(genderMaleRadio);
    }

    public String getFirstNameTextboxValue() {
        waitForElementVisible(driver, firstNameTextbox);
        return getElementAttribute(firstNameTextbox, "value");
    }

    public String getLastNameTextboxValue() {
        waitForElementVisible(driver, lastNameTextbox);
        return getElementAttribute(lastNameTextbox, "value");
    }

    public String getDayDropdownSelectedValue() {
        waitForElementClickable(driver, dayDropdown);
        return getSelectedItemInDropdown(dayDropdown);
    }

    public String getMonthDropdownSelectedValue() {
        waitForElementClickable(driver, monthDropdown);
        return getSelectedItemInDropdown(monthDropdown);
    }

    public String getYearNameTextboxValue() {
        waitForElementClickable(driver, yearDropdown);
        return getSelectedItemInDropdown(yearDropdown);
    }

    public String getEmailTextboxValue() {
        waitForElementVisible(driver, emailTextbox);
        return getElementAttribute(emailTextbox, "value");
    }

    public String getCompanyTextboxValue() {
        waitForElementVisible(driver, companyTextbox);
        return getElementAttribute(companyTextbox, "value");
    }
}
