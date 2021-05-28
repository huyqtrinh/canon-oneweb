package Modules.Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import Ultilities.Constants;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LaunchBrowser {
    static WebDriver driver;

    public static WebDriver getDriver(String sBrowser, String sURL) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("pageLoadStrategy", "normal");
        String driverPath;

        switch (sBrowser) {
            case "Chrome": {
                ChromeOptions options = new ChromeOptions();
                //options.addArguments("user-data-dir=C:\\Users\\huyqtrinh.sp\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 2");
                options.addArguments("--disable-extensions");
                options.merge(capabilities);
                driverPath = "src/main/resources/WebDrivers/chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", driverPath);
                if (Constants.Emulator) {
                    Map<String, String> mobileEmulation = new HashMap<>();
                    mobileEmulation.put("deviceName", "iPhone X");
                    options.setExperimentalOption("mobileEmulation", mobileEmulation);
                }

                driver = new ChromeDriver(options);

                break;
            }
            case "Edge": {
                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy("normal");
                driverPath = "src/main/resources/WebDrivers/msedgedriver.exe";
                System.setProperty("webdriver.edge.driver", driverPath);
                driver = new EdgeDriver(options);
                break;
            }
            case "FireFox": {
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("deviceName", "iPad");
                driverPath = "src/main/resources/WebDrivers/geckodriver.exe";
                System.setProperty("webdriver.gecko.driver", driverPath);
                driver = new FirefoxDriver(options);
                break;
            }
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(sURL);
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        return driver;
    }
}
