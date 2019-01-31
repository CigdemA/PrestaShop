package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;
    protected Faker faker = new Faker();
    protected SoftAssert softAssert;
    protected Actions actions;

    @BeforeMethod
    public void setUpMethod(){
        driver = Driver.getDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
        softAssert = new SoftAssert();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        Driver.closeDriver();
        softAssert.assertAll();
    }
}

