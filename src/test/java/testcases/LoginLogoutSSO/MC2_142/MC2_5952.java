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

public class MC2_5952 {
    WebDriver driver;

    @BeforeTest
    public void StartBrowser() {

        driver = LaunchBrowser.getDriver(Constants.Browser);
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
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);
            Thread.sleep(8000);

            //Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown.
            Reporter.log("Step 2: Click on Account button at the top right corner and click on Sign In CTA in the Account dropdown", true);
            CommonActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(8000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            CommonActions.clickObject(btn_Login);
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
