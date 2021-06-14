package Utilities;

import PageObjects.WebBrowser.QA.QAHomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CommonActions {
    public static void clickObject(WebElement element) {
        element.click();
    }

    public static void setTexts(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    public static String getTexts(WebElement element) {
        return element.getText();
    }

    public static boolean checkDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public static void selectItemIndex(WebElement element, int index)  {
        Select sltPurchaseLocation = new Select(element);
        sltPurchaseLocation.selectByIndex(index);
    }

    public static String getDataFromCellTable(WebDriver driver, String xpath, int rowIndex, int columnIndex) {
        String xpathToData = xpath + "/tbody/tr["+rowIndex+"]/td["+columnIndex+"]/div";
        WebElement element = driver.findElement(By.xpath(xpathToData));
        return element.getText();

    }
    public static void HandlingPromoPopup(WebDriver driver){
        try {
            //Close popup promo
            WebElement btn_ClosePopupPromo = QAHomePage.btn_ClosePopupPromo(driver);
            if (CommonActions.checkDisplayed(btn_ClosePopupPromo)) {
                CommonActions.clickObject(btn_ClosePopupPromo);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
