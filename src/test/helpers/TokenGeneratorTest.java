package helpers;

import bookingbugAPI.models.HttpException;
import bookingbugAPI.services.HttpService;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.net.MalformedURLException;
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
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("first_name", "John");
            jsonObj.put("last_name", "Smith");
            jsonObj.put("email", "cornel+test@assist.ro");
            jsonObj.put("mobile", "0123456789");

            String token = TokenGenerator.getInstance(CompanyId, "abc").create(jsonObj);

            String urlStr = new Config().serverUrl + "/login/sso/" + CompanyId + "?token=" + token;
            //String urlStr = "https://eu1.bookingbug.com/api/v1/login/sso/37000?token=" + token;
            //String urlStr = "https://eu1.bookingbug.com/api/v1/login/sso/36990?token=" + token;
            //String urlStr = "http://192.168.100.123:3000/api/v1/login/sso/37021?token=" + token + "";

            URL url = new URL(urlStr);
            assertNotNull(HttpService.api_POST(url, true));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}