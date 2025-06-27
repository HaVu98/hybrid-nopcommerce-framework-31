package javaOOP;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Polymorphism {
    // Constructor (Hàm khởi tạo)

    public Polymorphism() {

    }

    public Polymorphism(WebDriver webDriver) {

    }

    public Polymorphism(WebDriver webDriver, WebDriverWait explicitWait) {

    }

    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        Actions actions = new Actions(driver);

        actions.clickAndHold();
        actions.clickAndHold(driver.findElement(By.cssSelector("")));

    }
}
