package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class NoteTest extends ModelTest {
    private JsonNode jsonNode;

    @Override
    @Before
    public void setUp() {
        jsonNode = getJSON("json/note.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Note note = new Note(new HttpServiceResponse(Utils.stringToContentRep(jsonNode.toString())));

        assertTrue(note.getId().equals(jsonNode.get("id").intValue()));
        assertTrue(note.getNote().equals(jsonNode.get("note").textValue()));
    }

    @Override
    @After
    public void tearDown() {
        jsonNode = null;
    }
}
