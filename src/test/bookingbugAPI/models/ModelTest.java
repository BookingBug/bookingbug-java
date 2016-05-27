package bookingbugAPI.models;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Abstract ModelTest class. Must be inherited by all tests for bb models
 * Has setUp, tearDown and modelInit methods
 */
@Ignore
public abstract class ModelTest {

    @Before
    public abstract void setUp();

    @Test
    public abstract void modelInit() throws ParseException;

    @After
    public abstract void tearDown();

    public JSONObject getJSON(String jsonFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName;
        try {
            fileName = classLoader.getResource(jsonFile).getFile();

            JSONParser parser = new JSONParser();
            return  (JSONObject) parser.parse(new FileReader(fileName));
        } catch (org.json.simple.parser.ParseException | IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return null;
    }
}
