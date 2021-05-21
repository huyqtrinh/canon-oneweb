package PageObjects.OrderManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderManagementLoginPage {
    private static WebElement element = null;

    public static WebElement tb_Username(WebDriver driver){
        element = driver.findElement(By.id("username"));
        return element;
    }

    public static WebElement tb_Password(WebDriver driver) {
        element = driver.findElement(By.id("password"));
        return element;
    }

    public static WebElement btn_SignIn(WebDriver driver) {
        element = driver.findElement(By.id("sign_in"));
        return element;
    }
}
