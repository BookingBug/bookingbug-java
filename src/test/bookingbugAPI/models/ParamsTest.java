package bookingbugAPI.models;

import bookingbugAPI.models.params.BookingCreateParams;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 13.01.2016.
 */
public class ParamsTest {

    @Test
    public void paramsMap(){
        BookingCreateParams params = new BookingCreateParams();
        params.setDatetime("datetime");
        params.setService_id("service_id");
        params.setMember_id("member_id");

        Map<String, String> map = params.getParams();
        assertTrue(map.size() == 3);

        assertTrue(map.containsKey("datetime"));
        assertTrue(map.containsKey("service_id"));
        assertTrue(map.containsKey("member_id"));

        assertTrue(map.get("datetime").equals("datetime"));
        assertTrue(map.get("service_id").equals("service_id"));
        assertTrue(map.get("member_id").equals("member_id"));
    }
}
