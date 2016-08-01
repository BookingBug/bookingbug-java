package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AdminTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/admin.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Administrator admin = new Administrator(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(admin.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(admin.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(admin.getRole().equals(jsonNode.get("role").textValue()));
        assertTrue(admin.getCompanyName().equals(jsonNode.get("company_name").textValue()));
        assertTrue(admin.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(admin.getPersonId().equals(jsonNode.get("person_id").intValue()));
        assertTrue(admin.getEditLink().equals((jsonLinks.get("edit")).get("href").textValue()));
        assertTrue(admin.getCompanyLink().equals((jsonLinks.get("company")).get("href").textValue()));
        assertTrue(admin.getPersonLink().equals((jsonLinks.get("person")).get("href").textValue()));
        assertTrue(admin.getLoginLink().equals((jsonLinks.get("login")).get("href").textValue()));
        assertTrue(admin.getBaseLoginLink().equals((jsonLinks.get("base_login")).get("href").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
