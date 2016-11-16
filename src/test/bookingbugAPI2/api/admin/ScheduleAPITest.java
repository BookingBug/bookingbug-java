package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.Params;
import bookingbugAPI2.models.params.ScheduleParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class ScheduleAPITest extends AbstractAPITest {


    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/schedule.json");
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
    public void scheduleList() {
        try {
            BBCollection<Schedule> schedules;

            //All schedules
            schedules = defaultAPI.admin().schedule().scheduleList(company, null);
            assertNotNull(schedules);

            //Paginated schedules
            schedules = defaultAPI.admin().schedule().scheduleList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(schedules);
            assertEquals(schedules.size(), 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleRead() {
        try {
            BBCollection<Schedule> schedules = defaultAPI.admin().schedule().scheduleList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(schedules);
            assertEquals(schedules.size(), 5);

            //Read the first schedule by id
            Schedule schedule = defaultAPI.admin().schedule().scheduleRead(company, schedules.getObjectAtIndex(0).id);
            assertNotNull(schedule);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleNewSchema() {
        try {
            // TODO: 12.07.2016 test new event chain git stat
            BBCollection<Schedule> schedules = defaultAPI.admin().schedule().scheduleList(company, new Params());
            assertNotNull(schedules);

            SchemaForm editSchema = defaultAPI.admin().schedule().getNewScheduleSchema(company);
            assertNotNull(editSchema);

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleEditSchema() {
        try {
            BBCollection<Schedule> schedules = defaultAPI.admin().schedule().scheduleList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(schedules);
            assertEquals(schedules.size(), 5);

           /* SchemaForm editSchema = defaultAPI.admin().schedule().getEditScheduleSchema(company, schedules.getObjectAtIndex(0).id);
            assertNotNull(editSchema);*/

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleUpdate() {
        try {
            BBCollection<Schedule> schedules = defaultAPI.admin().schedule().scheduleList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(schedules);

            ScheduleParams.Update params = new ScheduleParams.Update()
                    .setName("Test")
                    .setDesc("Test Description");

            /*Schedule schedule = defaultAPI.admin().schedule().scheduleUpdate(company, schedules.getObjectAtIndex(0), params);
            assertNotNull(schedule);
            assertEquals(schedule.getName(), params.getName());
            assertEquals(schedule.getDesc(), params.getDesc());*/

            //TODO: expand here asserts

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ScheduleParams.Create params = new ScheduleParams.Create()
                    .setName("First name")
                    .setDesc("Description");

            Schedule schedule = mockAPI.admin().schedule().scheduleCreate(getCompany(), params);
            assertNotNull(schedule);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void scheduleDelete() {
        try {
            MockWebServer server = mockServer(dispatcher);

            BBCollection<Schedule> schedules = defaultAPI.admin().schedule().scheduleList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(schedules);

            // TODO: 13.07.2016 test delete schedule
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
