package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.nopCommerce.PageGenerator;
import pageObjects.nopCommerce.users.UserAddressPageObject;
import pageObjects.nopCommerce.users.UserCustomerInfoPO;
import pageObjects.nopCommerce.users.UserOrderPO;
import pageObjects.nopCommerce.users.UserRewardPointPO;
import pageUIs.jquery.HomePageUI;
import pageUIs.nopCommerce.BasePageUI;
import pageUIs.nopCommerce.users.UserSidebarPageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
//        1 - Access Modifier: public/ protected/ private/ default
//        2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WwbElement>/...
//                    - Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
//        3 – Tên hàm: Đặt tên có ý nghĩa theo chức năng đang cần viết
//            Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
//            camelCase: từ đầu tiên viết thường – chữ cáu đầu tiên của các từ tiếp theo sẽ viết hoa
//        4 – Có tham số hay không (tuỳ vào chức năng cần viết)
//        5 – Kiểu dữ liệu trả về cho hàm (liên quan đến các step mình viết trong hàm đó
//            Nếu như có return dữ liệu thì sẽ khớp với kiểu dữ liệu ở số 2
//            Nếu như có return thì nó là cái step cuối cùng

//

    // Global: biến toàn cục (sinh ra ở phạm vi class)
//    String fullName;
//
//    public String getFullName() {  // Local: Biến cục bộ (sinh ra trong tham số của hàm)
//        // Local: Biến cục bộ (sinh ra trong thân hàm)
//        String fullName = null;
//
//        // Trong phạm vi khối lệnh (block code)
//        for (int i = 0; i < 10; i++) { // Cục bộ
//            int n = 1; // Cục bộ
//        }
//
//        if (n >0) { // Cục bộ
//            int x = 10;
//        }
//        return this.fullName;
//    }

    // Common function (hàm dùng chung) cho nhiều class khác
    public void openPageUrl(WebDriver driver, String url) {
        driver.get(url);
    }

    //openPageURL(driver,"https://demo.nopcommerce.com/")
    //openPageURL(driver,"https://demo.nopcommerce.com/register")


    // không truyền dữ liệu cụ thể riêng tư vào được
    public void openPageUrl(WebDriver driver) {
        driver.get("https://demo.nopcommerce.com/");
        // Url của trang Home
    }

    // Tuân theo nguyên tắc của tính đóng gói (Encapsulation)
    // Hàm static có thể truy cập từ phạm vi class
    public static BasePage getBasePage() {
        return new BasePage();
    }

    public String getPageTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT))
                .until(ExpectedConditions.alertIsPresent());
    }

    public void  acceptToAlert(WebDriver driver) {
        // Chỉ switch không wait
//        driver.switchTo().alert().accept();

        // Wait cho xuất hiện Alert rồi switch vào
        waitAlertPresence(driver).accept();
    }

    public void  cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
    }

    public String  getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void  sendkeyToAlert(WebDriver driver,String keysToSend) {
        waitAlertPresence(driver).sendKeys(keysToSend);
    }

    public void switchToWindowByID(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindow : allWindows) {
            if (!runWindow.equals(parentID)) {
                driver.switchTo().window(runWindow);
                break;
            }
        }
    }

    public void switchToWindowByTitle(WebDriver driver, String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            driver.switchTo().window(runWindows);
            String currentWin = driver.getTitle();
            if (currentWin.equals(title)) {
                break;
            }
        }
    }

    public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
            if (!runWindows.equals(parentID)) {
                driver.switchTo().window(runWindows);
                driver.close();
            }
        }
        driver.switchTo().window(parentID);
    }

    protected WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator) {
        return driver.findElements(getByLocator(locator));
    }

    protected List<WebElement> getListElement(WebDriver driver, String locator, String ... restParameter) {
        return driver.findElements(getByLocator(castParameter(locator,restParameter)));
    }

    private String castParameter(String locator, String ... restParameter) {
        return String.format(locator, (Object[]) restParameter);
    }

    // Truyền tham số vào loại gì sẽ trả về kiểu By tương ứng
    // String prefix: css/ id/ game/ class => By.css/ By.id/ By.name/ ...
    // Convention: css/ Css/ CSS - id/ ID/ Id/ iD
    // css=button#login => By.cssSelector("button#login");
    // Css=button#login => By.cssSelector("button#login");
    // CSS=button#login => By.cssSelector("button#login");
    private By getByLocator(String prefixLocator) {
        By by = null;
        if (prefixLocator.toUpperCase().startsWith("ID")) { // Có thể viết ngắn gọn như thế này. Bên dưới là viết tường minh
            by = By.id(prefixLocator.substring(3));
        } else if (prefixLocator.startsWith("class") || prefixLocator.startsWith("CLASS") || prefixLocator.startsWith("Class")) {
            by = By.className(prefixLocator.substring(6));
        } else if (prefixLocator.startsWith("name") || prefixLocator.startsWith("NAME") || prefixLocator.startsWith("Name")) {
            by = By.name(prefixLocator.substring(5));
        } else if (prefixLocator.startsWith("tagname") || prefixLocator.startsWith("TAGNAME") || prefixLocator.startsWith("Tagname")) {
            by = By.tagName(prefixLocator.substring(8));
        } else if (prefixLocator.startsWith("css") || prefixLocator.startsWith("CSS") || prefixLocator.startsWith("Css")) {
            by = By.cssSelector(prefixLocator.substring(4));
        } else if (prefixLocator.startsWith("xpath") || prefixLocator.startsWith("XPATH")
                || prefixLocator.startsWith("XPath") || prefixLocator.startsWith("Xpath")) {
            by = By.xpath(prefixLocator.substring(6));
        } else {
            throw new RuntimeException("Locator type is not support!!!!");
        }
        System.out.println(by);
        return by;
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String... restParameter) {
        getElement(driver, castParameter(locator, restParameter)).click();
    }

    public void sendKeyToElement(WebDriver driver, String locator, String keysToSend) {
        // Nếu 1 element là thẻ input mà bị ẩn => Lỗi trên firefox
        getElement(driver,locator).clear();
        getElement(driver, locator).sendKeys(keysToSend);
    }

    public void sendKeyToElement(WebDriver driver, String locator, String valueToSendkey, String... restParameter) {
        getElement(driver,castParameter(locator, restParameter)).clear();
        getElement(driver, castParameter(locator, restParameter)).sendKeys(valueToSendkey);
    }


    public void selectItemInDropdown(WebDriver driver, String locator, String textItem) {
        new Select(getElement(driver, locator)).selectByVisibleText(textItem);
    }

    public void selectItemInDropdown(WebDriver driver, String locator, String textItem, String... restParameter) {
        new Select(getElement(driver, castParameter(locator,restParameter))).selectByVisibleText(textItem);
    }

    public String getSeclectedItemDropdown(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropdownMultiple(WebDriver driver, String locator) {
        return new Select(getElement(driver, locator)).isMultiple();
    }

    public void selectItemInCustomDropdown(WebDriver driver, String parentLocator, String childItemLocator, String expectedItem) {
        getElement(driver, parentLocator).click();
        sleepInSeconds(2);

        List<WebElement> allItems = new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childItemLocator)));

        sleepInSeconds(2);
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                item.click();
                break;
            }
        }
    }

    public void sleepInSeconds(long timeInSeconds) {
        try {
            Thread.sleep(timeInSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName) {
        return getElement(driver, locator).getAttribute(attributeName);
    }

    public String getElementAttribute(WebDriver driver, String locator, String attributeName, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getAttribute(attributeName);
    }


    public String getElementText(WebDriver driver, String locator) {
        return getElement(driver, locator).getText();
    }

    public String getElementText(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).getText();
    }

    public String getElementCssValue(WebDriver driver, String locator, String propertyName) {
        return getElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListElement(driver, locator).size();
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator) {
        if (!getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void checkToCheckboxRadio(WebDriver driver, String locator, String... restParameter) {
        if (!getElement(driver, castParameter(locator, restParameter)).isSelected()) {
            getElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator) {
        if (getElement(driver, locator).isSelected()) {
            getElement(driver, locator).click();
        }
    }

    public void uncheckToCheckbox(WebDriver driver, String locator, String... restParameter) {
        if (!getElement(driver, castParameter(locator, restParameter)).isSelected()) {
            getElement(driver, castParameter(locator, restParameter)).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        return getElement(driver, locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isDisplayed();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getElement(driver, locator).isEnabled();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        return getElement(driver, locator).isSelected();
    }

    public boolean isElementSelected(WebDriver driver, String locator, String... restParameter) {
        return getElement(driver, castParameter(locator, restParameter)).isSelected();
    }

    public void switchToIframe(WebDriver driver, String locator) {
        driver.switchTo().frame(getElement(driver, locator));
    }

    public void switchToDefaultPage(WebDriver driver) {
        driver.switchTo().defaultContent();
    }

    public void hoverToElement(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getElement(driver, locator)).perform();
    }

    public void clickToElementByAction(WebDriver driver, String locator) {
        new Actions(driver).click(getElement(driver, locator)).perform();
    }

    public void clickAndHoldToElement(WebDriver driver, String locator) {
        new Actions(driver).clickAndHold(getElement(driver, locator)).perform();
    }

    public void releaseMouse(WebDriver driver) {
        new Actions(driver).release();
    }

    public void DoubleClickToElement(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getElement(driver, locator)).perform();
    }

    public void rightClickToElement(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getElement(driver, locator)).perform();
    }

    // Từ element nguồn đến element đích
    public void dragAndDropToElement(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getElement(driver, sourceLocator),getElement(driver, targetLocator)).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).sendKeys(getElement(driver, locator),keys).perform();
    }

    public void pressKeyToElement(WebDriver driver, String locator, Keys keys, String... restParameter) {
        new Actions(driver).sendKeys(getElement(driver,
                castParameter(locator, restParameter)),keys).perform();
    }

    public void scrollToElement(WebDriver driver, String locator, Keys keys) {
        new Actions(driver).scrollToElement(getElement(driver, locator)).perform();
    }

    public void hightlightElement(WebDriver driver,String locator) {
        WebElement element = getElement(driver, locator);
        String originalStyle = element.getAttribute("style");
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", getElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTopByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", getElement(driver, locator));
    }

    public void scrollToElementOnDownByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", getElement(driver, locator));
    }

    public void scrollToBottomPageByJS(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(driver, locator));
    }

    public String getAttributeInDOMByJS(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].validationMessage;", getElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete " +
                        "&& typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
                getElement(driver, locator));
    }

    public void waitForElementVisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementVisible(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementSelected(WebDriver driver, String locator) {
    new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }

    public void waitForElementSelected(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeSelected(getByLocator(castParameter(locator, restParameter))));
    }

    public void waitForElementPresence(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementInvisible(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator, String... restParameter) {
        new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT)).until(ExpectedConditions.elementToBeClickable(getByLocator(castParameter(locator, restParameter))));
    }

    public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
        // Lấy ra đường dẫn của thư mục upload file
        String filePath = GlobalConstants.UPLOAD_PATH;

        // driver.findElement(uploadBy).sendKeys(hcmFilePath + "\n" + hnFilePath + "\n" + dnFilePath);
        String fullFileName = "";

        // Dùng vòng lặp duyệt qua các file name
        for (String file : fileNames) {
            fullFileName += filePath + file + "\n";
        }

        // Cắt kí tự xuống dòng (\n) ở 2 đầu chuỗi đi
        fullFileName = fullFileName.trim();

        // Sendkey
        getElement(driver, BasePageUI.UPLOAD_FILE_TYPE).sendKeys(fullFileName);
    }

    // Only use for Level_07_Switch_Page_Object
    public UserRewardPointPO openRewardPointPage(WebDriver driver) {
        waitForElementClickable(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInfoPO openCustomerInfoPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }

    public UserAddressPageObject openAddressPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
        clickToElement(driver, BasePageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage(WebDriver driver) {
        waitForElementClickable(driver, BasePageUI.ORDER_LINK);
        clickToElement(driver, BasePageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

}

