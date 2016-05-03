package bookingbugAPI.api;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.BookingListParams;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;


public class AdminAPI extends AbstractAPI {

    AdminAPI(ApiConfig builder) {
        super(builder);
    }


    public BookingAPI booking() {
        return new BookingAPI(newConfig());
    }

    public class BookingAPI extends AbstractAPI {

        BookingAPI(ApiConfig config) {
            super(config);
        }

        public BBCollection<Booking> getBookings(Company company, BookingListParams bLParams) throws IOException {
            URL url = new URL(Utils.inflateLink(company.get_bookingsLink(), bLParams.getParams()));
            BBCollection<Booking> bookings = new BBCollection<Booking>(HttpService.api_GET(url, token), token, "bookings", Booking.class);
            return bookings;
        }
    }

}
