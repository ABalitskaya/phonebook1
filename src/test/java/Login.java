import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;

public class Login extends TestBase {
    @BeforeMethod
    public void login() {
        By loginForm = By.id("login-form");
        By emailField = By.cssSelector("[placeholder=\"Email\"]");
        By passwordField = By.cssSelector("[placeholder=\"Password\"]");
        By loginButton = By.xpath("//*[@type=\"submit\"]");
        By contactTable = By.id("contacts-list");

        String loginAndPass = "test@gmail.com";
        driver.findElement(loginForm).isDisplayed();
        driver.findElement(passwordField).isDisplayed();

        fillField(loginAndPass, emailField);
        fillField(loginAndPass, passwordField);
        driver.findElement(loginButton).click();

        driver.findElement(contactTable);


    }


}
