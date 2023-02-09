package e2e.tests;

import e2e.helpers.LoginHelpers;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EditContactInfoTest extends LoginHelpers {

    By searchField = By.id("input-search-contact");
    By myCreatedContact = By.cssSelector("div[class='d-flex justify-content-between'] b");
    By editButton = By.id("btn-edit-contact");

    By descriptionField = By.name("input-ec-description");

    By buttonSave = By.cssSelector("button[class='btn btn-primary submit-btn-ec']");


    @Test
    public void editContactInformation() {
        String contactFirstName = "Alexandra";
        String contactLastName = "Balitskaya";
        String firstAndLastName = contactFirstName + contactLastName;
        driver.findElement(searchField).sendKeys(firstAndLastName);
        driver.findElement(myCreatedContact).click();
        driver.findElement(editButton).click();
        driver.findElement(descriptionField).click();
        String firstPartOfExpectedDescription = driver.findElement(descriptionField).getText();

        String newDescription = "Updated description for test";
        fillField(newDescription, descriptionField);
        driver.findElement(buttonSave).click();

        String actualTextOfDescription = driver.findElement(descriptionField).getText();
        String expectedTextOfDescription = firstPartOfExpectedDescription + newDescription;
        //Assert.assertEquals(expectedTextOfDescription, actualTextOfDescription);

    }
}
