package api.tests.contact;

import api.model.ContactDto;
import api.tests.ApiBase;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddNewContactTest extends ApiBase {
    ContactDto contactDto;
    Response response;
    Faker faker = new Faker();

    @Test
    public void createContactTest() {
        //contactDto = new ContactDto();
        //contactDto.setFirstName(faker.name().firstName());
        //contactDto.setLastName(faker.name().lastName());
        //contactDto.setDescription(faker.lorem().sentence(5));
        contactDto = new ContactDto(); // получился пустым
        contactDto.setFirstName(faker.name().firstName());
        contactDto.setLastName(faker.name().lastName());
        contactDto.setDescription(faker.lorem().sentence(5));


        response = doPostRequest("/api/contact", 201, contactDto);

        Assert.assertEquals(response.jsonPath().getString("firstName"), contactDto.getFirstName());

    }

}
