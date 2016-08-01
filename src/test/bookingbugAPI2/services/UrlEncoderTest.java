package bookingbugAPI2.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import helpers2.Http;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 13.04.2016.
 */
public class UrlEncoderTest {

    final String json = "{\"str\":\"val\", \"num\":0, \"arr\":[3, {\"prop\": \"false\"}, 1, null, 6 ], \"obj\":{\"prop1\":null, \"prop2\":[\"elem\"]}}";
    final String nullTrueResCorrect = "arr%5B%5D=3&arr%5B%5D%5Bprop%5D=false&arr%5B%5D=1&arr%5B%5D=6&num=0&obj%5Bprop2%5D%5B%5D=elem&str=val";
    final String nullFalseCorrect = "str=val&num=0&arr%5B%5D=3&arr%5B%5D%5Bprop%5D=false&arr%5B%5D=1&arr%5B%5D=null&arr%5B%5D=6&obj%5Bprop1%5D=null&obj%5Bprop2%5D%5B%5D=elem";

    private HashMap getMap() {
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = new HashMap<>();
        try {
            map = mapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    private boolean sameEncoding(String originalEncoding, String resultEncoding) {
        String[] split = resultEncoding.split("&");
        Set<String> set = new HashSet<>(Arrays.asList(originalEncoding.split("&")));

        if(split.length != set.size()) return false;
        if(!set.containsAll(Arrays.asList(split))) return false;
        return true;
    }

    @Test
    public void encodeMapIgnoreNullTrue(){
        HashMap map = getMap();
        try {
            Http.UrlEncoder encoder = new Http.UrlEncoder(true);
            String res = encoder.encode(map);
            assertTrue(sameEncoding(nullTrueResCorrect, res));
        } catch (Http.EncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void encodeMapIgnoreNullFalse(){
        HashMap map = getMap();
        try {
            Http.UrlEncoder encoder = new Http.UrlEncoder(false);
            String res = encoder.encode(map);
            assertTrue(sameEncoding(nullFalseCorrect, res));
        } catch (Http.EncodingException e) {
            e.printStackTrace();
        }
    }
}
