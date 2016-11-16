package bookingbugAPI2.models;

import bookingbugAPI2.models.params.BookingCreateParams;
import bookingbugAPI2.models.params.ClientCreateParams;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;
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

        Map map = params.getParams();

        assertTrue(map.containsKey("datetime"));
        assertTrue(map.containsKey("service_id"));
        assertTrue(map.containsKey("member_id"));

        assertTrue(map.get("datetime").equals("datetime"));
        assertTrue(map.get("service_id").equals("service_id"));
        assertTrue(map.get("member_id").equals("member_id"));
    }

    @Test
    public void clientCreateParamsFromMap(){
        ClientCreateParams params = new ClientCreateParams();
        params.setAddress1("addr1");
        params.setFirst_name("first");
        params.setLast_name("Last");

        Map<String, String> map = params.getParams();

        assertTrue(map.containsKey("first_name"));
        assertTrue(map.get("first_name").equals("first"));

        Map<String, String> map2 = new HashMap<>();
        map2.put("last_name", "last");
        map2.put("postcode", "123");
        map2.put("member_type", "3");

        ClientCreateParams params2 = new ClientCreateParams(map2);
        assertTrue(params2.getLast_name().equals("last"));
        assertTrue(params2.getPostcode().equals("123"));
        assertTrue(params2.getMember_type().equals("3"));
    }

    @Test
    public void jsonParamsTest() {
        String json = "{\"str\":\"val\", \"num\":0, \"arr\":[29], \"arr2\":[{\"name\":\"mkyong1\"}, {\"name\":\"mkyong1\"}]}}";
        BookingCreateParams params = new BookingCreateParams();
        params.setJson(json);
        Map m = params.getParams();
        assertNotNull(m);
        assertTrue(m.containsKey("arr2") && m.get("arr2") != null && m.get("arr2") instanceof ArrayList);
        assertTrue(m.containsKey("str") && "val".equals(m.get("str")));
    }
}
