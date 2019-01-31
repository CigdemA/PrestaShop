package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ProductPage {
    public ProductPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "h1[itemprop='name']")
    public WebElement productPageNameElm;

    @FindBy(css = "span#our_price_display")
    public WebElement productPagePriceElm;

    @FindBy(css = "#quantity_wanted")
    public WebElement quantityElm;

    @FindBy(css = "#group_1")
    public WebElement sizeDropdownElm;
    public Select sizeDropdownList() {
        return new Select(sizeDropdownElm);
    }

    @FindBy(xpath = "//span[.='Add to cart']")
    public WebElement addToCart;

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

    @FindBy(css = ".cross")
    public WebElement closeIcon;
}
