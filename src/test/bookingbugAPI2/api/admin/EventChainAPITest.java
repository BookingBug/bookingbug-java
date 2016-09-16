package bookingbugAPI2.api.admin;

import bookingbugAPI2.api.API;
import bookingbugAPI2.api.AbstractAPI;
import bookingbugAPI2.api.AdminAPI;
import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.EventChainParams;
import bookingbugAPI2.models.params.Params;
import bookingbugAPI2.services.cache.MockCacheService;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EventChainAPITest extends AbstractAPITest {


    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/event_chain.json");
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
    public void eventChainList() {
        try {
            BBCollection<EventChain> eventChains;

            //All eventChains
            eventChains = defaultAPI.admin().eventChain().eventChainList(company, null);
            assertNotNull(eventChains);

            //Paginated eventChains
            eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventChains);
            assertTrue(eventChains.size() <= 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainRead() {
        try {
            BBCollection<EventChain> eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventChains);
            assertTrue(eventChains.size() <= 5);

            //Read the first eventChain by id
            if(eventChains.size() > 0) {
                EventChain eventChain = defaultAPI.admin().eventChain().eventChainRead(company, eventChains.getObjectAtIndex(0).id);
                assertNotNull(eventChain);
            }

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainReadUsingRefId() {
        try {
            BBCollection<EventChain> eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventChains);
            assertTrue(eventChains.size() <= 5);

            // TODO: 12.07.2016  Read the eventChain by ref id

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainNewSchema() {
        try {
            // TODO: 12.07.2016 test new event chain git stat
            BBCollection<EventChain> eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params());
            assertNotNull(eventChains);

            SchemaForm editSchema = defaultAPI.admin().eventChain().getNewEventChainSchema(company);
            assertNotNull(editSchema);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainEditSchema() {
        try {
            BBCollection<EventChain> eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventChains);
            assertTrue(eventChains.size() <= 5);

            if(eventChains.size() > 0) {
                SchemaForm editSchema = defaultAPI.admin().eventChain().getEditEventChainSchema(company, eventChains.getObjectAtIndex(0).id);
                assertNotNull(editSchema);
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainUpdate() {
        try {
            BBCollection<EventChain> eventChains = defaultAPI.admin().eventChain().eventChainList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventChains);

            EventChainParams.Update params = new EventChainParams.Update()
                    .setName("Test")
                    .setDescription("Test Description");

            if(eventChains.size() > 0) {
                EventChain eventChain = defaultAPI.admin().eventChain().eventChainUpdate(eventChains.getObjectAtIndex(0), params);
                assertNotNull(eventChain);
                assertEquals(eventChain.getName(), params.getName());
                assertEquals(eventChain.getDescription(), params.getDescription());
                //TODO: expand here asserts
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventChainCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            EventChainParams.Create params = new EventChainParams.Create()
                    .setAddressId(240123)
                    .setName("First name")
                    .setDatetime("2014-04-14");

            EventChain eventChain = mockAPI.admin().eventChain().eventChainCreate(getCompany(), params);
            assertNotNull(eventChain);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void getEventsForEventChain() {
        String authToken = "TOoqPWGtDPa37YkJY8a9Iw";
        String eventChainLink = "/admin/37048/event_chains/104";

        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withAuthToken(authToken);

        AdminAPI.EventChainAPI api = new API(config).admin().eventChain();

        try {
            EventChain eventChain = new EventChain(
                    api.httpService().api_GET(new URL(defaultAPI.configService().serverUrl + eventChainLink)));

            assertNotNull(eventChain);

            BBCollection<Event> events = api.getEventsForEventChain(eventChain, new Params());
            assertNotNull(events);
            assertTrue(events.size() > 0);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }


    }

}
