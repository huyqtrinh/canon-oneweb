package PagesFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductListPage {
    WebDriver driver;
    /*
        Methods
     */
    //Init element
    public ProductListPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    public void clickOnProduct(String productName){
        String xpath = "//strong[@class='product name product-item-name']/a[contains(text(),'" + productName + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
    }

    public String getProductName(String productName){
        String xpath = "//strong[@class='product name product-item-name']/a[contains(text(),'" + productName + "')]";
        WebElement element = driver.findElement(By.xpath(xpath));
        return  element.getText();
    }


}
