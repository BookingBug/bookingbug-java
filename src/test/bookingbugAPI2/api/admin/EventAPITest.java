package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.Event;
import bookingbugAPI2.models.HttpException;
import bookingbugAPI2.models.SchemaForm;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 29.08.2016.
 */
public class EventAPITest extends AbstractAPITest {

    String authToken = "TOoqPWGtDPa37YkJY8a9Iw";
    String eventLink = "/admin/37048/event_chains/104/events/1048";

    @Test
    public void getNewBookingSchema() throws IOException {
        defaultAPI.configService().auth_token = authToken;
        Event event = new Event(defaultAPI.httpService().api_GET(new URL(defaultAPI.configService().serverUrl + eventLink)));

        SchemaForm schema = defaultAPI.admin().event().getNewBookingSchema(event);
        assertNotNull(schema);
    }
}
