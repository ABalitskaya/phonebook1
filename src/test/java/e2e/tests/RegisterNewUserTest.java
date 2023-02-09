package e2e.tests;

import com.github.javafaker.Faker;
import e2e.helpers.CommonHelpers;
import e2e.helpers.RegisterHelpers;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends CommonHelpers {

    Faker faker = new Faker();
    public RegisterHelpers registerHelpers = new RegisterHelpers();


    //Positive
    @Test
    public void registerNewUserWithValidData() {
        //Arrange

        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillRegistrationForm(userData, password);

        registerHelpers.clickSignUpButton();
        //checkItemText(errorMessageBlock, expectedErrorMessage, err);

    }


    //Negative
    @Test
    public void registerNewUserWithInvalidData() {
        //Arrange
        String userData = faker.internet().password();
        String password = faker.internet().emailAddress();
        String expectedEmailErrorMessage = "Email must be a valid email address.";
        String expectedPasswordErrorMessage = "Password must be no longer than 20 characters.";

        //Act
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresent(registerHelpers.errorMessageBlock));
        String actualEmailErrorMessage = driver.findElement(registerHelpers.errorEmailMessageBlock).getText();
        String actualPasswordMaxLengthMessageBlock = driver.findElement(registerHelpers.errorPasswordMaxLengthMessageBlock).getText();
        //Assert
        registerHelpers.checkErrorMessage(registerHelpers.errorEmailMessageBlock, expectedEmailErrorMessage);
        registerHelpers.checkErrorMessage(registerHelpers.errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);

    }


    //Negative
   /* @Test
    public void registerExistingUser() {

        //Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists e2e.helpers.Login now?";
        //Act
        registerHelpers.goToRegistrationPage();
        registerHelpers.fillField(userData, emailField);
        registerHelpers.fillField(password, passwordField);
        registerHelpers.fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        registerHelpers.clickSignUpButton();
        //Assert
        registerHelpers.checkErrorMessage(registerHelpers.errorMessageBlock, expectedErrorMessage);

    }*/


}