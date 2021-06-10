package testcases.webbrowser.MyOrders.epic;

import Ultilities.Constants;
import com.modules.Commons.LaunchBrowser;
import com.modules.Commons.MyActions;
import com.modules.QA.page.LoginQA;
import com.pageobjects.WebBrowser.QA.page.MyAccountPage;
import com.pageobjects.WebBrowser.QA.page.QAHomePage;
import com.pageobjects.WebBrowser.QA.page.QALoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_4088 {
    WebDriver driver;

    @BeforeMethod
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test()
    public void HIT_MC2_4088_TC_001_Update_Address_in_My_Canon() throws InterruptedException {
        Reporter.log("Start HIT_MC2_4088_TC_001 on browser " + Constants.Browser);

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

        // Step 5: Click on "Account Setting" from My Account page
        MyActions.clickObject((MyAccountPage.LeftMenu_AccountSettings(driver)));
        Thread.sleep(2000);
        strCurTitle = driver.getTitle();
        Assert.assertEquals(strCurTitle, "Account Settings", "Not navigate to My Orders Page page");
    }

    @Test()
    public void HIT_MC2_4088_TC_001_Update_Address_in_My_Canon() throws InterruptedException {

    }
    @AfterTest
    public void CloseBrowser() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
