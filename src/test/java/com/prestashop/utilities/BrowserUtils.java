package com.prestashop.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {

    public static boolean verifyTexts(String txt1, String txt2){
        return txt1.equals(txt2);
    }

    public static boolean verifyTextContains(String txt1, String txt2){
        return txt1.contains(txt2);
    }

    public static void waitFor(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            System.out.println("Exception message: "+e.getMessage());
        }
    }

    public static void explicitWait(WebElement target){
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
        wait.until(ExpectedConditions.visibilityOf(target));
    }

    public void switchWindow() {
        for(String winHandle : Driver.getDriver().getWindowHandles()){
            Driver.getDriver().switchTo().window(winHandle);
        }
    }

    public static void screenShot(WebDriver driver, String name) { //name parameter is for when you get different screenshot you can name them differently.)
        TakesScreenshot takesScreenshot =(TakesScreenshot) driver;
        File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(source, new File("./ScreenShots/" + name+ ".png"));
        } catch (Exception e) {
            System.out.println("Exception while taking Screen shot: " + e.getMessage());
        }
    }
}
