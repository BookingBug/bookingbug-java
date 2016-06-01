package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class BasketItemTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/basket_item.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        BasketItem basketItem = new BasketItem(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks =  jsonNode.get("_links");

        assertTrue(basketItem.getEventId().equals(jsonNode.get("event_id").intValue()));
        assertTrue(basketItem.getPersonId().equals(jsonNode.get("person_id").intValue()));
        assertTrue(basketItem.getServiceId().equals(jsonNode.get("service_id").intValue()));
        assertTrue(basketItem.getPrice().equals(jsonNode.get("price").intValue()));
        assertTrue(basketItem.getNumBook().equals(jsonNode.get("num_book").intValue()));
        assertTrue(basketItem.getPersonName().equals(jsonNode.get("person_name").textValue()));
        assertTrue(basketItem.getServiceName().equals(jsonNode.get("service_name").textValue()));
        assertTrue(basketItem.getStatus().equals(jsonNode.get("status").intValue()));
        assertTrue(basketItem.getId().equals(jsonNode.get("id").textValue()));
        assertTrue(basketItem.getDate().equals(new DateTime(jsonNode.get("date").textValue())));
        assertTrue(basketItem.getTime().equals(jsonNode.get("time").intValue()));
        assertTrue(basketItem.getDuration().equals(jsonNode.get("duration").intValue()));
        assertTrue(basketItem.getTotalPrice().equals(jsonNode.get("total_price").intValue()));
        assertTrue(basketItem.getAttachmentLink().equals((jsonLinks.get("attachment")).get("href").textValue()));
        assertTrue(basketItem.getAddAtachmentLink().equals((jsonLinks.get("add_attachment")).get("href").textValue()));
        assertTrue(basketItem.getPersonLink().equals((jsonLinks.get("person")).get("href").textValue()));
        assertTrue(basketItem.getServiceLink().equals((jsonLinks.get("service")).get("href").textValue()));
        assertTrue(basketItem.getCompanyLink().equals((jsonLinks.get("company")).get("href").textValue()));
        // TODO: 01.06.2016 Implement and Test getQuestions()
        // TODO: 01.06.2016 Test getSettings()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
