package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ClientTest extends ModelTest{
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/client.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Client client =new Client(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(client.getFirstName().equals(jsonNode.get("first_name").textValue()));
        assertTrue(client.getLastName().equals(jsonNode.get("last_name").textValue()));
        assertTrue(client.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(client.getAddress1().equals(jsonNode.get("address1").textValue()));
        assertTrue(client.getAddress2().equals(jsonNode.get("address2").textValue()));
        assertTrue(client.getAddress3().equals(jsonNode.get("address3").textValue()));
        assertTrue(client.getAddress4().equals(jsonNode.get("address4").textValue()));
        assertTrue(client.getCountry().equals(jsonNode.get("country").textValue()));
        assertTrue(client.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(client.getMemberType().equals(jsonNode.get("member_type").intValue()));
        assertTrue(client.getReference().equals(jsonNode.get("reference").textValue()));
        assertTrue(client.getDeleted().equals(jsonNode.get("deleted").booleanValue()));
        assertTrue(client.getPhonePrefix().equals(jsonNode.get("phone_prefix").textValue()));
        assertTrue(client.getMobilePrefix().equals(jsonNode.get("mobile_prefix").textValue()));
        assertTrue(client.getBookingsLink().equals((jsonLinks.get("bookings")).get("href").textValue()));
        assertTrue(client.getPrePaidBookingsLink().equals((jsonLinks.get("pre_paid_bookings")).get("href").textValue()));
        assertTrue(client.getQuestionsLink().equals((jsonLinks.get("questions")).get("href").textValue()));
        assertTrue(client.getEditLink().equals((jsonLinks.get("edit")).get("href").textValue()));
        // TODO: 01.06.2016 Implement and Test getFiles()
        // TODO: 01.06.2016 Test getAnswers()
        // TODO: 01.06.2016 Implement and Test getQ()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
