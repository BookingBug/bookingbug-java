package bookingbugAPI.api.admin;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.ClinicParams;
import bookingbugAPI.models.params.Params;
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
import static org.junit.Assert.assertTrue;


public class ClinicAPITest extends AbstractAPITest {

    private Company company;
    private Clinic clinic;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/clinic.json");
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
        clinic = getClinic();
    }

    @Test
    public void clinicList() {
        try {
            BBCollection<Clinic> clinics;

            //All clinics
            clinics = defaultAPI.admin().clinic().clinicList(company, null);
            assertNotNull(clinics);

            //Paginated clinics
            clinics = defaultAPI.admin().clinic().clinicList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(clinics);
            assertTrue(clinics.size() <= 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void clinicUpdate() {
        try {
            BBCollection<Clinic> clinics = defaultAPI.admin().clinic().clinicList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(clinics);

            ClinicParams.Update params = new ClinicParams.Update()
                    .setName("Test Name")
                    .setStartTime(new DateTime("2016-07-15T08:27:23.598Z"));

            if(clinics.size() > 0) {
                Clinic clinic = defaultAPI.admin().clinic().clinicUpdate(clinics.getObjectAtIndex(0), params);
                assertNotNull(clinic);
                assertEquals(clinic.getName(), params.getName());
                assertEquals(clinic.getStartTime(), params.getStartTime());
            }

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void clinicCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ClinicParams.Create params = new ClinicParams.Create()
                    .setName("string")
                    .setStartTime(new DateTime("2016-07-11T12:30:32.596Z"));

            Clinic clinic = mockAPI.admin().clinic().clinicCreate(getCompany(), params);
            assertNotNull(clinic);
            assertEquals(clinic.getName(), params.getName());
            assertEquals(clinic.getStartTime(), params.getStartTime());
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void clinicCancel() {
        try {
            MockWebServer server = mockServer(dispatcher);
            Params params = new Params();

            Clinic clinic = mockAPI.admin().clinic().clinicCancel(getCompany(), clinicId, params);
            assertNotNull(clinic);
            // TODO: 15.07.2016 Test clinic cancel.
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}


