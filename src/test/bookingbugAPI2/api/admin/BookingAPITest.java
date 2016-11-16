package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.BookingListParams;
import bookingbugAPI2.models.params.BookingParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 18.05.2016.
 */
public class BookingAPITest extends AbstractAPITest{

    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if( (Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT") || Objects.equals(request.getMethod(), "DELETE")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/booking.json");
                return new MockResponse().setResponseCode(201).setBody(resp.toString());
            }

            return new MockResponse().setResponseCode(400).setBody("{}");
        }
    };

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    @Test
    public void bookingList(){
        try {
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingParams.List(1, 10));
            assertNotNull(bookings);
            assertTrue(bookings.size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bookingRead(){
        try {
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingParams.List(1, 10));
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
            BBCollection<Booking> bookings = defaultAPI.admin().booking().bookingList(company, new BookingParams.List(1, 10));
            SchemaForm schemaForm = defaultAPI.admin().booking().getEditBookingSchema(bookings.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void bookingCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            BookingParams.Create params = new BookingParams.Create()
                    .setDatetime(new DateTime())
                    .setNotifications(true);
            Booking booking = mockAPI.admin().booking().bookingCreate(company, params);

            assertNotNull(booking);
            server.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void bookingUpdate() {
        try {
            Booking toUpdate = new Booking(new HttpServiceResponse(Utils.stringToContentRep(ModelTest.getJSON("json/booking.json").toString())));
            MockWebServer server = mockServer(dispatcher);
            BookingParams.Update params = new BookingParams.Update()
                    .setDatetime(new DateTime());
            Booking booking = mockAPI.admin().booking().bookingUpdate(toUpdate, params);

            assertNotNull(booking);
            server.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void bookingCancel() {
        try {
            MockWebServer server = mockServer(dispatcher);
            BookingParams.Cancel params = new BookingParams.Cancel()
                    .setNotify(true)
                    .setCancelReason("Because");
            Booking booking = new Booking(new HttpServiceResponse(Utils.stringToContentRep(ModelTest.getJSON("json/booking.json").toString())));
            booking = mockAPI.admin().booking().cancelBooking(booking, params);
            assertNotNull(booking);

            server.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
