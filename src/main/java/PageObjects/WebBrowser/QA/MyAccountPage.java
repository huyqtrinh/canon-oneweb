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
    public static WebElement LeftMenu_MyAccount(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"My Account\")]"));
        return element;
    }
    public static WebElement LeftMenu_MyOrders(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"My Orders\")]"));
        return element;
    }
    public static WebElement LeftMenu_MySubscriptions(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"My Subscriptions\")]"));
        return element;
    }
    public static WebElement LeftMenu_MyWishList(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"My Wish List\")]"));
        return element;
    }
    public static WebElement LeftMenu_StoreCredit(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"Store Credit\")]"));
        return element;
    }
    public static WebElement LeftMenu_GiftCard(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"Gift Card\")]"));
        return element;
    }
    public static WebElement LeftMenu_CompanyStructure(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"Company Structure\")]"));
        return element;
    }
    public static WebElement LeftMenu_AccountSettings(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"block-collapsible-nav\"]/ul/li/*[contains(text(),\"Account Settings\")]"));
        return element;
    }
}
