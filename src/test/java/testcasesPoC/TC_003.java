package testcasesPoC;

import Modules.Commons.MyActions;
import Modules.Commons.LaunchBrowser;
import Modules.OrderManagement.LoginOrderManagement;
import Modules.QA.*;
import Modules.CCIAdmin.LoginAdmin;
import PageObjects.CCIAdmin.AdminHomePage;
import PageObjects.CCIAdmin.OrdersPage;
import PageObjects.OrderManagement.DetailOrderPage;
import PageObjects.OrderManagement.OrderManagementHomePage;
import PageObjects.QA.*;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC_003 {
    WebDriver driver;

    //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
//    @BeforeTest
//    public void StartBrowser() throws InterruptedException {
//        driver = LaunchBrowser.getDriver(Constants.Browser, Constants.MagentoQA_Url);
//
//    }

    @Test()
    public void Verify_Create_and_move_Orders() {
        try {

            System.out.println("Start browser and navigate to Magento QA");
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
            String sOrderNumber_admin = MyActions.getDataFromCellTable(driver,OrdersPage.tbl_DataGrid(),1,2);
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
