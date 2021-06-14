package PageObjects.WebBrowser.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailPage {
    private static WebElement element = null;

    public static WebElement btn_AddToCart(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@type='submit'][@title='Add to Cart']"));
        return element;
    }
    public static WebElement txt_NumberOfReviews(WebDriver driver){
        element = driver.findElement(By.xpath("//meta[@itemprop='reviewCount']"));
        return element;
    }
    public static WebElement img_RatingStars(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='bv_stars_button_container']"));
        return element;
    }
    public static WebElement btn_WriteAReview(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='bv-write-review bv-focusable bv-submission-button']"));
        return element;
    }
    public static WebElement txt_ProductName(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div/div[2]/div[2]/h1/span"));
        return element;
    }
    public static WebElement txt_NumberOfReviews_ReviewSection(WebDriver driver){
        element = driver.findElement(By.xpath("//*[@id=\"BVRRContainer\"]/div/div/div/div/div[2]/div[4]/div[1]/div/span"));
        return element;
    }
    public static WebElement txt_ReviewContent_1st(WebDriver driver){
        element = driver.findElement(By.xpath("//div[@class='bv-content-summary-body-text'][1]"));
        return element;
    }

}
