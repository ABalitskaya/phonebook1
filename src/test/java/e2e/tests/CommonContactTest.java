package e2e.tests;

import com.github.javafaker.Faker;
import e2e.TestBase;
import e2e.helpers.EditeContactHelpers;
import org.testng.annotations.Test;

public class CommonContactTest extends TestBase {
    Faker faker = new Faker();

    @Test
    public void userCanCreateEditRemoveContact() throws InterruptedException {
        String firstName = faker.internet().uuid();
        String lastName = faker.internet().uuid();
        String description = faker.lorem().paragraph(1);
        Number expectedCountRow = 1;

        app.getLogin().login();
        app.getCreateContact().changeLanguage();
        app.getCreateContact().openAddNewContactDialog();
        app.getCreateContact().fillAddNewContactForm(firstName, lastName, description);
        app.getCreateContact().saveNewContact();
        app.getCreateContact().checkFieldsOnContactInfo(firstName, lastName, description);

        String newfirstName = faker.internet().uuid();
        String newLastName = faker.internet().uuid();
        String newdescription = faker.lorem().paragraph(1);


        app.getEditeContact().goToContactPageAndFillFilterField(firstName);
        app.getEditeContact().checkCountRows(1);
        app.getEditeContact().openContact();
        app.getEditeContact().openEditForm();
        app.getEditeContact().editContactInfoForm(newfirstName, newLastName, newdescription);
        app.getEditeContact().saveEditedContact();
        app.getEditeContact().checkFieldsOnContactInfo(newfirstName, newLastName, newdescription);

        EditeContactHelpers getRemoveContact = app.getEditeContact();
        getRemoveContact.goToContactPageAndFillFilterField(newfirstName);
        getRemoveContact.openRemoveContactDialog();
        getRemoveContact.removeContact();
        getRemoveContact.checkCountRows(0);

    }
}
