package Modules;

import PageObjects.HomePageQA;
import org.openqa.selenium.WebDriver;

public class ActionsHomePageQA {
    public static void clickOnUserIcon(WebDriver driver) throws InterruptedException{
        HomePageQA.ico_User(driver).click();
    }
    public static void clickOnButtonLogin(WebDriver driver) throws InterruptedException{
        HomePageQA.btn_Login(driver).click();
    }
    public static void closePopupPromo(WebDriver driver) throws InterruptedException{
        HomePageQA.btn_ClosePopupPromo(driver).click();
    }
    public static boolean checkBtnLoginDisplayed(WebDriver driver) throws InterruptedException{
        return HomePageQA.btn_Login(driver).isDisplayed();
    }
    public static void clickOnCamerasBtn(WebDriver driver) throws InterruptedException{
        HomePageQA.btn_Cameras(driver).click();
    }
    public static void clickOnLensesBtn(WebDriver driver) throws InterruptedException{
        HomePageQA.btn_Lenses(driver).click();
    }
    public static void clickOnButtonLogout(WebDriver driver) throws InterruptedException{
        HomePageQA.btn_Logout(driver).click();
    }
}
