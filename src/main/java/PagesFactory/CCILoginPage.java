package PagesFactory;

import Utilities.Commons;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class CCILoginPage {
    WebDriver driver;

    @FindBy(name = "user")
    WebElement tbx_Username;

    @FindBy(name = "password")
    WebElement tbx_Password;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement btn_Login;

    @FindBy(xpath = "//a[text()='Forgot your password?']")
    WebElement btn_ForgotPassword;

    @FindBy(xpath = "//a[text()='Sign Up']")
    WebElement btn_SignUp;

    @FindBy(xpath = "//*[@id='msg']")
    WebElement txt_Message;

    /*
        Methods
     */
    //Init element
    public CCILoginPage(WebDriver driver){
        this.driver = driver;
        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
    }

    //Wait for login page displayed
    public void waitLoginPageDisplayed(){
        Commons.waitForElementVisible(driver,btn_Login);
        Reporter.log("Verify Login page display", true);
        Boolean display = btn_Login.isDisplayed();
        Assert.assertEquals(display, Boolean.TRUE, "Login page not display");
        Reporter.log("Login page displayed", true);
    }

    //Login
    public void Login(String username, String password) throws InterruptedException {
        tbx_Username.clear();
        tbx_Username.sendKeys(username);
        tbx_Password.clear();
        tbx_Password.sendKeys(password);
        Thread.sleep(2000);
        btn_Login.click();
    }

    //Get message
    public String getMessage() {
        return txt_Message.getText();
    }

    //Check if login page is opened successfully
    public boolean isOpenedLoginPage() throws InterruptedException {
        return btn_Login.isDisplayed();
    }
}
