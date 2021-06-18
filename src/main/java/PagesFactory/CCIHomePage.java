package PagesFactory;

import Utilities.Commons;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.Locale;

public class CCIHomePage {

    WebDriver driver;

    /*
        User control
     */
    @FindBy(xpath = "//a[@data-trigger='trigger']")
    WebElement icon_User;

    @FindBy(id = "mcLogin")
    WebElement btn_Login;

    @FindBy(id = "mcLogout")
    WebElement btn_Logout;

    @FindBy(className = "username-text")
    WebElement lb_UserName;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/customer/account/index']")
    WebElement btn_MyAccount;
    /*
        Search products
     */
    @FindBy(id = "search")
    WebElement tbx_Search;

    @FindBy(id = "toolbar-amount")
    WebElement lb_SearchResult;

    @FindBy(xpath = "//a[@href='//*[@id=\"toolbar-amount\"]/div/span']")
    WebElement lb_SearchResultNumber;

    /*
        Category
     */
    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras']")
    WebElement btn_Cameras;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/lenses']")
    WebElement btn_Lenses;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/printers']")
    WebElement btn_Printers;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/lighting']")
    WebElement btn_Lighting;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/camcorders']")
    WebElement btn_Camcorders;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/ink-paper-toner']")
    WebElement btn_InkPaperToner;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/accessories-merchandise']")
    WebElement btn_AccessoriesMerchandise;

    @FindBy(xpath = "//a[@href='https://mcstaging-estore.canon.ca/en_ca/other-products']")
    WebElement btn_OtherProducts;

    @FindBy(xpath = "//button[@data-role='closeBtn']")
    WebElement btn_ClosePromoPopup;


    /*
        Methods
     */
    //Init element
    public CCIHomePage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Open Login page
    public void openLoginPage() throws InterruptedException {
        icon_User.click();
        Thread.sleep(1000);
        Reporter.log("Verify button login displayed:", true);
        Boolean display = btn_Login.isDisplayed();
        Assert.assertEquals(display, Boolean.TRUE, "Button Login is not displayed");
        Reporter.log("Button login displayed", true);
        btn_Login.click();
    }

    //Wait for home page display
    public void waitHomePageDisplayed(){
        Commons.waitForElementVisible(driver,icon_User);
    }

    //Check if login successfully
    public boolean checkLoginSuccess(){
        return lb_UserName.isDisplayed();
    }

    //Logout CCI
    public void Logout() throws InterruptedException {
        icon_User.click();
        Thread.sleep(1000);
        btn_Logout.click();
    }

    //Select Category
    public void clickOnCategory(String category){
        switch (category) {
            case "Cameras":
                btn_Cameras.click();
                break;
            case "Lenses":
                btn_Lenses.click();
                break;
            case "Printers":
                btn_Printers.click();
                break;
            case "Lighting":
                btn_Lighting.click();
                break;
            case "Camcorders":
                btn_Camcorders.click();
                break;
            case "InkPaperToner":
                btn_InkPaperToner.click();
                break;
            case "AccessoriesMerchandise":
                btn_AccessoriesMerchandise.click();
                break;
            case "OtherProducts":
                btn_OtherProducts.click();
                break;
        }
    }

    //Close promo popup
    public void closePromoPopup() throws InterruptedException {
        if (btn_ClosePromoPopup.isDisplayed()) {
            btn_ClosePromoPopup.click();
            Thread.sleep(1000);
        }
    }

    //Hover Category then select sub category
    public void hoverAndSelectSubCategory(String category,String subcategory) throws InterruptedException {
        Actions actions = new Actions(driver);
        switch (category) {
            case "Cameras":
                actions.moveToElement(btn_Cameras).build().perform();
                break;
            case "Lenses":
                actions.moveToElement(btn_Lenses).build().perform();
                break;
            case "Printers":
                actions.moveToElement(btn_Printers).build().perform();
                break;
            case "Lighting":
                actions.moveToElement(btn_Lighting).build().perform();
                break;
            case "Camcorders":
                actions.moveToElement(btn_Camcorders).build().perform();
                break;
            case "InkPaperToner":
                actions.moveToElement(btn_InkPaperToner).build().perform();
                break;
            case "AccessoriesMerchandise":
                actions.moveToElement(btn_AccessoriesMerchandise).build().perform();
                break;
            case "OtherProducts":
                actions.moveToElement(btn_OtherProducts).build().perform();
                break;
        }
        Thread.sleep(1000);
        String xpath_SubCategory = "//*[contains(text(),'" + subcategory.toUpperCase(Locale.ROOT) + "')]";
        WebElement subCategory = driver.findElement(By.xpath(xpath_SubCategory));
        subCategory.click();
    }
}
