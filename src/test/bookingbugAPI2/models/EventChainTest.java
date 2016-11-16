package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class EventChainTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/event_chain.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        EventChain eventChain = new EventChain(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(eventChain.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(eventChain.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(eventChain.getDescription().equals(jsonNode.get("description").textValue()));
        assertTrue(eventChain.getDuration().equals(jsonNode.get("duration").intValue()));
        assertTrue(eventChain.getGroup().equals(jsonNode.get("group").textValue()));
        assertTrue(eventChain.getTime().equals(new DateTime(jsonNode.get("time").textValue())));
        assertTrue(eventChain.getLongDescription().equals(jsonNode.get("long_description").textValue()));
        assertTrue(eventChain.getCapacityView().equals(jsonNode.get("capacity_view").intValue()));
        assertTrue(eventChain.getStartDate().equals(new DateTime(jsonNode.get("start_date").textValue())));
        assertTrue(eventChain.getEndDate().equals(new DateTime(jsonNode.get("end_date").textValue())));
        assertTrue(eventChain.getSpaces().equals(jsonNode.get("spaces").intValue()));
        assertTrue(eventChain.getPrice().equals(jsonNode.get("price").intValue()));
        assertTrue(eventChain.getMaxNumBookings().equals(jsonNode.get("max_num_bookings").intValue()));
        assertTrue(eventChain.getMinAdvanceTime().equals(new DateTime(jsonNode.get("min_advance_time").textValue())));
        assertTrue(eventChain.getTicketType().equals(jsonNode.get("ticket_type").textValue()));
        assertTrue(eventChain.getEmailPerTicket().equals(jsonNode.get("email_per_ticket").booleanValue()));
        assertTrue(eventChain.getQuestionPerTicket().equals(jsonNode.get("questions_per_ticket").booleanValue()));
        assertTrue(eventChain.getCourse().equals(jsonNode.get("course").booleanValue()));
        assertTrue(eventChain.getQuestionsLink().equals((jsonLinks.get("questions")).get("href").textValue()));
        // TODO: 01.06.2016 Implement and Test getQuestions()
        // TODO: 01.06.2016 Implement and Test getExtra()
        // TODO: 01.06.2016 Test getChildEventChains()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
