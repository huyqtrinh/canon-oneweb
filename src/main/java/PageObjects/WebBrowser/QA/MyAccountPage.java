package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MyAccountPage {
    WebDriver driver;
    private static WebElement element = null;

    public static WebElement btn_Edit_AccountInf(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/customer/account/edit/']"));
        return element;
    }
    public static WebElement tbx_FirstName(WebDriver driver){
        element = driver.findElement(By.id("firstname"));
        return element;
    }
    public static WebElement tbx_LastName(WebDriver driver){
        element = driver.findElement(By.id("lastname"));
        return element;
    }
    public static WebElement btn_Save(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@type='submit'][@title='Save']"));
        return element;
    }
    public static WebElement txt_UserDetail(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='box-content']"));
        return element;
    }
}
