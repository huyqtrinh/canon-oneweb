package testcases.LoginLogoutSSO.MC2_141;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.QA.*;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class MC2_4971 {
    WebDriver driver;
    Boolean display = true;
    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_4971_TC005_Validate_login_from_the_checkout_page_with_Registered_user_with_invalid_credential() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_141_TC005 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);

            //Step 2: Mouse over on the Product Meganu menu and select any subcategory link from the list displayed
            Reporter.log("Step 2: Mouse over on the Product Meganu menu and select any subcategory link from the list displayed", true);
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.btn_Cameras(driver));
                Thread.sleep(5000);
                driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
                Thread.sleep(5000);
            }
            else{
                driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            }
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.", "Not navigate to Compact Cameras");
            Reporter.log("Navigated to Compact Cameras", true);

            //Step 3: Click on any Product form the PLP and click on Add to Cart CTA
            Reporter.log("Step 3: Click on any Product form the PLP and click on Add to Cart CTA", true);
            String sProductName = "PowerShot G3 X";
            CommonActions.clickObject(CamerasPage.txt_ItemName(driver, sProductName));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName_PDP = CommonActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName_PDP, sProductName, "Not navigate to Product Detail page.");
            Reporter.log("Navigated to Product Detail Page.", true);
            Reporter.log("Click button Add to Cart", true);
            Thread.sleep(8000);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", ProductDetailPage.btn_AddToCart(driver));
            Thread.sleep(10000);

            //Step 4: User should able to see a popup window where we should able to click on Proceed to Cart link
            Reporter.log("Step 4: User should able to see a popup window where we should able to click on Proceed to Cart link", true);
            display = CommonActions.checkDisplayed(AddToCartPopup.btn_ProceedToCart(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Button ProceedToCart not appear. Popup not display.");
            Reporter.log("Pop up appeared.", true);
            Reporter.log("Click on Proceed to Cart", true);
            CommonActions.clickObject(AddToCartPopup.btn_ProceedToCart(driver));
            Thread.sleep(10000);
            display = CommonActions.checkDisplayed(ShoppingCartPage.btn_Checkout(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Button Check out not display. Not navigate to Shopping Cart.");
            Reporter.log("Navigated to Shopping Cart.", true);

            //Step 5: Click on Checkout link on the page
            Reporter.log("Step 5: Click on Checkout link on the page", true);
            CommonActions.clickObject(ShoppingCartPage.btn_Checkout(driver));
            Thread.sleep(6000);
            display = CommonActions.checkDisplayed(CustomerLoginPage.btn_Signin(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigated to login screen");
            Reporter.log("Navigated to login screen", true);

            //Step 6: Click on the SignIn CTA and User should provide invalid login credentials
            Reporter.log("Step 6: Click on the SignIn CTA and User should provide invalid login credentials", true);
            CommonActions.clickObject(CustomerLoginPage.btn_Signin(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Invalid_Execute(driver);
            Thread.sleep(2000);
            String displayMsg = CommonActions.getTexts(QALoginPage.txt_ErrorMessage(driver));
            String errorMsg = "The email address or password you provided is incorrect. Please try entering this information again.";
            Assert.assertEquals(displayMsg, errorMsg, "Error message not display correctly.");
            Reporter.log("Error message displayed correctly.", true);


        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        System.out.println("Clear session");
        driver.manage().deleteAllCookies();
        driver.quit();
        Thread.sleep(2000);
    }
}
