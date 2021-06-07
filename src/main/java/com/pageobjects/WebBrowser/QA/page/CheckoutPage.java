package com.pageobjects.WebBrowser.QA.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage {
    private static WebElement element = null;

    public static WebElement rad_Fixed(WebDriver driver){
        element = driver.findElement(By.xpath("//input[@type='radio'][@name='ko_unique_6']"));
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
        element = driver.findElement(By.xpath("//*[@id='checkout-payment-method-load']/div/div[3]/div/div[3]/div[1]"));
        return element;
    }
    public static WebElement cbx_SameAsShippingInf(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='checkout-billing-address-cci']/div[2]/label"));
        return element;
    }
    public static WebElement btn_ContinueToReview(WebDriver driver){
        element = driver.findElement(By.id("continue-to-review"));
        return element;
    }
    public static WebElement btn_PlaceOrder(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='review']/div[3]/button"));
        return element;
    }
    public static WebElement txt_OrderNumber(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@class='order-number']"));
        return element;
    }
    public static WebElement txt_OrderTotal(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='checkout-step-review']/div[2]/div[2]/div/div[2]/div/div/span"));
        return element;
    }
    public static WebElement rad_1stAddress(WebDriver driver){
        element = driver.findElement(By.xpath("//div/button[@class='action action-select-shipping-item'][1]"));
        return element;
    }
    public static WebElement rad_1stCreditCard(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='payment-method'][1]"));
        return element;
    }
}
