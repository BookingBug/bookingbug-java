package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class QuestionTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/question.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Question question = new Question(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(question.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(question.getName().equals(jsonNode.get("name").textValue()));
        assertTrue(question.getRequired().equals(jsonNode.get("required").booleanValue()));
        assertTrue(question.getImportant().equals(jsonNode.get("important").booleanValue()));
        assertTrue(question.getAdminOnly().equals(jsonNode.get("admin_only").booleanValue()));
        assertTrue(question.getAppliesTo().equals(jsonNode.get("applies_to").intValue()));
        assertTrue(question.getAskMember().equals(jsonNode.get("ask_member").booleanValue()));
        assertTrue(question.getDetailType().equals(jsonNode.get("detail_type").textValue()));
        assertTrue(question.getPrice().equals(jsonNode.get("price").intValue()));
        assertTrue(question.getPricePerBooking().equals(jsonNode.get("price_per_booking").booleanValue()));
        assertTrue(question.getOutcome().equals(jsonNode.get("outcome").booleanValue()));
        // TODO: 01.06.2016 Implement and Test getSettings()
        // TODO: 01.06.2016 Test getOptions()
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
