package PagesFactory;

import Utilities.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddToCartPopup {
    WebDriver driver;
    /*
        Elements
     */
    @FindBy(xpath = "//button[@class='button-modal-proceed']")
    WebElement btn_ProceedToCart;

    @FindBy(xpath = "//h2[@class='product-name']")
    WebElement txt_ProductName;

    @FindBy(xpath = "//div[@class='product-price']")
    WebElement txt_ProductPrice;

    @FindBy(xpath = "//div[@class='product-attributes']")
    WebElement txt_ProductAttribute;
    /*
        Methods
     */
    //Init element
    public AddToCartPopup(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    public void waitPopupDisplay(){
        Commons.waitForElementVisible(driver,btn_ProceedToCart);
    }

    //Check if element displayed
    public boolean isPopupDisplayed(){
        return btn_ProceedToCart.isDisplayed();
    }

    //Click button Add to Cart
    public void clickProceedToCart(){
        btn_ProceedToCart.click();
    }
}
