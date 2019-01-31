package com.prestashop.tests.functional_tests.login;

    import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;

public class RegistrationTestNegativeScneraio extends TestBase {
        RegistrationPage registrationPage;
        LoginPage loginPage;
        CommonPage commonPage;

        String fakeEmail = faker.internet().emailAddress();
        String fakeLastName = faker.name().lastName();
        String fakePassword = faker.internet().password();
        String fakeAddress = faker.address().streetAddress();
        String fakeCity = faker.address().city();
        String fakeState = faker.address().state();
        String fakeZipCode = faker.address().zipCode().substring(0,5);
        String fakePhoneNumber = faker.phoneNumber().cellPhone();

        @BeforeMethod
        public void setupPages() {
            registrationPage = new RegistrationPage();
            loginPage = new LoginPage();
            commonPage = new CommonPage();
        }

        @AfterMethod
        public void tearDown() {
            registrationPage = null;
            loginPage = null;
            commonPage = null;
        }

        /**
         * 1.Open browser
         * 2.Go to http://automationpractice.com/index.php
         * 3.Click Sign in link
         * 4.Enter new valid email to the email field
         * 5.Click on Create Account
         * 6.Fill all the required steps except for first name
         * 7.Click on Register
         * 8.Verify that error message firstname is required. is displayed
         */
        @Test
        public void registrationNegativeScenario() {
            //Go to http://automationpractice.com/index.php
            driver.get(ConfigurationReader.getProperty("url"));

            //Click Sign in link
            commonPage.signInButton.click();

            //Enter new valid email to the email field
            loginPage.emailForRegistration.sendKeys(fakeEmail);

            //Click on Create Account
            loginPage.createAccountButtton.click();

            //Fill all the required steps except for first name
            registrationPage.lastName.sendKeys(fakeLastName);
            registrationPage.passwordInput.sendKeys(fakePassword);
            registrationPage.address.sendKeys(fakeAddress);
            registrationPage.city.sendKeys(fakeCity);
            registrationPage.getState().selectByVisibleText(fakeState);
            registrationPage.postCode.sendKeys(fakeZipCode);
            registrationPage.mobilePhone.sendKeys(fakePhoneNumber);

            //Click on Register
            registrationPage.registerButton.click();

            //Verify that error message "firstname is required." is displayed
            String expectedAlert = "firstname is required.";
            assertTrue(registrationPage.alertElm.isDisplayed());
            assertEquals(expectedAlert, registrationPage.alertElm.getText());
        }
}
