package testcases.LoginLogoutSSO;

import Utilities.Constants;
import Modules.Commons.LaunchBrowser;
import Utilities.Actions;
import Modules.QA.LoginQA;
import PageObjects.WebBrowser.QA.MyAccountPage;
import PageObjects.WebBrowser.QA.QAHomePage;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

    public class MC2_140 {
        WebDriver driver;

        public MC2_140() {
        }

        @BeforeTest
        public void startBrowser() {
            this.driver = LaunchBrowser.getDriver(Constants.Browser);
        }

        @Test
        public void HIT_MC2_140_TC001_Verify_Login_Logout() {
            try {
                Reporter.log("Start HIT_MC2_140_TC001 on browser " + Constants.Browser, true);
                Reporter.log("Step 1: Navigated to Canada web application.", true);
                this.driver.get(Constants.MagentoQA_Url);
                this.driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
                WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(this.driver);
                if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                    Actions.clickObject(btn_ClosePopupPromo);
                    Thread.sleep(2000L);
                }

                String strHomeTitle = this.driver.getTitle();

                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                WebElement btn_Login = QAHomePage.btn_Login(this.driver);
                Boolean display = Actions.checkDisplayed(btn_Login);
                Thread.sleep(8000L);
                Reporter.log("Step 2: Verify login button displayed", true);
                Assert.assertEquals(display, Boolean.TRUE, "Failed! Login button is not displayed.");
                Reporter.log("Success! Login button displayed.", true);
                Actions.clickObject(btn_Login);
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
                LoginQA.Execute(this.driver);
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
                Thread.sleep(8000L);

                Reporter.log("Step 3: Verify after login:", true);
                String strCurTitle = this.driver.getTitle();
                Assert.assertEquals(strCurTitle, strHomeTitle, "Failed! Unable to navigated to the the same page.");
                Reporter.log("Success! Able to navigated to the same page.", true);

                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(2000L);
                Actions.clickObject(QAHomePage.btn_Logout(this.driver));
                Thread.sleep(5000L);
                strCurTitle = this.driver.getTitle();
                Reporter.log("Step 4: Verify after logout:", true);
                Assert.assertEquals(strCurTitle, strHomeTitle, "Failed! Unable to redirected to home page.");
                Reporter.log("Success! Able to redirected to home page.", true);

                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(2000L);
                display = Actions.checkDisplayed(QAHomePage.btn_Login(this.driver));
                Reporter.log("Step 5: Recheck after logged out:", true);
                Assert.assertEquals(display, Boolean.TRUE, "Failed! Login button is not displayed.");
                Reporter.log("Success! Button Login is displayed. Confirmed that user has logged out.", true);
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(5000L);
            } catch (InterruptedException var6) {
                var6.printStackTrace();
            }

        }

        @Test
        public void HIT_C2_140_TC002_Verify_Login_CTA() {
            try {
                Reporter.log("Start HIT_C2_140_TC002 on browser " + Constants.Browser, true);
                this.driver.get(Constants.MagentoQA_Url);
                this.driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
                WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(this.driver);
                if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                    Actions.clickObject(btn_ClosePopupPromo);
                    Thread.sleep(2000L);
                }
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }

        }

        @Test
        public void HIT_MC2_140_TC003_Verify_User_Profile_Changes() {
            try {
                Reporter.log("Start HIT_MC2_140_TC003 on browser " + Constants.Browser, true);
                Reporter.log("Step 1: Navigated to Canada web application.", true);
                this.driver.get(Constants.MagentoQA_Url);
                this.driver.manage().timeouts().pageLoadTimeout(30L, TimeUnit.SECONDS);
                WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(this.driver);
                if (Actions.checkDisplayed(btn_ClosePopupPromo)) {
                    Actions.clickObject(btn_ClosePopupPromo);
                    Thread.sleep(8000L);
                }

                String strHomeTitle = this.driver.getTitle();
                //Step 1: Login the account
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                WebElement btn_Login = QAHomePage.btn_Login(this.driver);
                Boolean display = Actions.checkDisplayed(btn_Login);
                Thread.sleep(8000L);
                Reporter.log("Step 2: Verify login button displayed", true);
                Assert.assertEquals(display, Boolean.TRUE, "Failed! Login button is not displayed.");
                Reporter.log("Success! Login button displayed.", true);
                Actions.clickObject(btn_Login);
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
                LoginQA.Execute(this.driver);
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);

                //Step 2: Open My profile page
                Reporter.log("Step 3: Verify my account displayed", true);
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.btn_Login(this.driver));
                Thread.sleep(8000L);
                Assert.assertEquals(display, Boolean.TRUE, "Failed! My Account button is not displayed.");
                Reporter.log("Success! My Account button is displayed and redirected to My Account page");
                Reporter.log("Step 4: Apply changes to account", true);

                //Step 3: Make changes in My Profile(First name and last name)
                Actions.clickObject(MyAccountPage.btn_Edit_AccountInf(this.driver));
                Thread.sleep(8000L);
                MyAccountPage.tbx_FirstName(this.driver).clear();
                MyAccountPage.tbx_LastName(this.driver).clear();
                Thread.sleep(8000L);
                MyAccountPage.tbx_FirstName(this.driver).sendKeys(Constants.MyAccountPage_firstName);
                String display_firstname = this.driver.findElement(By.id("firstname")).getAttribute("value");
                MyAccountPage.tbx_LastName(this.driver).sendKeys(Constants.MyAccountPage_lastName);
                String display_lastname = this.driver.findElement(By.id("lastname")).getAttribute("value");
                Actions.clickObject(MyAccountPage.btn_Save(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.btn_Logout(this.driver));
                Thread.sleep(8000L);

                //Step 4: Log out and Log in again
                String strCurTitle = this.driver.getTitle();
                Reporter.log("Step 5: Verify after logout:", true);
                Assert.assertEquals(strCurTitle, strHomeTitle, "Failed! Unable to redirected to home page.");
                Reporter.log("Success! Able to redirected to home page.", true);
                Thread.sleep(8000L);

                Reporter.log("Step 6: Log in the account", true);
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.btn_Login(this.driver));
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);
                LoginQA.Execute(this.driver);
                this.driver.manage().timeouts().pageLoadTimeout(60L, TimeUnit.SECONDS);

                //Step 5: Verify the changes in My Profile page
                Reporter.log("Step 7: Confirm changes in My Profile", true);
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.ico_User(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(QAHomePage.btn_Login(this.driver));
                Thread.sleep(8000L);
                Actions.clickObject(MyAccountPage.btn_Edit_AccountInf(this.driver));
                String display_firstname_change = this.driver.findElement(By.id("firstname")).getAttribute("value");
                String display_lastname_change = this.driver.findElement(By.id("lastname")).getAttribute("value");
                Thread.sleep(8000L);
                Assert.assertEquals(display_firstname_change, display_firstname, "Failed! Firstname is altered or not matched");
                Reporter.log("Success! Firstname is matched", true);
                Assert.assertEquals(display_lastname_change, display_lastname, "Failed! Lastname is altered or not matched");
                Reporter.log("Success! Lastname is matched", true);
            } catch (InterruptedException var10) {
                var10.printStackTrace();
            }

        }

        @AfterTest
        public void closeBrowser() {
            this.driver.manage().deleteAllCookies();
            this.driver.quit();
        }
    }

