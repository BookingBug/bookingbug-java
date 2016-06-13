package bookingbugAPI.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

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

    public JsonNode getJSON(String jsonFile) {
        ClassLoader classLoader = getClass().getClassLoader();
        String fileName;
        try {
            fileName = classLoader.getResource(jsonFile).getFile();
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readTree(new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> boolean compareLists(List<T> list, String json, String key) {
        try {
            JsonNode jsonArray = new ObjectMapper().readTree(json).get(key);
            if (jsonArray.isArray()) {
                for (JsonNode objNode : jsonArray) {
                    boolean eq = false;
                    for (T item : list) {
                        if (objNode.asText().equals(item.toString())) {
                            eq = true;
                            break;
                        }
                    }
                    if (!eq) {
                        return false;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
