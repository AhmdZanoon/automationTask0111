package pages.webPages;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.WebProperties;

public class Helper {

    WebDriver driver;
    String browser ;
    protected static final WebProperties webProperties = ConfigFactory.create(WebProperties.class);

    public WebDriver browserSetup() {

        browser = webProperties.browser();

        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("Browser system property is not set or is empty.");
        }
        switch(browser) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");               // Run in headless mode
                options.addArguments("--no-sandbox");             // Bypass OS security model
                options.addArguments("--disable-dev-shm-usage");  // Overcome limited resource issues
                options.addArguments("--disable-gpu");            // Disable GPU rendering
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                driver.manage().window().maximize();

                break;
            default :
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        return driver;
    }



}
