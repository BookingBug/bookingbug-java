package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.Params;
import bookingbugAPI2.models.params.ResourceParams;
import com.fasterxml.jackson.databind.JsonNode;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class ResourceAPITest extends AbstractAPITest {

    private Company company;
    private Resource resource;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if( (Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/resource.json");
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
        resource = getResource();
    }

    @Test
    public void resourceList() {
        try {
            BBCollection<Resource> resources;

            //All services
            resources = defaultAPI.admin().resource().resourceList(company, null);
            assertNotNull(resources);

            //Paginated services
            resources = defaultAPI.admin().resource().resourceList(company, new Params<>().setPage(1).setPerPage(5));
            assertNotNull(resources);
            assertTrue(resources.size() <= 5);

        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void resourceRead() {
        try {
            Resource resource = defaultAPI.admin().resource().resourceRead(company, resourceId);
            assertNotNull(resource);

        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void resourceCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ResourceParams.Create params = new ResourceParams.Create()
                    .setName("test resource")
                    .setDescription("adfasd asd");
            Resource resource = mockAPI.admin().resource().resourceCreate(company, params);

            assertNotNull(resource);
            server.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void resourceUpdate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ResourceParams.Update params = new ResourceParams.Update()
                    .setName("update resource")
                    .setDescription("dsfg asd");
            Resource updatedResource = mockAPI.admin().resource().resourceUpdate(resource, params);
            assertNotNull(updatedResource);
            server.shutdown();
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void resourceNewSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().resource().getNewResourceSchema(company);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void resourceEditSchema() {
        try {
            SchemaForm schemaForm = defaultAPI.admin().resource().getEditResourceSchema(resource);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
