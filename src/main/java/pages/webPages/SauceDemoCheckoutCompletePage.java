package pages.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;

public class SauceDemoCheckoutCompletePage {
    private final WebDriver driver;

    public SauceDemoCheckoutCompletePage(WebDriver driver) {
        this.driver = driver;
    }

    private final By secondaryHeader = By.xpath("//div[@data-test='secondary-header']//span");
    public By thankYouMessageContainer = By.xpath("//h2[@data-test='complete-header']");
    public By orderDispatchedMessageContainer = By.xpath("//div[@data-test='complete-text']");


    public void assertThatILandedCheckOutCompletePage() {
        Assert.assertEquals(driver.findElement(secondaryHeader).getText(), "Checkout: Complete!");
        new Assisstant().takeScreenShoot(driver);
    }

public void assertMessages(By element , String expectedMessage){
        Assert.assertEquals(driver.findElement(element).getText(),expectedMessage);
}

}

