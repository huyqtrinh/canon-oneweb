package Modules.QA;

import Utilities.Actions;
import PageObjects.WebBrowser.QA.MyOrdersPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class MyOrderPageActions {
    public static void SearchString(WebDriver driver, String sSearchKey) throws InterruptedException {
        Actions.setTexts(MyOrdersPage.tbx_Search(driver), sSearchKey);
        Thread.sleep(1000);
        MyOrdersPage.tbx_Search(driver).sendKeys(Keys.RETURN);
        Thread.sleep(1000);
    }
    public static void FilterOrder(WebDriver driver, String sOrderNum, String sInvoiceNum, String sCreatedBy,
          String sOrderStatus, String sOrderDateFrom, String sOrderDateTo, String sOrderMin, String sOrderMax)
            throws InterruptedException
        {
            if (Actions.checkDisplayed(MyOrdersPage.btn_Filter(driver))) {
                Actions.clickObject(MyOrdersPage.btn_Filter(driver));
                Thread.sleep(1000);
            }
            if (!sOrderNum.equals("")){
                Actions.setTexts(MyOrdersPage.tb_Filter_OrderNumber(driver), sOrderNum); }
            if (!sInvoiceNum.equals("")){
                Actions.setTexts(MyOrdersPage.tb_Filter_InvoiceNumber(driver), sInvoiceNum); }
            if (!sCreatedBy.equals(""))
            {
                Select DDL_CreatedBy = new Select(driver.findElement(By.id("created-by")));
                DDL_CreatedBy.selectByVisibleText(sCreatedBy);
            }
            if (!sOrderStatus.equals(""))
            {
                Select DDL_OrderStatus = new Select(driver.findElement(By.id("order-status")));
                DDL_OrderStatus.selectByVisibleText(sOrderStatus);
            }
            if (!sOrderDateFrom.equals("")) {
                Actions.setTexts(MyOrdersPage.tb_Filter_OrderDateFrom(driver), sOrderDateFrom);}
            if (!sOrderDateTo.equals("")) { Actions.setTexts(MyOrdersPage.tb_Filter_OrderDateTo(driver), sOrderDateTo); }
            if (!sOrderMin.equals("")) { Actions.setTexts(MyOrdersPage.tb_Filter_OrderTotalMin(driver), sOrderMin); }
            if (!sOrderMax.equals("")) { Actions.setTexts(MyOrdersPage.tb_Filter_OrderTotalMax(driver), sOrderMax); }
            Thread.sleep(1000);

            Actions.clickObject(MyOrdersPage.btn_Apply(driver));
            Thread.sleep(1000);
        }
    public static boolean VerifyFilterOrder(WebDriver driver, String sOrderNum, String sInvoiceNum, String sCreatedBy,
                                   String sOrderStatus, String sOrderDateFrom, String sOrderDateTo, String sOrderMin, String sOrderMax) {
        boolean bool_FindingStatus = false;
        try {
            List<WebElement> rows_table = MyOrdersPage.tbl_Orders(driver).findElements(By.tagName("tr"));
            int rows_count = rows_table.size();
            System.out.println("VerifyFilterOrder --- rows_count >>> " + rows_count);
            int row = 1;
            String celtext;
            while (row < rows_count) {
                List<WebElement> Columns_row = rows_table.get(row).findElements(By.tagName("td"));
                int columns_count = Columns_row.size();
                System.out.println("VerifyFilterOrder --- columns_count >>> " + columns_count);
                int column = 0;
                while (column < columns_count + 1) {
                    switch (column){
                        case 0:
                            celtext = Columns_row.get(column).getText();
                            if (!sOrderNum.equals("")) {
                                System.out.println("VerifyFilterOrder - celtext >>> " + celtext);
                                Assert.assertEquals(celtext, sOrderNum);
                            }
                            break;
                        case 2:
                            celtext = Columns_row.get(column).getText();
                            if (!sCreatedBy.equals("")) {
                                Assert.assertEquals(celtext, sCreatedBy);
                            }
                            break;
                        case 6:
                            celtext = Columns_row.get(column).getText();
                            if (!sCreatedBy.equals("")) {
                                Assert.assertEquals(celtext, sOrderStatus);
                            }
                            break;

                    }
                    column = column + 1;
                }
                row = row + 1;
            }
            bool_FindingStatus = true;
        }
        catch(Exception ignored) {

        }
        return bool_FindingStatus;
    }
    public static void ClearFilterOrder(WebDriver driver) throws InterruptedException {
        if (Actions.checkDisplayed(MyOrdersPage.href_ClearAll(driver))) {
            Actions.clickObject(MyOrdersPage.href_ClearAll(driver));
            Thread.sleep(1000);
        }
    }
}
