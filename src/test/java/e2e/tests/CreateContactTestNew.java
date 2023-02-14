package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

public class CreateContactTestNew extends TestBase {

    Faker faker = new Faker();


    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
    public void createNewContactDataProvider(String firstName, String lastName, String description) throws InterruptedException {
        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class)
    public void createNewContactWithCVS(String firstName, String lastName, String description) throws InterruptedException {

        Number expectedCountRow = 1;
        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);
        app.getCreateContact().goToContactPageAndFillFilterField(firstName);
        app.getCreateContact().checkCountRows(expectedCountRow);
    }

    //negative test
    @Test
    public void createContactWithInvalidData() throws InterruptedException {
        String firstName = " ";
        String lastName = " ";
        String description = " ";
        String expectedErrorMessage = "Contact save fail";


        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();

        //String actualErrorMessage = driver.findElement(errorMessageBlock).getText();
        //Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
    }


    @Test
    public void createContactWithEmptyFirstFill() throws InterruptedException {

        String expectedErrorMessageOfForm = "To add a contact, you must specify a name";

        app.getCreateContact().openAddNewContactDialog();

        //clickOnInputFields();

        //checkErrorMsg(expectedErrorMessageOfForm);

    }


}

