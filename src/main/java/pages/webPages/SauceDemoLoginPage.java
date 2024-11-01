package pages.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;

import static tools.WebConfigurationManager.webProperties;


public class SauceDemoLoginPage {

    private final WebDriver driver;

    public SauceDemoLoginPage(WebDriver driver){
        this.driver = driver ;
    }

    private final By emailField = By.xpath("//input[@name='user-name']");
    private final By passwordField = By.xpath("//input[@name='password']");
    private final By loginButton = By.xpath("//input[@name='login-button']");
    private final By errorMessageContainer = By.xpath("//h3[@data-test='error']");
    private final String url = webProperties.webUrl();

public String wrongUserNameAndPassword = "Epic sadface: Username and password do not match any user in this service";
public String passwordIsRequired = "Epic sadface: Password is required";
public String usernameIsRequired = "Epic sadface: Username is required";

    public void navigateToUrl() {
        driver.navigate().to(url);
    }


    public void enterUsernameAndPassword(String userName , String password) {
        driver.findElement(emailField).sendKeys(userName);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void assertErrorMessage(String errorMsg){
        String errorMessage = driver.findElement(errorMessageContainer).getText();
        Assert.assertEquals(errorMessage, errorMsg);
        new Assisstant().takeScreenShoot(driver);
    }


}
