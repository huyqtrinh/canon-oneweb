package testcases.MegaMenu;

import Utilities.Constants;
import Modules.Commons.LaunchBrowser;
import Utilities.Actions;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.CamerasPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import PageObjects.WebBrowser.QA.QALoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MC2_634 {
    WebDriver driver;
    Boolean display = true;
    @BeforeMethod()
    public void beforeMethod() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_634_TC01_Mega_Menu_Verify_mega_menu_links_in_the_homepage_which_should_navigate_404_page_when_user_clicked_on_the_links() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC001 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Canada home page not displayed.");
            Reporter.log("Canada home page displayed.", true);

            //Step 2: Click on main menu link
            Reporter.log("Step 2: Click on main menu link", true);
            Actions.clickObject(QAHomePage.btn_Cameras(driver));
            /*
            Will be update when page 404 is available
             */
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC02_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Cameras_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC002 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Camera mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Camera mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Cameras(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[1]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Camera mega menu link
            Reporter.log("Step 5: Click on Cameras mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllCameras(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/cameras", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC03_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Lenses_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC003 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Lenses mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Lenses mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Lenses(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[2]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Lenses mega menu link
            Reporter.log("Step 5: Click on Lenses mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Lenses(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllLenses(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/lenses", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC04_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Printers_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC004 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Printers mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Printers mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Printers(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[3]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Printers mega menu link
            Reporter.log("Step 5: Click on Printers mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Printers(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllPrinters(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/printers", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC05_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Lighting_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC005 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Lighting mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Lighting mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Lighting(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[4]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Lighting mega menu link
            Reporter.log("Step 5: Click on Lighting mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Lighting(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Lighting(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/lighting", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC06_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Camcorders_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC006 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Camcorders mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Camcorders mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Camcorders(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[5]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Camcorders mega menu link
            Reporter.log("Step 5: Click on Camcorders mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Camcorders(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Camcorders(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/camcorders", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC07_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Ink_Paper_Toner_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC007 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Ink,Paper&Toner mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Ink,Paper&Toner mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_InkPaperToner(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[6]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Ink,Paper&Toner mega menu link
            Reporter.log("Step 5: Click on Ink,Paper&Toner mega menu link", true);
            Actions.clickObject(QAHomePage.btn_InkPaperToner(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Ink_Paper_Toner(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/ink-paper-toner", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC08_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Accessories_Merchandise_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC008 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Accessories&Merchandise mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Accessories&Merchandise mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_AccessoriesMerchandise(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[7]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Accessories&Merchandise mega menu link
            Reporter.log("Step 5: Click on Accessories&Merchandise mega menu link", true);
            Actions.clickObject(QAHomePage.btn_AccessoriesMerchandise(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Accessories_Merchandise(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/accessories-merchandise", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC09_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_OtherProducts_megamenu_link_as_a_Registered_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC009 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: Click on Your Account dropdown link and click on Login link
            Reporter.log("Step 2: Click on Your Account dropdown link and click on Login link", true);
            Actions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            WebElement btn_Login = QAHomePage.btn_Login(driver);
            Boolean display = Actions.checkDisplayed(btn_Login);
            Reporter.log("Verify button login displayed:", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Button Login is not displayed.");
            Reporter.log("Passed. Button login displayed.", true);
            Actions.clickObject(btn_Login);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QALoginPage.btn_LogIn(driver));
            Reporter.log("Verify navigated to the Federation services i.e. Mycanon Login screen", true);
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Not navigated to Mycanon login screen");
            Reporter.log("Passed. Navigated to Mycanon login screen", true);

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            LoginQA.Execute(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Login not successfully.");
            Reporter.log("Passed. Login successfully.", true);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Not navigate to the Canada Home page");
            Reporter.log("Passed. Navigate to the Canada Home page successfully", true);

            //Step 4: On the homepage screen, hover over on Other Products mega menu
            Reporter.log("Step 4: On the homepage screen, hover over on Other Products mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_OtherProducts(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[8]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 5: Click on Other Products mega menu link
            Reporter.log("Step 5: Click on Accessories&Merchandise mega menu link", true);
            Actions.clickObject(QAHomePage.btn_OtherProducts(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_OtherProducts(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/other-products", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC10_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Cameras_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC10 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Camera mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Camera mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Cameras(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[1]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Camera mega menu link
            Reporter.log("Step 3: Click on Cameras mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllCameras(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/cameras", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC11_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Lenses_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC11 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Lenses mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Lenses mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Lenses(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[2]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Lenses mega menu link
            Reporter.log("Step 3: Click on Lenses mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Lenses(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllLenses(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/lenses", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC12_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Printers_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC12 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Printers mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Printers mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Printers(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[3]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Printers mega menu link
            Reporter.log("Step 3: Click on Printers mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Printers(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.btn_ShopAllPrinters(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/printers", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC13_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Lighting_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC13 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Lighting mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Lighting mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Lighting(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[4]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Lighting mega menu link
            Reporter.log("Step 3: Click on Lighting mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Lighting(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Lighting(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/lighting", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC14_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Camcorders_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC14 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Camcorders mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Camcorders mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_Camcorders(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[5]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Camcorders mega menu link
            Reporter.log("Step 3: Click on Camcorders mega menu link", true);
            Actions.clickObject(QAHomePage.btn_Camcorders(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Camcorders(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/camcorders", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC15_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Ink_Paper_Toner_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC15 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Ink,Paper&Toner mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Ink,Paper&Toner mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_InkPaperToner(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[6]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Ink,Paper&Toner mega menu link
            Reporter.log("Step 3: Click on Ink,Paper&Toner mega menu link", true);
            Actions.clickObject(QAHomePage.btn_InkPaperToner(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Ink_Paper_Toner(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/ink-paper-toner", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC16_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_Accessories_Merchandise_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC16 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Accessories&Merchandise mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Accessories&Merchandise mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_AccessoriesMerchandise(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[7]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Accessories&Merchandise mega menu link
            Reporter.log("Step 3: Click on Accessories&Merchandise mega menu link", true);
            Actions.clickObject(QAHomePage.btn_AccessoriesMerchandise(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_Accessories_Merchandise(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/accessories-merchandise", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
        } catch (InterruptedException e) {
            Reporter.log(e.toString());
        }
    }

    @Test()
    public void HIT_MC2_634_TC17_Mega_Menu_Verify_user_can_navigate_to_flyout_menu_with_content_blocks_when_clicked_on_OtherProducts_megamenu_link_as_a_Guest_User() {
        try {
            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_634_TC17 on browser " + Constants.Browser, true);
            Reporter.log("Step 1: Launch Canada web application with the URL", true);
            driver.get(Constants.MagentoQA_Url);
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                Actions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(2000);
            }
            String strHomeTitle = "Home page CCI EN";
            String strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, strHomeTitle, "Failed. Canada home page not displayed.");
            Reporter.log("Passed. Canada home page displayed.", true);

            //Step 2: On the homepage screen, hover over on Other Products mega menu
            Reporter.log("Step 2: On the homepage screen, hover over on Other Products mega menu", true);
            org.openqa.selenium.interactions.Actions actions = new org.openqa.selenium.interactions.Actions(driver);
            actions.moveToElement(QAHomePage.btn_OtherProducts(driver)).perform();
            Thread.sleep(2000);
            String xpath = "("+QAHomePage.btn_CLoseFlyoutMenu()+")"+"[8]";
            display = Actions.checkDisplayed(driver.findElement(By.xpath(xpath)));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. Flyout menu not displayed");
            Reporter.log("Passed. Flyout menu displayed", true);

            //Step 3: Click on Other Products mega menu link
            Reporter.log("Step 3: Click on Accessories&Merchandise mega menu link", true);
            Actions.clickObject(QAHomePage.btn_OtherProducts(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            display = Actions.checkDisplayed(CamerasPage.txt_OtherProducts(driver));
            Assert.assertEquals(display, Boolean.TRUE, "Failed. The category homepage of corresponding category not displayed");
            Reporter.log("Passed. The category homepage of corresponding category displayed", true);
            Assert.assertEquals(driver.getCurrentUrl(), "https://mcstaging-estore.canon.ca/en_ca/other-products", "Failed. Link not open on the same tab");
            Reporter.log("Passed. Link opened on the same tab", true);
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
