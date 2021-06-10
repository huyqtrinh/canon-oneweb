package com.modules.QA.page;

import com.pageobjects.WebBrowser.QA.page.QALoginPage;
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

        Thread.sleep(10000);

    }
    public static void Invalid_Execute(WebDriver driver) throws InterruptedException {

        QALoginPage.tb_Username(driver).clear();
        QALoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        QALoginPage.tb_Username(driver).sendKeys(Constants.MagentoQA_Username);
        QALoginPage.tb_Password(driver).sendKeys("abcd1234");
        Thread.sleep(3000);

        QALoginPage.btn_LogIn(driver).click();

        Thread.sleep(3000);

    }
    public static void Execute_Wrong_Pass(WebDriver driver) throws InterruptedException {

        QALoginPage.tb_Username(driver).clear();
        QALoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        QALoginPage.tb_Username(driver).sendKeys(Constants.MagentoQA_Username);
        QALoginPage.tb_Password(driver).sendKeys(Constants.Wrong_Password);
        Thread.sleep(3000);

        QALoginPage.btn_LogIn(driver).click();
        Thread.sleep(3000);
    }
    public static void Execute_Wrong_Email_Pass(WebDriver driver) throws InterruptedException {

        QALoginPage.tb_Username(driver).clear();
        QALoginPage.tb_Password(driver).clear();
        Thread.sleep(2000);

        QALoginPage.tb_Username(driver).sendKeys(Constants.Wrong_Username);
        QALoginPage.tb_Password(driver).sendKeys(Constants.Wrong_Password);
        Thread.sleep(3000);

        QALoginPage.btn_LogIn(driver).click();
        Thread.sleep(3000);
    }
}
