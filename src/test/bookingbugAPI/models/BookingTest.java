package bookingbugAPI.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import helpers.HttpServiceResponse;
import helpers.Utils;
import helpers.hal_addon.CustomJsonDeserializer;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class BookingTest extends ModelTest {

    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/booking.json");
    }

    @Override
    @Test
    public void modelInit() throws java.text.ParseException {
        Booking booking = new Booking(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");
        JSONObject jsonEmbedded = (JSONObject) jsonObject.get("_embedded");
        CustomJsonDeserializer jsonDeserializer = new CustomJsonDeserializer();

        assertTrue(booking.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(booking.getFull_describe().equals(jsonObject.get("full_describe").toString()));
        assertTrue(booking.getResource_name().equals(jsonObject.get("resource_name").toString()));
        assertTrue(booking.getService_name().equals(jsonObject.get("service_name").toString()));
        assertTrue(booking.getResource_id().toString().equals(jsonObject.get("resource_id").toString()));
        assertTrue(booking.getMember_id().toString().equals(jsonObject.get("member_id").toString()));
        assertTrue(booking.getClient_name().equals(jsonObject.get("client_name")));
        assertTrue(booking.getClient_email().equals(jsonObject.get("client_email")));
        assertTrue(booking.getClient_phone().equals(jsonObject.get("client_phone")));
        assertTrue(booking.getClient_mobile().equals(jsonObject.get("client_mobile")));
        assertTrue(booking.getService_id().equals(jsonObject.get("service_id").toString()));

        //TODO: fix datetime with Joda and java.util.Date. Also find way to compare dates (not strings)
        //assertTrue(booking.getDatetime().equals(new DateTime(jsonObject.get("datetime").toString())));


        assertTrue(booking.getDuration().toString().equals(jsonObject.get("duration").toString()));
        assertTrue(booking.getOn_waitlist().toString().equals(jsonObject.get("on_waitlist").toString()));
        assertTrue(booking.getCompany_id().toString().equals(jsonObject.get("company_id").toString()));
        assertTrue(booking.getEventChainId().toString().equals(jsonObject.get("event_chain_id").toString()));
        assertTrue(booking.getAttended().toString().equals(jsonObject.get("attended").toString()));

        //TODO: fix datetime with Joda and java.util.Date. Also find way to compare dates (not strings)
        //assertTrue(booking.getBooking_updated().equals(new DateTime(jsonObject.get("booking_updated"))));
        //assertTrue(booking.getUpdated_at().equals(new DateTime(jsonObject.get("updated_at"))));
        //assertTrue(booking.getCreated_at().equals(new DateTime(jsonObject.get("created_at"))));

        assertTrue(booking.getClient_id().toString().equals(jsonObject.get("client_id").toString()));
        assertTrue(booking.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(booking.getPaid().toString().equals(jsonObject.get("paid").toString()));
        assertTrue(booking.getQuantity().toString().equals(jsonObject.get("quantity").toString()));
        assertTrue(booking.getIs_cancelled().toString().equals(jsonObject.get("is_cancelled").toString()));
        assertTrue(booking.getMulti_status().equals(jsonObject.get("multi_status")));
        assertTrue(booking.getPurchase_id().toString().equals(jsonObject.get("purchase_id").toString()));
        assertTrue(booking.getPurchase_ref().equals(jsonObject.get("purchase_ref")));
        assertTrue(booking.getChannel().equals(jsonObject.get("channel")));
        assertTrue(booking.getStatus().toString().equals(jsonObject.get("status").toString()));
        assertTrue(booking.getSlot_id().toString().equals(jsonObject.get("slot_id").toString()));
        assertTrue(booking.getClientLink().equals(((JSONObject) jsonLinks.get("client")).get("href")));
        assertTrue(booking.getCheckInLink().equals(((JSONObject) jsonLinks.get("check_in")).get("href")));
        assertTrue(booking.getQuestionsLink().equals(((JSONObject) jsonLinks.get("questions")).get("href")));
        assertTrue(booking.getEventGroupsLink().equals(((JSONObject) jsonLinks.get("event_groups")).get("href")));
        assertTrue(booking.getEventChainLink().equals(((JSONObject) jsonLinks.get("event_chain")).get("href")));
        assertTrue(booking.getEditLink().equals(((JSONObject) jsonLinks.get("edit")).get("href")));
        assertTrue(booking.getCancelLink().equals(((JSONObject) jsonLinks.get("cancel")).get("href")));
        assertTrue(booking.getAddressLink().equals(((JSONObject) jsonLinks.get("address")).get("href")));
        assertTrue(booking.getSettings().equals(jsonObject.get("settings")));
        assertTrue(booking.getSlot_settings().equals(jsonObject.get("slot_settings")));
        assertTrue(booking.getSurveyAnswersSummary().equals(jsonObject.get("survey_answers_summary")));
        assertTrue(booking.getMinCancellationTime().equals(new DateTime(jsonObject.get("min_cancellation_time"))));
        try {
            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getNotes())).equals(jsonObject.get("notes").toString()));
//            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getClient())).equals(jsonEmbedded.get("client").toString()));
//            assertTrue((jsonDeserializer.getMapper().writeValueAsString(booking.getAnswers())).equals(jsonEmbedded.get("answers").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }

}
