package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class BasketTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/basket.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Basket basket = new Basket(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(basket.getCompanyId().equals(jsonNode.get("company_id").textValue()));
        assertTrue(basket.getTotalPrice().equals(jsonNode.get("total_price").intValue()));
        assertTrue(basket.getTotalDuePrice().equals(jsonNode.get("total_due_price").intValue()));
        assertTrue(basket.getCheckoutLink().equals(jsonLinks.get("checkout").get("href").textValue()));
        assertTrue(basket.getItemsLink().equals(jsonLinks.get("items").get("href").textValue()));
        assertTrue(basket.getAddItemLink().equals(jsonLinks.get("add_item").get("href").textValue()));
        assertTrue(basket.getDealLink().equals(jsonLinks.get("deal").get("href").textValue()));
        // TODO: 01.06.2016 Test getItems()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
