package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.Params;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class EventGroupAPITest extends AbstractAPITest {

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
    public void eventGroupList() {
        try {
            BBCollection<EventGroup> eventGroups;

            //All eventGroups
            eventGroups = defaultAPI.admin().eventGroup().eventGroupList(company, null);
            assertNotNull(eventGroups);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventGroupRead() {
        try {
            BBCollection<EventGroup> eventGroups = defaultAPI.admin().eventGroup().eventGroupList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventGroups);
            assertTrue(eventGroups.size() <= 5);

            if(eventGroups.size() > 0) {
                //Read the first eventGroup by id
                EventGroup eventGroup = defaultAPI.admin().eventGroup().eventGroupRead(company, eventGroups.getObjectAtIndex(0).id);
                assertNotNull(eventGroup);
            }

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventGroupNewSchema() {
        try {
            // TODO: 12.07.2016 test new event chain git stat
            BBCollection<EventGroup> eventGroups = defaultAPI.admin().eventGroup().eventGroupList(company, new Params());
            assertNotNull(eventGroups);

            SchemaForm editSchema = defaultAPI.admin().eventGroup().getNewEventGroupSchema(company);
            assertNotNull(editSchema);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void eventGroupEditSchema() {
        try {
            BBCollection<EventGroup> eventGroups = defaultAPI.admin().eventGroup().eventGroupList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(eventGroups);
            assertTrue(eventGroups.size() <= 5);

            if (eventGroups.size() > 0) {
                SchemaForm editSchema = defaultAPI.admin().eventGroup().getEditEventGroupSchema(company, eventGroups.getObjectAtIndex(0).id);
                assertNotNull(editSchema);
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
