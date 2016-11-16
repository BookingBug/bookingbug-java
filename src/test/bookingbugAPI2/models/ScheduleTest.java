package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ScheduleTest extends MemberTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/schedule.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Schedule schedule = new Schedule(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(schedule.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(schedule.getDesc().equals(jsonNode.get("desc").textValue()));
        assertTrue(schedule.getStyle().equals(jsonNode.get("style").intValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
