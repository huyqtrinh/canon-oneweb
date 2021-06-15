package testcases.ElasticSearch_Search.MC2_578;

import Modules.Commons.LaunchBrowser;
import Modules.QA.MainPageActions;
import PageObjects.WebBrowser.QA.QAHomePage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_7038 {
    WebDriver driver;
    String var_validSearchString = "EOS";

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test()
    public void MC2_7038_TC_002_Verify_Search_Results_screen_when_there_are_no_results_for_users_search_terms_and_synonyms_for_the_search_terms_are_set_up() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_002 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        CommonActions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the search string in search text box and press Enter");
        MainPageActions.SearchString(driver, var_validSearchString);
        Thread.sleep(12000);

        try {
            Reporter.log("Step 3: Verify the products in each results");

            Assert.assertTrue(CommonActions.checkDisplayed(QAHomePage.lb_SearchResult(driver)));

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

    @AfterMethod
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
