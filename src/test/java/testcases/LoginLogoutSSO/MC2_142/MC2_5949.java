package testcases.LoginLogoutSSO.MC2_142;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_5949 {
    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_142_TC03_Login_Logout_SSO_Verify_that_user_is_logged_in_automatically_when_refresh_the_commerce_website_after_logging_in_a_SSO_application() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_03 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Log in with  SSO application. Refresh the commerce website
            Reporter.log("Step 2: Log in with  SSO application. Refresh the commerce website", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_Login);
            Thread.sleep(8000);
            Reporter.log("Provide valid login credentials and click on login CTA.", true);
            LoginQA.Execute(driver);
            Thread.sleep(10000);
            Reporter.log("Verify after login:", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);
            driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
            Thread.sleep(8000);
            Reporter.log("Navigated to My Canon page.", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_MyAccount);
            Thread.sleep(8000);
            Reporter.log("Verify My Canon page displayed:", true);
            String strMyCanonTitle = driver.getTitle();
            strCurTitle = "My Account";
            Assert.assertEquals(strCurTitle, strMyCanonTitle, "My Canon page not displayed.");
            Reporter.log("My Canon page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Click on Account button at the top right corner
            Reporter.log("Step 3: Click on Account button at the top right corner", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            //Verify My Account button
            Reporter.log("Verify My Account button in Account dropdown is displayed:", true);
            btn_MyAccount = QAHomePage.btn_Login(driver);
            Boolean btn_MyAccount_display = CommonActions.checkDisplayed(btn_MyAccount);
            Assert.assertEquals(btn_MyAccount_display, Boolean.TRUE, "Button My Account not displayed.");
            Reporter.log("Button My Account displayed.", true);
            Thread.sleep(8000);
            //Verify Logout button
            Reporter.log("Verify Logout button in Account dropdown is displayed:", true);
            WebElement btn_Logout = QAHomePage.btn_Logout(driver);
            Boolean btn_Logout_display = CommonActions.checkDisplayed(btn_Logout);
            Assert.assertEquals(btn_Logout_display, Boolean.TRUE, "Button Logout is not displayed.");
            Reporter.log("Button Logout displayed.", true);
            Thread.sleep(8000);

            //Step 4: Navigating to the commerce website
            Reporter.log("Step 4: Navigating to the commerce website", true);
            CommonActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(8000);
            strCurTitle = driver.getTitle();
            Reporter.log("Verify after logout:", true);
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not redirected to home page.");
            Reporter.log("Logout successful. User redirected to home page.", true);
            Thread.sleep(8000);

            //Step 5: Log in a SSO application. Refresh the commerce website
            Reporter.log("Step 5: Log in a SSO application. Refresh the commerce website", true);
            //Close popup
            btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            btn_Login = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_Login);
            Thread.sleep(8000);
            LoginQA.Execute(driver);
            Thread.sleep(8000);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not redirected to home page.");
            Reporter.log("Login successful. User redirected to home page.", true);
            //Verify User's name
            Reporter.log("Verify User's name is displayed at the top right corner:", true);
            WebElement lb_UserName = QAHomePage.lb_UserName(driver);
            Boolean lb_UserName_display = CommonActions.checkDisplayed(lb_UserName);
            Assert.assertEquals(lb_UserName_display, Boolean.TRUE, "User's name not displayed.");
            Reporter.log("User's name displayed.", true);
            Thread.sleep(8000);

            //Step 6: Click on Account button at the top right corner
            Reporter.log("Step 6: Click on Account button at the top right corner", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            //Verify My Account button
            Reporter.log("Verify My Account button in Account dropdown is displayed:", true);
            btn_MyAccount = QAHomePage.btn_Login(driver);
            btn_MyAccount_display = CommonActions.checkDisplayed(btn_MyAccount);
            Assert.assertEquals(btn_MyAccount_display, Boolean.TRUE, "Button My Account not displayed.");
            Reporter.log("Button My Account displayed.", true);
            Thread.sleep(8000);
            //Verify Logout button
            Reporter.log("Verify Logout button in Account dropdown is displayed:", true);
            btn_Logout = QAHomePage.btn_Logout(driver);
            btn_Logout_display = CommonActions.checkDisplayed(btn_Logout);
            Assert.assertEquals(btn_Logout_display, Boolean.TRUE, "Button Logout is not displayed.");
            Reporter.log("Button Logout displayed.", true);
            Thread.sleep(8000);

            //Step 7: Logout for next test case
            Reporter.log("Step 7: Logout for next test case.", true);
            CommonActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(8000);
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
