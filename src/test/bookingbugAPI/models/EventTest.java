package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EventTest extends ModelTest {

    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/event.json");
    }

    @Override
    @Test
    public void modelInit() throws java.text.ParseException {
        Event event = new Event(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(event.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(event.getDatetime().equals(new DateTime(jsonNode.get("datetime").textValue())));
        assertTrue(event.getDescription().equals(jsonNode.get("description").textValue()));
        assertTrue(event.getStatus().equals(jsonNode.get("status").intValue()));
        assertTrue(event.getSpacesHeld().equals(jsonNode.get("spaces_held").intValue()));
        assertTrue(event.getSpacesBooked().equals(jsonNode.get("spaces_booked").intValue()));
        assertTrue(event.getSpacesReserved().equals(jsonNode.get("spaces_reserved").intValue()));
        assertTrue(event.getSpacesBlocked().equals(jsonNode.get("spaces_blocked").intValue()));
        assertTrue(event.getNumSpaces().equals(jsonNode.get("num_spaces").intValue()));
        assertTrue(event.getSpacesWait().equals(jsonNode.get("spaces_wait").intValue()));
        assertTrue(event.getEventChainId().equals(jsonNode.get("event_chain_id").intValue()));
        assertTrue(event.getServiceId().equals(jsonNode.get("service_id").intValue()));
        assertTrue(event.getDuration().equals(jsonNode.get("duration").intValue()));
        assertTrue(event.getPrice().equals(jsonNode.get("price").intValue()));
        assertTrue(event.getEventGroupsLink().equals((jsonLinks.get("event_groups")).get("href").textValue()));
        assertTrue(event.getEventChainsLink().equals((jsonLinks.get("event_chains")).get("href").textValue()));
        assertTrue(event.getBookLink().equals((jsonLinks.get("book")).get("href").textValue()));
        // TODO: 01.06.2016 Implement and Test getTicketSpaces()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }

}
