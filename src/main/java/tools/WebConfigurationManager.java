package tools;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.WebProperties;

public class WebConfigurationManager {

    WebDriver driver;
    String browser ;
    public static final WebProperties webProperties = ConfigFactory.create(WebProperties.class);

    public WebDriver browserSetup() {

        browser = webProperties.browser();

        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser system property is not set or is empty.");
        }
        switch(browser) {
            case "chrome":
                ChromeOptions chOptions = new ChromeOptions();
                if (webProperties.headlessMode()){
                    chOptions.addArguments("--headless");               // Run in headless mode
                }

                chOptions.addArguments("--no-sandbox");             // Bypass OS security model
                chOptions.addArguments("--disable-dev-shm-usage");  // Overcome limited resource issues
                chOptions.addArguments("--disable-gpu");            // Disable GPU rendering
                driver = new ChromeDriver(chOptions);
                driver.manage().window().maximize();
                break;
            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (webProperties.headlessMode()){
                    ffOptions.addArguments("--headless");               // Run in headless mode
                }
                ffOptions.addArguments("--no-sandbox");             // Bypass OS security model
                ffOptions.addArguments("--disable-dev-shm-usage");  // Overcome limited resource issues
                ffOptions.addArguments("--disable-gpu");
                driver = new FirefoxDriver();
                driver.manage().window().maximize();

                break;
            default :
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }



}
