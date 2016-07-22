package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ClinicTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/clinic.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Clinic clinic = new Clinic(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(clinic.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(clinic.getStartTime().equals(new DateTime(jsonNode.get("start_time").textValue())));
        assertTrue(clinic.getEndTime().equals(new DateTime(jsonNode.get("end_time").textValue())));
        assertTrue(compareLists(clinic.getResourceIds(),jsonNode.toString(),"resource_ids"));
        assertTrue(compareLists(clinic.getPersonIds(),jsonNode.toString(),"person_ids"));
        assertTrue(compareLists(clinic.getServiceIds(),jsonNode.toString(),"service_ids"));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
