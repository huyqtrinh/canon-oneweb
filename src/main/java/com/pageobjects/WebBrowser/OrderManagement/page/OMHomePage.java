package com.pageobjects.WebBrowser.OrderManagement.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OMHomePage {
    private static WebElement element = null;

    public static WebElement tbx_Search(WebDriver driver){
        element = driver.findElement(By.id("filter_order_id"));
        return element;
    }
    public static WebElement btn_Search(WebDriver driver){
        element = driver.findElement(By.id("order_quick_search"));
        return element;
    }
    public static WebElement btn_Reset(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='filter-container']/form/div[3]/a"));
        return element;
    }
    public static WebElement table_Items(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id='order-items-panel']"));
        return element;
    }
}
