package testcases.webbrowser.LoginLogoutSSO.epic;

import Ultilities.Constants;
import com.modules.Commons.LaunchBrowser;
import com.modules.Commons.MyActions;
import com.modules.QA.page.LoginQA;
import com.pageobjects.WebBrowser.CustomerLogin.page.CustomerLoginPage;
import com.pageobjects.WebBrowser.QA.page.QAHomePage;
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
    public void TC_001_Verify_Login_MyAccount() {
        try {
            //Step 1: User navigates to the customer login page
            Reporter.log("Start TC_001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: User navigates to the customer login page", true);
            driver.get(Constants.MagentoCustomerLogin_Url);
            String strCustomerLoginTitle = driver.getTitle();
            String strCurCLTitle = "Customer Login";
            Assert.assertEquals(strCurCLTitle, strCustomerLoginTitle, "Customer login page not displayed.");
            Reporter.log("Customer login page displayed.", true);
            Thread.sleep(4000);

            //Step 2: Clicks on the Login With Single Sign On button and redirected to the FDS Login Page
            Reporter.log("Step 2: Clicks on the Login With Single Sign On button redirected to the FDS Login Page", true);
            WebElement btn_SignIn = CustomerLoginPage.btn_SignIn(driver);
            MyActions.clickObject(btn_SignIn);
            driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);

            //Step 3: Enters valid credentials
            Reporter.log("Step 3: Enters in valid credentials.", true);
            LoginQA.Execute(driver);
            Thread.sleep(8000);

            //Step 4: Verify navigated to the commerce website after login
            Reporter.log("Step 4: Verify navigated to the commerce website after login:", true);
            String strHomeTitle = driver.getTitle();
            String strCurTitle = "Home page CCI EN";
            Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigated to the commerce website.");
            Reporter.log("Login successful. User navigated to the commerce website.", true);
            driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);

            //Step 5: Close popup
            Reporter.log("Step 5: Close popup", true);
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(8000);
            }

            //Step 6: Navigates to the customer account page
            Reporter.log("Step 6: Navigates to the customer account page.", true);
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(7000);
            WebElement btn_MyAccount = QAHomePage.btn_Login(driver);
            MyActions.clickObject(btn_MyAccount);
            Thread.sleep(2000);

            //Step 7: Verify My Account page displayed
            String strMyAccountTittle = driver.getTitle();
            String strMACurTittle = "My Account";
            Reporter.log("Step 7: Verify My Account page displayed:", true);
            Assert.assertEquals(strMACurTittle, strMyAccountTittle, "My Account page is not displayed.");
            Reporter.log("My Account page is displayed.", true);
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
