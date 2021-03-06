package testcases.LoginLogoutSSO.MC2_140;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
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

public class MC2_5019 {
    WebDriver driver;

    @BeforeTest
    public void startBrowser() {
        this.driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test
    public void HIT_MC2_5019_TC001_Verify_Login_Logout() {
        try {
            Reporter.log("Start HIT_MC2_140_TC001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Navigated to Canada web application.", true);
            this.driver.get(Constants.MagentoQA_Url);
            this.driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(this.driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000L);
            }

            String strHomeTitle = this.driver.getTitle();

            CommonActions.clickObject(QAHomePage.ico_User(this.driver));
            Thread.sleep(8000L);
            WebElement btn_Login = QAHomePage.btn_Login(this.driver);
            Boolean display = CommonActions.checkDisplayed(btn_Login);
            Thread.sleep(8000L);
            Reporter.log("Step 2: Verify login button displayed", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed! Login button is not displayed.");
            Reporter.log("Success! Login button displayed.", true);
            CommonActions.clickObject(btn_Login);
            this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
            LoginQA.Execute(this.driver);
            this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
            Thread.sleep(8000L);

            Reporter.log("Step 3: Verify after login:", true);
            String strCurTitle = this.driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed! Unable to navigated to the the same page.");
            Reporter.log("Success! Able to navigated to the same page.", true);

            CommonActions.clickObject(QAHomePage.ico_User(this.driver));
            Thread.sleep(2000L);
            CommonActions.clickObject(QAHomePage.btn_Logout(this.driver));
            Thread.sleep(5000L);
            strCurTitle = this.driver.getTitle();
            Reporter.log("Step 4: Verify after logout:", true);
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed! Unable to redirected to home page.");
            Reporter.log("Success! Able to redirected to home page.", true);

            CommonActions.clickObject(QAHomePage.ico_User(this.driver));
            Thread.sleep(2000L);
            display = CommonActions.checkDisplayed(QAHomePage.btn_Login(this.driver));
            Reporter.log("Step 5: Recheck after logged out:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed! Login button is not displayed.");
            Reporter.log("Success! Button Login is displayed. Confirmed that user has logged out.", true);
            CommonActions.clickObject(QAHomePage.ico_User(this.driver));
            Thread.sleep(5000L);
        } catch (InterruptedException var6) {
            var6.printStackTrace();
        }

    }

    @AfterTest
    public void closeBrowser() {
        this.driver.manage().deleteAllCookies();
        this.driver.quit();
    }
}
