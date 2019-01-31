package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage {

    public RegistrationPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "email")
    public WebElement emailInputElm;

    @FindBy(id = "customer_firstname")
    public WebElement firstName;

    @FindBy(id = "customer_lastname")
    public WebElement lastName;

    @FindBy(id = "passwd")
    public WebElement passwordInput;

    @FindBy(id = "address1")
    public WebElement address;

    @FindBy(id = "city")
    public WebElement city;

    @FindBy (id = "id_state")
    public WebElement state;
    public Select getState() { return new Select(state); }

    @FindBy(id = "postcode")
    public WebElement postCode;

    @FindBy(id = "phone_mobile")
    public WebElement mobilePhone;

    @FindBy(id = "submitAccount")
    public WebElement registerButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol//li")
    public WebElement alertElm;}
