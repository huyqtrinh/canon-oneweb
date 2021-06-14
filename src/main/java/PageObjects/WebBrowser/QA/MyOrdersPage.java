package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyOrdersPage {
    WebDriver driver;
    private static WebElement element = null;

    public static WebElement tbx_Search(WebDriver driver){
        element = driver.findElement(By.id("product-name-sku"));
        return element;
    }
    public static WebElement btn_Filter(WebDriver driver){
        element = driver.findElement(By.id("filter-show-btn"));
        return element;
    }
    public static WebElement btn_CloseFilter(WebDriver driver){
        element = driver.findElement(By.id("filter-close-btn"));
        return element;
    }
    public static WebElement tbl_Orders(WebDriver driver){
        element = driver.findElement(By.id("my-orders-table"));
        return element;
    }
    public static WebElement lb_NumOfItems(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@class='toolbar-number']"));
        return element;
    }
    public static WebElement ctrl_Paging(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='pages']"));
        return element;
    }
    // [SN] Add item for FILTER section
    public static WebElement tb_Filter_OrderNumber(WebDriver driver){
        element = driver.findElement(By.id("order-number"));
        return element;
    }
    public static WebElement tb_Filter_InvoiceNumber(WebDriver driver){
        element = driver.findElement(By.id("invoice-number"));
        return element;
    }
    public static WebElement ddl_Filter_CreatedBy(WebDriver driver){
        element = driver.findElement(By.id("created-by"));
        return element;
    }
    public static WebElement ddl_Filter_OrderStatus(WebDriver driver){
        element = driver.findElement(By.id("order-status"));
        return element;
    }
    public static WebElement tb_Filter_OrderDateFrom(WebDriver driver){
        element = driver.findElement(By.id("order-date-from"));
        return element;
    }
    public static WebElement tb_Filter_OrderDateTo(WebDriver driver){
        element = driver.findElement(By.id("order-date-to"));
        return element;
    }
    public static WebElement tb_Filter_OrderTotalMin(WebDriver driver){
        element = driver.findElement(By.id("order-total-min"));
        return element;
    }
    public static WebElement tb_Filter_OrderTotalMax(WebDriver driver){
        element = driver.findElement(By.id("order-total-max"));
        return element;
    }
    public static WebElement btn_Apply(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@type=\"submit\" and @title=\"Apply\"]"));
        return element;
    }
    public static WebElement btn_ClearAll(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='secondary']"));
        return element;
    }
    public static WebElement href_ClearAll(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"my-orders-search-advanced-form\"]/fieldset[2]//a[@class='action-remove action-clear-all']"));
        return element;
    }
}
