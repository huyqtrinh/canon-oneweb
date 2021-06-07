package com.modules.OrderManagement.page;

import com.pageobjects.WebBrowser.OrderManagement.page.OMLoginPage;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;

public class LoginOrderManagement {
    public static void Execute(WebDriver driver) throws InterruptedException {

        OMLoginPage.tb_Username(driver).clear();
        OMLoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        OMLoginPage.tb_Username(driver).sendKeys(Constants.MagentoOrderManagement_Username1);
        OMLoginPage.tb_Password(driver).sendKeys(Constants.MagentoOrderManagement_Password1);
        Thread.sleep(3000);

        OMLoginPage.btn_SignIn(driver).click();

        Thread.sleep(3000);

    }
}
