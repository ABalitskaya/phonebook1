package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TestBase {

    static Logger logger = LoggerFactory.getLogger(TestBase.class);
    protected static ApplicationManager app = new ApplicationManager();


    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup(); //Драйверы для хрома (можно для любого браузера
        logger.info("Setup chrome driver");
    }

    @BeforeMethod
    public void setupTest(Method m, Object[] p) {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p) {
        logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }

    @AfterMethod
    public void tearDown() {
        stop();
    }

    private void stop() {
        if (app.driver != null) {
            app.driver.quit();
        }
    }

}
