package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ShopAllCamerasPage {
    private static WebElement element = null;

    public static WebElement lb_ViewMore(WebDriver driver){
        element = driver.findElement(By.className("filter-more"));
        return element;
    }
    public static WebElement lb_Color(WebDriver driver){
        element = driver.findElement(By.xpath("//h2[text()='Color']"));
        return element;
    }
    public static WebElement css_Plus(WebDriver driver){
        element = driver.findElement(By.cssSelector("dt.filter-options-title"));
        return element;
    }
    public static WebElement lb_ColorExpand(WebDriver driver, int row){
        element = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div["+row+"]/dt"));
        return element;
    }
    public static WebElement Color_Red(WebDriver driver, int row){
        element = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div["+row+"]/dd/form/div/div[1]/a/div[1]"));
        return element;
    }
    public static WebElement Color_Black(WebDriver driver, int row){
        element = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div["+row+"]/dd/form/div/div[2]/a/div[1]"));
        return element;
    }
    public static WebElement Circle_Color(WebDriver driver){
        element = driver.findElement(By.cssSelector("#narrow-by-list > div:nth-child(4) > dd > form > div > div:nth-child(2) > a > div.selected.swatch-option.color"));
        return element;
    }
    public static WebElement btn_Clear_Filter(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[5]/div[2]/div[2]/div[2]/div[2]/div[2]/a"));
        return element;
    }
    public static WebElement form_Color(WebDriver driver, int row){
        element = driver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div["+row+"]/dd/form"));
        return element;
    }
}
