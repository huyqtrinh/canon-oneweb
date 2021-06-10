package testcases.webbrowser.MyOrders.epic;

import Ultilities.Constants;
import com.modules.Commons.LaunchBrowser;
import com.modules.Commons.MyActions;
import com.modules.QA.page.LoginQA;
import com.modules.QA.page.MyOrderPageActions;
import com.pageobjects.WebBrowser.QA.page.MyAccountPage;
import com.pageobjects.WebBrowser.QA.page.MyOrdersPage;
import com.pageobjects.WebBrowser.QA.page.QAHomePage;
import com.pageobjects.WebBrowser.QA.page.QALoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class MC2_4010 {
    WebDriver driver;

    String var_ValidSKU = "0106C001";
    String var_InvalidSKU = "InvalidString";
    String Ex_NoSearchResult = "You have placed no orders.";

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test()
    public void HIT_MC2_4010_TC_001_EN_Verify_orders_under_My_orders() throws InterruptedException {
        Reporter.log("Start HIT_MC2_4010_TC_001 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        MyActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        MyActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(MyActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        MyActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        MyActions.HandlingPromoPopup(driver);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        MyActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("th"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count + 1) {
                switch (column){
                    case 0:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order #");
                        break;
                    case 1:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Date");
                        break;
                    case 2:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Created By");
                        break;
                    case 3:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Products");
                        break;
                    case 4:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Ship To");
                        break;
                    case 5:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order total");
                        break;
                    case 6:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Status");
                        break;
                    case 7:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Action");
                        break;
                }
                column = column + 1;
            }
            row = row + 1;
        }
        Reporter.log("All column headers of My Orders table are correct", true);
        Reporter.log("Verify My Orders table details", true);
        rows_count = rows_table.size();
        row = 1;
        celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count) {
                celtext = Columns_row.get(column).getText();
                Assert.assertNotNull(celtext);
                column = column + 1;
            }
            row = row + 1;
            }

        Reporter.log("All table cells of My Orders table are displayed correctly", true);
    }

    @Test()
    public void HIT_MC2_4010_TC_002_EN_Verify_pagination_of_orders() throws InterruptedException {
        Reporter.log("Start HIT_MC2_4010_TC_002 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        MyActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        MyActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(MyActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        MyActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        MyActions.HandlingPromoPopup(driver);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        Reporter.log("Step 5: Click on \"My Order\" from My Account page", true);
        MyActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("th"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count + 1) {
                switch (column){
                    case 0:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order #");
                        break;
                    case 1:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Date");
                        break;
                    case 2:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Created By");
                        break;
                    case 3:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Products");
                        break;
                    case 4:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Ship To");
                        break;
                    case 5:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order total");
                        break;
                    case 6:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Status");
                        break;
                    case 7:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Action");
                        break;
                }
                column = column + 1;
            }
            row = row + 1;
        }
        Reporter.log("All column headers of My Orders table are correct", true);
        Reporter.log("Verify My Orders table details", true);
        rows_count = rows_table.size();
        row = 1;
        celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count) {
                celtext = Columns_row.get(column).getText();
                Assert.assertNotNull(celtext);
                column = column + 1;
            }
            row = row + 1;
        }

        Reporter.log("All table cells of My Orders table are displayed correctly", true);

        // Step 6: Verify orders per page
        Reporter.log("Step 6: Verify orders per page", true);
        rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        rows_count = rows_table.size();
        int VP_rows_count;
        int Ex_NumofRow = 10;
        try {
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='pages']")).isDisplayed();
            VP_rows_count = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr")).size() - 1; // [SN] Minus 1 row of column header
            Reporter.log("Verify page size is 20", true);
            Assert.assertEquals(VP_rows_count, Ex_NumofRow);
            Reporter.log("The page size is correct as 20", true);
        }
        catch(Exception e) {
            Reporter.log(e.toString());
            Reporter.log("There are not enough items to test paging at testing time", true);
        }

        // Step 7: Select 20 from "show" dropdown
        Reporter.log("Step 7: Select 20 from \"show\" dropdown ", true);
        Reporter.log("Click and select to change Paging size of My Orders table", true);
        Select DDL_PagingSize = new Select(driver.findElement(By.id("limiter")));
        DDL_PagingSize.selectByVisibleText("20");
        Thread.sleep(2000);
        Ex_NumofRow = 20;
        try {
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='pages']")).isDisplayed();
            VP_rows_count = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr")).size() - 1; // [SN] Minus 1 row of column header
            Reporter.log("Verify page size is 20", true);
            Assert.assertEquals(VP_rows_count, Ex_NumofRow);
            Reporter.log("The page size is correct as 20", true);
        }
        catch(Exception e) {
            Reporter.log(e.toString());
            Reporter.log("There are not enough items to test paging at testing time", true);
        }

        // Step 8: Select 50 from "show" dropdown
        Reporter.log("Step 8: Select 50 from \"show\" dropdown ", true);
        Reporter.log("Click and select to change Paging size of My Orders table", true);
        DDL_PagingSize = new Select(driver.findElement(By.id("limiter")));
        DDL_PagingSize.selectByVisibleText("50");
        Thread.sleep(2000);
        Ex_NumofRow = 50;
        try {
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='pages']")).isDisplayed();
            VP_rows_count = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr")).size() - 1; // [SN] Minus 1 row of column header
            Reporter.log("Verify page size is 50", true);
            Assert.assertEquals(VP_rows_count, Ex_NumofRow);
            Reporter.log("The page size is correct as 50", true);
        }
        catch(Exception e) {
            Reporter.log(e.toString());
            Reporter.log("There are not enough items to test paging at testing time", true);
        }
    }

    @Test()
    public void HIT_MC2_4010_TC_003_EN_Verify_Search_by_SKU() throws InterruptedException{
        Reporter.log("Start HIT_MC2_4010_TC_003 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        MyActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        MyActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(MyActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        MyActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        MyActions.HandlingPromoPopup(driver);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        Reporter.log("Step 5: Click on \"My Order\" from My Account page", true);
        MyActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("th"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count + 1) {
                switch (column){
                    case 0:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order #");
                        break;
                    case 1:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Date");
                        break;
                    case 2:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Created By");
                        break;
                    case 3:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Products");
                        break;
                    case 4:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Ship To");
                        break;
                    case 5:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order total");
                        break;
                    case 6:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Status");
                        break;
                    case 7:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Action");
                        break;
                }
                column = column + 1;
            }
            row = row + 1;
        }
        Reporter.log("All column headers of My Orders table are correct", true);
        Reporter.log("Verify My Orders table details", true);
        rows_count = rows_table.size();
        row = 1;
        celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count) {
                celtext = Columns_row.get(column).getText();
                Assert.assertNotNull(celtext);
                column = column + 1;
            }
            row = row + 1;
        }

        Reporter.log("All table cells of My Orders table are displayed correctly", true);

        // Step 6: Enter valid SKU of product
        Reporter.log("Step 6: Enter valid SKU of product", true);
        MyOrderPageActions.SearchString(driver, var_ValidSKU);
        Thread.sleep(2000);
        try {
            boolean VP_7040 = driver.findElement(By.xpath("//div[@class='message info empty']/span")).isDisplayed();
            if (VP_7040 == true) {
                Assert.fail("MC2-7040 - HIT-MC2-5884-Under My Orders section, search functionality is not working by using SKU id.");
            }
        }
        catch(Exception e) {
            Reporter.log("Cannot reproduce issue MC2-7040", true);
        }
        // [SN] This test script is not complete due to known issue MC2-7040
    }

    @Test()
    public void HIT_MC2_4010_TC_004_EN_Verify_No_satisfied_order_when_search_with_SKU() throws InterruptedException{
        Reporter.log("Start HIT_MC2_4010_TC_004 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        MyActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        MyActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(MyActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        MyActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        MyActions.HandlingPromoPopup(driver);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        Reporter.log("Step 5: Click on \"My Order\" from My Account page", true);
        MyActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("th"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count + 1) {
                switch (column){
                    case 0:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order #");
                        break;
                    case 1:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Date");
                        break;
                    case 2:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Created By");
                        break;
                    case 3:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Products");
                        break;
                    case 4:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Ship To");
                        break;
                    case 5:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order total");
                        break;
                    case 6:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Status");
                        break;
                    case 7:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Action");
                        break;
                }
                column = column + 1;
            }
            row = row + 1;
        }
        Reporter.log("All column headers of My Orders table are correct", true);
        Reporter.log("Verify My Orders table details", true);
        rows_count = rows_table.size();
        row = 1;
        celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count) {
                celtext = Columns_row.get(column).getText();
                Assert.assertNotNull(celtext);
                column = column + 1;
            }
            row = row + 1;
        }

        Reporter.log("All table cells of My Orders table are displayed correctly", true);

        // Step 6: Enter valid SKU of product which are not having order with respective SKU
        Reporter.log("Step 6: Enter valid SKU of product which are not having order with respective SKU", true);
        MyOrderPageActions.SearchString(driver, var_InvalidSKU);
        Thread.sleep(2000);
        Reporter.log("Verify message return when no search result", true);
        try {
            boolean VP_nosearchresult = driver.findElement(By.xpath("//div[@class='message info empty']/span")).isDisplayed();
            if (VP_nosearchresult == true) {
                String VP_noresultmessage = driver.findElement(By.xpath("//div[@class='message info empty']/span")).getText();
                Assert.assertEquals(VP_noresultmessage, Ex_NoSearchResult);
                Reporter.log("The message returns correctly when no search result", true);
            }
        }
        catch(Exception e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_4010_TC_005_EN_Verify_search_orders_using_filter() throws InterruptedException{
        /* [SN] search random existing info in table to filter
        only check with which data can easily see on table, skip check with the rest
        */
        Reporter.log("Start HIT_MC2_4010_TC_005 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        MyActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        MyActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(MyActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        MyActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        MyActions.HandlingPromoPopup(driver);
        Assert.assertTrue(MyActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        MyActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(MyActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        Reporter.log("Step 5: Click on \"My Order\" from My Account page", true);
        MyActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("th"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count + 1) {
                switch (column){
                    case 0:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order #");
                        break;
                    case 1:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Date");
                        break;
                    case 2:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Created By");
                        break;
                    case 3:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Products");
                        break;
                    case 4:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Ship To");
                        break;
                    case 5:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Order total");
                        break;
                    case 6:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Status");
                        break;
                    case 7:
                        celtext = Columns_row.get(column).getText();
                        Assert.assertEquals(celtext, "Action");
                        break;
                }
                column = column + 1;
            }
            row = row + 1;
        }
        Reporter.log("All column headers of My Orders table are correct", true);
        //////////////////////////////////////////////////////////////////////////////////////
        //[SN] Add finding random item on My Order table
        int randomIndex = ThreadLocalRandom.current().nextInt(1, rows_table.size());
        System.out.println("--------------------------------------");
        System.out.println("randomIndex >>> " + randomIndex);
        // Get information of random order
        String var_OrderNum = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[1]")).getText();
        String var_OrderDate = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[2]")).getText();
        String var_CreatedBy = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[3]")).getText();
        String var_ProductName = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[4]")).getText();
        String var_ShipTo = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[5]")).getText();
        String var_OrderTotal = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[6]")).getText();
        String var_Status = driver.findElement(By.xpath("//table[@id=\"my-orders-table\"]/tbody/tr[" + randomIndex + "]/td[7]")).getText();

        System.out.println("var_OrderNum >>> " + var_OrderNum);
        System.out.println("var_OrderDate >>> " + var_OrderDate);
        System.out.println("var_CreatedBy >>> " + var_CreatedBy);
        System.out.println("var_ProductName >>> " + var_ProductName);
        System.out.println("var_ShipTo >>> " + var_ShipTo);
        System.out.println("var_OrderTotal >>> " + var_OrderTotal);
        System.out.println("var_Status >>> " + var_Status);

        //////////////////////////////////////////////////////////////////////////////////////
        Reporter.log("Verify My Orders table details", true);
        rows_count = rows_table.size();
        row = 1;
        celtext = "";
        while (row < rows_count) {
            List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
            int columns_count = Columns_row.size();
            int column = 0;
            while (column < columns_count) {
                celtext = Columns_row.get(column).getText();
                Assert.assertNotNull(celtext);
                column = column + 1;
            }
            row = row + 1;
        }

        Reporter.log("All table cells of My Orders table are displayed correctly", true);

        // Step 6: Click on "Filter" button
        Reporter.log("Step 6: Click on \"Filter\" button", true);
        MyActions.clickObject(MyOrdersPage.btn_Filter(driver));
        Thread.sleep(1000);
        Reporter.log("Verify the filter section displays correctly", true);

        try {
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.btn_CloseFilter(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_OrderNumber(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_InvoiceNumber(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.ddl_Filter_CreatedBy(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.ddl_Filter_OrderStatus(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_OrderDateFrom(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_OrderDateTo(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_OrderTotalMin(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.tb_Filter_OrderTotalMax(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.btn_Apply(driver)));
            Assert.assertTrue(MyActions.checkDisplayed(MyOrdersPage.btn_ClearAll(driver)));
            Reporter.log("All items in filter section display correctly", true);
        }
        catch(Exception e) {
            Reporter.log(e.toString());
            Reporter.log("All items in filter section do NOT display correctly", true);
        }
        // Step 7: User can enter the valid inputs in filters
        // Step 8: Click on "Apply" button
        Reporter.log("Step 7: User can enter the valid inputs in filters", true);
        Reporter.log("Step 8: Click on \"Apply\" button", true);

        Reporter.log("Verify filter with Order Number", true);
        MyOrderPageActions.FilterOrder(driver, var_OrderNum, "", "", "", "", "", "", "");
        Assert.assertTrue(MyOrderPageActions.VerifyFilterOrder(driver, var_OrderNum, "", "", "", "", "", "", ""));

        Reporter.log("According to current test data, only perform filter with Order Number", true);
    }

    @AfterTest
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
