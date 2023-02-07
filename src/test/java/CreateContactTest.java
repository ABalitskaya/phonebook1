import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class CreateContactTest extends ChangeLanguage {

    Faker faker = new Faker();

    @DataProvider
    public Iterator<Object[]> newContact() {
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{
                "Sasha", "Balitskaya", "Hi"
        });
        list.add(new Object[]{
                "Sasha1", "Balitskaya1", "Hi1"
        });
        list.add(new Object[]{
                "Sasha2", "Balitskaya2", "Hi2"
        });
        return list.iterator();
    }

    private void openAddNewContact() {
        driver.findElement(By.cssSelector("[href=\"/contacts\"]")).click();
        Assert.assertTrue(isElementPresent(By.xpath("//*[@role=\"dialog\"]")));
        //driver.findElement(button).click();
        //Assert.assertTrue(isElementPresent(dialog));
    }


    @Test(dataProvider = "newContact")
    public void createNewContact(String firstName, String lastName, String description) throws InterruptedException {
        //String firstName = faker.internet().uuid();
        //String lastName = faker.internet().uuid();
        //String description = faker.internet().uuid();
        //String firstAndLastName = firstName + lastName;
        Number expectedCountRow = 1;


        //Click on the button 'Add new contact'
        openAddNewContact();
        //Fill field First name
        /*fillField(firstName, By.xpath("//*[@role='dialog']//*[@placeholder='First name']"));
        //Fill field Last name
        fillField(lastName, By.xpath("//*[@role='dialog']//*[@placeholder='Last name']"));
        //Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));*/

        fillField(firstName, By.id("form-name"));
// "//*[@role='dialog']//*[@placeholder=\"First name\"]"
//Fill field Last name
        fillField(lastName, By.id("form-lastName"));
//Fill field About
        fillField(description, By.xpath("//*[@role='dialog']//*[@placeholder='About']"));

        //Click on the button 'Save'
        Thread.sleep(1000);
        driver.findElement(By.xpath("//form//button[@type=\"submit\"]"
        )).click();
        //Assert.assertFalse(isElementPresent(By.xpath("//*[@role=\"dialog\"]")));
        Assert.assertFalse(isElementPresent(By.xpath("//*[@class=‘modal-content’]")));


        checkItemText(By.id("contact-first-name"), firstName, "Actual first name is not equal expected first name");
        checkItemText(By.id("contact-last-name"), lastName, "Actual last name is not equal expected last name");
        checkItemText(By.id("contact-description"), description, "Actual description is not equal expected description");
        //driver.findElement(By.cssSelector("[href='/']")).click();
        driver.findElement(By.xpath("//a[@class='navbar-brand']//*[name()='svg']")).click();


        //Filter by creation name
        //fillField(firstAndLastName, By.xpath("//*[@placeholder = 'Search...']"));
        //Expected result: Created contact show with correct data in the contact table
        Number actualCountRow = driver.findElements(By.className("list-group")).size();
        //Number actualCountRow = driver.findElements(By.xpath("//div[@id='contacts-list']")).size();

        //Assert.assertEquals(actualCountRow, expectedCountRow);


    }


}
