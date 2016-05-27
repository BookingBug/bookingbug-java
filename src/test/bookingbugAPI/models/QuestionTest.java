package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import helpers.hal_addon.CustomJsonDeserializer;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class QuestionTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/question.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Question question = new Question(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        CustomJsonDeserializer jsonDeserializer = new CustomJsonDeserializer();

        assertTrue(question.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(question.getName().equals(jsonObject.get("name")));
        assertTrue(question.getRequired().toString().equals(jsonObject.get("required").toString()));
        assertTrue(question.getImportant().toString().equals(jsonObject.get("important").toString()));
        assertTrue(question.getAdminOnly().toString().equals(jsonObject.get("admin_only").toString()));
        assertTrue(question.getAppliesTo().toString().equals(jsonObject.get("applies_to").toString()));
        assertTrue(question.getAskMember().toString().equals(jsonObject.get("ask_member").toString()));
        assertTrue(question.getDetailType().equals(jsonObject.get("detail_type").toString()));
        assertTrue(question.getSettings().equals(jsonObject.get("settings")));
        assertTrue(question.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(question.getPricePerBooking().toString().equals(jsonObject.get("price_per_booking").toString()));
        assertTrue(question.getOutcome().toString().equals(jsonObject.get("outcome").toString()));
/*
        try {
            assertTrue((jsonDeserializer.getMapper().writeValueAsString(question.getOptions())).equals(jsonObject.get("options").toString()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
*/
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
