package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShoppingCartPage {
    private static WebElement element = null;

    public static WebElement btn_Checkout(WebDriver driver){
        element = driver.findElement(By.xpath("//li/button[@type='button'][@title='Checkout']"));
        return element;
    }
}
