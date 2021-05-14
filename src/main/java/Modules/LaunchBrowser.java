package Modules;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;


public class LaunchBrowser {
    static WebDriver driver;

    public static WebDriver getDriver(String sBrowser, String sURL) throws InterruptedException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String driverPath;
        Thread.sleep(3000);
        if (sBrowser.equals("Chrome")) {
            driverPath = "src/main/resources/WebDrivers/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);
            driver = new ChromeDriver(capabilities);

        }
        else if (sBrowser.equals("WebDrivers.Edge")){
            driverPath = "src/main/resources/WebDrivers/msedgedriver.exe";
            System.setProperty("webdriver.edge.driver", driverPath);
            driver = new EdgeDriver(capabilities);
        }
        driver.manage().window().maximize();
        driver.get(sURL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        Thread.sleep(3000);
        return driver;
    }
}
