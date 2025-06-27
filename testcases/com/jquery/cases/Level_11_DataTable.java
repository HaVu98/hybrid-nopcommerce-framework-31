package com.jquery.cases;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pageObjects.jquery.HomePO;
import pageObjects.jquery.PageGenerator;

public class Level_11_DataTable extends BaseTest {
    @Parameters({"browser", "url"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePage = PageGenerator.getHomePage(driver);
    }


//    @Test
    public void Table_01_Paging() {
        // Navigate to any page (paging) in table
        homePage.openPageByNumber("15");
        Assert.assertTrue(homePage.isPageNumberActived("15"));

        homePage.openPageByNumber("5");
        Assert.assertTrue(homePage.isPageNumberActived("5"));

        homePage.openPageByNumber("12");
        Assert.assertTrue(homePage.isPageNumberActived("12"));

    }

//    @Test
    public void Table_02_Search_And_Verify(){
        // Enter value to header textbox end search
        homePage.enterToTextboxByHeaderName("Country", "Algeria");
        homePage.sleepInSeconds(3);

        // Verify data in any row
        Assert.assertTrue(homePage.isRowDataValueDisplayed("283821", "Algeria", "295140", "578961"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Males","12599691");
        homePage.sleepInSeconds(3);
        Assert.assertTrue(homePage.isRowDataValueDisplayed("12253515", "AFRICA", "12599691", "24853148"));
        homePage.refreshCurrentPage(driver);

        homePage.enterToTextboxByHeaderName("Females","764956");
        homePage.sleepInSeconds(3);
        Assert.assertTrue(homePage.isRowDataValueDisplayed("764956", "Arab Rep of Egypt", "802948", "1567904"));
    }

//    @Test
    public void Table_03_Delete_Edit(){
        // Tìm ra 1 key duy nhất trong table - key của column từ đó dùng giá trị này để đến được các action mong muốn

        // Click Delete button (trường hợp thực tế sẽ search trước rồi delete)
        homePage.enterToTextboxByHeaderName("Country", "Afghanistan");
        homePage.sleepInSeconds(3);
        homePage.deleteRowByCountryName("Afghanistan");
        homePage.refreshCurrentPage(driver);

        // Click Edit button
        homePage.enterToTextboxByHeaderName("Country", "Arab Rep of Egypt");
        homePage.sleepInSeconds(3);
        homePage.editRowByCountryName("Arab Rep of Egypt");
        homePage.refreshCurrentPage(driver);
    }

//    @Test
    public void Table_04_Get_All_Value_Row_Or_Column(){
        homePage.getAllValueAtColumnName("Country");
    }

//    @Test
    public void Table_05_Action_By_Index(){
        homePage.openPageUrl(driver,"https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");

        homePage.clickToLoadDataButton();
        homePage.sleepInSeconds(2);

        // Có thể thao tác với bất kì 1 column/ row nào
//        homePage.enterToTextboxByIndex("4", "Contact Person", "Michael Jackson");
//        homePage.sleepInSeconds(2);
//
//        homePage.enterToTextboxByIndex("2", "Company", "MJ Company");
//        homePage.sleepInSeconds(2);
//
//        homePage.enterToTextboxByIndex("3", "Order Placed", "881");
//        homePage.sleepInSeconds(2);
//
//        homePage.selectToDropdownByIndex("6", "Country", "Hong Kong");
//        homePage.sleepInSeconds(2);
//
//        homePage.selectToDropdownByIndex("8", "Country", "United Kingdom");
//        homePage.sleepInSeconds(2);
//
//        homePage.checkToCheckboxByIndex("6", "NPO?", true);
//
//        homePage.checkToCheckboxByIndex("5", "NPO?", false);

        // Sau mỗi sự kiện thao tác thì index của từng row sẽ được cập nhật lại
        homePage.clickToIconByIndex("8","Move Up");

        homePage.clickToIconByIndex("6","Remove");

        homePage.clickToIconByIndex("4","Insert");

    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    private WebDriver driver;
    private HomePO homePage;

}
