package Modules;

import PageObjects.LoginPageQA;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;


public class LoginQA {
    public static void Execute(WebDriver driver) throws InterruptedException{

        LoginPageQA.tb_Username(driver).clear();
        LoginPageQA.tb_Password(driver).clear();
        Thread.sleep(2000);

        LoginPageQA.tb_Username(driver).sendKeys(Constants.MagentoQA_Username);
        LoginPageQA.tb_Password(driver).sendKeys(Constants.MagentoQA_Password);
        Thread.sleep(3000);

        LoginPageQA.btn_LogIn(driver).click();

        Thread.sleep(3000);

    }
}
