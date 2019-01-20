package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class Registration extends TestBase {

    String fakeEmail, fakeFirstName, fakeLastName, fakeAddress, fakeCity, fakeZipCode, fakePassword;
    Random random;

    @Test
    public void registrationTest() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");

        //Click Sign in link
        WebElement signIn = driver.findElement(By.linkText("Sign in"));
        signIn.click();

        //Enter new valid email to the email field
        WebElement emailField = driver.findElement(By.id("email_create"));
        fakeEmail = faker.internet().emailAddress();
        emailField.sendKeys(fakeEmail + Keys.ENTER);
        Thread.sleep(3000);

        //Verify that email link displays current email
        WebElement emailInput = driver.findElement(By.id("email"));
        Thread.sleep(3000);
        String emailInputText = emailInput.getAttribute("value");
        Assert.assertEquals(fakeEmail, emailInputText);

        //Fill out all the required steps
        WebElement firstName = driver.findElement(By.id("customer_firstname"));
        fakeFirstName = faker.name().firstName();
        firstName.sendKeys(fakeFirstName);

        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        fakeLastName = faker.name().lastName();
        lastName.sendKeys(fakeLastName);

        String expectedFullName = fakeFirstName + " " + fakeLastName;
        WebElement password = driver.findElement(By.id("passwd"));
        fakePassword = faker.internet().password();
        password.sendKeys(fakePassword);

        WebElement address = driver.findElement(By.id("address1"));
        fakeAddress = faker.address().streetAddress();
        address.sendKeys(fakeAddress);

        WebElement city = driver.findElement(By.id("city"));
        fakeCity = faker.address().city();
        city.sendKeys(fakeCity);

        WebElement state = driver.findElement(By.id("id_state"));
        Select select = new Select(state);
        random = new Random();
        int stateNumbers = random.nextInt(50) + 1;
        select.selectByIndex(stateNumbers);

        String selectedState = select.getFirstSelectedOption().getText();
        System.out.println(selectedState);

        WebElement postCode = driver.findElement(By.id("postcode"));
        fakeZipCode = faker.address().zipCode().substring(0, 5);
        postCode.sendKeys(fakeZipCode);

        WebElement country = driver.findElement(By.id("id_country"));

        Select countrySelect = new Select(country);
        countrySelect.selectByIndex(1);
        String selectedCountry = countrySelect.getFirstSelectedOption().getText();

        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        mobilePhone.sendKeys(faker.phoneNumber().cellPhone());

        driver.findElement(By.id("submitAccount")).click();
        String fullName = driver.findElement(By.xpath("//a[@class='account']//span")).getText();
        Assert.assertEquals(fullName, expectedFullName);

        //Click on My personal information
        driver.findElement(By.xpath("//a[@title='Information']/span")).click();

        //Verify that personal information is displayed correctly
        String inputFirstName = driver.findElement(By.id("firstname")).getAttribute("value");
        Assert.assertEquals(inputFirstName, fakeFirstName);

        String inputLastName = driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(inputLastName, fakeLastName);

        String inputEmail = driver.findElement(By.id("email")).getAttribute("value");
        Assert.assertEquals(inputEmail, fakeEmail);

        //Click on Back to your account
        driver.findElement(By.linkText("Back to your account")).click();

       // Click on My addresses verify that address information is displayed correctly
        driver.findElement(By.linkText("My addresses")).click();

        String myAddress = driver.findElement(By.xpath("//div[@class='addresses']//ul//li[4]//span[1]")).getText();
        String myAddressCity = driver.findElement(By.xpath("//div[@class='addresses']//ul//li[5]//span[1]")).getText().replace(",","");
        String myAddressState = driver.findElement(By.xpath("//div[@class='addresses']//ul//li[5]//span[2]")).getText();
        String myAddressZip = driver.findElement(By.xpath("//div[@class='addresses']//div//div//ul//li[5]//span[3]")).getText();
        String myAddressCountry = driver.findElement(By.xpath("//div[@class='addresses']//div//div//ul//li[6]//span[1]")).getText();

        Assert.assertEquals(myAddress, fakeAddress);
        Assert.assertEquals(myAddressCity, fakeCity);
        Assert.assertEquals(myAddressState, selectedState);
        Assert.assertEquals(myAddressZip, fakeZipCode);
        Assert.assertEquals(myAddressCountry, selectedCountry);

        //Click on sign out link
        driver.findElement(By.linkText("Sign out")).click();

        //Login using the email and password if the current user
        driver.findElement(By.id("email")).sendKeys(fakeEmail);
        Thread.sleep(1500);
        driver.findElement(By.id("passwd")).sendKeys(fakePassword);
        driver.findElement(By.id("SubmitLogin")).click();

        //Verify that correct first and last name is displayed correctly on top right
        Assert.assertEquals(fullName, expectedFullName);

    }




    @Test
    public void cartDetails(){

    }
}