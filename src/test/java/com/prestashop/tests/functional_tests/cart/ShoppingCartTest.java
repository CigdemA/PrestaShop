package com.prestashop.tests.functional_tests.cart;

    import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.openqa.selenium.Keys;
import org.testng.annotations.*;
import java.util.*;
import static org.testng.Assert.*;

    public class ShoppingCartTest extends TestBase {
        HomePage homePage;
        ProductPage productPage;
        CommonPage commonPage;

        @BeforeMethod
        public void setupPages() {
            homePage = new HomePage();
            commonPage = new CommonPage();
            productPage = new ProductPage();
        }

        @AfterMethod
        public void tearDown() {
            homePage = null;
            commonPage = null;
        }

        /**
         * Cart Details
         * 2.Go to http://automationpractice.com/index.php
         * 3.Click on any product that is not on sale
         * 4.Enter a random quantity between 2 and 5
         * 5.Select a different size
         * 6.Click on add to cart
         * 7.Verify confirmation message Product successfully added to your shopping cart
         * 8.Dismiss the confirmation window by clicking on the x icon
         * 9.Click on the company logo
         * 10.Click on any product that is on sale
         * 11.Enter a random quantity between 2 and 5
         * 12.Select a different size
         * 13.Click on add to cart
         * 14.Verify confirmation message Product successfully added to your shopping cart
         * 15.Dismiss the confirmation window by clicking on the x icon
         * 16.Hover over the Cart icon
         * 17.Verify that correct total is displayed
         * 18.Verify that total is correct based on the price and item count of the products you added to cart.
         *    (Shipping is always $2)
         */
        @Test
        public void cartDetails() {
            driver.get(ConfigurationReader.getProperty("url"));

            //Click on any product that is not on sale
            homePage.notSaleProduct.click();
            String notSaleProductPrice = productPage.productPagePriceElm.getText();

            //Enter a random quantity between 2 and 5
            int randomQuantity = 2 + (int) (Math.random() * 4);
            productPage.quantityElm.sendKeys(Keys.BACK_SPACE + "" + randomQuantity);

            //Select a different size
            productPage.sizeDropdownList().selectByIndex((int)(Math.random() * 2 + 1));

            //Click on add to cart
            productPage.addToCart.click();

            //Verify confirmation message Product successfully added to your shopping cart
            productPage.confirmationMessage.click();
            assertEquals(productPage.confirmationMessage.getText(), "Product successfully added to your shopping cart");

            //Dismiss the confirmation window by clicking on the x icon
            productPage.closeIcon.click();
            BrowserUtils.waitFor(2);

            //Click on the company logo
            commonPage.companyLogo.click();

            //Click on any product that is on sale
            homePage.saleProduct.click();
            String saleProductPrice = productPage.productPagePriceElm.getText();

            //Enter a random quantity between 2 and 5
            productPage.quantityElm.sendKeys(Keys.BACK_SPACE + "" + randomQuantity);

            //Select a different size
            productPage.sizeDropdownList().selectByIndex((int)(Math.random() * 2 + 1));

            //Click on add to cart
            productPage.addToCart.click();

            //Verify confirmation message Product successfully added to your shopping cart
            productPage.confirmationMessage.click();
            assertEquals(productPage.confirmationMessage.getText(), "Product successfully added to your shopping cart");

            //Dismiss the confirmation window by clicking on the x icon
            productPage.closeIcon.click();

            //Hover over the Cart icon
            actions.moveToElement(commonPage.cartIcon).perform();
            BrowserUtils.waitFor(1);

            //Verify that correct total is displayed
            assertTrue(commonPage.totalPriceText.isDisplayed());

            //Verify that total is correct based on the price and item count of the products you added to cart.
            //(Shipping is always $2)
            List<Double> productPrices = new ArrayList<>();
            productPrices.add(Double.parseDouble(saleProductPrice.replace("$", "")));
            productPrices.add(Double.parseDouble(notSaleProductPrice.replace("$", "")));

            double total = Double.parseDouble(commonPage.totalPriceText.getText().replace("$", ""));
            double totalPriceOfProducts = 0;

            for (Double price : productPrices) {
                totalPriceOfProducts += price * randomQuantity;
            }
            totalPriceOfProducts += 2;

            assertEquals(totalPriceOfProducts, total);
        }

    }
