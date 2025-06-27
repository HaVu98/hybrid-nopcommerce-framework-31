package javaInheritance;

public class OrderPO extends SidebarPO {

    public void clickToOrderCheckbox() {
        // Sidebar
        clickToSearchButton();

        // BasePage
        clickToElement();
    }
}
