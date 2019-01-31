package com.prestashop.tests.smoke_tests;


import com.prestashop.pages.HomePage;
import com.prestashop.pages.ProductPage;
import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
public class ProductionInformation  extends TestBase{


        HomePage homePage;
        ProductPage productPage;

        public String homePageName;
        public String homePagePrice;

        @BeforeMethod
        public void setUp() {
            homePage = new HomePage();
            productPage = new ProductPage();
            driver.get(ConfigurationReader.getProperty("url"));
        }

        /**
         * Product information - price:
         * 1.Go to http://automationpractice.com/index.php
         * 2.Click on any product
         * 3.Verify that same name and price displayed as on the home page
         */

        @Test(priority = 1)
        public void nameAndPriceVerification() {
            homePageName = homePage.homePageProductName.getText();
            homePagePrice = homePage.homePageProductPrice.getText();

            homePage.homePageProductName.click();

            String productPageName = productPage.productPageNameElm.getText();
            String productPagePrice = productPage.productPagePriceElm.getText();

            Assert.assertEquals(homePageName, productPageName);
            Assert.assertEquals(homePagePrice, productPagePrice);
        }


        /**
         * Product information-details:
         * 4.Verify that default quantity is 1
         * 5.Verify that default size is S
         * 6.Verify that size options are S, M, L
         */

        @Test (priority = 2, dependsOnMethods = "nameAndPriceVerification")
        public void quantityAndSizeVerification() {
            homePage.homePageProductName.click();

            //4.Verify that default quantity is 1
            Assert.assertEquals("1", productPage.quantityElm.getAttribute("value"));

            //5: Verify that default size is S
            String defaultSize = productPage.sizeDropdownList().getFirstSelectedOption().getText();
            Assert.assertEquals("S", defaultSize);

            //6.Verify that size options are S, M, L
            List<String> expectedSizeOptions = new ArrayList<>();
            expectedSizeOptions.add("S"); expectedSizeOptions.add("M"); expectedSizeOptions.add("L");
            List<WebElement> actualSizeOptions = productPage.sizeDropdownList().getOptions();

            for (int i = 0; i < actualSizeOptions.size(); i++) {
                Assert.assertEquals(expectedSizeOptions.get(i), actualSizeOptions.get(i).getText());
            }
        }

        /**
         * Product information– Add to cart:
         * 7.Click on Add to cart
         * 8.Verify confirmation message “Product successfully added to your shopping cart”
         * 9.that default quantity is 1
         * 10.Verify that default size is S
         * 11.Verify that same name and price displayed as on the home page
         */

        @Test (priority = 3, dependsOnMethods = {"quantityAndSizeVerification", "nameAndPriceVerification"})
        public void addToCart() {
            homePage.homePageProductName.click();
            productPage.addToCart.click();

            BrowserUtils.waitFor(2);

            //8.Verify confirmation message “Product successfully added to your shopping cart”
            String expectedMessage = "Product successfully added to your shopping cart";
            String actualMessage = productPage.confirmationMessage.getAttribute("textContent").trim();
            Assert.assertEquals(expectedMessage, actualMessage);

            //9. Verify that default quantity is 1
            Assert.assertEquals(productPage.defaultQuantityElm.getText(), "1");

            //10.Verify that default size is S
            String actualDefaultSize = productPage.defaultSize.getText();
            Assert.assertTrue(actualDefaultSize.substring(actualDefaultSize.indexOf(",")).contains("S"));

            //11.Verify that same name and price displayed as on the home page
            Assert.assertEquals(homePageName, productPage.cartNameElm.getText());
            Assert.assertEquals(homePagePrice, productPage.cartPriceElm.getText());
        }


    }
