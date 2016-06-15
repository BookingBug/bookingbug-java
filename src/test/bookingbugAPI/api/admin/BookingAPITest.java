package bookingbugAPI.api.admin;

import bookingbugAPI.models.BBCollection;
import bookingbugAPI.models.Booking;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.SchemaForm;
import bookingbugAPI.models.params.BookingListParams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 18.05.2016.
 */
public class BookingAPITest extends AbstractAPITest{

    private Company company;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    @Test
    public void bookingList(){
        try {
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingListParams(1).setPerPage(10));
            assertNotNull(bookings);
            assertTrue(bookings.size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bookingRead(){
        try {
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingListParams(1).setPerPage(10));
            Booking booking = defaultAPI.admin().booking().bookingRead(company, bookings.getObjectAtIndex(0).id);
            assertNotNull(booking);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bookingEditSchema() {
        try {
            //Paginated services
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingListParams(1).setPerPage(10));
            SchemaForm schemaForm = defaultAPI.admin().booking().getEditBookingSchema(bookings.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
