package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class EventTest extends ModelTest {

    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName = null;
        try {
            fileName = classLoader.getResource("json/event.json").getFile();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        try {
             jsonObject = (JSONObject) parser.parse(new FileReader(fileName));
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Test
    public void modelInit() throws java.text.ParseException {
        Event event = new Event(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");
        assertTrue(event.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(event.getDatetime().equals(new DateTime(jsonObject.get("datetime").toString())));
        assertTrue(event.getDescription().equals(jsonObject.get("description").toString()));
        assertTrue(event.getStatus().toString().equals(jsonObject.get("status").toString()));
        assertTrue(event.getSpacesHeld().toString().equals(jsonObject.get("spaces_held").toString()));
        assertTrue(event.getSpacesBooked().toString().equals(jsonObject.get("spaces_booked").toString()));
        assertTrue(event.getSpacesReserved().toString().equals(jsonObject.get("spaces_reserved").toString()));
        assertTrue(event.getSpacesBlocked().toString().equals(jsonObject.get("spaces_blocked").toString()));
        assertTrue(event.getNumSpaces().toString().equals(jsonObject.get("num_spaces").toString()));
        assertTrue(event.getSpacesWait().toString().equals(jsonObject.get("spaces_wait").toString()));
        assertTrue(event.getEventChainId().toString().equals(jsonObject.get("event_chain_id").toString()));
        assertTrue(event.getServiceId().toString().equals(jsonObject.get("service_id").toString()));
        assertTrue(event.getDuration().toString().equals(jsonObject.get("duration").toString()));
        assertTrue(event.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(event.getEventGroupsLink().equals(((JSONObject) jsonLinks.get("event_groups")).get("href")));
        assertTrue(event.getEventChainsLink().equals(((JSONObject) jsonLinks.get("event_chains")).get("href")));
        assertTrue(event.getBookLink().equals(((JSONObject) jsonLinks.get("book")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }

}
