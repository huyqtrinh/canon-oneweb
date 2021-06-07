package com.pageobjects.WebBrowser.CCIAdmin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdminLoginPage {
    private static WebElement element = null;

    public static WebElement tb_Username(WebDriver driver){
        element = driver.findElement(By.id("username"));
        return element;
    }

    public static WebElement tb_Password(WebDriver driver) {
        element = driver.findElement(By.id("login"));
        return element;
    }

    public static WebElement btn_LogIn(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@class='action-login action-primary']"));
        return element;
    }
}
