package Modules.QA;

import Utilities.Actions;
import PageObjects.WebBrowser.QA.QAHomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class Search {
    public static void SearchString(WebDriver driver, String sSearchKey) throws InterruptedException {

        Actions.setTexts(QAHomePage.tb_Search(driver), sSearchKey);
        Thread.sleep(1000);
        QAHomePage.tb_Search(driver).sendKeys(Keys.RETURN);
        Thread.sleep(1000);

    }
}
