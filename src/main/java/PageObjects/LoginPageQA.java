package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageQA {
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
}
