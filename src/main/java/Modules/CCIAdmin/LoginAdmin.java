package Modules.CCIAdmin;

import PageObjects.WebBrowser.CCIAdmin.AdminLoginPage;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;

public class LoginAdmin {
    public static void Execute(WebDriver driver) throws InterruptedException{

        AdminLoginPage.tb_Username(driver).clear();
        AdminLoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        AdminLoginPage.tb_Username(driver).sendKeys(Constants.MagentoCCIAdmin_Username1);
        AdminLoginPage.tb_Password(driver).sendKeys(Constants.MagentoCCIAdmin_Password1);
        Thread.sleep(3000);

        AdminLoginPage.btn_LogIn(driver).click();

        Thread.sleep(3000);

    }
}
