package bookingbugAPI.api.admin;

import bookingbugAPI.models.BBCollection;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.ModelTest;
import bookingbugAPI.models.Session;
import bookingbugAPI.models.params.SessionListParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

//TODO: Find company with session
@Ignore
public class SessionAPITest extends AbstractAPITest {
    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/session.json");
                return new MockResponse().setResponseCode(201).setBody(resp.toString());
            }

            return new MockResponse().setResponseCode(400).setBody("{}");
        }
    };

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    @Test
    public void sessionList() {
        try {
            BBCollection<Session> sessions = defaultAPI.admin().session().sessionList(company, new SessionListParams());
            assertNotNull(sessions);
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void sessionRead() {
        try {
            BBCollection<Session> sessions = defaultAPI.admin().session().sessionList(company, new SessionListParams());
            assertNotNull(sessions);

            Session session = defaultAPI.admin().session().sessionRead(company, sessions.getObjectAtIndex(0).id);
            assertNotNull(session);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
