package com.pageobjects.WebBrowser.CCIAdmin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrdersPage {
    private static WebElement element = null;
    public static WebElement tb_Username(WebDriver driver){
        element = driver.findElement(By.id("username"));
        return element;
    }
    public static String tbl_DataGrid(){
        return "//table[@class='data-grid data-grid-draggable']";

    }
}
