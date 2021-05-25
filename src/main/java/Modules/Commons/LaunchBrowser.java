package Modules.Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;


public class LaunchBrowser {
    static WebDriver driver;

    public static WebDriver getDriver(String sBrowser, String sURL) throws InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("pageLoadStrategy", "normal");
        String driverPath;

        if (sBrowser.equals("Chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("user-data-dir=C:\\Users\\huyqtrinh.sp\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 2");
            options.addArguments("--disable-extensions");
            options.merge(capabilities);
            driverPath = "src/main/resources/WebDrivers/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(options);

        }
        else if (sBrowser.equals("Edge")){
            EdgeOptions options = new EdgeOptions();
            options.setPageLoadStrategy("normal");
            driverPath = "src/main/resources/WebDrivers/msedgedriver.exe";
            System.setProperty("webdriver.edge.driver", driverPath);
            driver = new EdgeDriver(options);
        }
        driver.manage().window().maximize();
        driver.get(sURL);
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        return driver;
    }
}
