import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterNewUserTest extends TestBase {
    By loginForm = By.id("login-form");
    By userRegistrationLink = By.cssSelector("[href=\"/user/registration\"]");
    By registrationForm = By.id("registration-form");
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By passwordField = By.cssSelector("[placeholder=\"Password\"]");
    By confirmPasswordField = By.cssSelector("[ng-reflect-name=\"confirm_password\"]");
    By loginButton = By.xpath("//*[@type=\"submit\"]");
    By errorMessageBlock = By.id("error-message");
    By errorEmailMessageBlock = By.id("email-error-invalid");
    By errorPasswordMaxLengthMessageBlock = By.id("password-error-maxlength");
    Faker faker = new Faker();

    private void fillRegistrationForm(String userData, String password) {
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
    }

    private void checkErrorMessage(By locator, String expectedErrorMessage) {
        String actualErrorMessage = driver.findElement(locator).getText();
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }

    private void goToRegistrationPage() {
        Assert.assertTrue(isElementPresent(loginForm));
        driver.findElement(userRegistrationLink).click();
        Assert.assertTrue(isElementPresent(registrationForm));
    }

    //Positive
    @Test
    public void registerNewUserWithValidData() {
        //Arrange
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        //Act
        goToRegistrationPage();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        clickSignUpButton();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        //Assert
        String err = "Actual error message is not equal expected";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, err);
    }

    private void clickSignUpButton() {
        driver.findElement(loginButton).click();
        driver.findElement(loginButton).isEnabled();
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
        goToRegistrationPage();
        fillRegistrationForm(userData, password);
        Assert.assertFalse(isElementPresent(errorMessageBlock));
        String actualEmailErrorMessage = driver.findElement(errorEmailMessageBlock).getText();
        String actualPasswordMaxLengthMessageBlock = driver.findElement(errorPasswordMaxLengthMessageBlock).getText();
        //Assert
        checkErrorMessage(errorEmailMessageBlock, expectedEmailErrorMessage);
        checkErrorMessage(errorPasswordMaxLengthMessageBlock, expectedPasswordErrorMessage);

    }


    //Negative
    @Test
    public void registerExistingUser() {
        //Arrange
        String userData = "test@gmail.com";
        String password = "test@gmail.com";
        String expectedErrorMessage = "Error! User already exists Login now?";
        //Act
        goToRegistrationPage();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        driver.findElement(loginButton).click();
        clickSignUpButton();
        //Assert
        checkErrorMessage(errorMessageBlock, expectedErrorMessage);
    }
}