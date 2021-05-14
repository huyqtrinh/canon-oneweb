package PageObjects;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageQA {
    WebDriver driver;
    By ico_User = By.xpath("//a[@data-trigger='trigger']");
    By btn_Login = By.id("mcLogin");
    By btn_Cameras = By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/cameras']");
    By btn_Lenses = By.xpath("//a[@href='https://mcstaging-estore.canon.ca/en_ca/lenses']");
    By btn_ClosePopupPromo = By.xpath("//button[@data-role='closeBtn']");
    By btn_Logout = By.id("mcLogout");

    //Click user icon
    public HomePageQA(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnUserIcon() {
        WebElement iconUser = driver.findElement(ico_User);
        iconUser.click();
    }

    public void clickOnButtonLogin() {
        WebElement btnLogin = driver.findElement(btn_Login);
        btnLogin.click();
    }

    public void closePopupPromo() {
        WebElement closeElement = driver.findElement(btn_ClosePopupPromo);
        closeElement.click();
    }

    public boolean checkBtnLoginDisplayed() {
        return driver.findElement(btn_Login).isDisplayed();
    }

    public void clickOnCamerasBtn() {
        WebElement btnCameras = driver.findElement(btn_Cameras);
        btnCameras.click();
    }

    public void clickOnLensesBtn() {
        WebElement btnLenses = driver.findElement(btn_Lenses);
        btnLenses.click();
    }
    public void clickOnButtonLogout() {
        WebElement btnLogout = driver.findElement(btn_Logout);
        btnLogout.click();
    }

}
