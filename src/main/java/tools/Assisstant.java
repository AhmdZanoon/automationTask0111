package tools;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Assisstant {

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmssSSS");
    String tenDigitString = dateFormat.format(new Date());


    @Step
    public void takeScreenShoot(WebDriver driver) {
        Allure.addAttachment("screenshot_"+tenDigitString, new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

     }

