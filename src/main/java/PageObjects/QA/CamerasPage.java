package PageObjects.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CamerasPage {
    private static WebElement element = null;

    public static WebElement txt_PowerShotSX540HS(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@class='product-item-link'][text()='PowerShot SX540 HS']"));
        return element;
    }
    public static WebElement btn_CompactCameras(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[3]/div/div/div[3]/div[2]/div/a/span"));
        return element;
    }
    public static WebElement txt_ItemName(WebDriver driver,String itemName){
        String xpath = "//strong[@class='product name product-item-name']/a[contains(text(),'"+itemName+"')]";
        element = driver.findElement(By.xpath(xpath));
        return element;
    }
}
