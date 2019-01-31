package com.prestashop.pages;


import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

    public class CheckoutPage {

        public CheckoutPage(){
            PageFactory.initElements(Driver.getDriver(), this);
        }

        @FindBy(css = ".heading-counter")
        public WebElement cartContainsMessage;

        @FindBy(xpath = "(//i[@class='icon-trash'])[1]")
        public WebElement deleteIcon;

        @FindBy(xpath = "//div[@id='center_column']/p")
        public WebElement checkoutAlert;

    }
