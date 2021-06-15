package testcases.LoginLogoutSSO.MC2_142;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.QALoginPage;
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

public class MC2_5950 {
    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void MC2_5950_TC04_Login_Logout_SSO_Verify_Login_page_of_the_Canada_webpage() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_04 on browser " + Constants.Browser, true);
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

            //Step 2: Click on Account button at the top right corner. Click on Sign In CTA in the Account dropdown.
            Reporter.log("Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_Login);
            Thread.sleep(8000);
            Reporter.log("Verify Login page displayed:", true);
            String strLoginTitle = "Canon Federation SSO";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strLoginTitle, "Login page not displayed.");
            Reporter.log("Login page displayed.", true);

            //Step 3: Check the login page
            Reporter.log("Step 3: Check the login page", true);
            //Verify input username enabled
            Reporter.log("Verify input username enabled:", true);
            boolean tb_Username_Enable = QALoginPage.tb_Username(driver).isEnabled();
            if(tb_Username_Enable) {
                Reporter.log("Input username enabled.", true);
            } else {
                Reporter.log("Input username not enabled.", true);
            }
            //Verify input password enabled
            Reporter.log("Verify input password enabled:", true);
            boolean tb_Password_Enable = QALoginPage.tb_Password(driver).isEnabled();
            if(tb_Password_Enable) {
                Reporter.log("Input password enabled.", true);
            } else {
                Reporter.log("Input password not enabled.", true);
            }
            //Verify forgot password button displayed
            Reporter.log("Verify forgot password button displayed:", true);
            WebElement btn_forgot = QALoginPage.btn_forgot(driver);
            Boolean btn_forgot_display = CommonActions.checkDisplayed(btn_forgot);
            Assert.assertEquals(btn_forgot_display, Boolean.TRUE, "Forgot password button not displayed.");
            Reporter.log("Forgot password button displayed.", true);
            Thread.sleep(8000);
            //Verify Signup button displayed
            Reporter.log("Verify signup button button displayed:", true);
            WebElement btn_signup = QALoginPage.btn_signup(driver);
            Boolean btn_signup_display = CommonActions.checkDisplayed(btn_signup);
            Assert.assertEquals(btn_signup_display, Boolean.TRUE, "Signup button not displayed.");
            Reporter.log("Signup button displayed.", true);
            Thread.sleep(8000);

            //Step 4: Input email and password to the corresponding field in the Login page
            Reporter.log("Step 4: Input email and password to the corresponding field in the Login page", true);
            QALoginPage.tb_Username(driver).clear();
            QALoginPage.tb_Password(driver).clear();
            Thread.sleep(8000);
            QALoginPage.tb_Username(driver).sendKeys(Constants.MagentoQA_Username);
            QALoginPage.tb_Password(driver).sendKeys(Constants.MagentoQA_Password);
            Thread.sleep(8000);
            Reporter.log("Verify inputted values are displayed in corresponding fields:", true);
            String tb_Username = QALoginPage.tb_Username(driver).getText();
            if(tb_Username != null) {
                Reporter.log("Inputted values are displayed in the username field.", true);
            } else {
                Reporter.log("Inputted values are not displayed in the username field.", true);
            }
            String tb_Password = QALoginPage.tb_Password(driver).getText();
            if(tb_Password != null) {
                Reporter.log("Inputted values are displayed in the username field.", true);
            } else {
                Reporter.log("Inputted values are not displayed in the username field.", true);
            }
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
