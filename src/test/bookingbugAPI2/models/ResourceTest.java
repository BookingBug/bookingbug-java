package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class ResourceTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/resource.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Resource resource = new Resource(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(resource.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(resource.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(resource.getType().equals(jsonNode.get("type").textValue()));
        assertTrue(resource.getDeleted().equals(jsonNode.get("deleted").booleanValue()));
        assertTrue(resource.getDisabled().equals(jsonNode.get("disabled").booleanValue()));
        assertTrue(resource.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(resource.getEmail().equals(jsonNode.get("email").textValue()));
        assertTrue(resource.getOrder().equals(jsonNode.get("order").intValue()));
        assertTrue(resource.getItemsLink().equals(jsonLinks.get("items").get("href").textValue()));
        assertTrue(resource.getAddressLink().equals(jsonLinks.get("address").get("href").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
