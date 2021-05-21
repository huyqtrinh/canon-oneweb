package testcasesPoC;

import Modules.Commons.LaunchBrowser;
import Modules.Commons.MyActions;
import Modules.QA.LoginQA;
import PageObjects.QA.QAHomePage;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TC_001 {

    WebDriver driver;

    //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
    @BeforeTest
    public void StartBrowser() throws InterruptedException {

        driver = LaunchBrowser.getDriver(Constants.Browser, Constants.MagentoQA_Url);

    }

    @Test()
    public void Verify_Login_Logout() {
        try {
            System.out.println("Start browser and navigate to Magento QA");
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (MyActions.checkDisplayed(btn_ClosePopupPromo)) {
                MyActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = driver.getTitle();

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = MyActions.checkDisplayed(btn_Login);
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Button login displayed.");
            MyActions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("LoginQA successful. User navigated to the same page where the user logged in from.");


            //Step 4: Browse the site (navigate to one or two pages)
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            System.out.println("Navigate to page : " + driver.getTitle());
            MyActions.clickObject(QAHomePage.btn_Lenses(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            System.out.println("Navigate to page : " + driver.getTitle());

            //Step 5: Click on the logout link and the user must log out successfully and must be redirected to home page.
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("Logout successful. User redirected to home page.");

            //Step 6: Reconfirm that the user is logged out by clicking on the user icon on the top right corner of the page
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            display = MyActions.checkDisplayed(QAHomePage.btn_Login(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Reconfirm that the user is logged out. Button LoginQA is displayed.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }

}
