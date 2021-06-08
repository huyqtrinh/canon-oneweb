package com.pageobjects.WebBrowser.QA.page;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QAHomePage {
    WebDriver driver;
    private static WebElement element = null;

    public static WebElement ico_User(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@data-trigger='trigger']"));
        return element;
    }
    public static WebElement btn_Login(WebDriver driver){
        element = driver.findElement(By.id("mcLogin"));
        return element;
    }
    public static WebElement btn_MyAccount(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/customer/account/index']"));
        return element;
    }
    public static WebElement btn_Cameras(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras']"));
        return element;
    }
    public static WebElement btn_Lenses(WebDriver driver){
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/lenses']"));
        return element;
    }
    public static WebElement btn_ClosePopupPromo(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@data-role='closeBtn']"));
        return element;
    }
    public static WebElement btn_Logout(WebDriver driver){
        element = driver.findElement(By.id("mcLogout"));
        return element;
    }
    public static WebElement txt_UsernameText(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@class='username-text']"));
        return element;
    }
}
