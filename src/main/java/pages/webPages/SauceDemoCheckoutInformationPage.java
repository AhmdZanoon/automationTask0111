package pages.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;

public class SauceDemoCheckoutInformationPage {
    private final WebDriver driver;

    public SauceDemoCheckoutInformationPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By secondaryHeader = By.xpath("//div[@data-test='secondary-header']//span");
    private final By firstNameInputField = By.id("first-name");
    private final By lastNameInputField = By.id("last-name");
    private final By postalCodeInputField = By.id("postal-code");
    private final By continueButton = By.id("continue");


    public void assertThatILandedCheckOutInformationPage() {
        Assert.assertEquals(driver.findElement(secondaryHeader).getText(), "Checkout: Your Information");
        new Assisstant().takeScreenShoot(driver);
    }

public void fillCheckoutDetails(){
        driver.findElement(firstNameInputField).sendKeys("Ahmed");
        driver.findElement(lastNameInputField).sendKeys("Zanoon");
    driver.findElement(postalCodeInputField).sendKeys("123");
}

public void clickContinue(){
        driver.findElement(continueButton).click();
}

}

