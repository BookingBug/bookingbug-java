package helpers2;

import bookingbugAPI2.services.http.PlainHttpService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertNotNull;

/**
 * Testing that the urls are available and receiving requests (the response is not relevant).
 */
public class TokenGeneratorTest {

    private static final String CompanyId = "37023";

    @Test
    public void companyDetails(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.createObjectNode();

            ((ObjectNode)jsonNode).put("first_name", "John");
            ((ObjectNode)jsonNode).put("last_name", "Smith");
            ((ObjectNode)jsonNode).put("email", "cornel+test@assist.ro");
            ((ObjectNode)jsonNode).put("mobile", "0123456789");

            String token = TokenGenerator.getInstance(CompanyId, "abc").create(jsonNode);

            String urlStr = new Config().serverUrl + "/login/sso/" + CompanyId + "?token=" + token;
            //String urlStr = "https://eu1.bookingbug.com/api/v1/login/sso/37000?token=" + token;
            //String urlStr = "https://eu1.bookingbug.com/api/v1/login/sso/36990?token=" + token;
            //String urlStr = "http://192.168.100.123:3000/api/v1/login/sso/37021?token=" + token + "";

            URL url = new URL(urlStr);
            assertNotNull(PlainHttpService.api_POST(url, true));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}