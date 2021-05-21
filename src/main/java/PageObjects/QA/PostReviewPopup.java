package PageObjects.QA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PostReviewPopup {
    private static WebElement element = null;
    public static WebElement rad_OverallRating_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating-5"));
        return element;
    }
    public static WebElement tbx_ReviewTitle(WebDriver driver){
        element = driver.findElement(By.id("bv-text-field-title"));
        return element;
    }
    public static WebElement tbx_ReviewContent(WebDriver driver){
        element = driver.findElement(By.id("bv-textarea-field-reviewtext"));
        return element;
    }
    public static WebElement tbx_NickName(WebDriver driver){
        element = driver.findElement(By.id("bv-text-field-usernickname"));
        return element;
    }
    public static WebElement tbx_Email(WebDriver driver){
        element = driver.findElement(By.id("bv-email-field-hostedauthentication_authenticationemail"));
        return element;
    }
    public static WebElement slt_PurchaseLocation(WebDriver driver){
        element = driver.findElement(By.id("bv-select-field-contextdatavalue_PurchaseLocation"));
        return element;
    }
    public static WebElement rad_RecommendYes(WebDriver driver){
        element = driver.findElement(By.xpath("//li[@class='bv-radio-isrecommended bv-radio-isrecommended-group-true bv-radio-container-li bv-width-from-rating-stats-50']"));
        return element;
    }
    public static WebElement rad_RecommendNo(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-isrecommended-false-label"));
        return element;
    }
    public static WebElement slt_LengthUse(WebDriver driver){
        element = driver.findElement(By.id("bv-select-field-contextdatavalue_lengthUse"));
        return element;
    }
    public static WebElement slt_Replacement(WebDriver driver){
        element = driver.findElement(By.id("bv-select-field-contextdatavalue_Replacement"));
        return element;
    }
    public static WebElement slt_Expertise(WebDriver driver){
        element = driver.findElement(By.id("bv-select-field-contextdatavalue_Expertise"));
        return element;
    }
    public static WebElement slt_Enthusiast(WebDriver driver){
        element = driver.findElement(By.id("bv-select-field-contextdatavalue_canonEnthusiast"));
        return element;
    }
    public static WebElement rad_Features_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating_Features-5"));
        return element;
    }
    public static WebElement rad_Performance_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating_Performance-5"));
        return element;
    }
    public static WebElement rad_Value_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating_Value-5"));
        return element;
    }
    public static WebElement rad_Quality_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating_Quality-5"));
        return element;
    }
    public static WebElement rad_Satisfaction_5stars(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-rating_Satisfaction-5"));
        return element;
    }
    public static WebElement rad_PromoScore_10(WebDriver driver){
        element = driver.findElement(By.id("bv-radio-netpromoterscore-10-label"));
        return element;
    }
    public static WebElement cbx_TermAndConditions(WebDriver driver){
        element = driver.findElement(By.className("bv-fieldset-label-checkbox"));
        return element;
    }
    public static WebElement tbx_PromoComment(WebDriver driver){
        element = driver.findElement(By.xpath("//textarea[@id='bv-textarea-field-netpromotercomment']"));
        return element;
    }
    public static WebElement btn_AcceptTerm(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='bv-button bv-accept-tc-button bv-focusable']"));
        return element;
    }
    public static WebElement btn_PostReview(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@class='bv-form-actions-submit bv-submit bv-focusable bv-submission-button-submit'][@name='bv-submit-button']"));
        return element;
    }
    public static WebElement txt_ReviewSubmitted(WebDriver driver){
        element = driver.findElement(By.xpath("//span[@id='bv-mbox-label-text'][@class='bv-submission-text']"));
        return element;
    }
    public static WebElement btn_CloseConfirmPopup(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@name='Cancel'][@class='bv-mbox-close bv-focusable']"));
        return element;
    }
    public static WebElement btn_ClosePostReviewPopup(WebDriver driver){
        element = driver.findElement(By.xpath("//button[@name='Cancel'][@class='bv-mbox-close bv-focusable']"));
        return element;
    }

}
