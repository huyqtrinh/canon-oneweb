package Modules.QA;

import Utilities.CommonActions;
import PageObjects.WebBrowser.QA.QAHomePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class MainPageActions {
    public static void SearchString(WebDriver driver, String sSearchKey) throws InterruptedException {

        CommonActions.setTexts(QAHomePage.tb_Search(driver), sSearchKey);
        Thread.sleep(1000);
        QAHomePage.tb_Search(driver).sendKeys(Keys.RETURN);
        Thread.sleep(1000);

    }
}
