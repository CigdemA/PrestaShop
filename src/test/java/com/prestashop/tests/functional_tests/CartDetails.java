package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CartDetails extends TestBase {
    @Test
    public void cartDetails() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");

        //Click on any product that is not on sale
        WebElement product = driver.findElement(By.xpath("(//div[@class='product-container'])[6]//h5/a"));
        product.click();
        String price = driver.findElement(By.xpath("//span[@id='our_price_display']")).getText();

        //Enter a random quantity between 2 and 5
        Random random = new Random();
        int quantity = random.nextInt(4)+2;

        WebElement qty =driver.findElement(By.name("qty"));
        qty.clear();
        qty.sendKeys("" + quantity);

        //Select a different size
        Select size = new Select(driver.findElement(By.id("group_1")));
        size.selectByIndex(2);

        //Click on add to cart
        //div[@class='box-cart-bottom']/div/p/button/span
        driver.findElement(By.cssSelector("#add_to_cart")).click();

       //Verify confirmation message "Product successfully added to your shopping cart"
        String expectedMsg= "Product successfully added to your shopping cart";
        WebElement actualmsg = driver.findElement(By.xpath("//span[@class='cross']/../h2"));
        actualmsg.click();
        String actualMsg = actualmsg.getText();
        Assert.assertEquals(expectedMsg, actualMsg);


        //Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//div[@class='clearfix']/div/span")).click();
        Thread.sleep(2500);

        //Click on the company logo
        driver.findElement(By.xpath("//div[@id='header_logo']/a/img")).click();

        //Click on any product that is on sale
        driver.findElement(By.xpath("(//a[@title=\"Printed Summer Dress\"])[4]")).click();
        String priceOnsale = driver.findElement(By.xpath("//span[@itemprop='price']")).getText();

        //Enter a random quantity between 2 and 5
        WebElement qtyOnSale = driver.findElement(By.id("quantity_wanted"));
        qtyOnSale.clear();
        qtyOnSale.sendKeys("" + quantity);

        //Select a different size
        Select selectSize = new Select(driver.findElement(By.id("group_1")));
        selectSize.selectByIndex(1);

        //Click on add to cart
        driver.findElement(By.name("Submit")).click();

        //Verify confirmation message Product successfully added to your shopping cart
        String expMsg = "Product successfully added to your shopping cart";
        WebElement msg = driver.findElement(By.xpath("(//div[@class='clearfix']//div//h2)[1]"));
        msg.click();
        String actMsg = msg.getText();
        Assert.assertEquals(actMsg, expMsg);

        //Dismiss the confirmation window by clicking on the x icon
        driver.findElement(By.xpath("//span[@class='cross']")).click();

        //Hover over the Cart icon
        WebElement cart = driver.findElement(By.xpath("//div[@class='shopping_cart']/a/b"));
        Actions action = new Actions(driver);
        action.moveToElement(cart).perform();
        Thread.sleep(2000);

        //Verify that correct total is displayed
        //Verify that total is correct based on the price and item count of the products you added to cart.(Shipping is always $2)
        List<Double> productPrices = new ArrayList<Double>();
        productPrices.add(Double.parseDouble(price.replace("$", "")));
        productPrices.add(Double.parseDouble(priceOnsale.replace("$", "")));

        String totalPriceText = driver.findElement(By.xpath("(//span[.='Total']/../span)[1]")).getText();
        double total = Double.parseDouble(totalPriceText.replace("$", ""));

        double totalPrices= 0;
        for (Double eachPrice : productPrices) {
           totalPrices += eachPrice * quantity;
        }
        //shipping always 2
        totalPrices += 2;

        Assert.assertEquals(totalPrices, total);
    }
}