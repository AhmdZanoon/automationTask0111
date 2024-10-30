package pages.webPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import tools.Assisstant;

public class SauceDemoCheckoutOverviewPage {
    private final WebDriver driver;

    public SauceDemoCheckoutOverviewPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By secondaryHeader = By.xpath("//div[@data-test='secondary-header']//span");
    private final By summary_subtotal_label = By.xpath("//div[@data-test='subtotal-label']");
    private final By finishButton = By.id("finish");


    public void assertThatILandedCheckOutOverviewPage() {
        Assert.assertEquals(driver.findElement(secondaryHeader).getText(), "Checkout: Overview");
        new Assisstant().takeScreenShoot(driver);
    }

  public void assertTotalWithoutTax(String expectedSubTotal){
      Assert.assertEquals(driver.findElement(summary_subtotal_label).getText(), expectedSubTotal);
      new Assisstant().takeScreenShoot(driver);
  }

public void assertUrl(String expectedUrl){
        Assert.assertEquals(driver.getCurrentUrl(),expectedUrl);
}

public void clickFinish(){
        driver.findElement(finishButton).click();
}

}

