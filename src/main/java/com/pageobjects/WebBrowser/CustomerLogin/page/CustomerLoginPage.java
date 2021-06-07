package com.pageobjects.WebBrowser.CustomerLogin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    private static WebElement element = null;

    public static WebElement btn_SignIn(WebDriver driver){
        element = driver.findElement(By.name("websso-login-button"));
        return element;
    }
    public static WebElement btn_CreateAccount(WebDriver driver){
        element = driver.findElement(By.xpath("//a[contains(text(),'Create an Account')"));
        return element;
    }
    public static WebElement btn_GuestLogin(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@type='submit']"));
        return element;
    }
    public static WebElement ck_reCaptcha(WebDriver driver){
        element = driver.findElement(By.className("recaptcha-checkbox-borderAnimation"));
        return element;
    }
}
