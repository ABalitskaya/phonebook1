package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonHelpers {
    public WebDriverWait wait;

    WebDriver driver;

    public CommonHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriverWait setWait() {
        wait = new WebDriverWait(driver, 10);
        return wait;
    }


    public void clickOnVisibleElement(By locator) {
        Assert.assertTrue(isElementPresent(locator));
        driver.findElement(locator).click();
    }

    public void openDialog(By locator) {
        clickOnVisibleElement(locator);
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role='dialog']")));
    }

    public void fillField(String userData, By locator) {
        driver.findElement(locator).click();
        driver.findElement(locator).clear();
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

    public boolean isElementClickable(By by) {
        try {
            driver.findElement(by).click();
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

}
