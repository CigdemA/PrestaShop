package com.prestashop.tests.smoke_tests;


import com.prestashop.pages.CommonPage;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.LoginPage;
import com.prestashop.pages.MyAccountPage;
import com.prestashop.utilities.ConfigurationReader;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
public class AccountInformationTests extends TestBase {

        HomePage homePage;
        LoginPage loginPage;
        MyAccountPage myAccountPage;
        CommonPage commonPage;

        @BeforeMethod
        public void setupPages() {
            homePage = new HomePage();
            loginPage = new LoginPage();
            myAccountPage = new MyAccountPage();
            commonPage = new CommonPage();

            driver.get(ConfigurationReader.getProperty("url"));
            commonPage.signInButton.click();
            loginPage.login(ConfigurationReader.getProperty("username"), ConfigurationReader.getProperty("password"));
        }

        @AfterMethod
        public void tearDown() {
            homePage = null;
            loginPage = null;
            myAccountPage = null;
            commonPage = null;
        }

        /**
         * Login: my account
         * 1.Go to http://automationpractice.com/index.php
         * 2.Click Sign in link
         * 3.Login using valid username and password
         * 4.Verify that title contains My account
         * 5.Verify that account holder full name is displayed next to the Sign out link
         */

        @Test(priority = 1)
        public void login() {
            //4.Verify that title contains My account
            Assert.assertTrue(driver.getTitle().contains("My account"));

            //5.Verify that account holder full name is displayed next to the Sign out link
            Assert.assertEquals(commonPage.fullNameTop.getText(), ConfigurationReader.getProperty("fullname"));
            loginPage.signOutButton.click();
        }

        /**
         * Login: My personal information
         * 6.Click on My personal information button
         * 7.Verify title contains Identity
         * 8.Verify that first name and last name matches the full name on top
         * 9.Click on Save button
         * 10.Verify error message “The password you entered is incorrect.”
         * 11.Click on Back to your account
         * 12.Verify that title contains My account
         */

        @Test (priority = 2, dependsOnMethods = "login")
        public void myPersonalInformation() {
            myAccountPage.myPersonalInfoButton.click();

            //7.Verify title contains Identity
            Assert.assertTrue(driver.getTitle().contains("Identity"));

            //8.Verify that first name and last name matches the full name on top
            String actualFullName = myAccountPage.firstNameInputElm.getAttribute("value") + " " + myAccountPage.lastNameInputElm.getAttribute("value");
            Assert.assertEquals(actualFullName, commonPage.fullNameTop.getText());

            //9.Click on Save button
            myAccountPage.saveButton.click();

            //10.Verify error message “The password you entered is incorrect.”
            String expectedErrorMsg =  "The password you entered is incorrect.";
            Assert.assertEquals(expectedErrorMsg, myAccountPage.errorMessageElm.getText());

            //11.Click on Back to your account
            myAccountPage.backToYourAccount.click();

            //12.Verify that title contains My account
            Assert.assertTrue(driver.getTitle().contains("My account"));
            loginPage.signOutButton.click();
        }

        /**
         * Login: My addresses
         * 13.Click on My addresses
         * 14.Click on Add a new address
         * 15.Verify that first name and last name matches the full name on top
         * 16.Delete the first name
         * 17.Click save
         * 18.Verify error message “firstname is required.”
         */

        @Test (priority = 3, dependsOnMethods = {"myPersonalInformation", "login"})
        public void myAddresses() {
            myAccountPage.myAddressesButton.click();

            myAccountPage.addANewAddressButton.click();

            //15.Verify that first name and last name matches the full name on top
            String actualFullName = myAccountPage.firstNameInputElm.getAttribute("value") + " " + myAccountPage.lastNameInputElm.getAttribute("value");

            Assert.assertEquals(commonPage.fullNameTop.getText(), actualFullName);

            //16.Delete the first name
            myAccountPage.firstNameInputElm.clear();

            //17.Click save
            myAccountPage.saveButtonAddress.click();

            //18.Verify error message “firstname is required.”
            String expectedErrorMsg = "firstname is required.";
            Assert.assertEquals(expectedErrorMsg, myAccountPage.errorMessage.getText());
        }
}
