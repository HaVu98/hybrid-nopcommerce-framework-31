package pageObjects.jquery;

import commons.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.jquery.HomePageUI;

import java.util.ArrayList;
import java.util.List;

public class HomePO  extends BasePage {
    WebDriver driver;

    public HomePO(WebDriver driver) {
        this.driver= driver;
    }


    public void openPageByNumber(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK,pageNumber);
        clickToElement(driver,HomePageUI.DYNAMIC_PAGE_LINK, pageNumber);
        sleepInSeconds(2);
    }

    public boolean isPageNumberActived(String pageNumber) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_PAGE_LINK,pageNumber);
        return getElementAttribute(driver, HomePageUI.DYNAMIC_PAGE_LINK,"class", pageNumber)
                .endsWith("active");
    }

    public void enterToTextboxByHeaderName(String headerName, String valueToSendkey) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, headerName);
        sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, valueToSendkey, headerName);
        pressKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_HEADER_NAME, Keys.ENTER, headerName);
    }

    public boolean isRowDataValueDisplayed(String female, String country, String male, String total) {
        waitForElementVisible(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
        return isElementDisplayed(driver, HomePageUI.DYNAMIC_DATA_ROW, female, country, male, total);
    }

    public void deleteRowByCountryName(String countryName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY_NAME,countryName);
        clickToElement(driver,HomePageUI.DYNAMIC_DELETE_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSeconds(2);
    }

    public void editRowByCountryName(String countryName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME,countryName);
        clickToElement(driver,HomePageUI.DYNAMIC_EDIT_BUTTON_BY_COUNTRY_NAME, countryName);
        sleepInSeconds(2);
    }

    public void clickToLoadDataButton() {
        waitForElementClickable(driver, HomePageUI.LOAD_DATA_BUTTON);
        clickToElement(driver,HomePageUI.LOAD_DATA_BUTTON);
    }

    public void enterToTextboxByIndex(String rowIndex, String columnName, String valueToSendkey) {
        // Từ column name làm sao để lấy ra được column index?
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text (String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị : rowIndex/ columnIndex vào locator để tương tác và sendkey
        sendKeyToElement(driver, HomePageUI.DYNAMIC_TEXTBOX_BY_ROW_AND_COLUMN_INDEX, valueToSendkey, rowIndex, columnIndex);
    }

    public void selectToDropdownByIndex(String rowIndex, String columnName, String valueToSelect) {
        // Từ column name làm sao để lấy ra được column index?
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text (String)
        String columnIndex = String.valueOf(columnIndexNumber);

        // Truyền 2 giá trị : rowIndex/ columnIndex vào locator để tương tác và select dropdown
        selectItemInDropdown(driver, HomePageUI.DYNAMIC_DROPDOWN_BY_ROW_AND_COLUMN_INDEX, valueToSelect, rowIndex, columnIndex);
    }

    public void checkToCheckboxByIndex(String rowIndex, String columnName, boolean checkOrUncheck) {
        // Từ column name làm sao để lấy ra được column index?
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER, columnName).size() + 1;

        // Convert nó qua dạng text (String)
        String columnIndex = String.valueOf(columnIndexNumber);

        if (checkOrUncheck) {
            checkToCheckboxRadio(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        } else {
            uncheckToCheckbox(driver, HomePageUI.DYNAMIC_CHECKBOX_BY_ROW_AND_COLUMN_INDEX, rowIndex, columnIndex);
        }
    }

    public void clickToIconByIndex(String rowIndex, String iconName) {
        waitForElementClickable(driver, HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, rowIndex, iconName);
        clickToElement(driver,HomePageUI.DYNAMIC_ICON_BY_ROW_INDEX, iconName);
    }

    public List<String> getAllValueAtColumnName(String columnName) {
        // Từ column name làm sao để lấy ra được column index?
        int columnIndexNumber = getListElement(driver, HomePageUI.DYNAMIC_PRECEDING_SIBLING_COLUMN_NUMBER_2, columnName).size() + 1;

        // Convert nó qua dạng text (String)
        String columnIndex = String.valueOf(columnIndexNumber);

        List<WebElement> allElementValueAtColumn = getListElement(driver,HomePageUI.ALL_VALUE_BY_COLUMN_INDEX, columnIndex);
        List<String> allTextValue = new ArrayList<String>();
        for (WebElement element: allElementValueAtColumn) {
            allTextValue.add(element.getText());
        }
        return allTextValue;
    }

    public boolean isFileLoadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_LOAD_BY_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_LOAD_BY_FILE_NAME, fileName);
    }

    public void clickToUploadButton(WebDriver driver) {
        List<WebElement> startButtons = getListElement(driver, HomePageUI.UPLOAD_BUTTON);
        for (WebElement button : startButtons) {
            button.click();
            sleepInSeconds(3);
        }
    }

    public boolean isFileUploadedByName(String fileName) {
        waitForElementVisible(driver, HomePageUI.FILE_UPLOAD_SUCCESS_BY_FILE_NAME, fileName);
        return isElementDisplayed(driver, HomePageUI.FILE_UPLOAD_SUCCESS_BY_FILE_NAME, fileName);
    }
}
