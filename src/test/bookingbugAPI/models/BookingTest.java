package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import helpers.hal_addon.CustomJsonDeserializer;
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
        JsonNode jsonLinks = (JsonNode) jsonNode.get("_links");
        JsonNode jsonEmbedded = (JsonNode) jsonNode.get("_embedded");
        CustomJsonDeserializer jsonDeserializer = new CustomJsonDeserializer();

        assertTrue(booking.getId().toString().equals(jsonNode.get("id").asText()));
        assertTrue(booking.getFull_describe().equals(jsonNode.get("full_describe").asText()));
        assertTrue(booking.getResource_name().equals(jsonNode.get("resource_name").asText()));
        assertTrue(booking.getService_name().equals(jsonNode.get("service_name").asText()));
        assertTrue(booking.getResource_id().toString().equals(jsonNode.get("resource_id").asText()));
        assertTrue(booking.getMember_id().toString().equals(jsonNode.get("member_id").asText()));
        assertTrue(booking.getClient_name().equals(jsonNode.get("client_name").asText()));
        assertTrue(booking.getClient_email().equals(jsonNode.get("client_email").asText()));
        assertTrue(booking.getClient_phone().equals(jsonNode.get("client_phone").asText()));
        assertTrue(booking.getClient_mobile().equals(jsonNode.get("client_mobile").asText()));
        assertTrue(booking.getService_id().equals(jsonNode.get("service_id").asText()));

        //TODO: fix datetime with Joda and java.util.Date. Also find way to compare dates (not strings)
        //assertTrue(booking.getDatetime().equals(new DateTime(jsonNode.get("datetime").toString())));


        assertTrue(booking.getDuration().toString().equals(jsonNode.get("duration").asText()));
        assertTrue(booking.getOn_waitlist().toString().equals(jsonNode.get("on_waitlist").asText()));
        assertTrue(booking.getCompany_id().toString().equals(jsonNode.get("company_id").asText()));
        assertTrue(booking.getEventChainId().toString().equals(jsonNode.get("event_chain_id").asText()));
        assertTrue(booking.getAttended().toString().equals(jsonNode.get("attended").asText()));

        //TODO: fix datetime with Joda and java.util.Date. Also find way to compare dates (not strings)
        //assertTrue(booking.getBooking_updated().equals(new DateTime(jsonNode.get("booking_updated"))));
        //assertTrue(booking.getUpdated_at().equals(new DateTime(jsonNode.get("updated_at"))));
        //assertTrue(booking.getCreated_at().equals(new DateTime(jsonNode.get("created_at"))));

        assertTrue(booking.getClient_id().toString().equals(jsonNode.get("client_id").asText()));
        assertTrue(booking.getPrice().toString().equals(jsonNode.get("price").asText()));
        assertTrue(booking.getPaid().toString().equals(jsonNode.get("paid").asText()));
        assertTrue(booking.getQuantity().toString().equals(jsonNode.get("quantity").asText()));
        assertTrue(booking.getIs_cancelled().toString().equals(jsonNode.get("is_cancelled").asText()));

        //TODO: find way to compare maps
        //assertTrue(booking.getMulti_status().equals(jsonNode.get("multi_status").asText()));

        assertTrue(booking.getPurchase_id().toString().equals(jsonNode.get("purchase_id").asText()));
        assertTrue(booking.getPurchase_ref().equals(jsonNode.get("purchase_ref").asText()));
        assertTrue(booking.getChannel().equals(jsonNode.get("channel").asText()));
        assertTrue(booking.getStatus().toString().equals(jsonNode.get("status").asText()));
        assertTrue(booking.getSlot_id().toString().equals(jsonNode.get("slot_id").asText()));
        assertTrue(booking.getClientLink().equals(((JsonNode) jsonLinks.get("client")).get("href").asText()));
        assertTrue(booking.getCheckInLink().equals(((JsonNode) jsonLinks.get("check_in")).get("href").asText()));
        assertTrue(booking.getQuestionsLink().equals(((JsonNode) jsonLinks.get("questions")).get("href").asText()));
        assertTrue(booking.getEventGroupsLink().equals(((JsonNode) jsonLinks.get("event_groups")).get("href").asText()));
        assertTrue(booking.getEventChainLink().equals(((JsonNode) jsonLinks.get("event_chain")).get("href").asText()));
        assertTrue(booking.getEditLink().equals(((JsonNode) jsonLinks.get("edit")).get("href").asText()));
        assertTrue(booking.getCancelLink().equals(((JsonNode) jsonLinks.get("cancel")).get("href").asText()));
        assertTrue(booking.getAddressLink().equals(((JsonNode) jsonLinks.get("address")).get("href").asText()));


        //TODO: find way to compare maps
        //assertTrue(booking.getSettings().equals(jsonNode.get("settings")));
        //assertTrue(booking.getSlot_settings().equals(jsonNode.get("slot_settings")));

        //TODO: find way to compare arrays
        //assertTrue(booking.getSurveyAnswersSummary().equals(jsonNode.get("survey_answers_summary").asText()));

        assertTrue(booking.getMinCancellationTime().equals(new DateTime(jsonNode.get("min_cancellation_time").asText())));

        /*try {
            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getNotes())).equals(jsonNode.get("notes").toString()));
//            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getClient())).equals(jsonEmbedded.get("client").toString()));
//            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getAnswers())).equals(jsonEmbedded.get("answers").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }

}
