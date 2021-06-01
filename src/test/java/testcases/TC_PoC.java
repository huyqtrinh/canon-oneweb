package testcases;

import Modules.CCIAdmin.LoginAdmin;
import Modules.Commons.LaunchBrowser;
import Modules.Commons.MyActions;
import Modules.OrderManagement.LoginOrderManagement;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.CCIAdmin.AdminHomePage;
import PageObjects.WebBrowser.CCIAdmin.OrdersPage;
import PageObjects.WebBrowser.OrderManagement.DetailOrderPage;
import PageObjects.WebBrowser.OrderManagement.OrderManagementHomePage;
import PageObjects.WebBrowser.QA.*;
import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TC_PoC {

    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser, Constants.MagentoQA_Url);
    }

    @Test()
    public void TC_001_Verify_Login_Logout() {
        try {
            Reporter.log("Start TC_001 on browser " + Constants.Browser, true);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();
            Reporter.log("Step 1: Navigated to CCI application.", true);

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Reporter.log("Step 2: Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
            Reporter.log("Button login displayed.", true);
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            Reporter.log("Step 3: Verify after login:", true);
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);


            //Step 4: Browse the site (navigate to one or two pages)
            Reporter.log("Step 4: Browse the site:", true);
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            Reporter.log("Navigate to page : " + driver.getTitle(), true);
            MyActions.clickObject(QAHomePage.btn_Lenses(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            Reporter.log("Navigate to page : " + driver.getTitle(), true);

            //Step 5: Click on the logout link and the user must log out successfully and must be redirected to home page.
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
            strCurTitle = driver.getTitle();
            Reporter.log("Step 5: Verify after logout:", true);
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not redirected to home page.");
            Reporter.log("Logout successful. User redirected to home page.", true);

            //Step 6: Reconfirm that the user is logged out by clicking on the user icon on the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            display = MyActions.checkDisplayed(QAHomePage.btn_Login(driver));
            Reporter.log("Step 6: Reconfirm after logged out:", true);
            Assert.assertEquals(display, Boolean.TRUE, "User icon not displayed.");
            Reporter.log("Reconfirm that the user is logged out. Button Login is displayed.", true);
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void TC_002_Verify_Rating_and_Review() {
        try {

            Reporter.log("Start TC_002 on browser " + Constants.Browser);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();
            Reporter.log("Step 1: Navigated to CCI application.", true);

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Reporter.log("Step 2: Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
            Reporter.log("Button login displayed.", true);
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            Reporter.log("Step 3: Verify after login:", true);
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);

            //Step 4: Click on the Product Category from mega menu (Click on Cameras > Compact Cameras)
            Reporter.log("Step 4: Verify after click on the Product Category from mega menu:", true);
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.", "Not navigate to Compact Cameras");
            Reporter.log("Navigated to Compact Cameras", true);

            //Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)
            String sProductName = "PowerShot SX540 HS";
            Reporter.log("Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)", true);
            MyActions.clickObject(CamerasPage.txt_ItemName(driver, sProductName));
            Reporter.log("Verify after click product name:", true);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName_PDP = MyActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName_PDP, sProductName, "Not navigate to Product Detail page.");
            Reporter.log("Navigated to Product Detail Page.", true);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            Thread.sleep(5000);

            //Step 6: Make a note of the number of reviews that are available for the product
            Reporter.log("Step 6: Make a note of the number of reviews that are available for the product:", true);
            String sReviewNum = ProductDetailPage.txt_NumberOfReviews(driver).getAttribute("content");
            Reporter.log("Number of reviews that are available for the product: " + sReviewNum, true);

            //Step 7: Click on the Rating (Stars). This will navigate the user to Review section
            Reporter.log("Step 7: Verify after click on the Rating (Stars)", true);
            MyActions.clickObject(ProductDetailPage.img_RatingStars(driver));
            Thread.sleep(500);
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to review section");
            Reporter.log("Navigated to review section.", true);

            //Step 8: Compare the number of ratings in the Review section. Both should match
            Reporter.log("Step 8: Compare the number of ratings in the Review section:", true);
            String sRvNum = ((JavascriptExecutor) driver).executeScript("res = arguments[0].split(\" \"); return res[2];", MyActions.getTexts(ProductDetailPage.txt_NumberOfReviews_ReviewSection(driver))).toString();
            Assert.assertEquals(sRvNum, sReviewNum, "Number of review are not match");
            Reporter.log("Number of reviews are all match.", true);

            //Step 9: Click on Write A Review button. User will be navigated to My Review page
            Reporter.log("Step 9: Verify after click on Write A Review button.", true);
            MyActions.clickObject(ProductDetailPage.btn_WriteAReview(driver));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bv-text-field-title")));
            display = MyActions.checkDisplayed(PostReviewPopup.tbx_ReviewTitle(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to My Review page");
            Reporter.log("Navigated to My Review page.", true);

            //Step 10: Enter all the details and click on Post Review. A confirmation message will appear. User should be navigated back to the Review section
            Thread.sleep(2000);
            Reporter.log("Step 10: Enter all the details and click on Post Review.", true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            MyActions.clickObject(PostReviewPopup.rad_OverallRating_5stars(driver));
            String sReviewContent = ("This is test review content. This is test review content. " + timestamp.getTime());
            String sReviewTitle = ("Test Review Title");
            MyActions.setTexts(PostReviewPopup.tbx_ReviewTitle(driver), sReviewTitle);
            MyActions.setTexts(PostReviewPopup.tbx_ReviewContent(driver), sReviewContent);
            MyActions.clickObject(PostReviewPopup.rad_RecommendYes(driver));
            MyActions.setTexts(PostReviewPopup.tbx_NickName(driver), "automation test");
            MyActions.setTexts(PostReviewPopup.tbx_Email(driver), "automation@gmail.com");
            MyActions.selectItemIndex(PostReviewPopup.slt_PurchaseLocation(driver), 1);
            MyActions.selectItemIndex(PostReviewPopup.slt_LengthUse(driver), 1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Replacement(driver), 1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Expertise(driver), 1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Enthusiast(driver), 1);
            MyActions.clickObject(PostReviewPopup.rad_Features_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Performance_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Value_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Quality_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Satisfaction_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_PromoScore_10(driver));
            MyActions.setTexts(PostReviewPopup.tbx_PromoComment(driver), "Test promo comment.");
            MyActions.clickObject(PostReviewPopup.cbx_TermAndConditions(driver));
            Thread.sleep(2000);
            MyActions.clickObject(PostReviewPopup.btn_AcceptTerm(driver));
            Thread.sleep(2000);
            MyActions.clickObject(PostReviewPopup.btn_PostReview(driver));
            Thread.sleep(10000);

            //Verify after click button post review
            Reporter.log("Verify after click button post review", true);
            display = MyActions.checkDisplayed(PostReviewPopup.txt_ReviewSubmitted(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Confirmation message not display");
            Reporter.log("Confirmation message displayed");
            MyActions.clickObject(PostReviewPopup.btn_CloseConfirmPopup(driver));
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not back to Reviews section.");
            Reporter.log("Back to Reviews section.", true);

            //Step 11: User must be able to view the latest review in the Review section.
            Reporter.log("Step 11: Verify user can view the latest review in the Review section.", true);
            String content = MyActions.getTexts(ProductDetailPage.txt_ReviewContent_1st(driver));
            Assert.assertEquals(content, sReviewContent);
            Reporter.log("Latest review is displayed on Review section.", true);

            //Logout
            /*
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", E_QAHomePage.ico_User(driver));
            MyActions.clickObject(E_QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(E_QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
            */
        } catch (InterruptedException e) {
            Reporter.log(e.toString(), true);
        }
    }

    @Test()
    public void TC_003_Verify_Create_and_move_Orders() {
        WebDriverWait wait = new WebDriverWait(driver,20);
        try {

            Reporter.log("Start TC_003 on browser " + Constants.Browser);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();
            Reporter.log("Step 1: Navigated to CCI application.", true);

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Reporter.log("Step 2: Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
            Reporter.log("Button login displayed.", true);
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            Reporter.log("Step 3: Verify after login:", true);
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);

            //Step 4: Click on the Product Category from mega menu (Click on Cameras > Compact Cameras)
            Reporter.log("Step 4: Verify after click on the Product Category from mega menu:", true);
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.", "Not navigate to Compact Cameras");
            Reporter.log("Navigated to Compact Cameras", true);

            //Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)
            String sProductName = "PowerShot G3 X";
            Reporter.log("Step 5: Click on PowerShot G3 X HS from the Product List Page. This will navigate the user to Product Detail page (PDP)", true);
            MyActions.clickObject(CamerasPage.txt_ItemName(driver, sProductName));
            Reporter.log("Verify after click product name:", true);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName_PDP = MyActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName_PDP, sProductName, "Not navigate to Product Detail page.");
            Reporter.log("Navigated to Product Detail Page.", true);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            Thread.sleep(5000);

            //Step 6: Click on Add to Cart. An pop up page must appear with product details and pricing
            Reporter.log("Step 6: Verify after click button Add to Cart:", true);
            MyActions.clickObject(ProductDetailPage.btn_AddToCart(driver));
            Thread.sleep(10000);
            display = MyActions.checkDisplayed(AddToCartPopup.btn_ProceedToCart(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Button ProceedToCart not appear. Popup not display.");
            Reporter.log("Pop up appeared.", true);
            Boolean checkProductDetail = MyActions.checkDisplayed(AddToCartPopup.txt_ProductName(driver)) && MyActions.checkDisplayed(AddToCartPopup.txt_ProductPrice(driver)) && MyActions.checkDisplayed(AddToCartPopup.txt_ProductAttribute(driver));
            Assert.assertEquals(checkProductDetail, Boolean.TRUE, "Product detail not display.");
            Reporter.log("Product detail and pricing displayed.", true);

            //Step 7: Click on Proceed to Cart. User must be navigated to the Shopping Cart
            Reporter.log("Step 7: Verify after click on Proceed to Cart:", true);
            MyActions.clickObject(AddToCartPopup.btn_ProceedToCart(driver));
            Thread.sleep(10000);
            display = MyActions.checkDisplayed(ShoppingCartPage.btn_Checkout(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Button Check out not display. Not navigate to Shopping Cart.");
            Reporter.log("Navigated to Shopping Cart.", true);

            //Step 8: Click on Check out. User must be navigated to Check Out > Shipping options page
            Reporter.log("Step 8: Verify after click on Check out:", true);
            MyActions.clickObject(ShoppingCartPage.btn_Checkout(driver));
            Thread.sleep(5000);
            display = MyActions.checkDisplayed(CheckoutPage.btn_ContinueToPayment(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Button Continue to Payment not display. Not navigate to Shipping Options page.");
            Reporter.log("Navigated to Shipping Options Page.", true);
            Thread.sleep(5000);

            //Step 9: Select Fixed option under Shipping Methods
            Reporter.log("Step 9: Select Fixed option under Shipping Methods.", true);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click()", CheckoutPage.rad_1stAddress(driver));
            Thread.sleep(3000);
            MyActions.clickObject(CheckoutPage.rad_Fixed(driver));
            Thread.sleep(3000);

            //Step 10: Click on Continue To Payment button. Address verification pop up might appear
            Reporter.log("Step 10: Click on Continue To Payment button, check if Address verification pop up appear.", true);
            MyActions.clickObject(CheckoutPage.btn_ContinueToPayment(driver));
            Thread.sleep(15000);
            display = MyActions.checkDisplayed(CheckoutPage.btn_UseVerifiedAddress(driver));
            if (display) {
                Reporter.log("Address verification pop up appeared.", true);
                MyActions.clickObject(CheckoutPage.btn_UseVerifiedAddress(driver));
            } else {
                Reporter.log("Address verification pop up not appeared.", true);
            }

            //Step 11: Click on Use Verified Address button. User should navigate to the Payment section
            Reporter.log("Step 11: Verify after click on Use Verified Address button:", true);
            Thread.sleep(5000);
            display = MyActions.checkDisplayed(CheckoutPage.rad_PaymentMethod_Credit(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to payment section.");
            Reporter.log("Navigated to payment section.", true);

            //Step 12: Select Credit Card Moneris
            Reporter.log("Step 12: Select Credit Card Moneris.", true);
            MyActions.clickObject(CheckoutPage.rad_PaymentMethod_Credit(driver));
            MyActions.clickObject(CheckoutPage.rad_1stCreditCard(driver));
            Thread.sleep(3000);

            //Step 13: Select Billing Address (Select Same as Shipping Address)
            Reporter.log("Step 13: Select Billing Address (Select Same as Shipping Address)", true);
            MyActions.clickObject(CheckoutPage.cbx_SameAsShippingInf(driver));
            Thread.sleep(5000);

            //Step 14: Click on Continue To Review. User should navigate to Review page
            Reporter.log("Step 14: Verify after click on Continue To Review button:", true);
            MyActions.clickObject(CheckoutPage.btn_ContinueToReview(driver));
            Thread.sleep(3000);
            String sGrandTotal = MyActions.getTexts(CheckoutPage.txt_OrderTotal(driver));
            System.out.println("Grand total = " + sGrandTotal);
            display = MyActions.checkDisplayed(CheckoutPage.btn_PlaceOrder(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to Review page.");
            Reporter.log("Navigated to Review page.", true);

            //Step 15: Scroll down and click on Place Order button. User should navigate to Thank You page
            Reporter.log("Step 15: Verify after click on Place Order button:", true);
            MyActions.clickObject(CheckoutPage.btn_PlaceOrder(driver));
            Thread.sleep(5000);
            display = MyActions.checkDisplayed(CheckoutPage.txt_OrderNumber(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigate to Thank You page.");
            Reporter.log("Navigated to Thank You page.");
            Thread.sleep(5000);

            //Step 16: Verify if the Order number appears on this page
            Reporter.log("Step 16: Verify if the Order number appears on this page", true);
            display = MyActions.checkDisplayed(CheckoutPage.txt_OrderNumber(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Order Number is not displayed.");
            String sOrderNumber = MyActions.getTexts(CheckoutPage.txt_OrderNumber(driver));
            Reporter.log("Order number is " + sOrderNumber + " displayed");
            //Step 17: Add wait time for the order to show up in Magento Admin.
            Reporter.log("Step 17: Add wait time for the order to show up in Magento Admin.", true);
            Thread.sleep(30000);

            //Step 18: Use the following URL to log into Magento Admin https://mcstaging-shop.usa.canon.com/admin
            Reporter.log("Step 18: Navigate to Magento Admin.", true);
            driver.navigate().to(Constants.MagentoCCIAdmin_Url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sPageTitle = driver.getTitle();
            Assert.assertEquals(sPageTitle, "Magento Admin", "Not navigate to Magento Admin.");
            Reporter.log("Navigated to Magento Admin.", true);

            //Step 19: Login
            Reporter.log("Step 19: Logging in to Magento Admin.", true);
            LoginAdmin.Execute(driver);
            display = MyActions.checkDisplayed(AdminHomePage.btn_Sales(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Login not success.");
            Reporter.log("Login successful");

            //Step 20: Navigate to Sales > Orders page
            Reporter.log("Step 20: Navigate to Sales > Orders page.", true);
            MyActions.clickObject(AdminHomePage.btn_Sales(driver));
            Thread.sleep(600);
            MyActions.clickObject(AdminHomePage.btn_Orders(driver));
            Thread.sleep(10000);

            //Step 21: Enter the order # from step # 16 and click on Search
            Reporter.log("Step 21: Enter the order # from step # 16 and click on Search", true);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@class='action-submit'])[2]")));
            MyActions.setTexts(AdminHomePage.tbx_Search(driver), sOrderNumber);
            MyActions.clickObject(AdminHomePage.btn_Search(driver));
            Thread.sleep(5000);

            //Step 22: Verify that the Order detail appear in the table
            Reporter.log("Step 22: Verify that the Order detail appear in the table:", true);
            String sOrderNumber_admin = MyActions.getDataFromCellTable(driver, OrdersPage.tbl_DataGrid(), 1, 2);
            Assert.assertEquals(sOrderNumber, sOrderNumber_admin, "Order number displayed incorrect");
            Reporter.log("Order number displayed correctly.", true);
            String sGrandTotal_admin = MyActions.getDataFromCellTable(driver, OrdersPage.tbl_DataGrid(), 1, 8);
            Assert.assertEquals(sGrandTotal_admin, sGrandTotal, "Grand total displayed correctly");
            Reporter.log("Grand total displayed correctly.", true);

            //Step 23: A wait time may be needed in the script as it takes a few minutes for the order to show up in Magento Order Management.
            Thread.sleep(30000);

            //Step 24: Use the following URL to log into Magento Order Management (MOM) Order Management System (magento.com)
            Reporter.log("Step 24: Navigate to Magento Order Management (MOM) Order Management System", true);
            driver.navigate().to(Constants.MagentoOrderManagement_Url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            sPageTitle = driver.getTitle();
            Assert.assertEquals(sPageTitle, "Order Management System", "Not navigate to Order Management System");
            Reporter.log("Navigated to Order Management System.", true);

            //Step 25: Username: gdewan, password C@non2021
            Reporter.log("Step 25:Login to Order Management System.", true);
            LoginOrderManagement.Execute(driver);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            display = MyActions.checkDisplayed(OrderManagementHomePage.tbx_Search(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Login unsuccessful.");
            Reporter.log("Login successful", true);

            //Step 26: On the Dashboard, enter the order # and click on Search
            Reporter.log("Step 26: On the Dashboard, enter the order # and click on Search", true);
            MyActions.setTexts(OrderManagementHomePage.tbx_Search(driver), sOrderNumber);
            MyActions.clickObject(OrderManagementHomePage.btn_Search(driver));
            Thread.sleep(5000);

            //Step 27: Verify the Order #, Product name and Price
            Reporter.log("Step 27: Verify the Order #, Product name and Price", true);
            String sOrderNumber_OM = MyActions.getTexts(DetailOrderPage.txt_OrderId(driver));
            sOrderNumber = "#"+sOrderNumber;
            Assert.assertEquals(sOrderNumber_OM, sOrderNumber, "Order number on Orders Management displayed incorrect");
            Reporter.log("Order number on Orders Management displayed correctly.");
            String sProductName_OM = MyActions.getTexts(DetailOrderPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName_OM, sProductName, "Product name on Orders Management displayed incorrect");
            Reporter.log("Product name on Orders Management displayed correctly.");
            String sGrandPrice_OM = MyActions.getTexts(DetailOrderPage.txt_Price(driver));
            sGrandPrice_OM = "$" + sGrandPrice_OM;
            Assert.assertEquals(sGrandPrice_OM, sGrandTotal, "Price on Orders Management displayed incorrect");
            Reporter.log("Grand total on Orders Management displayed correctly.");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
