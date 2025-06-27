package pageObjects.nopCommerce.users;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.nopCommerce.PageGenerator;
import pageUIs.nopCommerce.users.UserSidebarPageUI;

public class UserSidebarPO extends BasePage {
    WebDriver driver;

    public UserSidebarPO(WebDriver driver) {
        this.driver = driver;
    }

    public UserRewardPointPO openRewardPointPage() {
        waitForElementClickable(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        clickToElement(driver, UserSidebarPageUI.REWARD_POINT_LINK);
        return PageGenerator.getUserRewardPointPage(driver);
    }

    public UserCustomerInfoPO openCustomerInfoPage() {
        waitForElementClickable(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        clickToElement(driver, UserSidebarPageUI.CUSTOMER_INFO_LINK);
        return PageGenerator.getUserCustomerPage(driver);
    }

    public UserAddressPageObject openAddressPage() {
        waitForElementClickable(driver, UserSidebarPageUI.ADDRESS_LINK);
        clickToElement(driver, UserSidebarPageUI.ADDRESS_LINK);
        return PageGenerator.getUserAddressPage(driver);
    }

    public UserOrderPO openOrderPage() {
        waitForElementClickable(driver, UserSidebarPageUI.ORDER_LINK);
        clickToElement(driver, UserSidebarPageUI.ORDER_LINK);
        return PageGenerator.getUserOrderPage(driver);
    }

    // Phù hợp cho số lượng page ít - switch case ít
    public UserSidebarPO openSidebarLinkByPageName(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUI.DYNAMIC_PAGE_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_PAGE_LINK_BY_PAGE_NAME, pageName);

        switch (pageName) {
            case "Addresses":
                return PageGenerator.getUserAddressPage(driver);
            case "Reward points":
                return PageGenerator.getUserRewardPointPage(driver);
            case "Customer info":
                return PageGenerator.getUserCustomerPage(driver);
            case "Orders":
                return PageGenerator.getUserOrderPage(driver);
            default:
                throw new RuntimeException("Page name is not valid!!!");
        }
    }

    public void openSidebarLinkByPageNames(String pageName) {
        waitForElementClickable(driver, UserSidebarPageUI.DYNAMIC_PAGE_LINK_BY_PAGE_NAME, pageName);
        clickToElement(driver, UserSidebarPageUI.DYNAMIC_PAGE_LINK_BY_PAGE_NAME, pageName);

    }

}
