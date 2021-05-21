package testcasesPoC;

import Modules.CCIAdmin.LoginAdmin;
import Modules.Commons.LaunchBrowser;
import Modules.Commons.MyActions;
import Modules.OrderManagement.LoginOrderManagement;
import Modules.QA.LoginQA;
import PageObjects.CCIAdmin.AdminHomePage;
import PageObjects.CCIAdmin.OrdersPage;
import PageObjects.OrderManagement.DetailOrderPage;
import PageObjects.OrderManagement.OrderManagementHomePage;
import PageObjects.QA.*;
import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TestcasesPoC {

    WebDriver driver;

    //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
    @BeforeTest
    public void StartBrowser() throws InterruptedException {

        driver = LaunchBrowser.getDriver(Constants.Browser, Constants.MagentoQA_Url);

    }

    @Test()
    public void TC_001_Verify_Login_Logout() {
        try {
            System.out.println("Start TC_001");
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Button login displayed.");
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("LoginQA successful. User navigated to the same page where the user logged in from.");


            //Step 4: Browse the site (navigate to one or two pages)
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            System.out.println("Navigate to page : " + driver.getTitle());
            MyActions.clickObject(QAHomePage.btn_Lenses(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            System.out.println("Navigate to page : " + driver.getTitle());

            //Step 5: Click on the logout link and the user must log out successfully and must be redirected to home page.
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("Logout successful. User redirected to home page.");

            //Step 6: Reconfirm that the user is logged out by clicking on the user icon on the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            display = MyActions.checkDisplayed(QAHomePage.btn_Login(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Reconfirm that the user is logged out. Button LoginQA is displayed.");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test()
    public void TC_002_Verify_Rating_and_Review() {
        try {

            System.out.println("Start TC_002");
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page

            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Button login displayed.");
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("LoginQA successful. User navigated to the same page where the user logged in from.");

            //Step 4: Click on the Product Category from mega menu (Click on Cameras > Compact Cameras)
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.");
            System.out.println("Navigated to Compact Cameras");

            //Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)
            MyActions.clickObject(CamerasPage.txt_PowerShotSX540HS(driver));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName = MyActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName, "Powershot SX540 HS");
            System.out.println("Navigated to Product Detail Page.");

            //Step 6: Make a note of the number of reviews that are available for the product
            String sReviewNum = MyActions.getTexts(ProductDetailPage.txt_NumberOfReviews(driver));
            System.out.println("Number of reviews that are available for the product: " + sReviewNum);

            //Step 7: Click on the Rating (Stars). This will navigate the user to Review section
            MyActions.clickObject(ProductDetailPage.img_RatingStars(driver));
            Thread.sleep(500);
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to review section.");

            //Step 8: Compare the number of ratings in the Review section. Both should match
            String sRvNum = ((JavascriptExecutor) driver).executeScript("res = arguments[0].split(\" \"); return res[2];", MyActions.getTexts(ProductDetailPage.txt_NumberOfReviews_ReviewSection(driver))).toString();
            Assert.assertEquals(sRvNum, sReviewNum);
            System.out.println("Number of reviews are all match.");

            //Step 9: Click on Write A Review button. User will be navigated to My Review page
            MyActions.clickObject(ProductDetailPage.btn_WriteAReview(driver));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bv-text-field-title")));
            display = MyActions.checkDisplayed(PostReviewPopup.tbx_ReviewTitle(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to My Review page.");

            //Step 10: Enter all the details and click on Post Review. A confirmation message will appear. User should be navigated back to the Review section
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            MyActions.clickObject(PostReviewPopup.rad_OverallRating_5stars(driver));
            String sReviewContent = ("This is test review content. This is test review content. " + timestamp.getTime());
            String sReviewTitle = ("Test Review Title");
            MyActions.setTexts(PostReviewPopup.tbx_ReviewTitle(driver),sReviewTitle);
            MyActions.setTexts(PostReviewPopup.tbx_ReviewContent(driver),sReviewContent);
            MyActions.clickObject(PostReviewPopup.rad_RecommendYes(driver));
            MyActions.setTexts(PostReviewPopup.tbx_NickName(driver),"automation test");
            MyActions.setTexts(PostReviewPopup.tbx_Email(driver),"automation test");
            MyActions.selectItemIndex(PostReviewPopup.slt_PurchaseLocation(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_LengthUse(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Replacement(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Expertise(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Enthusiast(driver),1);
            MyActions.clickObject(PostReviewPopup.rad_Features_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Performance_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Value_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Quality_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Satisfaction_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_PromoScore_10(driver));
            MyActions.setTexts(PostReviewPopup.tbx_PromoComment(driver),"Test promo comment.");
            MyActions.clickObject(PostReviewPopup.cbx_TermAndConditions(driver));
            Thread.sleep(2000);
            MyActions.clickObject(PostReviewPopup.btn_AcceptTerm(driver));
            MyActions.clickObject(PostReviewPopup.btn_PostReview(driver));
            Thread.sleep(2000);

            //Verify after click button post review
            display = MyActions.checkDisplayed(PostReviewPopup.txt_ReviewSubmitted(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Confirmation message displayed");
            MyActions.clickObject(PostReviewPopup.btn_CloseConfirmPopup(driver));
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Back to Reviews section.");

            //Step 11: User must be able to view the latest review in the Review section.
            String content = MyActions.getTexts(ProductDetailPage.txt_ReviewContent_1st(driver));
            Assert.assertEquals(content, sReviewContent);
            System.out.println("Latest review is displayed con Review section.");

            //Logout
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test()
    public void TC_003_Verify_Create_and_move_Orders() {
        try {

            System.out.println("Start TC_003");
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page

            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Button login displayed.");
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("LoginQA successful. User navigated to the same page where the user logged in from.");

            //Step 4: Click on the Product Category from mega menu (Click on Cameras > Compact Cameras)
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.");
            System.out.println("Navigated to Compact Cameras");

            //Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)
            MyActions.clickObject(CamerasPage.txt_PowerShotSX540HS(driver));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName = MyActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName, "Powershot SX540 HS");
            System.out.println("Navigated to Product Detail Page.");

            //Step 6: Click on Add to Cart. An pop up page must appear with product details and pricing
            MyActions.clickObject(ProductDetailPage.btn_AddToCart(driver));
            Thread.sleep(3000);
            display = MyActions.checkDisplayed(AddToCartPopup.btn_ProceedToCart(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Pop up appeared.");
            Boolean checkProductDetail = MyActions.checkDisplayed(AddToCartPopup.txt_ProductName(driver)) && MyActions.checkDisplayed(AddToCartPopup.txt_ProductPrice(driver)) && MyActions.checkDisplayed(AddToCartPopup.txt_ProductAttribute(driver));
            Assert.assertEquals(checkProductDetail, Boolean.TRUE);
            System.out.println("Product detail and pricing displayed.");

            //Step 7: Click on Proceed to Cart. User must be navigated to the Shopping Cart
            MyActions.clickObject(AddToCartPopup.btn_ProceedToCart(driver));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            display = MyActions.checkDisplayed(ShoppingCartPage.btn_Checkout(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to Shopping Cart.");

            //Step 8: Click on Check out. User must be navigated to Check Out > Shipping options page
            MyActions.clickObject(ShoppingCartPage.btn_Checkout(driver));
            Thread.sleep(3000);
            display = MyActions.checkDisplayed(CheckoutPage.btn_ContinueToPayment(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to Shipping Options Page.");

            //Step 9: Select Fixed option under Shipping Methods
            MyActions.clickObject(CheckoutPage.rad_Fixed(driver));

            //Step 10: Click on Continue To Payment button. Address verification pop up might appear
            MyActions.clickObject(CheckoutPage.btn_ContinueToPayment(driver));
            Thread.sleep(3000);
            display = MyActions.checkDisplayed(CheckoutPage.btn_UseVerifiedAddress(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Address verification pop up appeared.");

            //Step 11: Click on Use Verified Address button. User should navigate to the Payment section
            MyActions.clickObject(CheckoutPage.btn_UseVerifiedAddress(driver));
            Thread.sleep(5000);
            display = MyActions.checkDisplayed(CheckoutPage.rad_PaymentMethod_Credit(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to payment section.");

            //Step 12: Select Credit Card Moneris
            MyActions.clickObject(CheckoutPage.rad_PaymentMethod_Credit(driver));

            //Step 13: Select Billing Address (Select Same as Shipping Address)
            MyActions.clickObject(CheckoutPage.cbx_SameAsShippingInf(driver));

            //Step 14: Click on Continue To Review. User should navigate to Review page
            String sGrandTotal = MyActions.getTexts(CheckoutPage.txt_OrderTotal(driver));
            System.out.println("sGrandTotal: " + sGrandTotal);
            MyActions.clickObject(CheckoutPage.btn_ContinueToReview(driver));
            display = MyActions.checkDisplayed(CheckoutPage.btn_PlaceOrder(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to Review page.");

            //Step 15: Scroll down and click on Place Order button. User should navigate to Thank You page
            MyActions.clickObject(CheckoutPage.btn_PlaceOrder(driver));

            //Step 16: Verify if the Order number appears on this page
            display = MyActions.checkDisplayed(CheckoutPage.txt_OrderNumber(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to Thank you page. Order number is displayed");
            String sOrderNumber = MyActions.getTexts(CheckoutPage.txt_OrderNumber(driver));
            System.out.println("sOrderNumber: " + sOrderNumber);

            //Step 17: Add wait time for the order to show up in Magento Admin.
            Thread.sleep(60000);

            //Step 18: Use the following URL to log into Magento Admin https://mcstaging-shop.usa.canon.com/admin
            driver.navigate().to(Constants.MagentoCCIAdmin_Url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sPageTitle = driver.getTitle();
            Assert.assertEquals(sPageTitle, "Magento Admin");
            System.out.println("Navigated to Magento Admin.");

            //Step 19: Login
            LoginAdmin.Execute(driver);
            display = MyActions.checkDisplayed(AdminHomePage.btn_Sales(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Login successful");

            //Step 20: Navigate to Sales > Orders page
            MyActions.clickObject(AdminHomePage.btn_Sales(driver));
            Thread.sleep(600);
            MyActions.clickObject(AdminHomePage.btn_Orders(driver));
            Thread.sleep(10000);

            //Step 21: Enter the order # from step # 16 and click on Search
            MyActions.setTexts(AdminHomePage.tbx_Search(driver), sOrderNumber);
            MyActions.clickObject(AdminHomePage.btn_Search(driver));
            Thread.sleep(5000);

            //Step 22: Verify that the Order detail appear in the table
            String sOrderNumber_admin = MyActions.getDataFromCellTable(driver, OrdersPage.tbl_DataGrid(),1,2);
            sOrderNumber_admin = "#"+sOrderNumber_admin;
            Assert.assertEquals(sOrderNumber, sOrderNumber_admin);
            System.out.println("Order number displayed correctly.");
            String sGrandTotal_admin = MyActions.getDataFromCellTable(driver,OrdersPage.tbl_DataGrid(),1,8);
            Assert.assertEquals(sGrandTotal_admin, sGrandTotal);
            System.out.println("Grand total displayed correctly.");

            //Step 23: A wait time may be needed in the script as it takes a few minutes for the order to show up in Magento Order Management.
            Thread.sleep(60000);

            //Step 24: Use the following URL to log into Magento Order Management (MOM) Order Management System (magento.com)
            driver.navigate().to(Constants.MagentoOrderManagement_Url);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            sPageTitle = driver.getTitle();
            Assert.assertEquals(sPageTitle, "Order Management System");
            System.out.println("Navigated to Order Management System.");

            //Step 25: Username: gdewan, password C@non2021
            LoginOrderManagement.Execute(driver);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            display = MyActions.checkDisplayed(OrderManagementHomePage.tbx_Search(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Login successful");

            //Step 26: On the Dashboard, enter the order # and click on Search
            MyActions.setTexts(OrderManagementHomePage.tbx_Search(driver),sOrderNumber);
            MyActions.clickObject(OrderManagementHomePage.btn_Search(driver));
            Thread.sleep(5000);

            //Step 27: Verify the Order #, Product name and Price
            String sOrderNumber_OM = MyActions.getTexts(DetailOrderPage.txt_OrderId(driver));
            Assert.assertEquals(sOrderNumber_OM, sOrderNumber);
            System.out.println("Order number on Orders Management displayed correctly.");
            String sProductName_OM = MyActions.getTexts(DetailOrderPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName_OM, sProductName);
            System.out.println("Product name on Orders Management displayed correctly.");
            String sGrandPrice_OM = MyActions.getTexts(DetailOrderPage.txt_Price(driver));
            sGrandPrice_OM = "$"+sGrandPrice_OM;
            Assert.assertEquals(sGrandPrice_OM, sGrandTotal);
            System.out.println("Grand total on Orders Management displayed correctly.");


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
