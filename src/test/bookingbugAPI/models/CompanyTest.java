package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;

/**
 * Created by sergiu on 10.06.2016.
 */
public class CompanyTest extends ModelTest {
    JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/company.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Company company = new Company(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(company.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(company.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(company.getDescription().equals(jsonNode.get("description").textValue()));
        assertTrue(company.getAddressId().equals(jsonNode.get("address_id").intValue()));
        assertTrue(company.getWebsite().equals(jsonNode.get("website").textValue()));
        assertTrue(company.getNumericWidgetId().equals(jsonNode.get("numeric_widget_id").intValue()));
        assertTrue(company.getCurrencyCode().equals(jsonNode.get("currency_code").textValue()));
        assertTrue(company.getTimezone().equals(jsonNode.get("timezone").textValue()));
        assertTrue(company.getCountryCode().equals(jsonNode.get("country_code").textValue()));
        assertTrue(company.getLive().equals(jsonNode.get("live").booleanValue()));

        assertTrue(company.getSettingsLink().equals(jsonLinks.get("settings").get("href").textValue()));
        assertTrue(company.getServicesLink().equals(jsonLinks.get("services").get("href").textValue()));
        assertTrue(company.getCategoriesLink().equals(jsonLinks.get("categories").get("href").textValue()));
        assertTrue(company.getAddressLink().equals(jsonLinks.get("address").get("href").textValue()));
        assertTrue(company.getAddressesLink().equals(jsonLinks.get("addresses").get("href").textValue()));
        assertTrue(company.getBookLink().equals(jsonLinks.get("book").get("href").textValue()));
        assertTrue(company.getNamedCategoriesLink().equals(jsonLinks.get("named_categories").get("href").textValue()));
        assertTrue(company.getResourcesLink().equals(jsonLinks.get("resources").get("href").textValue()));
        assertTrue(company.getPeopleLink().equals(jsonLinks.get("people").get("href").textValue()));
        assertTrue(company.getClinicsLink().equals(jsonLinks.get("clinics").get("href").textValue()));
        assertTrue(company.getEventsLink().equals(jsonLinks.get("events").get("href").textValue()));
        assertTrue(company.getEventChainsLink().equals(jsonLinks.get("event_chains").get("href").textValue()));
        assertTrue(company.getEventGroupsLink().equals(jsonLinks.get("event_groups").get("href").textValue()));
        assertTrue(company.getClientDetailsLink().equals(jsonLinks.get("client_details").get("href").textValue()));
        assertTrue(company.getPackagesLink().equals(jsonLinks.get("packages").get("href").textValue()));
        assertTrue(company.getBulkPurchasesLink().equals(jsonLinks.get("bulk_purchases").get("href").textValue()));
        assertTrue(company.getCheckoutLink().equals(jsonLinks.get("checkout").get("href").textValue()));
        assertTrue(company.getTotalLink().equals(jsonLinks.get("total").get("href").textValue()));
        assertTrue(company.getLoginLink().equals(jsonLinks.get("login").get("href").textValue()));
        assertTrue(company.getClientLink().equals(jsonLinks.get("client").get("href").textValue()));
        assertTrue(company.getClientByEmailLink().equals(jsonLinks.get("client_by_email").get("href").textValue()));
        assertTrue(company.getBookingTextLink().equals(jsonLinks.get("booking_text").get("href").textValue()));
        assertTrue(company.getBasketLink().equals(jsonLinks.get("basket").get("href").textValue()));
        assertTrue(company.getDaysLink().equals(jsonLinks.get("days").get("href").textValue()));
        assertTrue(company.getTimesLink().equals(jsonLinks.get("times").get("href").textValue()));
        assertTrue(company.getCouponLink().equals(jsonLinks.get("coupon").get("href").textValue()));
        assertTrue(company.getCompanyQuestionsLink().equals(jsonLinks.get("company_questions").get("href").textValue()));
        assertTrue(company.getEmailPasswordResetLink().equals(jsonLinks.get("email_password_reset").get("href").textValue()));
        assertTrue(company.getDealsLink().equals(jsonLinks.get("deals").get("href").textValue()));
        assertTrue(company.getServiceGroupsLink().equals(jsonLinks.get("service_groups").get("href").textValue()));
        assertTrue(company.getMemberLevelsLink().equals(jsonLinks.get("member_levels").get("href").textValue()));
        assertTrue(company.getFacebookLoginLink().equals(jsonLinks.get("facebook_login").get("href").textValue()));
        assertTrue(company.getSpaceStatusesLink().equals(jsonLinks.get("space_statuses").get("href").textValue()));
        assertTrue(company.getNewPersonLink().equals(jsonLinks.get("new_person").get("href").textValue()));
        assertTrue(company.getNewResourceLink().equals(jsonLinks.get("new_resource").get("href").textValue()));
        assertTrue(company.getSchedulesLink().equals(jsonLinks.get("schedules").get("href").textValue()));
        assertTrue(company.getNewScheduleLink().equals(jsonLinks.get("new_schedule").get("href").textValue()));
        assertTrue(company.getAdministratorsLink().equals(jsonLinks.get("administrators").get("href").textValue()));
        assertTrue(company.getNewAdministratorLink().equals(jsonLinks.get("new_administrator").get("href").textValue()));
        assertTrue(company.getSlotsLink().equals(jsonLinks.get("slots").get("href").textValue()));
        assertTrue(company.getCalendarEventsLink().equals(jsonLinks.get("calendar_events").get("href").textValue()));
        assertTrue(company.getNewServiceLink().equals(jsonLinks.get("new_service").get("href").textValue()));
        assertTrue(company.getBookingsLink().equals(jsonLinks.get("bookings").get("href").textValue()));
        assertTrue(company.getQueuersLink().equals(jsonLinks.get("queuers").get("href").textValue()));
        assertTrue(company.getClientQueuesLink().equals(jsonLinks.get("client_queues").get("href").textValue()));
        assertTrue(company.getNewQueuerLink().equals(jsonLinks.get("new_queuer").get("href").textValue()));
        assertTrue(company.getPusherLink().equals(jsonLinks.get("pusher").get("href").textValue()));
        assertTrue(company.getExternalBookings().equals(jsonLinks.get("external_bookings").get("href").textValue()));
        // TODO: 10.06.2016 Test settings
        // TODO: 10.06.2016 Implement and Test multi-status
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
