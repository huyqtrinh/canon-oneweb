package testcases.webbrowser.Product_Facets.epic;

import PageObjects.WebBrowser.QA.CamerasPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import Utilities.CommonActions;
import Utilities.Constants;
import Modules.Commons.LaunchBrowser;

import PageObjects.WebBrowser.QA.ShopAllCamerasPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class MC2_437 {
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

    @Test()
    public void HIT_MC2_MC2_437_TC_002_Verify_colors_under_colors() {
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

            //Step 3: Click on "Shop all cameras"
            Reporter.log("Step 3: Click on 'Shop all cameras", true);
            WebElement btn_ShopAllCameras = CamerasPage.btn_ShopAllCameras(driver);
            CommonActions.clickObject(btn_ShopAllCameras);

            //Step 4: Verify "+" for color under shopping option
            WebElement lb_ViewMore = ShopAllCamerasPage.lb_ViewMore(driver);
            CommonActions.clickObject(lb_ViewMore);
            Reporter.log("Step 4: Verify '+' for color under shopping option:", true);
            WebElement lb_Color = ShopAllCamerasPage.lb_Color(driver);
            WebElement css_Plus = ShopAllCamerasPage.css_Plus(driver);
            String css_Plus_Display = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('content');",css_Plus).toString();
            String Plus = "\"+\"";
            Assert.assertEquals(css_Plus_Display, Plus, "'+' for color under shopping option not displayed.");
            Reporter.log("'+' for color under shopping option displayed.", true);

            //Step 5: Verify Click on "+" for colors
            Reporter.log("Step 5: Verify Click on '+' for color:", true);
            CommonActions.clickObject(lb_Color);
            int row = 4;
            WebElement lb_ColorExpand = ShopAllCamerasPage.lb_ColorExpand(driver,row);
            Boolean lb_ColorExpand_Display = CommonActions.checkDisplayed(lb_ColorExpand);
            Assert.assertEquals(lb_ColorExpand_Display, Boolean.TRUE, "'+' not clicked.");
            Reporter.log("'+' clicked.", true);
            Reporter.log("Verify Colors displayed:", true);
            WebElement form_Color = ShopAllCamerasPage.form_Color(driver,row);
            String form_Color_Text = form_Color.getText();
            Reporter.log(form_Color_Text, true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_MC2_437_TC_003_Verify_black_circle_when_highlight_the_color() {
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
            Reporter.log("Verify Cameras page displayed'", true);
            String strCamerasTitle = "Shop Canon Cameras | Canon Canada, Inc.";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strCamerasTitle, "Cameras page not displayed.");
            Reporter.log("Cameras page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Click on "Shop all cameras"
            Reporter.log("Step 3: Click on 'Shop all cameras'", true);
            WebElement btn_ShopAllCameras = CamerasPage.btn_ShopAllCameras(driver);
            CommonActions.clickObject(btn_ShopAllCameras);

            //Step 4: Verify "+" for color under shopping option
            //driver.get("https://mcstaging-estore.canon.ca/en_ca/cameras/all-cameras");
            WebElement lb_ViewMore = ShopAllCamerasPage.lb_ViewMore(driver);
            CommonActions.clickObject(lb_ViewMore);
            Reporter.log("Step 4: Verify '+' for color under shopping option:", true);
            WebElement lb_Color = ShopAllCamerasPage.lb_Color(driver);
            WebElement css_Plus = ShopAllCamerasPage.css_Plus(driver);
            String css_Plus_Display = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('content');",css_Plus).toString();
            String Plus = "\"+\"";
            Assert.assertEquals(css_Plus_Display, Plus, "'+' for color under shopping option not displayed.");
            Reporter.log("'+' for color under shopping option displayed.", true);

            //Step 5: Verify Click on "+" for colors
            Reporter.log("Step 5: Verify Click on '+' for color:", true);
            CommonActions.clickObject(lb_Color);
            int row = 4;
            WebElement lb_ColorExpand = ShopAllCamerasPage.lb_ColorExpand(driver,row);
            Boolean lb_ColorExpand_Display = CommonActions.checkDisplayed(lb_ColorExpand);
            Assert.assertEquals(lb_ColorExpand_Display, Boolean.TRUE, "'+' not clicked.");
            Reporter.log("'+' clicked.", true);
            Reporter.log("Verify Colors displayed:", true);
            WebElement form_Color = ShopAllCamerasPage.form_Color(driver,row);
            String form_Color_Text = form_Color.getText();
            Reporter.log(form_Color_Text, true);

//            //Black
//            WebElement Color_Black = ShopAllCamerasPage.Color_Black(driver);
//            String Color_Black_text = Color_Black.getText();
//            Reporter.log(Color_Black_text, true);
//            Reporter.log("---------------------", true);
//            //Blue
//            WebElement Color_Blue = ShopAllCamerasPage.Color_Blue(driver);
//            String Color_Blue_text = Color_Blue.getText();
//            Reporter.log(Color_Blue_text, true);
//            Reporter.log("---------------------", true);
//            //Green
//            WebElement Color_Green = ShopAllCamerasPage.Color_Green(driver);
//            String Color_Green_text = Color_Green.getText();
//            Reporter.log(Color_Green_text, true);
//            Reporter.log("---------------------", true);
//            //Gray
//            WebElement Color_Gray = ShopAllCamerasPage.Color_Gray(driver);
//            String Color_Gray_text = Color_Gray.getText();
//            Reporter.log(Color_Gray_text, true);
//            Reporter.log("---------------------", true);
//            //Pink
//            WebElement Color_Pink = ShopAllCamerasPage.Color_Pink(driver);
//            String Color_Pink_text = Color_Pink.getText();
//            Reporter.log(Color_Pink_text, true);
//            Reporter.log("---------------------", true);
//            //Silver
//            WebElement Color_Silver = ShopAllCamerasPage.Color_Silver(driver);
//            String Color_Silver_text = Color_Silver.getText();
//            Reporter.log(Color_Silver_text, true);
//            Reporter.log("---------------------", true);
//            //Gold
//            WebElement Color_Gold = ShopAllCamerasPage.Color_Gold(driver);
//            String Color_Gold_text = Color_Gold.getText();
//            Reporter.log(Color_Gold_text, true);
//            Reporter.log("---------------------", true);
//            //White
//            WebElement Color_White = ShopAllCamerasPage.Color_White(driver);
//            String Color_White_text = Color_White.getText();
//            Reporter.log(Color_White_text, true);
//            Reporter.log("---------------------", true);

            //Step 6: Click on any color which listed under colors
            Reporter.log("Step 6: Click on any color which listed under colors", true);
            WebElement Color_Black = ShopAllCamerasPage.Color_Black(driver,row);
            String Color_Black_Text = Color_Black.getCssValue("background-color");
            CommonActions.clickObject(Color_Black);

            Reporter.log(Color_Black_Text, true);
            WebElement Circle_Color = ShopAllCamerasPage.Circle_Color(driver);
            String Circle_Color_Display = ((JavascriptExecutor)driver).executeScript("return window.getComputedStyle(arguments[0], ':after').getPropertyValue('border');",Circle_Color).toString();
            Reporter.log("Selected color visibly with " + Circle_Color_Display + " color", true);

            WebElement btn_Clear_Filter = ShopAllCamerasPage.btn_Clear_Filter(driver);
            CommonActions.clickObject(btn_Clear_Filter);
//
//            if(btn_Clear_Filter.isDisplayed()) {
//                Reporter.log("Filter not clear.", true);
//            } else {
//                Reporter.log("Filter clear.", true);
//            }


        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_MC2_437_TC_004_Verify_close_colors_filter_User_shopping_options() {
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
            Reporter.log("Verify Cameras page displayed'", true);
            String strCamerasTitle = "Shop Canon Cameras | Canon Canada, Inc.";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strCamerasTitle, "Cameras page not displayed.");
            Reporter.log("Cameras page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Click on "Shop all cameras"
            Reporter.log("Step 3: Click on 'Shop all cameras'", true);
            WebElement btn_ShopAllCameras = CamerasPage.btn_ShopAllCameras(driver);
            CommonActions.clickObject(btn_ShopAllCameras);

            //Step 4: Verify "+" for color under shopping option
            //Step 5: Verify Click on "+" for colors
            //Step 6: Click on any color which listed under colors
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_MC2_437_TC_005_Verify_close_colors_filter_user_shopping_options() {
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
            Reporter.log("Verify Cameras page displayed'", true);
            String strCamerasTitle = "Shop Canon Cameras | Canon Canada, Inc.";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strCamerasTitle, "Cameras page not displayed.");
            Reporter.log("Cameras page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Click on "Shop all cameras"
            Reporter.log("Step 3: Click on 'Shop all cameras'", true);
            WebElement btn_ShopAllCameras = CamerasPage.btn_ShopAllCameras(driver);
            CommonActions.clickObject(btn_ShopAllCameras);

            //Step 4: Verify "+" for color under shopping option
            WebElement lb_ViewMore = ShopAllCamerasPage.lb_ViewMore(driver);
            CommonActions.clickObject(lb_ViewMore);


            //Step 5: Verify Click on "+" for colors
            //Step 6: Click on any color which listed under colors
            //Step 7: Click "X" button top of the layered navigation
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @AfterTest
    public void closeDriver() {
//        driver.manage().deleteAllCookies();
//        driver.quit();
    }
}
