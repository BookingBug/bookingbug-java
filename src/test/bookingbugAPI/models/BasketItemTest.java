package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class BasketItemTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/basket_item.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        BasketItem basketItem = new BasketItem(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");

        assertTrue(basketItem.getEventId().toString().equals(jsonObject.get("event_id").toString()));
        assertTrue(basketItem.getPersonId().toString().equals(jsonObject.get("person_id").toString()));
        assertTrue(basketItem.getServiceId().toString().equals(jsonObject.get("service_id").toString()));
        assertTrue(basketItem.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(basketItem.getNumBook().toString().equals(jsonObject.get("num_book").toString()));
        assertTrue(basketItem.getPersonName().equals(jsonObject.get("person_name")));
        assertTrue(basketItem.getServiceName().equals(jsonObject.get("service_name")));
        assertTrue(basketItem.getStatus().toString().equals(jsonObject.get("status").toString()));
        assertTrue(basketItem.getId().equals(jsonObject.get("id")));
        assertTrue(basketItem.getDate().equals(new DateTime(jsonObject.get("date"))));
        assertTrue(basketItem.getTime().toString().equals(jsonObject.get("time").toString()));
        assertTrue(basketItem.getDuration().toString().equals(jsonObject.get("duration").toString()));
        assertTrue(basketItem.getTotalPrice().toString().equals(jsonObject.get("total_price").toString()));
        assertTrue(basketItem.getAttachmentLink().equals(((JSONObject) jsonLinks.get("attachment")).get("href")));
        assertTrue(basketItem.getAddAtachmentLink().equals(((JSONObject) jsonLinks.get("add_attachment")).get("href")));
        assertTrue(basketItem.getPersonLink().equals(((JSONObject) jsonLinks.get("person")).get("href")));
        assertTrue(basketItem.getServiceLink().equals(((JSONObject) jsonLinks.get("service")).get("href")));
        assertTrue(basketItem.getCompanyLink().equals(((JSONObject) jsonLinks.get("company")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
