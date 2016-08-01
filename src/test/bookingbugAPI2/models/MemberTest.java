package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class MemberTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/member.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Member member = new Member(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())), jsonNode.get("auth_token").textValue());
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(member.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(member.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(member.getFirstName().equals(jsonNode.get("first_name").textValue()));
        assertTrue(member.getLastName().equals(jsonNode.get("last_name").textValue()));
        assertTrue(member.getClientType().equals(jsonNode.get("client_type").textValue()));
        assertTrue(member.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(member.getAddress1().equals(jsonNode.get("address1").textValue()));
        assertTrue(member.getAddress2().equals(jsonNode.get("address2").textValue()));
        assertTrue(member.getAddress3().equals(jsonNode.get("address3").textValue()));
        assertTrue(member.getAddress4().equals(jsonNode.get("address4").textValue()));
        assertTrue(member.getPostCode().equals(jsonNode.get("postcode").textValue()));
        assertTrue(member.getCountry().equals(jsonNode.get("country").textValue()));
        assertTrue(member.getPhone().equals(jsonNode.get("phone").textValue()));
        assertTrue(member.getPhonePrefix().equals(jsonNode.get("phone_prefix").textValue()));
        assertTrue(member.getMobile().equals(jsonNode.get("mobile").textValue()));
        assertTrue(member.getMobilePrefix().equals(jsonNode.get("mobile_prefix").textValue()));
        assertTrue(member.getAuthToken().equals(jsonNode.get("auth_token").textValue()));
        assertTrue(member.getPath().equals(jsonNode.get("path").textValue()));
        assertTrue(member.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(member.getHasActiveWallet().equals(jsonNode.get("has_active_wallet").booleanValue()));
        assertTrue(member.getHasWallet().equals(jsonNode.get("has_wallet").booleanValue()));
        assertTrue(member.getBookingsLink().equals(jsonLinks.get("bookings").get("href").textValue()));
        assertTrue(member.getPrePaidBookingslink().equals(jsonLinks.get("pre_paid_bookings").get("href").textValue()));
        assertTrue(member.getPurchaseTotalsLink().equals(jsonLinks.get("purchase_totals").get("href").textValue()));
        assertTrue(member.getEditMemberLink().equals(jsonLinks.get("edit_member").get("href").textValue()));
        assertTrue(member.getCompanyLink().equals(jsonLinks.get("company").get("href").textValue()));
        assertTrue(member.getUpdatePasswordLink().equals(jsonLinks.get("update_password").get("href").textValue()));
        assertTrue(member.getSendWelcomeEmailLink().equals(jsonLinks.get("send_welcome_email").get("href").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
