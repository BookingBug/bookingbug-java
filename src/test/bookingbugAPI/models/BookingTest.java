package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class BookingTest extends ModelTest {

    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/booking.json");
    }

    @Override
    @Test
    public void modelInit() throws java.text.ParseException {
        Booking booking = new Booking(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(booking.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(booking.getFullDescribe().equals(jsonNode.get("full_describe").textValue()));
        assertTrue(booking.getResourceName().equals(jsonNode.get("resource_name").textValue()));
        assertTrue(booking.getServiceName().equals(jsonNode.get("service_name").textValue()));
        assertTrue(booking.getResourceId().equals(jsonNode.get("resource_id").intValue()));
        assertTrue(booking.getMemberId().equals(jsonNode.get("member_id").intValue()));
        assertTrue(booking.getClientName().equals(jsonNode.get("client_name").textValue()));
        assertTrue(booking.getClientEmail().equals(jsonNode.get("client_email").textValue()));
        assertTrue(booking.getClientPhone().equals(jsonNode.get("client_phone").textValue()));
        assertTrue(booking.getClientMobile().equals(jsonNode.get("client_mobile").textValue()));
        assertTrue(booking.getServiceId().equals(jsonNode.get("service_id").intValue()));
        assertTrue(booking.getStartDatetime().equals(new DateTime(jsonNode.get("datetime").textValue())));
        assertTrue(booking.getDuration().equals(jsonNode.get("duration").intValue()));
        assertTrue(booking.getOnWaitlist().equals(jsonNode.get("on_waitlist").booleanValue()));
        assertTrue(booking.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(booking.getEventChainId().equals(jsonNode.get("event_chain_id").intValue()));
        assertTrue(booking.getAttended().equals(jsonNode.get("attended").booleanValue()));
        assertTrue(booking.getBookingUpdated().equals(new DateTime(jsonNode.get("booking_updated").textValue())));
        assertTrue(booking.getUpdatedAt().equals(new DateTime(jsonNode.get("updated_at").textValue())));
        assertTrue(booking.getCreatedAt().equals(new DateTime(jsonNode.get("created_at").textValue())));
        assertTrue(booking.getClientId().equals(jsonNode.get("client_id").intValue()));
        assertTrue(booking.getPrice().equals(jsonNode.get("price").intValue()));
        assertTrue(booking.getPaid().equals(jsonNode.get("paid").intValue()));
        assertTrue(booking.getQuantity().equals(jsonNode.get("quantity").intValue()));
        assertTrue(booking.getIsCancelled().equals(jsonNode.get("is_cancelled").booleanValue()));
        assertTrue(booking.getMultiStatus().getHere().equals(jsonNode.get("multi_status").get("here").textValue()));
        assertTrue(booking.getPurchaseId().equals(jsonNode.get("purchase_id").intValue()));
        assertTrue(booking.getPurchaseRef().equals(jsonNode.get("purchase_ref").textValue()));
        assertTrue(booking.getChannel().equals(jsonNode.get("channel").textValue()));
        assertTrue(booking.getStatus().equals(jsonNode.get("status").intValue()));
        assertTrue(booking.getSlotId().equals(jsonNode.get("slot_id").intValue()));
        assertTrue(booking.getClientLink().equals(((jsonLinks.get("client")).get("href")).textValue()));
        assertTrue(booking.getCheckInLink().equals(((jsonLinks.get("check_in")).get("href").textValue())));
        assertTrue(booking.getQuestionsLink().equals(((jsonLinks.get("questions")).get("href").textValue())));
        assertTrue(booking.getEventGroupsLink().equals(((jsonLinks.get("event_groups")).get("href").textValue())));
        assertTrue(booking.getEventChainLink().equals(((jsonLinks.get("event_chain")).get("href").textValue())));
        assertTrue(booking.getEditLink().equals(((jsonLinks.get("edit")).get("href").textValue())));
        assertTrue(booking.getCancelLink().equals(((jsonLinks.get("cancel")).get("href").textValue())));
        assertTrue(booking.getAddressLink().equals(((jsonLinks.get("address")).get("href").textValue())));
        assertTrue(booking.getMinCancellationTime().equals(new DateTime(jsonNode.get("min_cancellation_time").textValue())));
        // TODO: Test notes, client, answers
        // TODO: Implement and Test answers_summary, survey_answers_summary
        // TODO: Implement and Test settings and slot_settings
        // TODO: Implement and Test questions
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }

}
