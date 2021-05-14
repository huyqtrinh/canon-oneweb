package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPageCCIAdmin {
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
        element = driver.findElement(By.className("action-login action-primary"));
        return element;
    }
}
