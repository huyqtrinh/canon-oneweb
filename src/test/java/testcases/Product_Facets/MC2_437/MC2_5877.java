package testcases.Product_Facets.MC2_437;

import Modules.Commons.LaunchBrowser;
import PageObjects.WebBrowser.QA.CamerasPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.ShopAllCamerasPage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_5877 {
    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_MC2_437_TC_001_Product_Facets_Verify_for_colors_under_shopping_option() {
        try {
            //Step 1: Launch the Home page for CCI
            Reporter.log("Start TC_001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch the Home page for CCI", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on cameras from mega menu
            Reporter.log("Step 2: Click on cameras from mega menu", true);
            WebElement btn_Cameras = QAHomePage.btn_Cameras(driver);
            CommonActions.clickObject(btn_Cameras);
            Thread.sleep(8000);
            Reporter.log("Verify Cameras page displayed:", true);
            String strCamerasTitle = "Shop Canon Cameras | Canon Canada, Inc.";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strCamerasTitle, "Cameras page not displayed.");
            Reporter.log("Cameras page displayed.", true);
            Thread.sleep(8000);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

            //Step 3: Click on "Shop all cameras"
            Reporter.log("Step 3: Click on 'Shop all cameras:", true);
            WebElement btn_ShopAllCameras = CamerasPage.btn_ShopAllCameras(driver);
            CommonActions.clickObject(btn_ShopAllCameras);

            //Step 4: Verify "+" for color under shopping option.
            WebElement lb_ViewMore = ShopAllCamerasPage.lb_ViewMore(driver);
            CommonActions.clickObject(lb_ViewMore);
            Reporter.log("Step 4: Verify '+' for color under shopping option:", true);
            WebElement css_Plus = ShopAllCamerasPage.css_Plus(driver);
            String css_Plus_Display = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('content');",css_Plus).toString();
            String Plus = "\"+\"";
            Assert.assertEquals(css_Plus_Display, Plus, "'+' for color under shopping option not displayed.");
            Reporter.log("'+' for color under shopping option displayed.", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
