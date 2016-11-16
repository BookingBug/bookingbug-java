package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class DealTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/deal.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Deal deal = new Deal(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(deal.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(deal.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(deal.getNum().equals(jsonNode.get("num").intValue()));
        assertTrue(deal.getService_id().equals(jsonNode.get("service_id").intValue()));
        assertTrue(deal.getCreated_at().equals(new DateTime(jsonNode.get("created_at").textValue())));
        assertTrue(deal.getRedemption().equals(jsonNode.get("redemption").intValue()));
        assertTrue(deal.getTotalCodes().equals(jsonNode.get("total_codes").intValue()));
        assertTrue(deal.getUsedCodes().equals(jsonNode.get("used_codes").intValue()));
        assertTrue(deal.getVendor().equals(jsonNode.get("vendor").textValue()));
        assertTrue(deal.getAllowMixedBookings().equals(jsonNode.get("allow_mixed_bookings").booleanValue()));
        assertTrue(deal.getWidgetShow().equals(jsonNode.get("widget_show").booleanValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
