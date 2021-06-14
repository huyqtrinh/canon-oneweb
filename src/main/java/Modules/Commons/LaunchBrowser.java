package Modules.Commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import Utilities.Constants;
import java.util.HashMap;
import java.util.Map;


public class LaunchBrowser {
    static WebDriver driver;

    public static WebDriver getDriver(String sBrowser) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String driverPath;
        switch (sBrowser) {
            case "Chrome": {
                ChromeOptions options = new ChromeOptions();
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
        return driver;
    }
}
