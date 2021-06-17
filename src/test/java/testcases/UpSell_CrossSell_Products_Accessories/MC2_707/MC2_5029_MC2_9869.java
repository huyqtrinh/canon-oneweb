package testcases.UpSell_CrossSell_Products_Accessories.MC2_707;

import Modules.Commons.LaunchBrowser;
import Modules.QA.LoginQA;
import PageObjects.MobileEmulator.QA.E_QAHomePage;
import PageObjects.WebBrowser.QA.*;
import Utilities.CommonActions;
import Utilities.Constants;
import Utilities.ExcelReader;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MC2_5029_MC2_9869 {

    WebDriver driver;
    Boolean display = true;

    @BeforeTest
    public void StartBrowser() {
        driver = LaunchBrowser.getDriver(Constants.Browser);
    }

    @Test()
    public void HIT_MC2_707_TC_001_Login_Validate_the_Recommended_Accessories_section_in_the_Product_detail_page_for_Registered_user() {
        try {
            //Read data file
            ExcelReader objExcelFile = new ExcelReader();
            String filePath = Constants.TestData_Path + "UpSell_CrossSell_Products_Accessories/MC2_707";

            //Step 1: Launch Canada web application with the URL
            Reporter.log("Start testcase HIT_MC2_9869_MC2_5029 on browser " + Constants.Browser, true);
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
            if (Constants.Emulator) {
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                WebElement btn_Login = E_QAHomePage.btn_Login(driver);
                Reporter.log("Verify button login displayed:", true);
                Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed.");
                Reporter.log("Button login displayed.", true);
                CommonActions.clickObject(btn_Login);
            } else {
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

            //Step 3: User should provide valid login credentials and click on login CTA
            Reporter.log("Step 3: User should provide valid login credentials and click on login CTA", true);
            String sUsername = "canonestore@gmail.com";
            String sPassword = "Canon$5678";
            LoginQA.Execute_From_Data(driver, sUsername, sPassword);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            if (Constants.Emulator) {
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
                CommonActions.clickObject(E_QAHomePage.menu_Account(driver));
                Thread.sleep(1000);
                display = CommonActions.checkDisplayed(E_QAHomePage.btn_Logout(driver));
                Assert.assertEquals(display, Boolean.TRUE, "Login not successfully.");
                Reporter.log("Login successfully.", true);
                strCurTitle = driver.getTitle();
                Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
                Reporter.log("Navigate to the Canada Home page successfully", true);
                CommonActions.clickObject(E_QAHomePage.menu_MenuList(driver));
                Thread.sleep(1000);
            } else {
                display = CommonActions.checkDisplayed(QAHomePage.txt_UsernameText(driver));
                Assert.assertEquals(display, Boolean.TRUE, "Login not successfully.");
                Reporter.log("Login successfully.", true);
                strCurTitle = driver.getTitle();
                Assert.assertEquals(strCurTitle, strHomeTitle, "Not navigate to the Canada Home page");
                Reporter.log("Navigate to the Canada Home page successfully", true);
            }

            //Step 4: Mouseover on the product megamenu and click on the any Sub category link
            objExcelFile.setExcelFile(filePath, "MC2-9869.xlsx", "ProductDetail");
            int rowCount = objExcelFile.getRowNum();
            for (int i = 1; i <= rowCount; i++) {
                String sProductID = objExcelFile.getCellData("ID", i);
                String sCategory = objExcelFile.getCellData("Category", i);
                String sSubCategory = objExcelFile.getCellData("SubCategory", i);
                String sProductName = objExcelFile.getCellData("ProductName", i);

                String xpath_Category = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/" + sCategory.toLowerCase(Locale.ROOT) + "']";
                String xpath_SubCategory = "//*[contains(text(),'" + sSubCategory.toUpperCase(Locale.ROOT) + "')]";
                Reporter.log("Step 4: Mouseover on the Cameras and click on the any Sub category Mirrorless", true);
                Actions action = new Actions(driver);
                WebElement eCategory = driver.findElement(By.xpath(xpath_Category));
                action.moveToElement(eCategory).build().perform();
                Thread.sleep(1000);
                WebElement eSubCategory = driver.findElement(By.xpath(xpath_SubCategory));
                eSubCategory.click();
                driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
                display = CommonActions.checkDisplayed(ProductListPage.lst_SubCatList(driver));
                Assert.assertEquals(display, Boolean.TRUE, "Not redirect to PLP");
                Reporter.log("Redirected to PLP successfully", true);

                //Step 5: On the PLP click on any product which is having Recommended Accessories section configured
                Reporter.log("Step 5: On the PLP click on any product which is having Recommended Accessories section configured", true);
                CommonActions.clickObject(CamerasPage.txt_ItemName(driver, sProductName));
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                String sProductName_PDP = CommonActions.getTexts(ProductDetailPage.txt_ProductName(driver));
                Assert.assertEquals(sProductName_PDP, sProductName, "Not navigate to Product Detail page.");
                Reporter.log("Navigated to Product Detail Page.", true);

                //Step 6: On the PDP user should able to see the Recommended Accessories section in the page
                Reporter.log("Step 6: On the PDP user should able to see the Recommended Accessories section in the page", true);
                display = CommonActions.checkDisplayed(ProductDetailPage.txt_RecommendedAccessories(driver));
                Assert.assertEquals(display, Boolean.TRUE, "Recommended Accessories section not display");
                Reporter.log("Recommended Accessories section not displayed", true);

                //Step 7: User should see the carousel with left and right arrows if the number of accessories are more than 4.
                Reporter.log("Step 7: Verify carousel: ", true);
                //Verify if number of accessories are more than 4.
                List<WebElement> lst_RelatedProduct = driver.findElements(By.xpath("//div[@id='accessories']//div[contains(@class,'product-item-info')]"));
                int iRelatedProductNum = lst_RelatedProduct.size();
                if (iRelatedProductNum > 4) {
                    Reporter.log("Number of accessories are more than 4", true);
                    display = CommonActions.checkDisplayed(ProductDetailPage.btn_RAsection_Carousel_RightArrow(driver));
                    Assert.assertEquals(display, Boolean.TRUE, "Carousel not display");
                    Reporter.log("Carousel displayed", true);

                    //Step 8: Click on left and the right arrows to move the accessories, once the accessories are seen from any one side, the arrow of that side should be disabled.
                    int numClick = iRelatedProductNum - 4;
                    for( i=1;i<=numClick;i++){
                        ProductDetailPage.btn_RAsection_Carousel_RightArrow(driver).click();
                        display = CommonActions.checkDisplayed(ProductDetailPage.btn_RAsection_Carousel_RightArrow(driver));
                        Assert.assertEquals(display, Boolean.FALSE, "The right arrow not disable when the accessories are seen from any one side");
                        Reporter.log("The right arrow disabled when the accessories are seen from any one side", true);
                    }
                    for( i=1;i<=numClick;i++){
                        ProductDetailPage.btn_RAsection_Carousel_LeftArrow(driver).click();
                        display = CommonActions.checkDisplayed(ProductDetailPage.btn_RAsection_Carousel_LeftArrow(driver));
                        Assert.assertEquals(display, Boolean.FALSE, "The left arrow not disable when the accessories are seen from any one side");
                        Reporter.log("The left arrow disabled when the accessories are seen from any one side", true);
                    }

                }
                //If the accessories are 4 or less, the carousel and arrows should  not be displayed.
                else {
                    Reporter.log("Number of accessories are less than 4", true);
                    display = CommonActions.checkDisplayed(ProductDetailPage.btn_RAsection_Carousel_RightArrow(driver));
                    Assert.assertEquals(display, Boolean.FALSE, "Carousel display");
                    Reporter.log("Carousel not displayed", true);
                }

                //Step 10: Click on any product of the accessories section ,it will be redirected to the PDP page of the accessory.
                Reporter.log("Step 10: Click on any product of the accessories section", true);
                String sRelatedProductName_1stItem = CommonActions.getTexts(ProductDetailPage.btn_RelatedProduct_1stItem(driver));
                CommonActions.clickObject(ProductDetailPage.btn_RelatedProduct_1stItem(driver));
                driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
                String sActual_ProductName = CommonActions.getTexts(ProductDetailPage.txt_ProductName(driver));
                Assert.assertEquals(sActual_ProductName, sRelatedProductName_1stItem, "Not redirect to PDP of the accessory");
                Reporter.log("Redirected to PDP of the accessory", true);

                //Step 11: Check for  "Shop All Accessories"  CTA and click on it
                //Back to previous page

            }


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
