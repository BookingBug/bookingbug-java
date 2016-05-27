package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import helpers.hal_addon.CustomJsonDeserializer;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ClientTest extends ModelTest{
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/client.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Client client =new Client(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");
        CustomJsonDeserializer jsonDeserializer = new CustomJsonDeserializer();

        assertTrue(client.getFirstName().equals(jsonObject.get("first_name")));
        assertTrue(client.getLastName().equals(jsonObject.get("last_name")));
        assertTrue(client.getEmail().equals(jsonObject.get("email")));
        assertTrue(client.getAddress1().equals(jsonObject.get("address1")));
        assertTrue(client.getAddress2().equals(jsonObject.get("address2")));
        assertTrue(client.getAddress3().equals(jsonObject.get("address3")));
        assertTrue(client.getAddress4().equals(jsonObject.get("address4")));
        assertTrue(client.getCountry().equals(jsonObject.get("country")));
        assertTrue(client.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(client.getMemberType().toString().equals(jsonObject.get("member_type").toString()));
        assertTrue(client.getReference().equals(jsonObject.get("reference")));
        assertTrue(client.getFiles().equals(jsonObject.get("files")));
        assertTrue(client.getDeleted().toString().equals(jsonObject.get("deleted").toString()));
        assertTrue(client.getPhonePrefix().equals(jsonObject.get("phone_prefix")));
        assertTrue(client.getMobilePrefix().equals(jsonObject.get("mobile_prefix")));
        assertTrue(client.getBookingsLink().equals(((JSONObject) jsonLinks.get("bookings")).get("href")));
        assertTrue(client.getPrePaidBookingsLink().equals(((JSONObject) jsonLinks.get("pre_paid_bookings")).get("href")));
        assertTrue(client.getQuestionsLink().equals(((JSONObject) jsonLinks.get("questions")).get("href")));
        assertTrue(client.getEditLink().equals(((JSONObject) jsonLinks.get("edit")).get("href")));
/*
        try {
            assertTrue((jsonDeserializer.getMapper().writeValueAsString(client.getAnswers())).equals(jsonObject.get("answers").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
