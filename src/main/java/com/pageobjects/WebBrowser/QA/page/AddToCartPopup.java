package com.pageobjects.WebBrowser.QA.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddToCartPopup {
    private static WebElement element = null;

    public static WebElement btn_ProceedToCart(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='button-modal-proceed']"));
        return element;
    }
    public static WebElement txt_ProductName(WebDriver driver){
        element = driver.findElement(By.xpath("//h2[@class='product-name']"));
        return element;
    }
    public static WebElement txt_ProductPrice(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='product-price']"));
        return element;
    }
    public static WebElement txt_ProductAttribute(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='product-attributes']"));
        return element;
    }

}
