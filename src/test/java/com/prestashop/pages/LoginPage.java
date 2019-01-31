package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public LoginPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "email")
    public WebElement emailElm;

    @FindBy(id = "passwd")
    public WebElement passwordElm;

    @FindBy(linkText = "Sign out")
    public WebElement signOutButton;

    @FindBy(id = "email_create")
    public WebElement emailForRegistration;

    @FindBy(id = "SubmitCreate")
    public WebElement createAccountButtton;

    public void login(String email, String password) {
        emailElm.sendKeys(email);
        passwordElm.sendKeys(password + Keys.ENTER);
    }
}
