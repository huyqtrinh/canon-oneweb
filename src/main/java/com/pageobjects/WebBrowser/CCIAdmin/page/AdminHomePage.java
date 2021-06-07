package com.pageobjects.WebBrowser.CCIAdmin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminHomePage {
    private static WebElement element = null;

    public static WebElement btn_Sales(WebDriver driver){
        element = driver.findElement(By.xpath("//a/span[text() = 'Sales']"));
        return element;
    }

    public static WebElement btn_Orders(WebDriver driver) {
        element = driver.findElement(By.xpath("//a/span[text() = 'Orders']"));
        return element;
    }
    public static WebElement tbx_Search(WebDriver driver) {
        element = driver.findElement(By.xpath("//input[@class='admin__control-text data-grid-search-control'][1]"));
        return element;
    }
    public static WebElement btn_Search(WebDriver driver) {
        element = driver.findElement(By.xpath("(//button[@class='action-submit'])[2]"));
        return element;
    }

}
