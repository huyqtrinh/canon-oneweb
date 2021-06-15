package testcases.ElasticSearch_Search.MC2_578;

import Modules.Commons.LaunchBrowser;
import Modules.QA.MainPageActions;
import PageObjects.WebBrowser.QA.QAHomePage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_7037 {
    WebDriver driver;

    String var_invalidSearchString = "!@#$%^&*()";
    String Ex_NoSearchResult_1 = "Modify your keyword and try again";
    String Ex_NoSearchResult_2 = "Check your spelling";
    String Ex_NoSearchResult_3 = "Browse our popular categories or products";
    String Ex_NoSearchResult_4 = "Contact Support";

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test()
    public void MC2_7037_TC_001_Verify_Search_Results_screen_when_there_are_no_search_results() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_001 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        CommonActions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the text \"@#$!%\"  in search text box and press Enter");
        MainPageActions.SearchString(driver, var_invalidSearchString);
        Thread.sleep(12000);

        try {
            Reporter.log("Step 3: Get and verify Search Result");

            String VP_NoSearchResult_1 = QAHomePage.lb_NoSearchResult_Line1(driver).getText();
            String VP_NoSearchResult_2 = QAHomePage.lb_NoSearchResult_Line2(driver).getText();
            String VP_NoSearchResult_3 = QAHomePage.lb_NoSearchResult_Line3(driver).getText();
            String VP_NoSearchResult_4 = QAHomePage.lb_NoSearchResult_Line4(driver).getText();

            Assert.assertEquals(VP_NoSearchResult_1, Ex_NoSearchResult_1, "Cannot find Search String as expected");
            Assert.assertEquals(VP_NoSearchResult_2, Ex_NoSearchResult_2, "Cannot find Search String as expected");
            Assert.assertEquals(VP_NoSearchResult_3, Ex_NoSearchResult_3, "Cannot find Search String as expected");
            Assert.assertEquals(VP_NoSearchResult_4, Ex_NoSearchResult_4, "Cannot find Search String as expected");

            Reporter.log("The Search Result displays correctly as expected");
        }
        catch (Exception e) {
            Reporter.log(e.toString());
            Reporter.log("The Search Result does NOT display correctly as expected");
        }
    }

    @AfterMethod
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
