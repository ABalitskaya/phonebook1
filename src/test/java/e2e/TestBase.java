package e2e;

import com.google.common.io.Files;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public WebDriver driver;

    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);

    }


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); //Драйверы для хрома (можно для любого браузера
        logger().info("Setup chrome driver");
    }

    /* @BeforeMethod
     public void setupTest() {
         driver = new ChromeDriver(); //выбор браузера
         driver.get("http://phonebook.telran-edu.de:8080/contacts"); //браузер открывает урл
         //driver.navigate().to("https://www.google.ru/");
         driver.manage().window().maximize(); //установка размера экрана
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); //выставили 10 секунд для этого шага (возможный максимум)
         logger().info("Start test");
     }*/
    @BeforeMethod
    public void setupTest(Method m, Object[] p) {
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        logger().info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, screenshot);
        return screenshot.getAbsolutePath();
    }




    /*@AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000); //Ожидание 1000милисекунд (ничего не будет делать)
        if (driver != null) {
            driver.quit(); // закроет браузер
            //driver.close(); закроет вкладку
        }
        logger().info("Stop test");

    }*/

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            logger().info("PASSED" + result.getMethod().getMethodName());
        } else {
            logger().info("FAILED" + result.getMethod().getMethodName() + "Screenshot path: " + takeScreenshot());
        }
        logger().info("=========================================================================");

    }

}