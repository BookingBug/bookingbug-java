package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AddressTest extends ModelTest{
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/address.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Address address = new Address(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(address.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(address.getAddress1().equals(jsonNode.get("address1").textValue()));
        assertTrue(address.getAddress2().equals(jsonNode.get("address2").textValue()));
        assertTrue(address.getAddress3().equals(jsonNode.get("address3").textValue()));
        assertTrue(address.getAddress4().equals(jsonNode.get("address4").textValue()));
        assertTrue(address.getAddress5().equals(jsonNode.get("address5").textValue()));
        assertTrue(address.getPostcode().equals(jsonNode.get("postcode").textValue()));
        assertTrue(address.getCountry().equals(jsonNode.get("country").textValue()));
        assertTrue(address.getLat().equals(jsonNode.get("lat").doubleValue()));
        assertTrue(address.getLong().equals(jsonNode.get("long").doubleValue()));
        assertTrue(address.getMapUrl().equals(jsonNode.get("map_url").textValue()));
        assertTrue(address.getMapMarker().equals(jsonNode.get("map_marker").textValue()));
        assertTrue(address.getPhone().equals(jsonNode.get("phone").textValue()));
        assertTrue(address.getHomephone().equals(jsonNode.get("homephone").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
