package PageObjects.OrderManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderManagementHomePage {
    private static WebElement element = null;

    public static WebElement tbx_Search(WebDriver driver){
        element = driver.findElement(By.id("filter_order_id"));
        return element;
    }
    public static WebElement btn_Search(WebDriver driver){
        element = driver.findElement(By.id("order_quick_search"));
        return element;
    }
}
