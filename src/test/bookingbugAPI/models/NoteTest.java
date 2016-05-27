package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import helpers.Utils;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertTrue;


public class NoteTest extends ModelTest {
    private JSONObject jsonObject;

    @Override
    @Before
    public void setUp() {
        jsonObject = getJSON("json/note.json");
    }

    @Override
    @Test
    public void modelInit() throws ParseException {
        Note note = new Note(new HttpServiceResponse(Utils.stringToContentRep(jsonObject.toString())));

        assertTrue(note.getId().toString().equals(jsonObject.get("id").toString()));
        assertTrue(note.getNote().equals(jsonObject.get("note")));
    }

    @Override
    @After
    public void tearDown() {
        jsonObject = null;
    }
}
