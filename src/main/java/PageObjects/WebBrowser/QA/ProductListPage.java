package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductListPage {
    private static WebElement element = null;

    public static WebElement lst_SubCatList(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='maincontent']/div[5]/div[2]/div[1]"));
        return element;
    }
}
