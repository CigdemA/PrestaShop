package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    public MyAccountPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//a[@title='Information']//span")
    public WebElement myPersonalInfoButton;

    @FindBy(name = "submitIdentity")
    public WebElement saveButton;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol//li")
    public WebElement errorMessageElm;

    @FindBy(linkText = "Back to your account")
    public WebElement backToYourAccount;

    @FindBy(xpath = "//span[.='My addresses']")
    public WebElement myAddressesButton;

    @FindBy(xpath = "//span[.='Add a new address']")
    public WebElement addANewAddressButton;

    @FindBy(id = "firstname")
    public WebElement firstNameInputElm;

    @FindBy(id = "lastname")
    public WebElement lastNameInputElm;

    @FindBy(id = "email")
    public WebElement emailInputElm;

    @FindBy(id = "submitAddress")
    public WebElement saveButtonAddress;

    @FindBy(xpath = "//div[@class='alert alert-danger']//ol//li[1]")
    public WebElement errorMessage;}
