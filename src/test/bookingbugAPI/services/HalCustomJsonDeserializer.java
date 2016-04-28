package bookingbugAPI.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import helpers.hal_addon.CustomJsonDeserializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 27.04.2016.
 */
public class HalCustomJsonDeserializer {

    private ObjectMapper mapper;

    @Before
    public void setUp() {
        this.mapper = new ObjectMapper();

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Object.class, new CustomJsonDeserializer());
        this.mapper.registerModule(simpleModule);
    }

    @After
    public void tearDown() throws Exception {
        mapper = null;
    }

    @Test
    public void nullFieldJsonTest() throws IOException {
        Map map = mapper.readValue("{\"settings\":{\"who_cancelled\":{\"embed\":null}, \"late\":1}}", Map.class);
        assertTrue(map.get("settings") != null);
        assertTrue(((Map)map.get("settings")).get("who_cancelled") != null);
        assertTrue(((Map)((Map)map.get("settings")).get("who_cancelled")).get("embed") != null);

    }
}

