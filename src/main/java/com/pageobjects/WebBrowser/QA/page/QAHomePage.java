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
    public static WebElement lb_UserName(WebDriver driver){
        element = driver.findElement(By.className("username-text"));
        return element;
    }

    public static WebElement tb_Search(WebDriver driver){
        element = driver.findElement(By.id("search"));
        return element;
    }
    public static WebElement lb_SearchResult(WebDriver driver){
        element = driver.findElement(By.id("toolbar-amount"));
        return element;
    }

    public static WebElement lb_SearchResultNumber(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"toolbar-amount\"]/div/span"));
        return element;
    }

    public static WebElement lb_NoSearchResult_Line1(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"tabs-amasty_search_products_tab\"]/div/ul/li[1]"));
        return element;
    }
    public static WebElement lb_NoSearchResult_Line2(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"tabs-amasty_search_products_tab\"]/div/ul/li[2]"));
        return element;
    }
    public static WebElement lb_NoSearchResult_Line3(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"tabs-amasty_search_products_tab\"]/div/ul/li[3]"));
        return element;
    }
    public static WebElement lb_NoSearchResult_Line4(WebDriver driver) {
        element = driver.findElement(By.xpath("//*[@id=\"tabs-amasty_search_products_tab\"]/div/ul/li[4]"));
        return element;
    }

    public static WebElement txt_UsernameText(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@class='username-text']"));
        return element;
    }
}
