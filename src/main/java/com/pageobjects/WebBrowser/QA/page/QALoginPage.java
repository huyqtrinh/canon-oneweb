package com.pageobjects.WebBrowser.QA.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QALoginPage {
    private static WebElement element = null;

    public static WebElement tb_Username(WebDriver driver){
        element = driver.findElement(By.name("user"));
        return element;
    }

    public static WebElement tb_Password(WebDriver driver) {
        element = driver.findElement(By.name("password"));
        return element;
    }

    public static WebElement btn_LogIn(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@type='submit']"));
        return element;
    }

    public static WebElement btn_forgot(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[text()='Forgot your password?']"));
        return element;
    }

    public static WebElement btn_signup(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[text()=' Sign Up']"));
        return element;
    }

    public static WebElement txt_ErrorMessage(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='msg']"));
        return element;
    }
}
