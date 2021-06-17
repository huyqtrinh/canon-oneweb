package testcases.LoginLogoutSSO.MC2_141;

import Modules.Commons.LaunchBrowser;
import Utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import PagesFactory.CCIHomePage;
import PagesFactory.CCILoginPage;

import java.util.concurrent.TimeUnit;

public class TC_001 {
    WebDriver driver;
    Boolean display = true;
    CCIHomePage objHomePage;
    CCILoginPage objLoginPage;

    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_141_TC001_Login_Validate_login_from_the_Canada_frontend_application_with_registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_141_TC001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

            //Create CCI home page object
            objHomePage = new CCIHomePage(driver);
            //Close popup promo
            objHomePage.closePromoPopup();
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            /*
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

             */

            //Open login page
            objHomePage.openLoginPage();
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Create CCI login page object
            objLoginPage = new CCILoginPage(driver);
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            display = objLoginPage.isOpenedLoginPage();
            Assert.assertEquals(display, Boolean.TRUE, "Not navigated to Mycanon login screen");
            Reporter.log("Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            String username = "automation@gmail.com";
            String password = "Canon123";
            objLoginPage.Login(username,password);

            /*
            if(Constants.Emulator){
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                display = CommonActions.checkDisplayed(E_QAHomePage.btn_Logout(driver));
            }

             */

            display = objHomePage.checkLoginSuccess();
            Assert.assertEquals(display, Boolean.TRUE, "Login not successfully.");
            Reporter.log("Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
            Reporter.log("Navigate to the Canada Home page successfully", true);
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
