package PageObjects.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private static WebElement element = null;

    public static WebElement rad_Fixed(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@type='radio'][@name='ko_unique_1']"));
        return element;
    }
    public static WebElement btn_ContinueToPayment(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='button action continue primary'][@data-role='opc-continue']"));
        return element;
    }
    public static WebElement btn_UseVerifiedAddress(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='button action use use-mobile']"));
        return element;
    }
    public static WebElement rad_PaymentMethod_Credit(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@id='moneris']"));
        return element;
    }
    public static WebElement cbx_SameAsShippingInf(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@type='checkbox'][@name='billing-address-same-as-shipping']"));
        return element;
    }
    public static WebElement btn_ContinueToReview(WebDriver driver){
        element = driver.findElement(By.id("continue-to-review"));
        return element;
    }
    public static WebElement btn_PlaceOrder(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@title='Place Order']"));
        return element;
    }
    public static WebElement txt_OrderNumber(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@class='order-number']"));
        return element;
    }
    public static WebElement txt_OrderTotal(WebDriver driver){
        element = driver.findElement(By.xpath("//tr[@class='grand totals']/td[@class='amount']/strong/span"));
        return element;
    }

}
