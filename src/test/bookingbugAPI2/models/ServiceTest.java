package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ServiceTest extends ModelTest {
    JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/service.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Service service = new Service(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(service.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(service.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(compareLists(service.getDurations(), jsonNode.toString(), "durations"));
        assertTrue(compareLists(service.getPrices(), jsonNode.toString(),"prices"));
        assertTrue(service.getDetailGroupId().equals(jsonNode.get("detail_group_id").intValue()));
        assertTrue(service.getBookingTimeStep().equals(jsonNode.get("booking_time_step").intValue()));
        assertTrue(service.getIsEventGroup().equals(jsonNode.get("is_event_group").booleanValue()));
        assertTrue(service.getType().equals(jsonNode.get("type").textValue()));
        assertTrue(service.getDeleted().equals(jsonNode.get("deleted").booleanValue()));
        assertTrue(service.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(service.getMinAdvancePeriod().equals(jsonNode.get("min_advance_period").intValue()));
        assertTrue(service.getMaxAdvancePeriod().equals(jsonNode.get("max_advance_period").intValue()));
        assertTrue(service.getMinCancelPeriod().equals(jsonNode.get("min_cancel_period").intValue()));
        assertTrue(service.getBookingTypePublic().equals(jsonNode.get("booking_type_public").textValue()));
        assertTrue(service.getBookingTypeMember().equals(jsonNode.get("booking_type_member").textValue()));
        assertTrue(service.getMinBookings().equals(jsonNode.get("min_bookings").intValue()));
        assertTrue(service.getMaxBookings().equals(jsonNode.get("max_bookings").intValue()));
        assertTrue(compareLists(service.getGroups(), jsonNode.toString(), "groups"));
        assertTrue(service.getOrder().equals(jsonNode.get("order").intValue()));
        assertTrue(service.getChildLevelService().equals(jsonNode.get("child_level_service").booleanValue()));
        assertTrue(service.getItemsLink().equals(jsonLinks.get("items").get("href").textValue()));
        assertTrue(service.getQuestionsLink().equals(jsonLinks.get("questions").get("href").textValue()));
        assertTrue(service.getDaysLink().equals(jsonLinks.get("days").get("href").textValue()));
        assertTrue(service.getTimesLink().equals(jsonLinks.get("times").get("href").textValue()));
        assertTrue(service.getBookLink().equals(jsonLinks.get("book").get("href").textValue()));
        assertTrue(service.getAllChildrenLink().equals(jsonLinks.get("all_children").get("href").textValue()));

        // TODO: 10.06.2016 Implement and Test extra
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
