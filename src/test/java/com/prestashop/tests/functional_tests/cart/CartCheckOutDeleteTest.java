package com.prestashop.tests.functional_tests.cart;


import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class CartCheckOutDeleteTest extends TestBase{
        HomePage homePage;
        ProductPage productPage;
        CommonPage commonPage;
        LoginPage loginPage;
        CheckoutPage checkoutPage;

        @BeforeMethod
        public void setupPages() {
            homePage = new HomePage();
            commonPage = new CommonPage();
            productPage = new ProductPage();
            loginPage = new LoginPage();
            checkoutPage = new CheckoutPage();
        }

        @AfterMethod
        public void tearDown() {
            homePage = null;
            commonPage = null;
            loginPage = null;
            productPage = null;
            checkoutPage = null;
        }

        /**
         * Cart Checkout DeleteTest
         * 1.Open browser
         * 2.Go to http://automationpractice.com/index.php
         * 3.Add any product in the homepage to the cart
         * 4.Click on Continue shopping
         * 5.Add another product in the homepage to the cart
         * 6.Click on Proceed to checkout
         * 7.Verify message Your shopping cart contains: 2 Products
         * 8.Click the delete icon to delete one of the products
         * 9.Verify message Your shopping cart contains: 1 Product
         * 10.Click the delete icon to delete the second product
         * 11.Verify message Your shopping cart is empty.
         */

        @Test
        public void checkoutDelete() {
            driver.get(ConfigurationReader.getProperty("url"));

            //Add any product in the homepage to the cart
            WebElement product = homePage.getProduct("Faded Short Sleeve T-shirts");
            actions.moveToElement(product).perform();
            BrowserUtils.waitFor(2);

            homePage.getAddToCartIdx(1).click();

            //Click on Continue shopping
            homePage.continueShoppingButton.click();

            //Add another product in the homepage to the cart
            WebElement product2 = homePage.getProduct("Printed Summer Dress");
            actions.moveToElement(product2).perform();
            BrowserUtils.waitFor(2);

            homePage.getAddToCartIdx(5).click();

            //Click on Proceed to checkout
            homePage.proceedToCheckoutButton.click();

            //Verify message Your shopping cart contains: 2 Products
            String expected = "Your shopping cart contains: 2 Products";
            assertEquals(expected, checkoutPage.cartContainsMessage.getText());

            //Click the delete icon to delete one of the products
            checkoutPage.deleteIcon.click();
            BrowserUtils.waitFor(2);

            //Verify message Your shopping cart contains: 1 Product
            String expected2 = "Your shopping cart contains: 1 Product";
            assertEquals(expected2, checkoutPage.cartContainsMessage.getText());

            //Click the delete icon to delete the second product
            checkoutPage.deleteIcon.click();
            BrowserUtils.waitFor(2);

            //Verify message Your shopping cart is empty.
            String expectedAlert = "Your shopping cart is empty.";
            System.out.println(checkoutPage.checkoutAlert.getText());
            assertEquals(expectedAlert, checkoutPage.checkoutAlert.getText());
        }

    }
