package pageObjects.nopCommerce.users;

import org.openqa.selenium.WebDriver;

public class UserAddressPageObject extends UserSidebarPO {

    private WebDriver driver;

    public UserAddressPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }


}
