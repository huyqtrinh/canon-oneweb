package testcases.LoginLogoutSSO.MC2_141;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.QAHomePage;
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

public class MC2_4972 {
    WebDriver driver;
    Boolean display = true;
    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_4972_TC006_Validate_login_from_the_Magento_application_and_Navigate_to_MyCanon_Profile_page() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_141_TC006 on browser " + Constants.Browser, true);
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
                Thread.sleep(1000);
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
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Not navigated to Mycanon login screen");
            Reporter.log("Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                display = CommonActions.checkDisplayed(E_QAHomePage.btn_Logout(driver));
            }
            else {
                CommonActions.clickObject(QAHomePage.ico_User(driver));
                display = CommonActions.checkDisplayed(QAHomePage.btn_Logout(driver));
            }
            Assert.assertEquals(display, Boolean.TRUE, "Login not successfully.");
            Reporter.log("Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
            Reporter.log("Navigate to the Canada Home page successfully", true);
            Thread.sleep(4000);

            //Step 4: Click on the My Profile link available in the right top corner on the page
            Reporter.log("Click on the My Profile link available in the right top corner on the page", true);
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                display = CommonActions.checkDisplayed(E_QAHomePage.btn_Login(driver));
            }
            else {
                CommonActions.clickObject(QAHomePage.ico_User(driver));
                driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/customer/account/index");
            }
            Thread.sleep(5000);
            display = CommonActions.checkDisplayed(MyAccountPage.btn_Edit_AccountInf(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Not navigated to MyCanon profile page");
            Reporter.log("Navigated to MyCanon profile page", true);
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
