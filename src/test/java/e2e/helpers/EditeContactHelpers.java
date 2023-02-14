package e2e.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditeContactHelpers extends ContactHelpers {
    public EditeContactHelpers(WebDriver driver) {
        super(driver);
    }

    public void openEditForm() {
        clickOnVisibleElement(By.id("btn-edit-contact"));
    }

    public void editeLastNameAndDescription(String lastName, String description) {
        fillField(lastName, By.cssSelector("[name='input-ec-lastName']"));
        fillField(description, By.cssSelector("[name='input-ec-description']"));
    }

    public void saveEditedContact() {
        clickOnVisibleElement(By.xpath("//*[@class='col-sm-3']//*[@type='submit']"));
    }
}
