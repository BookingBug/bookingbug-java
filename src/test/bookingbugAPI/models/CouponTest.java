package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class CouponTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/coupon.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Coupon coupon = new Coupon(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(coupon.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(coupon.getCode().equals(jsonNode.get("code").textValue()));
        assertTrue(coupon.getTitle().equals(jsonNode.get("title").textValue()));
        assertTrue(coupon.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(coupon.getValidFrom().equals(new DateTime(jsonNode.get("valid_from").textValue())));
        assertTrue(coupon.getTimesUsed().equals(jsonNode.get("times_used").intValue()));
        assertTrue(coupon.getPublic().equals(jsonNode.get("public").booleanValue()));
        assertTrue(coupon.getPrice().equals(jsonNode.get("price").doubleValue()));
        assertTrue(coupon.getPercentage().equals(jsonNode.get("percentage").doubleValue()));
        assertTrue(coupon.getCouponType().equals(jsonNode.get("coupon_type").intValue()));
        assertTrue(coupon.getBuy().equals(jsonNode.get("buy").intValue()));
        assertTrue(coupon.getFree().equals(jsonNode.get("free").intValue()));
        assertTrue(coupon.getApplyToQuestions().equals(jsonNode.get("apply_to_questions").booleanValue()));
        assertTrue(coupon.getMaxPerPerson().equals(jsonNode.get("max_per_person").intValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
