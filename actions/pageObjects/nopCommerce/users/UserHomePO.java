package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserHomePageUI;
import pageUIs.nopCommerce.users.UserRegisterPageUI;

public class UserHomePO extends BasePage {
    private WebDriver driver;


    public UserHomePO(WebDriver driver) {
        this.driver = driver;
    }

    // Hàm khởi tạo (Constructor function)
    // 1 - Cùng tên với tên class
    // 2 - Không có kiểu trả về (Data Type)
    // 3 - Chạy đầu tiên khi Class này gọi (new HomePage Object)
    // 4 - Có tham số hoặc không
    // 5 - Không tự define hàm khởi tạo thì JVM sẽ mặc định tạo ra 1 hàm khởi tạo rỗng

    public UserRegisterPO openRegisterPage() {
        // Level 1
//        driver.findElement(By.xpath("//a[@class='ico-register']")).click();

        // Level 2
//        waitForElementClickable(driver,"//a[@class='ico-register']");
//        clickToElement(driver,"//a[@class='ico-register']");

        //Level 3
        waitForElementClickable(driver, UserHomePageUI.REGISTER_LINK);
        clickToElement(driver, UserHomePageUI.REGISTER_LINK);
        return PageGenerator.getUserRegisterPage(driver);
    }

    public boolean isMyAccountLinkDisplayed() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(driver, UserHomePageUI.MY_ACCOUNT_LINK);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementVisible(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        clickToElement(driver, UserHomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }

    public UserLoginPageObject openLoginPage() {
        waitForElementClickable(driver, UserRegisterPageUI.LOGIN_LINK);
        clickToElement(driver, UserRegisterPageUI.LOGIN_LINK);
        return PageGenerator.getUserLoginPage(driver);
    }
}
