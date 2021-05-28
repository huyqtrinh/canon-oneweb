package Modules.OrderManagement;

import PageObjects.WebBrowser.OrderManagement.OrderManagementLoginPage;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;

public class LoginOrderManagement {
    public static void Execute(WebDriver driver) throws InterruptedException {

        OrderManagementLoginPage.tb_Username(driver).clear();
        OrderManagementLoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        OrderManagementLoginPage.tb_Username(driver).sendKeys(Constants.MagentoOrderManagement_Username1);
        OrderManagementLoginPage.tb_Password(driver).sendKeys(Constants.MagentoOrderManagement_Password1);
        Thread.sleep(3000);

        OrderManagementLoginPage.btn_SignIn(driver).click();

        Thread.sleep(3000);

    }
}
