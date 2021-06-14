package com.pageobjects.WebBrowser.QA.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CamerasPage {
    private static WebElement element = null;

    public static WebElement txt_ItemName(WebDriver driver,String itemName){
        String xpath = "//strong[@class='product name product-item-name']/a[contains(text(),'"+itemName+"')]";
        element = driver.findElement(By.xpath(xpath));
        return element;
    }
    public static WebElement btn_CompactCameras(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras']"));
        return element;
    }
    public static WebElement btn_ShopAllCameras(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[1]/div[1]/div[2]/div/a"));
        return element;
    }
}
