package com.prestashop.tests.functional_tests;


import com.prestashop.pages.*;
import com.prestashop.utilities.*;
import org.testng.annotations.*;
import static org.testng.Assert.*;

    public class RegistrationTest  extends TestBase {
        HomePage homePage;
        MyAccountPage myAccountPage;
        RegistrationPage registrationPage;
        LoginPage loginPage;
        CommonPage commonPage;

        String fakeEmail = faker.internet().emailAddress();
        String fakeFirstName = faker.name().firstName();
        String fakeLastName = faker.name().lastName();
        String fakePassword = faker.internet().password();
        String fakeAddress = faker.address().streetAddress();
        String fakeCity = faker.address().city();
        String fakeState = faker.address().state();
        String fakeZipCode = faker.address().zipCode().substring(0,5);
        String fakePhoneNumber = faker.phoneNumber().cellPhone();

        @BeforeMethod
        public void setupPages() {
            homePage = new HomePage();
            myAccountPage = new MyAccountPage();
            registrationPage = new RegistrationPage();
            loginPage = new LoginPage();
            commonPage = new CommonPage();
        }

        @AfterMethod
        public void tearDown() {
            homePage = null;
            myAccountPage = null;
            registrationPage = null;
            loginPage = null;
            commonPage = null;
        }

        /**
         * Click Sign in link
         * Enter new valid email to the email field
         * Click on Create Account
         * Verify that email link displays current email
         * Fill out all the required steps
         * Click on Register
         * Verify that correct first and last name is displayed correctly on top right
         * Click on My personal information
         * Verify that personal information is displayed correctly
         * Click on Back to your account
         * Click on My addresses verify that address information is displayed correctly
         * Click on sign out link
         * Login using the email and password if the current user
         * Verify that correct first and last name is displayed correctly on top right
         */

        @Test(priority = 1)
        public void registration() {
            //Click Sign in link
            driver.get(ConfigurationReader.getProperty("url"));
            commonPage.signInButton.click();

            //Enter new valid email to the email field
            loginPage.emailForRegistration.sendKeys(fakeEmail);

            //Click on Create Account
            loginPage.createAccountButtton.click();

            //Verify that email link displays current email
            BrowserUtils.waitFor(2);
            String emailInput = registrationPage.emailInputElm.getAttribute("value");
            assertEquals(emailInput, fakeEmail);

            //Fill out all the required steps
            registrationPage.firstName.sendKeys(fakeFirstName);
            registrationPage.lastName.sendKeys(fakeLastName);
            registrationPage.passwordInput.sendKeys(fakePassword);
            registrationPage.address.sendKeys(fakeAddress);
            registrationPage.city.sendKeys(fakeCity);
            registrationPage.getState().selectByVisibleText(fakeState);
            registrationPage.postCode.sendKeys(fakeZipCode);
            registrationPage.mobilePhone.sendKeys(fakePhoneNumber);

            //Click on Register
            registrationPage.registerButton.click();

            //Verify that correct first and last name is displayed correctly on top right
            assertEquals(commonPage.fullNameTop.getText(), fakeFirstName + " " + fakeLastName);

            //Click on My personal information
            myAccountPage.myPersonalInfoButton.click();

            //Verify that personal information is displayed correctly
            String firstNameInput = myAccountPage.firstNameInputElm.getAttribute("value");
            String lastNameInput = myAccountPage.lastNameInputElm.getAttribute("value");

            assertEquals(firstNameInput, fakeFirstName);
            assertEquals(lastNameInput, fakeLastName);
            assertEquals(emailInput, fakeEmail);

            //Click on Back to your account
            myAccountPage.backToYourAccount.click();

            //Click on My addresses verify that address information is displayed correctly
            myAccountPage.myAddressesButton.click();

            //Click on sign out link
            loginPage.signOutButton.click();

            //Login using the email and password if the current user
            loginPage.login(fakeEmail, fakePassword);

            //Verify that correct first and last name is displayed correctly on top right
            assertEquals(commonPage.fullNameTop.getText(), fakeFirstName + " " + fakeLastName);
        }

    }
