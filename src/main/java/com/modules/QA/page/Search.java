package com.modules.QA.page;

import Ultilities.Constants;
import com.modules.Commons.MyActions;
import com.pageobjects.WebBrowser.QA.page.QAHomePage;
import com.pageobjects.WebBrowser.QA.page.QALoginPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Search {
    public static void SearchString(WebDriver driver, String sSearchKey) throws InterruptedException {

        MyActions.setTexts(QAHomePage.tb_Search(driver), sSearchKey);
        Thread.sleep(1000);
        new QAHomePage().tb_Search(driver).sendKeys(Keys.RETURN);
        Thread.sleep(1000);

    }
}
