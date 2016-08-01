package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

public class PersonTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/person.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Person person = new Person(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(person.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(person.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(person.getDescription().equals(jsonNode.get("description").textValue()));
        assertTrue(person.getType().equals(jsonNode.get("type").textValue()));
        assertTrue(person.getDeleted().equals(jsonNode.get("deleted").booleanValue()));
        assertTrue(person.getDisabled().equals(jsonNode.get("disabled").booleanValue()));
        assertTrue(person.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(person.getOrder().equals(jsonNode.get("order").intValue()));
        assertTrue(person.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(person.getMobile().equals(jsonNode.get("mobile").textValue()));
        assertTrue(person.getQueuingDisabled().equals(jsonNode.get("queuing_disabled").booleanValue()));
        assertTrue(person.getEnabledServicesLinks().equals(getLinksHref(jsonLinks.toString(), "enabled_services")));
        assertTrue(person.getEnabledResourcesLinks().equals(getLinksHref(jsonLinks.toString(), "enabled_resources")));
        assertTrue(person.getItemsLink().equals(jsonLinks.get("items").get("href").textValue()));
        assertTrue(person.getEditLink().equals(jsonLinks.get("edit").get("href").textValue()));
        assertTrue(person.getAttendanceLink().equals(jsonLinks.get("attendance").get("href").textValue()));
        assertTrue(person.getBlockLink().equals(jsonLinks.get("block").get("href").textValue()));
        assertTrue(person.getCalLink().equals(jsonLinks.get("cal").get("href").textValue()));
        assertTrue(person.getOverlayCalLink().equals(jsonLinks.get("overlay_cal").get("href").textValue()));
        assertTrue(person.getScheduleLink().equals(jsonLinks.get("schedule").get("href").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
