package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class LoginTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/login.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Login login = new Login(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(login.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(login.getAuthToken().equals(jsonNode.get("auth_token").textValue()));
        assertTrue(login.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(login.getPath().equals(jsonNode.get("path").textValue()));
        assertTrue(login.getAdministratorLink().equals(jsonLinks.get("administrator").get("href").textValue()));

        // TODO: 13.06.2016 Test getMembers(), getAdministrators()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
