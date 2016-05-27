package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AddressTest extends ModelTest{
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/address.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Address address = new Address(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));

        assertTrue(address.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(address.getAddress1().equals(jsonObject.get("address1")));
        assertTrue(address.getAddress2().equals(jsonObject.get("address2")));
        assertTrue(address.getAddress3().equals(jsonObject.get("address3")));
        assertTrue(address.getAddress4().equals(jsonObject.get("address4")));
        assertTrue(address.getAddress5().equals(jsonObject.get("address5")));
        assertTrue(address.getPostcode().equals(jsonObject.get("postcode")));
        assertTrue(address.getCountry().equals(jsonObject.get("country")));
        assertTrue(address.getLat().toString().equals(jsonObject.get("lat").toString()));
        assertTrue(address.getLong().toString().equals(jsonObject.get("long").toString()));
        assertTrue(address.getMapUrl().equals(jsonObject.get("map_url")));
        assertTrue(address.getMapMarker().equals(jsonObject.get("map_marker")));
        assertTrue(address.getPhone().equals(jsonObject.get("phone")));
        assertTrue(address.getHomephone().equals(jsonObject.get("homephone")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
