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


public class CouponTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/coupon.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Coupon coupon = new Coupon(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));

        assertTrue(coupon.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(coupon.getCode().equals(jsonObject.get("code")));
        assertTrue(coupon.getTitle().equals(jsonObject.get("title")));
        assertTrue(coupon.getCompanyId().toString().equals(jsonObject.get("company_id").toString()));
        assertTrue(coupon.getValidFrom().equals(new DateTime(jsonObject.get("valid_from"))));
        assertTrue(coupon.getTimesUsed().toString().equals(jsonObject.get("times_used").toString()));
        assertTrue(coupon.getPublic().toString().equals(jsonObject.get("public").toString()));
        assertTrue(coupon.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(coupon.getPercentage().toString().equals(jsonObject.get("percentage").toString()));
        assertTrue(coupon.getCouponType().toString().equals(jsonObject.get("coupon_type").toString()));
        assertTrue(coupon.getBuy().toString().equals(jsonObject.get("buy").toString()));
        assertTrue(coupon.getFree().toString().equals(jsonObject.get("free").toString()));
        assertTrue(coupon.getApplyToQuestions().toString().equals(jsonObject.get("apply_to_questions").toString()));
        assertTrue(coupon.getMaxPerPerson().toString().equals(jsonObject.get("max_per_person").toString()));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
