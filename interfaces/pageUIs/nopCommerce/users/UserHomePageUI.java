package pageUIs.nopCommerce.users;

public class UserHomePageUI {
    public static final String REGISTER_LINK = "XPath=//a[@class='ico-register']";
    // public: gọi hàm / biến ra sử dụng bình thường
    // private/ default: khác package không dùng được
    // protected: các class bên PO không kế thừa PUI nên không áp dụng

    // static: cho phép gọi trực tiếp từ class
    // final: ngăn việc update lại các giá trị trong quá trình chạy
    // String: vì cái By locator của Selenium đều nhận String
    // REGISTER_LINK: static final để quy ước 1 biến là HẰNG SỐ trong JAVA
    // Convention cho hằng số: phải viết hoa - nhiều hơn 1 từ thì phải dùng dấu _ để phân tách

    public static final String MY_ACCOUNT_LINK = "XPath=//a[@class='ico-account']";

}
