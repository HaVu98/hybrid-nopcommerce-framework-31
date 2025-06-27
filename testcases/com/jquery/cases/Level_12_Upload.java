package com.jquery.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_12_Upload extends BaseTest {
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePage = PageGenerator.getHomePage(driver);

        danang = "DaNang.jpg";
        hanoi = "HaNoi.jpg";
        hochiminh = "HoChiMinh.jpg";
    }


    @Test
    public void Upload_01() {
        // Lấy ra được đường dẫn của file / thư mục cho đúng
        // Tất cả các OS: Window/ MAC/ Linux đều chạy được

        // Có thể upload 1 lần / 1 file => dùng 1 hàm
//        homePage.uploadMultipleFiles(driver, danang);
//        homePage.sleepInSeconds(3);
//        homePage.refreshCurrentPage(driver);

        // Có thể upload 1 lần nhiều file => dùng 1 hàm
        homePage.uploadMultipleFiles(driver, danang, hanoi, hochiminh);
        homePage.sleepInSeconds(3);

        // Verify load file lên
        Assert.assertTrue(homePage.isFileLoadedByName(danang));
        Assert.assertTrue(homePage.isFileLoadedByName(hanoi));
        Assert.assertTrue(homePage.isFileLoadedByName(hochiminh));

        // Click upload button tại từng file
        homePage.clickToUploadButton(driver);

        // Có thể verify 1 file/ nhiều file được upload xong
        Assert.assertTrue(homePage.isFileUploadedByName(danang));
        Assert.assertTrue(homePage.isFileUploadedByName(hanoi));
        Assert.assertTrue(homePage.isFileUploadedByName(hochiminh));


        // Có cần care tới Open File Dialog hay không ?
        // Không cần care - cách đang làm không cần đụng tới Open File Dialog
    }

    @Test
    public void Table_02_Search_And_Verify(){

    }


    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePO homePage;
    private String danang, hanoi, hochiminh;

}
