package com.prestashop.pages;

import com.prestashop.utilities.BrowserUtils;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends TestBase {

    public HomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//h5//a[@title='Printed Dress'])[1]")
    public WebElement homePageProductName;

    @FindBy(xpath = "(//span[@class='price product-price'])[6]")
    public WebElement homePageProductPrice;

    @FindBy(xpath = "(//h5//a[@title='Faded Short Sleeve T-shirts'])[1]")
    public WebElement notSaleProduct;

    @FindBy(xpath = "(//a[@title='Printed Summer Dress'])[2]")
    public WebElement saleProduct;

    @FindBy(xpath = "//span[@class='ajax_cart_no_product']")
    public WebElement cartHeader;

    @FindBy(css = "span[title='Continue shopping']")
    public WebElement continueShoppingButton;

    @FindBy(css = ".ajax_cart_block_remove_link")
    public WebElement removeFromCartButton;

    @FindBy(xpath = "//a[@title='Proceed to checkout']/span")
    public WebElement proceedToCheckoutButton;


    public WebElement getProduct(String productName) {
        String xpath = "(//h5//a[@title='"+productName+"'])[1]";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public WebElement getAddToCartIdx(int index) {
        String xpath = "//ul[@id='homefeatured']//li[" + index + "]//span[.='Add to cart']";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}
