package com.prestashop.pages;


import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class CommonPage {

        public CommonPage() {
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(css = ".login")
        public WebElement signInButton;

        @FindBy(xpath = "//a[@class='account']//span")
        public WebElement fullNameTop;

        @FindBy(xpath = "//a[@title='My Store']")
        public WebElement companyLogo;

        @FindBy(css = "div.shopping_cart>a")
        public WebElement cartIcon;

        @FindBy(css = ".price.cart_block_total")
        public WebElement totalPriceText;

        @FindBy(css = ".cart_block_product_name")
        public WebElement shoppingCartProductName;

    }
