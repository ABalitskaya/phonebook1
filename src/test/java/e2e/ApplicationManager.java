package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditeContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static e2e.TestBase.logger;

public class ApplicationManager {

    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;

    CreateContactHelpers createContact;
    EditeContactHelpers editeContact;

    public CreateContactHelpers getCreateContact() {
        return createContact;
    }

    public EditeContactHelpers getEditeContact() {
        return editeContact;
    }

    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }


    protected void init() {
        WebDriverManager.chromedriver().setup(); //Драйверы для хрома (можно для любого браузера
        driver = new ChromeDriver();
        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        createContact = new CreateContactHelpers(driver);
        editeContact = new EditeContactHelpers(driver);
    }

    public String takeScreenshot() throws IOException {
        File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File screenshot = new File("reference/screen" + System.currentTimeMillis() + ".png");

        Files.copy(tmp, screenshot);
        return screenshot.getAbsolutePath();
    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            logger.info("PASSED" + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED" + result.getMethod().getMethodName() + "Screenshot path: " + takeScreenshot());
        }
        logger.info("=========================================================================");

    }
}
