package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class EventGroupTest extends ModelTest{
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/event_group.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        EventGroup eventGroup = new EventGroup(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(eventGroup.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(eventGroup.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(eventGroup.getDescription().equals(jsonNode.get("description").textValue()));
        assertTrue(eventGroup.getImagesLink().equals((jsonLinks.get("images")).get("href").textValue()));
        // TODO: 01.06.2016 Implement and Test getExtra()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
