package helpers2;

import bookingbugAPI2.api.AbstractAPI;
import bookingbugAPI2.api.AdminURLS;
import bookingbugAPI2.services.cache.MockCacheService;
import bookingbugAPI2.services.http.PlainHttpService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.Test;

import java.net.URL;
import java.util.HashMap;

import static org.junit.Assert.assertNotNull;

/**
 * Testing that the urls are available and receiving requests (the response is not relevant).
 */
public class TokenGeneratorTest {

    private static final String companyId = "37048";
    //private static final String companyId = "37023";

    @Test
    public void companyDetails(){
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.createObjectNode();

            ((ObjectNode)jsonNode).put("first_name", "John2");
            ((ObjectNode)jsonNode).put("last_name", "Smith");
            ((ObjectNode)jsonNode).put("email", "sefu@bookingbug.com");
            ((ObjectNode)jsonNode).put("mobile", "0123466789");

            String token = TokenGenerator.getInstance(companyId, "abcd").create(jsonNode);

            AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig()
                    .withCacheService(new MockCacheService())
                    .withAuthToken(token);

            String loginUrl = new AdminURLS(config).login().sso().set("companyId", companyId).set("token", token).expand();
            HttpServiceResponse response = config.httpService().api_POST(new URL(loginUrl), new HashMap<>());
            assertNotNull(response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}