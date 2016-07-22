package bookingbugAPI.api.admin;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.Params;
import bookingbugAPI.models.params.SlotListParams;
import bookingbugAPI.models.params.SlotParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;

//TODO: Find company with slots
@Ignore
public class SlotAPITest extends AbstractAPITest {

    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/slot.json");
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
    public void slotList() {
        try {
            BBCollection<Slot> slots;

            //All slots
            slots = defaultAPI.admin().slot().slotList(company, new SlotListParams());
            assertNotNull(slots);

            //Paginated slots
            slots = defaultAPI.admin().slot().slotList(company, new SlotListParams());
            assertNotNull(slots);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void slotRead() {
        try {
            //All slots
            BBCollection<Slot> slots = defaultAPI.admin().slot().slotList(company, new SlotListParams());
            assertNotNull(slots);

            if(slots.size() > 0) {
                Slot slot = defaultAPI.admin().slot().slotRead(company, slots.getObjectAtIndex(0).id);
                assertNotNull(slot);
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;


        }
    }

    @Test
    public void slotCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            SlotParams.Create params = new SlotParams.Create()
                    .setStartTime("19/07/2016")
                    .setEndTime("20/07/2016");

            Slot slot = mockAPI.admin().slot().slotCreate(getCompany(), params);
            assertNotNull(slot);

            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Ignore
    @Test
    public void slotUpdate() {
        try {
            JsonNode slotJson = ModelTest.getJSON("json/slot.json");
            Slot slot = new Slot(new HttpServiceResponse(Utils.stringToContentRep(slotJson.toString())));
            MockWebServer server = mockServer(dispatcher);
            SlotParams.Update params = new SlotParams.Update()
                    .setStartTime("19/07/2016")
                    .setEndTime("20/07/2016");

            Slot updatedService = mockAPI.admin().slot().slotUpdate(slot, params);
            assertNotNull(updatedService);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void slotDelete() {
        try {
            MockWebServer server = mockServer(dispatcher);

            BBCollection<Slot> slots = defaultAPI.admin().slot().slotList(company, new SlotListParams());
            assertNotNull(slots);
            // TODO: 19.07.2016 Test slotDelete
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
