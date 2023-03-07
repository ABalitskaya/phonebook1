package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class TestBase {

    static Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app = new ApplicationManager();


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); //Драйверы для хрома (можно для любого браузера
        logger.info("Setup chrome driver");
    }

    @BeforeMethod
    public void setupTest(Method m, Object[] p) throws MalformedURLException {
        app.init(false);
    }


    @AfterMethod
    public void tearDown() {
        stop();
    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            logger.info("PASSED" + result.getMethod().getMethodName()); // + app.getRegister().deleteFiles("records"));
        } else {
            logger.info("FAILED" + result.getMethod().getMethodName()); // + "Screenshot path: " + app.takeScreenshot());
        }

        logger.info("=========================================================================");
    }

    private void stop() {
        if (app.driver != null) {
            app.driver.quit();
        }
    }
    /*protected void stop() {
        driver.quit();
    }*/


}
