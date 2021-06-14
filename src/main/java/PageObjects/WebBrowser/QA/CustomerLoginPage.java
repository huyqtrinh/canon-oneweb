package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CustomerLoginPage {
    WebDriver driver;
    private static WebElement element = null;

    public static WebElement btn_Signin(WebDriver driver) {
        element = driver.findElement(By.xpath("//div[@class='websso-custom-button']/a[@href='https://mcstaging-estore.canon.ca/en_ca/sso/account/login/']"));
        return element;
    }
}
