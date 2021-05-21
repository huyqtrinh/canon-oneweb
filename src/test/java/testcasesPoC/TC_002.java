package testcasesPoC;

import Modules.Commons.LaunchBrowser;
import Modules.QA.*;
import Modules.Commons.MyActions;
import PageObjects.QA.*;
import Ultilities.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class TC_002 {
    WebDriver driver;
    SoftAssert softAssert = new SoftAssert();

    //Step 1: Navigate to the CCI(Canada) application (https://mcstaging-estore.canon.ca/en_ca/)
//    @BeforeTest
//    public void StartBrowser() throws InterruptedException {
//        driver = LaunchBrowser.getDriver(Constants.Browser, Constants.MagentoQA_Url);
//
//    }

    @Test()
    public void Verify_Rating_and_Review() {
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

            //Step 4: Click on the Product Category from mega menu (Click on Cameras > Compact Cameras)
            MyActions.clickObject(QAHomePage.btn_Cameras(driver));
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
            driver.navigate().to("https://mcstaging-estore.canon.ca/en_ca/cameras/compact-cameras");
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            strCurTitle = driver.getTitle();
            Assert.assertEquals(strCurTitle, "Shop Canon Compact Cameras | Canon Canada, Inc.");
            System.out.println("Navigated to Compact Cameras");

            //Step 5: Click on Powershot SX540 HS from the Product List Page. This will navigate the user to Product Detail page (PDP)
            MyActions.clickObject(CamerasPage.txt_PowerShotSX540HS(driver));
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
            String sProductName = MyActions.getTexts(ProductDetailPage.txt_ProductName(driver));
            Assert.assertEquals(sProductName, "Powershot SX540 HS");
            System.out.println("Navigated to Product Detail Page.");

            //Step 6: Make a note of the number of reviews that are available for the product
            String sReviewNum = MyActions.getTexts(ProductDetailPage.txt_NumberOfReviews(driver));
            System.out.println("Number of reviews that are available for the product: " + sReviewNum);

            //Step 7: Click on the Rating (Stars). This will navigate the user to Review section
            MyActions.clickObject(ProductDetailPage.img_RatingStars(driver));
            Thread.sleep(500);
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to review section.");

            //Step 8: Compare the number of ratings in the Review section. Both should match
            String sRvNum = ((JavascriptExecutor) driver).executeScript("res = arguments[0].split(\" \"); return res[2];", MyActions.getTexts(ProductDetailPage.txt_NumberOfReviews_ReviewSection(driver))).toString();
            Assert.assertEquals(sRvNum, sReviewNum);
            System.out.println("Number of reviews are all match.");

            //Step 9: Click on Write A Review button. User will be navigated to My Review page
            MyActions.clickObject(ProductDetailPage.btn_WriteAReview(driver));
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("bv-text-field-title")));
            display = MyActions.checkDisplayed(PostReviewPopup.tbx_ReviewTitle(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Navigated to My Review page.");

            //Step 10: Enter all the details and click on Post Review. A confirmation message will appear. User should be navigated back to the Review section
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            MyActions.clickObject(PostReviewPopup.rad_OverallRating_5stars(driver));
            String sReviewContent = ("This is test review content. This is test review content. " + timestamp.getTime());
            String sReviewTitle = ("Test Review Title");
            MyActions.setTexts(PostReviewPopup.tbx_ReviewTitle(driver),sReviewTitle);
            MyActions.setTexts(PostReviewPopup.tbx_ReviewContent(driver),sReviewContent);
            MyActions.clickObject(PostReviewPopup.rad_RecommendYes(driver));
            MyActions.setTexts(PostReviewPopup.tbx_NickName(driver),"automation test");
            MyActions.setTexts(PostReviewPopup.tbx_Email(driver),"automation test");
            MyActions.selectItemIndex(PostReviewPopup.slt_PurchaseLocation(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_LengthUse(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Replacement(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Expertise(driver),1);
            MyActions.selectItemIndex(PostReviewPopup.slt_Enthusiast(driver),1);
            MyActions.clickObject(PostReviewPopup.rad_Features_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Performance_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Value_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Quality_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_Satisfaction_5stars(driver));
            MyActions.clickObject(PostReviewPopup.rad_PromoScore_10(driver));
            MyActions.setTexts(PostReviewPopup.tbx_PromoComment(driver),"Test promo comment.");
            MyActions.clickObject(PostReviewPopup.cbx_TermAndConditions(driver));
            Thread.sleep(2000);
            MyActions.clickObject(PostReviewPopup.btn_AcceptTerm(driver));
            MyActions.clickObject(PostReviewPopup.btn_PostReview(driver));
            Thread.sleep(2000);

            //Verify after click button post review
            display = MyActions.checkDisplayed(PostReviewPopup.txt_ReviewSubmitted(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Confirmation message displayed");
            MyActions.clickObject(PostReviewPopup.btn_CloseConfirmPopup(driver));
            display = MyActions.checkDisplayed(ProductDetailPage.btn_WriteAReview(driver));
            Assert.assertEquals(display, Boolean.TRUE);
            System.out.println("Back to Reviews section.");

            //Step 11: User must be able to view the latest review in the Review section.
            String content = MyActions.getTexts(ProductDetailPage.txt_ReviewContent_1st(driver));
            Assert.assertEquals(content, sReviewContent);
            System.out.println("Latest review is displayed con Review section.");

            //Logout
            MyActions.clickObject(QAHomePage.ico_User(driver));
            Thread.sleep(2000);
            MyActions.clickObject(QAHomePage.btn_Logout(driver));
            Thread.sleep(5000);
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
