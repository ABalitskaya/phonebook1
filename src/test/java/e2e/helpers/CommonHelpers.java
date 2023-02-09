package e2e.helpers;

import e2e.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

public class CommonHelpers extends TestBase {
    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).sendKeys(userData);
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void checkItemText(By locator, String expectedText, String err) {
        String actualText = driver.findElement(locator).getText();

        Assert.assertEquals(actualText, expectedText, err);
    }

    public boolean isElementClickable(By by) {
        try {
            driver.findElement(by).click();
            return true;
        } catch (NoSuchElementException exception) {
            exception.printStackTrace();
            return false;
        }
    }


}