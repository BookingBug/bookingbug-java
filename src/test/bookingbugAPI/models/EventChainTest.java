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


public class EventChainTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/event_chain.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        EventChain eventChain = new EventChain(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");

        assertTrue(eventChain.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(eventChain.getName().equals(jsonObject.get("name")));
        assertTrue(eventChain.getDescription().equals(jsonObject.get("description")));
        assertTrue(eventChain.getDuration().toString().equals(jsonObject.get("duration").toString()));
        assertTrue(eventChain.getGroup().equals(jsonObject.get("group")));
        assertTrue(eventChain.getTime().equals(new DateTime(jsonObject.get("time"))));
        assertTrue(eventChain.getLongDescription().equals(jsonObject.get("long_description")));
        assertTrue(eventChain.getCapacityView().toString().equals(jsonObject.get("capacity_view").toString()));
        assertTrue(eventChain.getStartDate().equals(new DateTime(jsonObject.get("start_date"))));
        assertTrue(eventChain.getEndDate().equals(new DateTime(jsonObject.get("end_date"))));
        assertTrue(eventChain.getSpaces().toString().equals(jsonObject.get("spaces").toString()));
        assertTrue(eventChain.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(eventChain.getMaxNumBookings().toString().equals(jsonObject.get("max_num_bookings").toString()));
        assertTrue(eventChain.getMinAdvanceTime().equals(new DateTime(jsonObject.get("min_advance_time"))));
        assertTrue(eventChain.getTicketType().equals(jsonObject.get("ticket_type")));
        assertTrue(eventChain.getEmailPerTicket().toString().equals(jsonObject.get("email_per_ticket").toString()));
        assertTrue(eventChain.getQuestionPerTicket().toString().equals(jsonObject.get("questions_per_ticket").toString()));
        assertTrue(eventChain.getCourse().toString().equals(jsonObject.get("course").toString()));
        assertTrue(eventChain.getQuestionsLink().equals(((JSONObject) jsonLinks.get("questions")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
