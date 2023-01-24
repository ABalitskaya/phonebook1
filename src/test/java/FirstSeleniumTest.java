import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class FirstSeleniumTest {
    WebDriver driver;
    By emailField = By.cssSelector("[placeholder=\"Email\"]");
    By email2Field = By.cssSelector("[placeholder=\"Email\"]");
    By email3Field = By.cssSelector("[placeholder=\"Email\"]");
    //before

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); //Драйверы для хрома (можно для любого браузера
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver(); //выбор браузера
        driver.get("http://phonebook.telran-edu.de:8080/contacts"); //браузер открывает урл
        //driver.navigate().to("https://www.google.ru/");
        driver.manage().window().maximize(); //установка размера экрана
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //выставили 10 секунд для этого шага (возможный максимум)


    }

    //test
    @Test
    public void locators() {
        driver.findElement(By.name("email")).sendKeys("1,2,3");
        driver.findElement(By.cssSelector("[placeholder=\"Password\"]")).sendKeys("1,2,3");
        //driver.findElement(By.xpath("//button[contains(text(), 'Login')]")).click();
        driver.findElement(By.cssSelector(".btn.btn-info"));
    }

    @Test
    public void registerNewUser() throws InterruptedException {
        String userData = "test3@gmail.com";
        driver.findElement(By.id("login-form")).isDisplayed();
        driver.findElement(By.cssSelector("[href=\"/user/registration\"]")).click();
        driver.findElement(By.id("registration-form")).isDisplayed();
        fillField(userData, emailField);
        fillField(userData, By.cssSelector("[placeholder=\"Password\"]"));
        fillField(userData, By.cssSelector("[ng-reflect-name=\"confirm_password\"]"));
        //driver.findElement(By.cssSelector("confirm-password")).sendKeys(userData);
        driver.findElement(By.xpath("//*[@type=\"submit\"]")).click();

        //driver.findElement(By.name("email"));
        //driver.findElement(By.cssSelector("[placeholder=\"Password\"]"));
        //driver.findElement(By.name("confirm-password"));
        //driver.findElement(By.xpath("//button[normalize-space()='Sign up']"));
    }

    private void fillField(String userData, By cssSelector) {
        driver.findElement(cssSelector).click();
        driver.findElement(cssSelector).sendKeys(userData);
    }

    //after
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000); //Ожидание 1000милисекунд (ничего не будет делать)
        if (driver != null) {
            driver.quit(); // закроет браузер
            //driver.close(); закроет вкладку
        }
    }
}

