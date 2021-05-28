package Modules.QA;

import PageObjects.WebBrowser.QA.QALoginPage;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;

public class LoginQA {
    public static void Execute(WebDriver driver) throws InterruptedException {

        QALoginPage.tb_Username(driver).clear();
        QALoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        QALoginPage.tb_Username(driver).sendKeys(Constants.MagentoQA_Username);
        QALoginPage.tb_Password(driver).sendKeys(Constants.MagentoQA_Password);
        Thread.sleep(3000);

        QALoginPage.btn_LogIn(driver).click();

        Thread.sleep(3000);

    }
}
