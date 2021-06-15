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

public class MC2_5018 {
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
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_Login);
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
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_MyAccount);
            Thread.sleep(8000);

            //Step 4: User should able to see all the user details in the page
            Reporter.log("Step 4: User should able to see all the user details in the page:", true);
            WebElement txt_UserDetail = MyAccountPage.txt_UserDetail(driver);
            Boolean User_detail_display = CommonActions.checkDisplayed(txt_UserDetail);
            Assert.assertEquals(User_detail_display, Boolean.TRUE, "User detail not displayed.");
            Reporter.log("User detail displayed.", true);
            Thread.sleep(8000);

            //Step 5: Logout for next test case
            Reporter.log("Step 5: Logout for next test case", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
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
