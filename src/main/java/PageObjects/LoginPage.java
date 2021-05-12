package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    WebDriver driver;
    By tb_user = By.name("user");
    By tb_password = By.name("password");
    By btn_Login = By.xpath("//button[@type='submit']");
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    //SSO Login
    public void loginToCCI(){
        WebElement user = driver.findElement(tb_user);
        WebElement password = driver.findElement(tb_password);
        WebElement btnLogin = driver.findElement(btn_Login);
        user.sendKeys("automation@gmail.com");
        password.sendKeys("Canon123");
        btnLogin.click();
    }
}
