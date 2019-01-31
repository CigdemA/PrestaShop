package com.prestashop.pages;

    import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class AddToCartPage {

        private String defaultQuantity;
        private String cartName;
        private String cartPrice;


        public AddToCartPage(){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(xpath = "//div[@class='layer_cart_product col-xs-12 col-md-6']//h2")
        public WebElement confirmationMessage;

        @FindBy(css = "#layer_cart_product_quantity")
        public WebElement defaultQuantityElm;

        @FindBy(css = "#layer_cart_product_attributes")
        public WebElement defaultSize;

        @FindBy(id = "layer_cart_product_title")
        public WebElement cartNameElm;

        @FindBy(id = "layer_cart_product_price")
        public WebElement cartPriceElm;

        public String getDefaultQuantity() {
            defaultQuantity = defaultQuantityElm.getText();
            return defaultQuantity;
        }

        public String getCartName() {
            cartName = cartNameElm.getText();
            return cartName;
        }

        public String getCartPrice() {
            cartPrice = cartPriceElm.getText();
            return cartPrice;
        }
}
