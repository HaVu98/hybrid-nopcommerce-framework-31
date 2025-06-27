package javaBasic;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

// final class
public final class Topic_02_Loop { // final: không cho class khác kế thừa nó

    // final: không cho phép gán lại giá trị mới
    // final class
    public static final String PI = "3.14187583475";

    // final method
    public final void clickToElement() { // không cho các class khác overide lại

    }

    // throw: dùng trong Try-Catch
    // throws: dùng trên các hàm

    public static void clickToButton() throws InterruptedException {
        Thread.sleep(5000);
    }

    public static void main(String[] args) {
        Topic_02_Loop topic02 = new Topic_02_Loop();
        topic02.clickToElement();
//        Topic_02_Loop.clickToButton();
        System.out.println(Topic_02_Loop.PI);

        System.out.println("For:");
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                System.out.println(i);
                continue;
            }
        }

        System.out.println("White:");
        int i = 0;
        while (i <= 10) { // Kiểm tra trước
            System.out.println(i); // Action sau
            i++;
        }

        System.out.println("Do-White:");
        i = 1;
        do { // Action trước
            System.out.println(i);
            i++;
        } while (i <= 10); // Kiểm tra sau
    }

    public boolean isElementDisplayed() {
        WebDriver driver = new FirefoxDriver();
        WebElement element = driver.findElement(By.cssSelector(""));
        boolean status = false;
        try {
            status = element.isDisplayed();
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            throw new RuntimeException(exception.getMessage());
        } finally { // Step bắt buộc phải chạy
            // Đóng kết nối vào DB / Clean dữ liệu
        }
        return status;
    }

}
