package com.prestashop.tests.functional_tests.cart;

    import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import static org.testng.Assert.assertTrue;

public class CartIconDeleteTest extends TestBase {
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
         * Cart Icon DeleteTest
         * 1.Open browser
         * 2.Go to http://automationpractice.com/index.php
         * 3.Add any product in the homepage to the cart
         * 4.Click on Continue shopping
         * 5.Hover over the cart icon
         * 6.Click the x to delete the product
         * 7.Verify word empty is displayed in the Cart icon
         */

        @Test
        public void cartIconDelete() {
            driver.get(ConfigurationReader.getProperty("url"));

            //Add any product in the homepage to the cart
            WebElement product = homePage.getProduct("Blouse");
            actions.moveToElement(product).perform();
            homePage.getAddToCartIdx(2).click();

            productPage.closeIcon.click();

            //Click on Continue shopping
            homePage.continueShoppingButton.click();

            //Hover over the cart icon
            actions.moveToElement(commonPage.cartIcon).perform();

            //Click the x to delete the product
            homePage.removeFromCartButton.click();
            BrowserUtils.waitFor(2);

            //Verify word empty is displayed in the Cart icon
            assertTrue(homePage.cartHeader.getText().contains("empty"));
        }
}
