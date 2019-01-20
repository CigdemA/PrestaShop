package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class productInformation {

    WebDriver driver;

    @BeforeClass
    public void setup() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://automationpractice.com/index.php");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void quitWindows() {
        try { Thread.sleep(3000); } catch (Exception e) { }
        driver.quit();
    }

    /**
     * Product information-price:
     * 1.Go to http://automationpractice.com/index.php
     * 2.Click on any product
     * 3.Verify that same name and price displayed as on the home page
     */

    @Test(priority = 1)
    public void price() throws InterruptedException {

        // STEP 1:  product name info on HOMEPAGE
        WebElement blouse = driver.findElement(By.linkText("Blouse"));
        String expectedName = blouse.getText();

        // STEP 2:  product price info on HOMEPAGE
        WebElement blousePrice =driver.findElement(By.xpath("(//span[@class='price product-price'])[4]"));
        String expectedPrice = blousePrice.getText();

        blouse.click();

        // STEP 3:  product name info on product page
        WebElement inBlouse = driver.findElement(By.xpath("//h1[.='Blouse']"));
        Assert.assertEquals(inBlouse.getText(), expectedName);

        // STEP 4:  product price info on product page
        WebElement inBlousePrice = driver.findElement(By.xpath("//span[@id='our_price_display']"));
        Assert.assertEquals(inBlousePrice.getText(),expectedPrice);

    }

    /**
     * Product information-details:
     * 4.that default quantity is 1
     * 5.Verify that default size is S
     * 6.Verify that size options are S, M, L
     */

    @Test(priority = 2)
    public void details()  {

        driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");

        //Verify default quantity is 1
        WebElement defaultQuantity = driver.findElement(By.xpath("//input[@id='quantity_wanted']"));
        String min = defaultQuantity.getAttribute("value");
        Assert.assertTrue(min.equals("1"),"verifying default quantity is 1.");

        //Verify the size options from dropdown
        WebElement sizes = driver.findElement(By.id("group_1"));
        Select size = new Select(sizes);
        String defaultSize =driver.findElement(By.tagName("option")).getText();
        Assert.assertTrue(defaultSize.equals("S"));

        List<WebElement> sizeList = size.getOptions();
        Assert.assertTrue(sizeList.size()==3);
        Assert.assertTrue(sizeList.get(1).getText().equals("M"));
        Assert.assertTrue(sizeList.get(2).getText().equals("L"));

    }

    /**
     * Product information– Add to cart:
     * 7.Click on Add to cart
     * 8.Verify confirmation message “Product successfully added to your shopping cart”
     * 9.that default quantity is 1
     * 10.Verify that default size is S
     * 11.Verify that same name and price displayed as on the home page
     */

    @Test(priority = 3)
    public void addToCart() throws InterruptedException{
        driver.get("http://automationpractice.com/index.php?id_product=2&controller=product");

        WebElement addCart = driver.findElement(By.xpath("//div[@class='box-cart-bottom']/div/p/button/span"));
        addCart.click();

        WebElement msg = driver.findElement(By.xpath("//div[@class='layer_cart_product col-xs-12 col-md-6']/h2"));
        msg.click();

        String msgText = msg.getText();
        Assert.assertTrue(msgText.equals("Product successfully added to your shopping cart"));

        String quantity = driver.findElement(By.xpath("(//div[@class='layer_cart_product_info']/div/span)[1]")).getText();
        Assert.assertTrue(quantity.trim().equals("1"));

        String size = driver.findElement(By.xpath("//span[@id='layer_cart_product_attributes']")).getText();
        Assert.assertTrue(size.contains("S"),"verifying size in cart");

        String price = driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();
        Assert.assertTrue(price.equals("$27.00"),"verifying price in cart matches pre- and postSelection");

        String InBlouseName = driver.findElement(By.xpath("//span[@id='layer_cart_product_title']")).getText();
        String InBlousePrice = driver.findElement(By.xpath("//span[@id='layer_cart_product_price']")).getText();

        WebElement close = driver.findElement(By.xpath("//span[@class='cross']"));
        close.click();
        driver.navigate().back();

        WebElement blouse = driver.findElement(By.linkText("Blouse"));
        String expectedName = blouse.getText();

        WebElement blousePrice =driver.findElement(By.xpath("(//span[@class='price product-price'])[4]"));
        String expectedPrice = blousePrice.getText();

        Assert.assertEquals(InBlouseName ,expectedName);
        Assert.assertEquals(InBlousePrice, expectedPrice);
    }
}