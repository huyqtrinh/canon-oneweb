
import java.util.concurrent.TimeUnit;

import PageObjects.HomePageCCI;
import PageObjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
public class TestLogin {
    String driverPath = "src/main/resources/WebDrivers/Chrome/chromedriver.exe";
    WebDriver driver;
    HomePageCCI objHomePage;
    LoginPage objLoginPage;

    @BeforeTest
    public void setup(){
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
        driver.get("https://mcstaging-estore.canon.ca/en_ca/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @Test(priority=0)
    public void test_Login_Logout(){
        //Create HomePage object
        objHomePage = new HomePageCCI(driver);
        objLoginPage = new LoginPage(driver);
        try {
            objHomePage.closePopupPromo();
            Thread.sleep(2000);
            String strTitle = driver.getTitle();
            //Step 2: Log into the application with above credentials clicking on user icon to the top right corner of the page
            objHomePage.clickOnUserIcon();
            Thread.sleep(2000);
            objHomePage.clickOnButtonLogin();
            Thread.sleep(5000);
            objLoginPage.loginToCCI();
            Thread.sleep(30000);

            //Step 3: On successful login, user will be navigated to the same page where the user logged in from
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strTitle,strCurTitle);

            //Step 4: Browse the site (navigate to one or two pages)
            objHomePage.clickOnCamerasBtn();
            Thread.sleep(5000);
            System.out.println("Navigate to page : " + driver.getTitle());
            objHomePage.clickOnLensesBtn();
            Thread.sleep(5000);
            System.out.println("Navigate to page : " + driver.getTitle());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
