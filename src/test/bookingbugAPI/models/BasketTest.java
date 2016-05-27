package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class BasketTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/basket.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Basket basket = new Basket(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");

        assertTrue(basket.getCompanyId().equals(jsonObject.get("company_id")));
        assertTrue(basket.getTotalPrice().toString().equals(jsonObject.get("total_price").toString()));
        assertTrue(basket.getTotalDuePrice().toString().equals(jsonObject.get("total_due_price").toString()));
        assertTrue(basket.getCheckoutLink().equals(((JSONObject) jsonLinks.get("checkout")).get("href")));
        assertTrue(basket.getItemsLink().equals(((JSONObject) jsonLinks.get("items")).get("href")));
        assertTrue(basket.getAddItemLink().equals(((JSONObject) jsonLinks.get("add_item")).get("href")));
        assertTrue(basket.getDealLink().equals(((JSONObject) jsonLinks.get("deal")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject =null;
    }
}
