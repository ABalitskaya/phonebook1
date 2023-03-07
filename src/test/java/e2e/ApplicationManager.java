package e2e;

import com.google.common.io.Files;
import e2e.helpers.CreateContactHelpers;
import e2e.helpers.EditContactHelpers;
import e2e.helpers.LoginHelpers;
import e2e.helpers.RegisterHelpers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static e2e.TestBase.logger;

public class ApplicationManager {

    public WebDriver driver;

    LoginHelpers login;
    RegisterHelpers register;

    CreateContactHelpers createContact;
    EditContactHelpers editeContact;

    public CreateContactHelpers getCreateContact() {
        return createContact;
    }

    public EditContactHelpers getEditeContact() {
        return editeContact;
    }

    public LoginHelpers getLogin() {
        return login;
    }

    public RegisterHelpers getRegister() {
        return register;
    }

    public WebDriver remoteDriverSelenoid() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("firefox");
        capabilities.setVersion("90.0");
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableLog", true);
        driver = new RemoteWebDriver(
                URI.create("http://127.0.0.1:4444/wd/hub").toURL(),
                capabilities);
        return driver;
    }

    protected void init(boolean useRemoteDriver) throws MalformedURLException {
        if (useRemoteDriver == true) {
            driver = remoteDriverSelenoid();
            System.out.println("Using remote driver(Selenoid)");
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            System.out.println("Using local Chromedriver");
        }


        //driver = remoteDriverSelenoid();

        driver.get("http://phonebook.telran-edu.de:8080/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login = new LoginHelpers(driver);
        register = new RegisterHelpers(driver);
        createContact = new CreateContactHelpers(driver);
        editeContact = new EditContactHelpers(driver);
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
