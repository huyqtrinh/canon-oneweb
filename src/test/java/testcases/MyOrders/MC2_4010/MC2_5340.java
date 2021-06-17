package testcases.MyOrders.MC2_4010;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.MyOrdersPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.QALoginPage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MC2_5340 {
    WebDriver driver;

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test()
    public void HIT_MC2_5340_TC_002_EN_Verify_pagination_of_orders() throws InterruptedException {
        Reporter.log("Start HIT_MC2_4010_TC_002 on browser " + Constants.Browser);

        Reporter.log("Step 1: Launch the Home page for CCI");
        driver.get(Constants.MagentoQA_Url);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        CommonActions.HandlingPromoPopup(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(QAHomePage.ico_User(driver)));

        String strHomeTitle = "Home page CCI EN";
        String strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
        Reporter.log("Canada home page displayed.", true);

        //Step 2: Click on Your Account dropdown link and click on Login link
        Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
        CommonActions.clickObject(QAHomePage.ico_User(driver));
        Thread.sleep(2000);

        Reporter.log("Verify button login displayed:", true);
        Assert.assertTrue(CommonActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Button Login is not displayed.");
        Reporter.log("Button login displayed.", true);
        CommonActions.clickObject(QAHomePage.btn_Login(driver));
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
        Assert.assertTrue(CommonActions.checkDisplayed(QALoginPage.btn_LogIn(driver)), "Not navigated to Mycanon login screen");
        Reporter.log("Navigated to Mycanon login screen", true);

        //Step 3: User should provide valid login credentials and click on login CTA
        Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
        LoginQA.Execute(driver);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        CommonActions.clickObject(QAHomePage.ico_User(driver));

        Assert.assertTrue(CommonActions.checkDisplayed(QAHomePage.btn_Logout(driver)), "Login not successfully.");
        Reporter.log("Login successfully.", true);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
        Reporter.log("Navigate to the Canada Home page successfully", true);

        // Step 4: Click on My account from your Account
        Reporter.log("Step 4: Click on My account from your Account", true);
        CommonActions.HandlingPromoPopup(driver);
        Assert.assertTrue(CommonActions.checkDisplayed(QAHomePage.btn_Login(driver)), "Login not successfully.");
        Reporter.log("Click on My account link", true);
        CommonActions.clickObject(QAHomePage.btn_Login(driver));

        WebDriverWait wait_MyAccountPage = new WebDriverWait(driver, 10);
        wait_MyAccountPage.until(ExpectedConditions.visibilityOf(MyAccountPage.LeftMenu_MyAccount(driver)));

        Reporter.log("Verify Left Menu items", true);
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_MyOrders(driver)), "The \"My Order\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_MySubscriptions(driver)), "The \"My Subscriptions\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_MyWishList(driver)), "The \"My Wish List\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_StoreCredit(driver)), "The \"Store Credit\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_GiftCard(driver)), "The \"Gift Card\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_CompanyStructure(driver)), "The \"Company Structure\" left menu not displays");
        Assert.assertTrue(CommonActions.checkDisplayed(MyAccountPage.LeftMenu_AccountSettings(driver)), "The \"Account Settings\" left menu not displays");

        // Step 5: Click on "My order" from My Account page
        Reporter.log("Step 5: Click on \"My Order\" from My Account page", true);
        CommonActions.clickObject((MyAccountPage.LeftMenu_MyOrders(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "My Orders", "Not navigate to My Orders Page page");

        Reporter.log("Verify SKU Search textbox", true);
        Assert.assertTrue(CommonActions.checkDisplayed(MyOrdersPage.tbx_Search(driver)), "The \"SKU Search\" text box not displays");
        Reporter.log("The SKU Search textbox displays correctly", true);

        Reporter.log("Verify My Orders table", true);
        Assert.assertTrue(CommonActions.checkDisplayed(MyOrdersPage.tbl_Orders(driver)), "The Orders table not displays");
        Reporter.log("The My Orders table displays correctly", true);

        Reporter.log("Verify My Orders table column headers", true);
        List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
        //int rows_count = rows_table.size();
        int rows_count = 1;
        int row = 0;
        String celtext;
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
        int VP_rows_count;
        int Ex_NumofRow = 10;
        try {
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='PagesFactory']")).isDisplayed();
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
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='PagesFactory']")).isDisplayed();
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
            boolean VP_PagingDisplay = driver.findElement(By.xpath("//div[@class='PagesFactory']")).isDisplayed();
            VP_rows_count = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr")).size() - 1; // [SN] Minus 1 row of column header
            Reporter.log("Verify page size is 50", true);
            Assert.assertEquals(VP_rows_count, Ex_NumofRow);
            Reporter.log("The page size is correct as 50", true);
        }
        catch(Exception e) {
            //Reporter.log(e.toString());
            Reporter.log("There are not enough items to test paging at testing time", true);
        }
    }

    @AfterTest
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
