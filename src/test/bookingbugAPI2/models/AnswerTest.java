package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class AnswerTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/answer.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Answer answer = new Answer(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));
        JsonNode jsonLinks = jsonNode.get("_links");

        assertTrue(answer.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(answer.getValue().equals(jsonNode.get("value").textValue()));
        assertTrue(answer.getPrice().equals(jsonNode.get("price").doubleValue()));
        assertTrue(answer.getQuestionId().equals(jsonNode.get("question_id").intValue()));
        assertTrue(answer.getAdminOnly().equals(jsonNode.get("admin_only").booleanValue()));
        assertTrue(answer.getImportant().equals(jsonNode.get("important").booleanValue()));
        assertTrue(answer.getQuestionText().equals(jsonNode.get("question_text").textValue()));
        assertTrue(answer.getOutcome().equals(jsonNode.get("outcome").booleanValue()));
        assertTrue(answer.getCompanyId().equals(jsonNode.get("company_id").intValue()));
        assertTrue(answer.getQuestionLink().equals((jsonLinks.get("question")).get("href").textValue()));
        // TODO: 01.06.2016 Test getQuestion()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
