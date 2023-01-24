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
    Faker faker = new Faker();


    @Test
    public void registerNewUser() throws InterruptedException {
        String userData = faker.internet().emailAddress();
        String password = faker.internet().password();
        String expectedErrorMessage = "noErrorMsg";
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(userRegistrationLink).click();
        driver.findElement(registrationForm).isDisplayed();
        fillField(userData, emailField);
        fillField(password, passwordField);
        fillField(password, confirmPasswordField);
        //driver.findElement(By.cssSelector("confirm-password")).sendKeys(userData);
        driver.findElement(loginButton).click();
        String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

        //driver.findElement(By.name("email"));
        //driver.findElement(By.cssSelector("[placeholder=\"Password\"]"));
        //driver.findElement(By.name("confirm-password"));
        //driver.findElement(By.xpath("//button[normalize-space()='Sign up']"));
    }


}
