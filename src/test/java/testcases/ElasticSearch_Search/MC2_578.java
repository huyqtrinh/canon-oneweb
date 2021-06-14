package testcases.ElasticSearch_Search;

import Utilities.Constants;
import Modules.Commons.LaunchBrowser;
import Utilities.Actions;
import Modules.QA.Search;
import PageObjects.WebBrowser.QA.QAHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class MC2_578 {
    WebDriver driver;

    String var_invalidSearchString = "!@#$%^&*()";
    String var_validSearchString = "EOS";

    String var_incorrectSearchString = "kamera";
    String var_synonymSearchString = "camera";

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
    public void HIT_MC2_578_TC_001_Verify_Search_Results_screen_when_there_are_no_search_results() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_001 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Actions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the text \"@#$!%\"  in search text box and press Enter");
        Search.SearchString(driver, var_invalidSearchString);
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

    @Test()
    public void HIT_MC2_578_TC_002_Verify_Search_Results_screen_when_there_are_no_results_for_users_search_terms_and_synonyms_for_the_search_terms_are_set_up() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_002 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Actions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the search string in search text box and press Enter");
        Search.SearchString(driver, var_validSearchString);
        Thread.sleep(12000);

        try {
            Reporter.log("Step 3: Verify the products in each results");

            Assert.assertTrue(Actions.checkDisplayed(QAHomePage.lb_SearchResult(driver)));

            // [SN] Run loop to check search result
            int VP_NumofSearchResult = Integer.parseInt(QAHomePage.lb_SearchResultNumber(driver).getText());

            for (int i = 1; i <= VP_NumofSearchResult; i++) {
                String CP_CheckItemName = "//*[@id=\"" + i + "\"]/div/div/strong/a";
                String VP_ItemName = driver.findElement(By.xpath(CP_CheckItemName)).getText().toLowerCase();

                Assert.assertTrue(VP_ItemName.contains(var_validSearchString.toLowerCase()));
            }
            Reporter.log("The Search Result displays correctly as expected");
        }
        catch (Exception e) {
            Reporter.log(e.toString());
            Reporter.log("The Search Result does NOT display correctly as expected");
        }
    }

    @Test()
    public void HIT_MC2_578_TC_003_Verify_CMS_block_when_Search_Results_screen_when_there_are_no_results() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_003 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        Actions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the synonym search string in search text box and press Enter");
        Search.SearchString(driver, var_incorrectSearchString);
        Thread.sleep(12000);

        try {
            Reporter.log("Step 3: Get and verify Search Result");

            Assert.assertTrue(Actions.checkDisplayed(QAHomePage.lb_SearchResult(driver)));

            // [SN] Run loop to check search result
            int VP_NumofSearchResult = Integer.parseInt(QAHomePage.lb_SearchResultNumber(driver).getText());

            for (int i = 1; i <= VP_NumofSearchResult; i++) {
                String CP_CheckItemName = "//*[@id=\"" + i + "\"]/div/div/strong/a";

                String VP_ItemName = driver.findElement(By.xpath(CP_CheckItemName)).getText().toLowerCase();
                Assert.assertTrue(VP_ItemName.contains(var_incorrectSearchString.toLowerCase()) || VP_ItemName.contains(var_synonymSearchString.toLowerCase()));
            }
            Reporter.log("The Search Result displays correctly as expected");
        }
        catch (Exception e) {
            Reporter.log(e.toString());
        }
    }
    @AfterMethod
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
