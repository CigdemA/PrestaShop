package com.prestashop.tests.functional_tests.cart;

    import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;

import static org.testng.Assert.assertTrue;

    public class CartLogout extends TestBase {
        HomePage homePage;
        ProductPage productPage;
        CommonPage commonPage;
        LoginPage loginPage;

        @BeforeMethod
        public void setupPages() {
            homePage = new HomePage();
            commonPage = new CommonPage();
            productPage = new ProductPage();
            loginPage = new LoginPage();
        }

        @AfterMethod
        public void tearDown() {
            homePage = null;
            commonPage = null;
            loginPage = null;
            productPage = null;
        }

        /**
         * Cart LogoutTest
         * 1.Open browser
         * 2.Go to http://automationpractice.com/index.php
         * 3.Login as any valid user
         * 4.Go back to home page
         * 5.Add any product in the homepage to the cart
         * 6.Hover over the cart icon
         * 7.Verify that cart contains the product
         * 8.Log out
         * 9.Verify the cart contains the word empty
         */

        @Test
        public void cartLogout() {
            driver.get(ConfigurationReader.getProperty("url"));

            //Login as any valid user
            commonPage.signInButton.click();
            loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));

            //Go back to home page
            commonPage.companyLogo.click();

            //Add any product in the homepage to the cart
            WebElement product = homePage.getProduct("Printed Chiffon Dress");
            String productName = product.getText();
            actions.moveToElement(product).perform();
            homePage.getAddToCartIdx(7).click();

            productPage.closeIcon.click();

            //Hover over the cart icon
            actions.moveToElement(commonPage.cartIcon).perform();

            //Verify that cart contains the product
            String cartProductName = commonPage.shoppingCartProductName.getText().replace(".", "");
            assertTrue(productName.contains(cartProductName));

            //Log out and verify the cart contains the word empty
            loginPage.signOutButton.click();
            BrowserUtils.waitFor(2);
            System.out.println(homePage.cartHeader.getText());
            assertTrue(homePage.cartHeader.getText().contains("empty"));
        }

    }
