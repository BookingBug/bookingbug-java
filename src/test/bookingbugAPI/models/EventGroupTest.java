package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class EventGroupTest extends ModelTest{
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/event_group.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        EventGroup eventGroup = new EventGroup(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");

        assertTrue(eventGroup.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(eventGroup.getName().equals(jsonObject.get("name")));
        assertTrue(eventGroup.getDescription().equals(jsonObject.get("description")));
        assertTrue(eventGroup.getImagesLink().equals(((JSONObject) jsonLinks.get("images")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
