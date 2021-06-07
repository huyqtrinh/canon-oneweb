package com.modules.Commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MyActions {
    public static void clickObject(WebElement element) throws InterruptedException {
        element.click();
    }

    public static void setTexts(WebElement element, String text) throws InterruptedException {
        element.clear();
        element.sendKeys(text);
    }

    public static String getTexts(WebElement element) throws InterruptedException {
        return element.getText();
    }

    public static boolean checkDisplayed(WebElement element) throws InterruptedException {
        return element.isDisplayed();
    }

    public static void selectItemIndex(WebElement element, int index) throws InterruptedException {
        Select sltPurchaseLocation = new Select(element);
        sltPurchaseLocation.selectByIndex(index);
    }

    public static String getDataFromCellTable(WebDriver driver, String xpath, int rowIndex, int columnIndex) throws InterruptedException {
        String xpathToData = xpath + "/tbody/tr["+rowIndex+"]/td["+columnIndex+"]/div";
        WebElement element = driver.findElement(By.xpath(xpathToData));
        return element.getText();

    }
}
