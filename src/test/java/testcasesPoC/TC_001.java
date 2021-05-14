package testcasesPoC;

import Modules.LaunchBrowser;
import Modules.LoginQA;
import PageObjects.HomePageQA;
import Ultilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC_001 {

    WebDriver driver;
    HomePageQA objHomePage;
    String Browser = "WebDrivers.Edge";

    //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
    @BeforeTest
    public void StartBrowser() throws InterruptedException {
        System.out.println("Start browser and navigate to Magento QA");
        driver = LaunchBrowser.getDriver(Browser, Constants.MagentoQA_Url);

    }

    @Test()
    public void Verify_Login_Logout() {
        objHomePage = new HomePageQA(driver);
        try {
            //Close popup promo
            objHomePage.closePopupPromo();
            Thread.sleep(2000);
            String strHomeTitle = driver.getTitle();

            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            objHomePage.clickOnUserIcon();
            Thread.sleep(2000);
            Boolean display = objHomePage.checkBtnLoginDisplayed();
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Button login displayed.");
            objHomePage.clickOnButtonLogin();
            Thread.sleep(5000);
            LoginQA.Execute(driver);
            Thread.sleep(30000);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("Login successful. User navigated to the same page where the user logged in from.");


            //Step 4: Browse the site (navigate to one or two pages)
            objHomePage.clickOnCamerasBtn();
            Thread.sleep(5000);
            System.out.println("Navigate to page : " + driver.getTitle());
            objHomePage.clickOnLensesBtn();
            Thread.sleep(5000);
            System.out.println("Navigate to page : " + driver.getTitle());

            //Step 5: Click on the logout link and the user must log out successfully and must be redirected to home page.
            objHomePage.clickOnUserIcon();
            Thread.sleep(2000);
            objHomePage.clickOnButtonLogout();
            Thread.sleep(5000);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle);
            System.out.println("Logout successful. User redirected to home page.");

            //Step 6: Reconfirm that the user is logged out by clicking on the user icon on the top right corner of the page
            objHomePage.clickOnUserIcon();
            Thread.sleep(2000);
            display = objHomePage.checkBtnLoginDisplayed();
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Reconfirm that the user is logged out. Button Login is displayed.");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeDriver() {
        driver.quit();
    }

}
