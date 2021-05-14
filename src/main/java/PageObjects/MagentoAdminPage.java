package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MagentoAdminPage {
    private static WebElement element = null;

    public static WebElement btn_Sales(WebDriver driver){
        element = driver.findElement(By.xpath("//a/span[text() = 'Sales']"));
        return element;
    }

    public static WebElement btn_Order(WebDriver driver) {
        element = driver.findElement(By.xpath("//a/span[text() = 'Orders']"));
        return element;
    }
    public static WebElement tbx_Search(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@class='admin__control-text data-grid-search-control'][1]"));
        return element;
    }
    public static WebElement btn_Search(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@class='action-submit'][2]"));
        return element;
    }
    public static WebElement tbl_orders(WebDriver driver) {
        element = driver.findElement(By.xpath("//table[@class='data-grid data-grid-draggable'][2]"));
        return element;
    }
}
