package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AdminTest extends ModelTest{
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/admin.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Administrator admin = new Administrator(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");

        assertTrue(admin.getName().equals(jsonObject.get("name")));
        assertTrue(admin.getEmail().equals(jsonObject.get("email")));
        assertTrue(admin.getRole().equals(jsonObject.get("role")));
        assertTrue(admin.getCompanyName().equals(jsonObject.get("company_name")));
        assertTrue(admin.getCompanyId().toString().equals(jsonObject.get("company_id").toString()));
        assertTrue(admin.getPersonId().toString().equals(jsonObject.get("person_id").toString()));
        assertTrue(admin.getEditLink().equals(((JSONObject) jsonLinks.get("edit")).get("href")));
        assertTrue(admin.getCompanyLink().equals(((JSONObject) jsonLinks.get("company")).get("href")));
        assertTrue(admin.getPersonLink().equals(((JSONObject) jsonLinks.get("person")).get("href")));
        assertTrue(admin.getLoginLink().equals(((JSONObject) jsonLinks.get("login")).get("href")));
        assertTrue(admin.getBaseLoginLink().equals(((JSONObject) jsonLinks.get("base_login")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
