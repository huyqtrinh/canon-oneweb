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

public class MC2_7039 {
    WebDriver driver;
    String var_incorrectSearchString = "kamera";
    String var_synonymSearchString = "camera";

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @Test()
    public void MC2_7039_TC_003_Verify_CMS_block_when_Search_Results_screen_when_there_are_no_results() throws InterruptedException {
        Reporter.log("Start HIT_MC2_578_TC_003 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        CommonActions.HandlingPromoPopup(driver);
        Thread.sleep(12000);
        Reporter.log("Step 2: Enter the synonym search string in search text box and press Enter");
        MainPageActions.SearchString(driver, var_incorrectSearchString);
        Thread.sleep(12000);

        try {
            Reporter.log("Step 3: Get and verify Search Result");

            Assert.assertTrue(CommonActions.checkDisplayed(QAHomePage.lb_SearchResult(driver)));

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
