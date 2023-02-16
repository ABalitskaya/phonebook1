package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class OldEditContactInfoTest extends TestBase {

    @Test(dataProvider = "changeLastNameAndDescription", dataProviderClass = DataProviders.class)
    public void changeEditContactInfo(String lastName, String description) throws InterruptedException {
        String firstName = "Sasha2";
        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().fillFilterField(firstName);
//        app.getContact().checkCountRows(expectedCountRow);
        app.getCreateContact().openContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().descriptionEditSave(lastName, description);
        app.getCreateContact().goToContactPage();
        app.getCreateContact().fillFilterField(firstName);
//        app.getContact().checkCountRows(expectedCountRow);
        app.getCreateContact().openContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
    }

}

/*package e2e.tests;

import e2e.TestBase;
import e2e.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class EditContactInfoTest extends TestBase {

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
        String firstPartOfExpectedDescription = app.driver.findElement(descriptionField).getText();

        String newDescription = "Updated description for test";
        //fillField(newDescription, descriptionField);
        driver.findElement(buttonSave).click();

        String actualTextOfDescription = app.driver.findElement(descriptionField).getText();
        String expectedTextOfDescription = firstPartOfExpectedDescription + newDescription;
        //Assert.assertEquals(expectedTextOfDescription, actualTextOfDescription);

    }

    @Test(dataProvider = "editContactWithCSV", dataProviderClass = DataProviders.class)
    public void editContactInformationWithCVS(String description) throws InterruptedException {
        String contactFirstName = "Alexandra";
        String contactLastName = "Balitskaya";
        String firstAndLastName = contactFirstName + contactLastName;
        driver.findElement(searchField).sendKeys(firstAndLastName);
        driver.findElement(myCreatedContact).click();
        driver.findElement(editButton).click();
        driver.findElement(descriptionField).click();


        String newDescription = description;
        //fillField(newDescription, descriptionField);
        driver.findElement(buttonSave).click();

    }
}
*/