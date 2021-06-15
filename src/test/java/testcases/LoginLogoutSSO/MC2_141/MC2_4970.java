package testcases.LoginLogoutSSO.MC2_141;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.QA.*;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MC2_4970 {
    WebDriver driver;
    Boolean display = true;
    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_4970_TC004_Validate_login_from_the_checkout_page_with_Registered_user_and_check_the_SSO_session_between_Magento_and_MyCanon() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_141_TC004 on browser " + Constants.Browser, true);
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
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            Thread.sleep(8000);
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", ProductDetailPage.btn_AddToCart(driver));
            Thread.sleep(10000);
            display = CommonActions.checkDisplayed(AddToCartPopup.btn_ProceedToCart(driver));

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

            //Step 6: Click on the SignIn CTA and User should provide Valid login credentials
            Reporter.log("Step 6: Click on the SignIn CTA and User should provide Valid login credentials", true);
            CommonActions.clickObject(CustomerLoginPage.btn_Signin(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                display = CommonActions.checkDisplayed(E_QAHomePage.btn_Logout(driver));
            }
            else {
                display = CommonActions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            }
            Assert.assertEquals(display, Boolean.TRUE, "Login not successfully");
            Reporter.log("Login successfully.", true);
            display = driver.findElements(By.xpath("//li/button[@type='button'][@title='Checkout']")).size() > 0;
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to Shopping Cart.");
            Reporter.log("Navigated to Shopping Cart.", true);

            //Step 7: Click on Checkout CTA in the page and provide valid Credit card details and click on Submit CTA
            CommonActions.clickObject(ShoppingCartPage.btn_Checkout(driver));
            Thread.sleep(5000);
            executor.executeScript("arguments[0].click()", CheckoutPage.rad_1stAddress(driver));
            Thread.sleep(3000);
            CommonActions.clickObject(CheckoutPage.rad_Fixed(driver));
            Thread.sleep(3000);
            CommonActions.clickObject(CheckoutPage.btn_ContinueToPayment(driver));
            Thread.sleep(15000);
            CommonActions.clickObject(CheckoutPage.btn_UseVerifiedAddress(driver));
            CommonActions.clickObject(CheckoutPage.rad_PaymentMethod_Credit(driver));
            CommonActions.clickObject(CheckoutPage.rad_1stCreditCard(driver));
            Thread.sleep(3000);
            CommonActions.clickObject(CheckoutPage.cbx_SameAsShippingInf(driver));
            Thread.sleep(5000);
            CommonActions.clickObject(CheckoutPage.btn_ContinueToReview(driver));
            Thread.sleep(3000);
            CommonActions.clickObject(CheckoutPage.btn_PlaceOrder(driver));
            Thread.sleep(5000);
            display = CommonActions.checkDisplayed(CheckoutPage.txt_OrderNumber(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Submit order unsuccessfully");
            Reporter.log("Submit order successfully.");
            Thread.sleep(5000);

            //Step 8: Simultaneously in the same browser take a new tab Launch the Mycanon url
            Reporter.log("Step 8: Simultaneously in the same browser take a new tab Launch the Mycanon url", true);
            ((JavascriptExecutor) driver).executeScript
                    ("window.open('https://mcstaging-estore.canon.ca/en_ca/','_blank');");
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            Thread.sleep(5000);
            display = CommonActions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Session is not active");
            Reporter.log("Logged in Canada Homepage successfully as the Magento session is Active", true);

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
