import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

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

    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000); //Ожидание 1000милисекунд (ничего не будет делать)
        if (driver != null) {
            driver.quit(); // закроет браузер
            //driver.close(); закроет вкладку
        }
    }
}