package bookingbugAPI.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import helpers.HttpServiceResponse;
import helpers.Utils;
import helpers.hal_addon.CustomJsonDeserializer;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AnswerTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/answer.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Answer answer = new Answer(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));
        JSONObject jsonLinks = (JSONObject) jsonObject.get("_links");
        JSONObject jsonEmbedded = (JSONObject) jsonObject.get("_embedded");
        CustomJsonDeserializer jsonDeserializer = new CustomJsonDeserializer();

        assertTrue(answer.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(answer.getValue().equals(jsonObject.get("value")));
        assertTrue(answer.getPrice().toString().equals(jsonObject.get("price").toString()));
        assertTrue(answer.getQuestionId().toString().equals(jsonObject.get("question_id").toString()));
        assertTrue(answer.getAdminOnly().toString().equals(jsonObject.get("admin_only").toString()));
        assertTrue(answer.getImportant().toString().equals(jsonObject.get("important").toString()));

        assertTrue(answer.getQuestion().id.equals("15571"));

        assertTrue(answer.getQuestionText().equals(jsonObject.get("question_text")));
        assertTrue(answer.getOutcome().toString().equals(jsonObject.get("outcome").toString()));
        assertTrue(answer.getCompanyId().toString().equals(jsonObject.get("company_id").toString()));
        assertTrue(answer.getQuestionLink().equals(((JSONObject) jsonLinks.get("question")).get("href")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
