package bookingbugAPI2.api.admin;

import bookingbugAPI2.api.AdminURLS;
import bookingbugAPI2.models.Administrator;
import bookingbugAPI2.models.Login;
import bookingbugAPI2.models.ModelTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Created by sebi on 19.08.2016.
 */
public class LoginAPITest extends AbstractAPITest {

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if (Objects.equals(request.getMethod(), "POST") && request.getBodySize() != 0) {
                String body = request.getBody().readUtf8();
                JsonNode resp;
                if(body.contains("email=sebii%40assist.ro")) {
                    resp = ModelTest.getJSON("json/simple_login.json");
                    return new MockResponse().setResponseCode(200).setBody(resp.toString());
                } else {
                    resp = ModelTest.getJSON("json/multiple_login.json");
                    return new MockResponse().setResponseCode(400).setBody(resp.toString());
                }
            }

            return new MockResponse().setResponseCode(404).setBody("{}");
        }
    };

    @Test
    public void auth() throws IOException {
        MockWebServer server = mockServer(dispatcher);

        //Test simple login
        Login login = mockAPI.admin().login().auth("sebii@assist.ro", "Assist123");
        assertNotNull(login);
        assertNotNull(login.getAuthToken());

        //Test multiple login (multiple companies)
        login = mockAPI.admin().login().auth("sebi+2@assist.ro", "Assist123");
        assertNotNull(login);
        assertNull(login.getAuthToken());
        assertTrue(login.isMultiLogin());

        server.shutdown();
    }

    @Test
    public void authWithCompanyAdministrator() throws IOException {
        MockWebServer server = mockServer(dispatcher);

        //Test multiple login (multiple companies)
        Login login = mockAPI.admin().login().auth("sebi+2@assist.ro", "Assist123");
        assertNotNull(login);
        assertNull(login.getAuthToken());
        assertTrue(login.isMultiLogin());

        for(Administrator administrator : login.getAdministrators()) {
            //Login with each company. Change email to get the simple_login
            Login adminLogin = mockAPI.admin().login().authWithCompanyAdministrator(administrator, "sebii@assist.ro", "Assist123");
            assertNotNull(adminLogin);
            assertNotNull(adminLogin.getAuthToken());
            assertFalse(adminLogin.isMultiLogin());
        }

        server.shutdown();
    }
}
