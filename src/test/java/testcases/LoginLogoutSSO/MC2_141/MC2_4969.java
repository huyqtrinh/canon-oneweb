package testcases.LoginLogoutSSO.MC2_141;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.QALoginPage;
import Utilities.CommonActions;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_4969 {
    WebDriver driver;
    Boolean display = true;
    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_4969_TC003_Validate_login_from_the_Magento_frontend_application_with_Registered_user_with_invalid_Password() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_141_TC003 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                WebElement btn_Login = E_QAHomePage.btn_Login(driver);
                Reporter.log("Verify button login displayed:", true);
                Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
                Reporter.log("Button login displayed.", true);
                CommonActions.clickObject(btn_Login);
            }
            else {
                CommonActions.clickObject(QAHomePage.ico_User(driver));
                Thread.sleep(2000);
                WebElement btn_Login = QAHomePage.btn_Login(driver);
                Boolean display = CommonActions.checkDisplayed(btn_Login);
                Reporter.log("Verify button login displayed:", true);
                Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
                Reporter.log("Button login displayed.", true);
                CommonActions.clickObject(btn_Login);
            }
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = CommonActions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Not navigated to Mycanon login screen");
            Reporter.log("Navigated to Mycanon login screen", true);

            //Step 3: User should provide invalid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Invalid_Execute(driver);
            Thread.sleep(2000);
            String displayMsg = CommonActions.getTexts(QALoginPage.txt_ErrorMessage(driver));
            String errorMsg = "The email address or password you provided is incorrect. Please try entering this information again.";
            Assert.assertEquals(displayMsg, errorMsg, "Error message not display correctly.");
            Reporter.log("Error message displayed correctly.", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @AfterMethod
    public void afterMethod() throws InterruptedException {
        System.out.println("Clear session");
        driver.manage().deleteAllCookies();
        driver.quit();
        Thread.sleep(2000);
    }
}
