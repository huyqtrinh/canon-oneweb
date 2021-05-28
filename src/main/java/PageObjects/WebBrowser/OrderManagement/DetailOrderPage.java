package PageObjects.WebBrowser.OrderManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DetailOrderPage {
    private static WebElement element = null;

    public static WebElement txt_OrderId(WebDriver driver){
        element = driver.findElement(By.xpath("//li[@class='breadcrumb-active']"));
        return element;
    }
    public static WebElement txt_ProductName(WebDriver driver){
        element = driver.findElement(By.xpath("//td[@class='table-column-item']/a/b[1]"));
        return element;
    }
    public static WebElement txt_Price(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='order-items-panel']/tfoot/tr/td[2]/span[2]"));
        return element;
    }
}
