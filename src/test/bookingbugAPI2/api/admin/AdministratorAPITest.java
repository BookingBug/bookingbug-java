package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.AdministratorParams;
import bookingbugAPI2.models.params.Params;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//TODO: Fix administrator missing info (use a predefined company and admin list
@Ignore
public class AdministratorAPITest extends AbstractAPITest {

    private Administrator administrator;
    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/admin.json");
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
        administrator = getAdministrator();
    }

    @Test
    public void administratorList() {
        try {
            BBCollection<Administrator> administrators;

            //All administrators
            administrators = defaultAPI.admin().administrator().administratorList(company, null);
            assertNotNull(administrators);

            //Paginated administrators
            administrators = defaultAPI.admin().administrator().administratorList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(administrators);
            assertEquals(administrators.size(), 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void administratorRead() {
        try {
            BBCollection<Administrator> administrators = defaultAPI.admin().administrator().administratorList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(administrators);
            assertEquals(administrators.size(), 5);

            //Read the first administrator by id
            Administrator administrator = defaultAPI.admin().administrator().administratorRead(company, administrators.getObjectAtIndex(0).id);
            assertNotNull(administrator);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: Fix 405 Not allowed
    @Ignore
    @Test
    public void administratorUpdate() {
        try {
            BBCollection<Administrator> administrators = defaultAPI.admin().administrator().administratorList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(administrators);

            AdministratorParams.Update params = new AdministratorParams.Update()
                    .setName("Test")
                    .setPersonId(12345);

            Administrator administrator = defaultAPI.admin().administrator().administratorUpdate(company, administrators.getObjectAtIndex(0).id, params);
            assertNotNull(administrator);
            assertEquals(administrator.getName(), params.getName());
            assertEquals(administrator.getPersonId(), params.getPersonId());

            //TODO: expand here asserts

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void administratorCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            AdministratorParams.Create params = new AdministratorParams.Create()
                    .setPersonId(16246)
                    .setRole("user");

            Administrator administrator = mockAPI.admin().administrator().administratorCreate(getCompany(), params);
            assertNotNull(administrator);
            assertEquals(administrator.getRole(), params.getRole());
            assertEquals(administrator.getPersonId(), params.getPersonId());
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void administratorDelete() {
        try {
            MockWebServer server = mockServer(dispatcher);

            BBCollection<Administrator> administrators = defaultAPI.admin().administrator().administratorList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(administrators);

            // TODO: Test delete administrator
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void administratorNewSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().administrator().getNewAdministratorSchema(company);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: schema is null
    @Ignore
    @Test
    public void administratorEditSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().administrator().getEditAdministratorSchema(administrator);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
