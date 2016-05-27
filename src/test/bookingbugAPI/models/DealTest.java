package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class DealTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/deal.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Deal deal = new Deal(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));

        assertTrue(deal.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(deal.getName().equals(jsonObject.get("name")));
        assertTrue(deal.getNum().toString().equals(jsonObject.get("num").toString()));
        assertTrue(deal.getService_id().toString().equals(jsonObject.get("service_id").toString()));
        assertTrue(deal.getCreated_at().equals(new DateTime(jsonObject.get("created_at"))));
        assertTrue(deal.getRedemption().toString().equals(jsonObject.get("redemption").toString()));
        assertTrue(deal.getTotalCodes().toString().equals(jsonObject.get("total_codes").toString()));
        assertTrue(deal.getUsedCodes().toString().equals(jsonObject.get("used_codes").toString()));
        assertTrue(deal.getVendor().equals(jsonObject.get("vendor")));
        assertTrue(deal.getAllowMixedBookings().toString().equals(jsonObject.get("allow_mixed_bookings").toString()));
        assertTrue(deal.getWidgetShow().toString().equals(jsonObject.get("widget_show").toString()));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
