package testcases.LoginLogoutSSO;

import Utilities.Constants;
import Modules.Commons.LaunchBrowser;
import Utilities.Actions;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.QALoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class MC2_142 {
    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_142_TC001_Login_Validate_login_from_the_Canada_frontend_application_with_registered_User_and_Navigate_to_Mycanon_page() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            Thread.sleep(8000);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
            Thread.sleep(8000);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            Thread.sleep(8000);
            Reporter.log("Verify after login:", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);
            driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
            Thread.sleep(8000);
            Reporter.log("Navigated MyCanon.", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_MyAccount);
            Thread.sleep(8000);

            //Step 4: User should able to see all the user details in the page
            Reporter.log("Step 4: User should able to see all the user details in the page:", true);
            WebElement txt_UserDetail = MyAccountPage.txt_UserDetail(driver);
            Boolean User_detail_display = Actions.checkDisplayed(txt_UserDetail);
            Assert.assertEquals(User_detail_display, Boolean.TRUE, "User detail not displayed.");
            Reporter.log("User detail displayed.", true);
            Thread.sleep(8000);

            //Step 5: Logout for next test case
            Reporter.log("Step 5: Logout for next test case", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            Actions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_142_TC02_Login_Logout_SSO_Verify_that_user_is_logged_in_automatically_when_navigating_to_the_commerce_website_after_logging_in_a_SSO_application() {
        try {
            //Step 1: Log into SSO application. Navigating to the commerce website.
            Reporter.log("Start TC_02 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Log into SSO application. Navigating to the commerce website", true);
            Reporter.log("Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);
            Reporter.log("Click on Account button dropdown link and click on Login link.", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
            Thread.sleep(8000);
            Reporter.log("Provide valid login credentials and click on login CTA.", true);
            LoginQA.Execute(driver);
            Thread.sleep(8000);
            Reporter.log("Verify after login:", true);
            String strMyCanonTitle = driver.getTitle();
            Assert.assertEquals(strMyCanonTitle, strHomeTitle, "Not navigated to the same page user logged in from.");
            Reporter.log("Login successful. User navigated to the same page where the user logged in from.", true);
            driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
            Thread.sleep(8000);
            //Verify User's name
            Reporter.log("Verify User's name is displayed at the top right corner:", true);
            WebElement lb_UserName = QAHomePage.lb_UserName(driver);
            Boolean lb_UserName_display = Actions.checkDisplayed(lb_UserName);
            Assert.assertEquals(lb_UserName_display, Boolean.TRUE, "User's name not displayed.");
            Reporter.log("User's name displayed.", true);
            Thread.sleep(8000);

            //Step 5: Click on Account button at the top right corner.
            Reporter.log("Step 2: Click on Account button and verify item in Account dropdown displayed.", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            //Verify My Account button
            Reporter.log("Verify My Account button in Account dropdown is displayed:", true);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            Boolean btn_MyAccount_display = Actions.checkDisplayed(btn_MyAccount);
            Assert.assertEquals(btn_MyAccount_display, Boolean.TRUE, "Button My Account not displayed.");
            Reporter.log("Button My Account displayed.", true);
            Thread.sleep(8000);
            //Verify Logout button
            Reporter.log("Verify Logout button in Account dropdown is displayed:", true);
            WebElement btn_Logout = QAHomePage.btn_Logout(driver);
            Boolean btn_Logout_display = Actions.checkDisplayed(btn_Logout);
            Assert.assertEquals(btn_Logout_display, Boolean.TRUE, "Button Logout is not displayed.");
            Reporter.log("Button Logout displayed.", true);
            Thread.sleep(8000);

            //Step 3: Logout for next test case
            Reporter.log("Step 3: Logout for next test case.", true);
            Actions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
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
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Log in with  SSO application. Refresh the commerce website
            Reporter.log("Step 2: Log in with  SSO application. Refresh the commerce website", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
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
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_MyAccount);
            Thread.sleep(8000);
            Reporter.log("Verify My Canon page displayed:", true);
            String strMyCanonTitle = driver.getTitle();
            strCurTitle = "My Account";
            Assert.assertEquals(strCurTitle, strMyCanonTitle, "My Canon page not displayed.");
            Reporter.log("My Canon page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Click on Account button at the top right corner
            Reporter.log("Step 3: Click on Account button at the top right corner", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            //Verify My Account button
            Reporter.log("Verify My Account button in Account dropdown is displayed:", true);
            btn_MyAccount = QAHomePage.btn_Login(driver);
            Boolean btn_MyAccount_display = Actions.checkDisplayed(btn_MyAccount);
            Assert.assertEquals(btn_MyAccount_display, Boolean.TRUE, "Button My Account not displayed.");
            Reporter.log("Button My Account displayed.", true);
            Thread.sleep(8000);
            //Verify Logout button
            Reporter.log("Verify Logout button in Account dropdown is displayed:", true);
            WebElement btn_Logout = QAHomePage.btn_Logout(driver);
            Boolean btn_Logout_display = Actions.checkDisplayed(btn_Logout);
            Assert.assertEquals(btn_Logout_display, Boolean.TRUE, "Button Logout is not displayed.");
            Reporter.log("Button Logout displayed.", true);
            Thread.sleep(8000);

            //Step 4: Navigating to the commerce website
            Reporter.log("Step 4: Navigating to the commerce website", true);
            Actions.clickObject(QAHomePage.btn_Logout(driver));
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
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
            Thread.sleep(8000);
            LoginQA.Execute(driver);
            Thread.sleep(8000);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not redirected to home page.");
            Reporter.log("Login successful. User redirected to home page.", true);
            //Verify User's name
            Reporter.log("Verify User's name is displayed at the top right corner:", true);
            WebElement lb_UserName = QAHomePage.lb_UserName(driver);
            Boolean lb_UserName_display = Actions.checkDisplayed(lb_UserName);
            Assert.assertEquals(lb_UserName_display, Boolean.TRUE, "User's name not displayed.");
            Reporter.log("User's name displayed.", true);
            Thread.sleep(8000);

            //Step 6: Click on Account button at the top right corner
            Reporter.log("Step 6: Click on Account button at the top right corner", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            //Verify My Account button
            Reporter.log("Verify My Account button in Account dropdown is displayed:", true);
            btn_MyAccount = QAHomePage.btn_Login(driver);
            btn_MyAccount_display = Actions.checkDisplayed(btn_MyAccount);
            Assert.assertEquals(btn_MyAccount_display, Boolean.TRUE, "Button My Account not displayed.");
            Reporter.log("Button My Account displayed.", true);
            Thread.sleep(8000);
            //Verify Logout button
            Reporter.log("Verify Logout button in Account dropdown is displayed:", true);
            btn_Logout = QAHomePage.btn_Logout(driver);
            btn_Logout_display = Actions.checkDisplayed(btn_Logout);
            Assert.assertEquals(btn_Logout_display, Boolean.TRUE, "Button Logout is not displayed.");
            Reporter.log("Button Logout displayed.", true);
            Thread.sleep(8000);

            //Step 7: Logout for next test case
            Reporter.log("Step 7: Logout for next test case.", true);
            Actions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_142_TC04_Login_Logout_SSO_Verify_Login_page_of_the_Canada_webpage() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_04 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Account button at the top right corner. Click on Sign In CTA in the Account dropdown.
            Reporter.log("Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
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
            Boolean btn_forgot_display = Actions.checkDisplayed(btn_forgot);
            Assert.assertEquals(btn_forgot_display, Boolean.TRUE, "Forgot password button not displayed.");
            Reporter.log("Forgot password button displayed.", true);
            Thread.sleep(8000);
            //Verify Signup button displayed
            Reporter.log("Verify signup button button displayed:", true);
            WebElement btn_signup = QALoginPage.btn_signup(driver);
            Boolean btn_signup_display = Actions.checkDisplayed(btn_signup);
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

    @Test()
    public void HIT_MC2_142_TC05_Login_Logout_SSO_Verify_that_user_should_not_login_with_invalid_credentials_with_Email_and_Password() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_05 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Account button at the top right corner. Click on Sign In CTA in the Account dropdown.
            Reporter.log("Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
            Thread.sleep(8000);
            Reporter.log("Verify Login page displayed:", true);
            String strLoginTitle = "Canon Federation SSO";
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strLoginTitle, "Login page not displayed.");
            Reporter.log("Login page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Input invalid email and password to the corresponding field in the Login page (correct email, wrong password).
            Reporter.log("Step 3: Input invalid email and password to the corresponding field in the Login page (correct email, wrong password)", true);
            LoginQA.Execute_Wrong_Pass(driver);
            Thread.sleep(8000);

            //Step 4: Click on Sign In button
            Reporter.log("Step 4: Click on Sign In button", true);
            Reporter.log("Verify alert message 'The email address or password you provided is incorrect' displayed:", true);
            String displayMsg = Actions.getTexts(QALoginPage.txt_ErrorMessage(driver));
            String errorMsg = "The email address or password you provided is incorrect. Please try entering this information again.";
            Assert.assertEquals(displayMsg, errorMsg, "Error message not display correctly.");
            Reporter.log("Error message displayed correctly.", true);
            Thread.sleep(8000);

            //Step 5: Input invalid email and password to the corresponding field in the Login page (wrong email, wrong password).
            Reporter.log("Step 5: Input invalid email and password to the corresponding field in the Login page (wrong email, wrong password)", true);
            LoginQA.Execute_Wrong_Email_Pass(driver);
            Thread.sleep(8000);

            //Step 6: Click on Sign In button
            Reporter.log("Step 6: Click on Sign In button", true);
            Reporter.log("Verify alert message 'The email address or password you provided is incorrect' displayed:", true);
            displayMsg = Actions.getTexts(QALoginPage.txt_ErrorMessage(driver));
            errorMsg = "The email address or password you provided is incorrect. Please try entering this information again.";
            Assert.assertEquals(displayMsg, errorMsg, "Error message not display correctly.");
            Reporter.log("Error message displayed correctly.", true);
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_142_TC06_Login_Logout_SSO_Verify_Login_function_when_user_is_at_Home_Page() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start TC_06 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown.
            Reporter.log("Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Actions.clickObject(btn_Login);
            Thread.sleep(8000);
            Reporter.log("Verify Login page displayed:", true);
            String strLoginTitle = driver.getTitle();
            strCurTitle = "Canon Federation SSO";
            Assert.assertEquals(strCurTitle, strLoginTitle, "Login page not displayed.");
            Reporter.log("Login page displayed.", true);
            Thread.sleep(8000);

            //Step 3: Input correct email and password to the corresponding fields.
            Reporter.log("Step 3: Input correct email and password to the corresponding fields", true);
            LoginQA.Execute(driver);
            Thread.sleep(8000);

            //Step 4: Click on Sign In button.
            Reporter.log("Step 4: Click on Sign In button:", true);
            Reporter.log("Verify navigated to Homepage:", true);
            String strMyCanonTitle = driver.getTitle();
            Assert.assertEquals(strMyCanonTitle, strHomeTitle, "Not navigated to Homepage.");
            Reporter.log("Login successful. User navigated to Homepage.", true);
            driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
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
