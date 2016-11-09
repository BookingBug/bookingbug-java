package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.ClientListParams;
import bookingbugAPI2.models.params.ClientParams;
import bookingbugAPI2.models.params.ClientToggleParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 17.06.2016.
 */
public class ClientAPITest extends AbstractAPITest {

    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/client.json");
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
    public void clientList() {
        try {
            BBCollection<Client> clients;

            //All clients
            clients = defaultAPI.admin().client().clientList(company, null);
            assertNotNull(clients);

            //Paginated clients
            clients = defaultAPI.admin().client().clientList(company, new ClientListParams().setPage(1).setPerPage(5));
            assertNotNull(clients);
            assertEquals(clients.size(), 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void clientRead() {
        try {
            BBCollection<Client> clients = defaultAPI.admin().client().clientList(company, new ClientListParams().setPage(1).setPerPage(5));
            assertNotNull(clients);
            assertEquals(clients.size(), 5);

            //Read the first client by id
            Client client = defaultAPI.admin().client().clientRead(company, clients.getObjectAtIndex(0).id);
            assertNotNull(client);

            //Read the first client by emails
            client = defaultAPI.admin().client().clientReadByEmail(company, clients.getObjectAtIndex(0).getEmail());
            assertNotNull(client);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: Remove ignore after 401 bug is solved
    @Ignore
    @Test
    public void clientEditSchema() {
        try {
            BBCollection<Client> clients = defaultAPI.admin().client().clientList(company, new ClientListParams().setPage(1).setPerPage(5));
            assertNotNull(clients);
            assertEquals(clients.size(), 5);

            SchemaForm editSchema = defaultAPI.admin().client().getEditClientSchema(clients.getObjectAtIndex(0));
            assertNotNull(editSchema);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: remove ignore after 404 solved (when including email only)
    @Ignore
    @Test
    public void clientEnableDisable() {
        try {
            BBCollection<Client> clients = defaultAPI.admin().client().clientList(company, new ClientListParams().setPage(1).setPerPage(5));
            assertNotNull(clients);

            ClientToggleParams params = new ClientToggleParams()
                    .setId(clients.getObjectAtIndex(0).id)
                    .setEmail(null)
                    .setDisabled(true);

            Client client = defaultAPI.admin().client().clientEnableDisable(company, params);
            assertNotNull(client);

            //TODO: assert client is disabled

            params = new ClientToggleParams()
                    .setId(null)
                    .setEmail(clients.getObjectAtIndex(0).getEmail())
                    .setDisabled(false);

            client = defaultAPI.admin().client().clientEnableDisable(company, params);
            assertNotNull(client);

            //TODO: assert client is enabled


        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: Remove ignore after 401 bug is solved
    @Ignore
    @Test
    public void clientUpdate() {
        try {
            BBCollection<Client> clients = defaultAPI.admin().client().clientList(company, new ClientListParams().setPage(1).setPerPage(5));
            assertNotNull(clients);

            DateTime joinDate = new DateTime();
            ClientParams.Update params = new ClientParams.Update()
                    .setMobile("0323942453")
                    .setJoin_date(joinDate);

            Client client = defaultAPI.admin().client().clientUpdate(clients.getObjectAtIndex(0), params);
            assertNotNull(client);
            assertEquals(client.getMobile(), params.getMobile());

            //TODO: expand here asserts

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: Make call to test env instead of mock
    @Test
    public void clientCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ClientParams.Create params = new ClientParams.Create()
                    .setEmail("asd@asd.asd")
                    .setFirst_name("First name")
                    .setLast_name("Last name");

            Client client = mockAPI.admin().client().clientCreate(getCompany(), params);
            assertNotNull(client);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
