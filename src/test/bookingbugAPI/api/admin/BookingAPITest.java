package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.models.BBCollection;
import bookingbugAPI.models.Booking;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.params.BookingListParams;
import bookingbugAPI.services.CacheService;
import bookingbugAPI.services.HttpService;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 18.05.2016.
 */
public class BookingAPITest {

    private static final String companyId = "37025";
    private static final String token = "x2_5PcI15mq7sEWm70JazA";

    public Company getCompany() {
        Company company = null;
        try {
            URL url = new URL(AdminURLS.Company.company().set("companyId", companyId).expand());
            company = new Company(HttpService.api_GET(url, token), token);
            assertNotNull(company);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }

    @Test
    public void getBookings(){
        Company company = getCompany();

        API.APIBuilder builder = new API.APIBuilder().withCache(CacheService.JDBC()).withAuthToken(token);
        API api = builder.build();

        try {
            BBCollection<Booking> bookings = api.admin().booking().getBookings(company, new BookingListParams(1));
            assertNotNull(bookings);
            assertTrue(bookings.size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
