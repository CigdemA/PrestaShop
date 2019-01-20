package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class TestBase {

//@BeforeTest : It will call Only once, before Test method.

//@BeforeMethod It will call Every time before Test Method.

    protected WebDriver driver;
    protected Faker faker = new Faker();

    @BeforeClass
    public void setUpClass(){

        WebDriverManager.chromedriver().setup();

    }
    @BeforeMethod
    public void setUpMethod(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void tearDown() {

      //  driver.quit();


    }
}

