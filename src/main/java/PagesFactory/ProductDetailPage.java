package PagesFactory;

import Utilities.Commons;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class ProductDetailPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"maincontent\"]/div[2]/div/div[2]/div[2]/h1/span")
    WebElement txt_ProductName;

    @FindBy(xpath = "//button[@type='submit'][@title='Add to Cart']")
    WebElement btn_AddToCart;

    @FindBy(xpath = "//meta[@itemprop='reviewCount']")
    WebElement txt_NumberOfReviews;

    @FindBy(xpath = "//div[@class='bv_stars_button_container']")
    WebElement img_RatingStars;

    @FindBy(xpath = "//button[@class='bv-write-review bv-focusable bv-submission-button']")
    WebElement btn_WriteAReview;

    @FindBy(xpath = "//*[@id=\"BVRRContainer\"]/div/div/div/div/div[2]/div[4]/div[1]/div/span")
    WebElement txt_NumberOfReviews_ReviewSection;

    @FindBy(xpath = "(//div[@class='bv-content-summary-body-text'])[1]")
    WebElement txt_ReviewContent_1stItem;

    @FindBy(xpath = "//*[contains(text(),'Recommended Accessories')]")
    WebElement txt_RecommendedAccessories;

    @FindBy(xpath = "//*[@id='accessories']/div/div[2]/div/ol/div[2]/button[2]")
    WebElement btn_RAsection_Carousel_RightArrow;

    @FindBy(xpath = "//*[@id='accessories']/div/div[2]/div/ol/div[2]/button[1]")
    WebElement btn_RAsection_Carousel_LeftArrow;

    @FindBy(xpath = "//div[@id='accessories']//div[contains(@class,'product-item-info')]/div/strong/a)[1]")
    WebElement btn_RelatedProduct_1stItem;

    @FindBy(xpath = "//*[@id=\"accessories\"]/a")
    WebElement btn_ShopAllAccessories;

    /*
        Methods
     */
    //Init element
    public ProductDetailPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }
    //Get text of element
    public String getText(WebElement element){
        return element.getText();
    }

    //Click button Add to cart by java script in case click by selenium not work
    public void clickBtnAddToCart(){
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", btn_AddToCart);
    }

    //Click Element
    public void clickElement(WebElement element){
        element.click();
    }

    //Wait PDP display
    public void waitPDPDisplay(){
        Commons.waitForElementVisible(driver,btn_AddToCart);
    }

    //Click button "Add to cart" then verify popup display
    public void addProductToCart(WebDriver driver){
        AddToCartPopup objAddToCartPopup = new AddToCartPopup(driver);
        clickElement(btn_AddToCart);
        objAddToCartPopup.waitPopupDisplay();
        Reporter.log("Verify Add to cart popup", true);
        Boolean display = objAddToCartPopup.isPopupDisplayed();
        Assert.assertEquals(display, Boolean.TRUE, "Add to cart popup not display");
        Reporter.log("Add to cart popup displayed", true);

        //Click on button Proceed to cart
        objAddToCartPopup.clickProceedToCart();
    }
}
