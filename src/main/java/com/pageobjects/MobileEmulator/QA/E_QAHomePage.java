package com.pageobjects.MobileEmulator.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class E_QAHomePage {
    private static WebElement element = null;

    public static WebElement btn_Login(WebDriver driver) {
        element = driver.findElement(By.xpath("//div[@class='switcher']/a[@id='mcLogin']"));
        return element;
    }
    public static WebElement btn_Logout(WebDriver driver) {
        element = driver.findElement(By.xpath("//div[@class='switcher']/a[@id='mcLogout']"));
        return element;
    }
    public static WebElement btn_Cameras(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras']"));
        return element;
    }

    public static WebElement btn_Lenses(WebDriver driver) {
        element = driver.findElement(By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/lenses']"));
        return element;
    }
    public static WebElement btn_All_Lenses(WebDriver driver) {
        element = driver.findElement(By.xpath("//li[@class='ui-menu-item all-category']/a[@href='https://mcstaging-estore.canon.ca/en_ca/lenses']"));
        return element;
    }
    public static WebElement btn_All_Cameras(WebDriver driver) {
        element = driver.findElement(By.xpath("//li[@class='ui-menu-item all-category']/a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras']"));
        return element;
    }
    public static WebElement btn_ClosePopupPromo(WebDriver driver) {
        element = driver.findElement(By.xpath("//button[@data-role='closeBtn']"));
        return element;
    }

    public static WebElement menu_MenuList(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='html-body']/div[4]/header/div/span"));
        return element;
    }

    public static WebElement menu_Account(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id='html-body']/div[4]/div[1]/div/div[5]/a"));
        return element;
    }
}
