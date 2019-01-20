package com.prestashop.tests.functional_tests;

import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.awt.windows.WEmbeddedFrame;

import java.util.Random;

public class RegistrationNegativeScneraio extends TestBase {

    String fakeEmail, fakeFirstName, fakeLastName, fakeAddress, fakeCity, fakeZipCode, fakePassword;
    Random random;

    @Test
    public void registration() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");

        //Click Sign in link
        WebElement signIn = driver.findElement(By.linkText("Sign in"));
        signIn.click();

        //Enter new valid email to the email field and Click enter
        WebElement emailField = driver.findElement(By.id("email_create"));
        fakeEmail = faker.internet().emailAddress();
        emailField.sendKeys(fakeEmail + Keys.ENTER);
        Thread.sleep(3000);

        //Fill all the required steps except for first name
        WebElement lastName = driver.findElement(By.id("customer_lastname"));
        fakeLastName = faker.name().lastName();
        lastName.sendKeys(fakeLastName);

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

        String selectedNumbers = select.getFirstSelectedOption().getText();
        System.out.println(selectedNumbers);

        WebElement postCode = driver.findElement(By.id("postcode"));
        fakeZipCode = faker.address().zipCode().substring(0,5);
        postCode.sendKeys(fakeZipCode);

        WebElement country = driver.findElement(By.id("id_country"));
        Select country1 = new Select(country);
        country1.selectByIndex(1);
        String selectedCountry = select.getFirstSelectedOption().getText();

        WebElement mobilePhone = driver.findElement(By.id("phone_mobile"));
        mobilePhone.sendKeys(faker.phoneNumber().cellPhone());

        driver.findElement(By.id("submitAccount")).click();

        String msg = driver.findElement(By.xpath("//div[@id='center_column']/div/ol/li")).getText().trim();
        Assert.assertEquals(msg,"firstname is required." );

    }
}
