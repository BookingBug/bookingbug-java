package bookingbugAPI.api.admin;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.ServiceListParams;
import bookingbugAPI.models.params.ServiceParams;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 10.06.2016.
 */
public class ServiceAPITest extends AbstractAPITest {

    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if( (Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/service.json");
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
    public void serviceList() {
        try {
            BBCollection<Service> services;

            //All services
            services = defaultAPI.admin().service().serviceList(company, null);
            assertNotNull(services);

            //Paginated services
            services = defaultAPI.admin().service().serviceList(company, new ServiceListParams().setPage(1).setPerPage(5));
            assertNotNull(services);
            assertTrue(services.size() <= 5);

        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceRead() {
        try {
            //All services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            Service service = defaultAPI.admin().service().serviceRead(company, services.getObjectAtIndex(0).id);
            assertNotNull(service);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;


         }
    }

    @Test
    public void serviceCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            ServiceParams.ServiceCreateParams params = new ServiceParams.ServiceCreateParams()
                    .setName("Test service")
                    .setReference("ref")
                    .setSpaces(15)
                    .setDuration(45);

            Service service = mockAPI.admin().service().serviceCreate(getCompany(), params);
            assertNotNull(service);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceUpdate() {
        try {
            JsonNode serviceJson = ModelTest.getJSON("json/service.json");
            Service service = new Service(new HttpServiceResponse(Utils.stringToContentRep(serviceJson.toString())));
            MockWebServer server = mockServer(dispatcher);
            ServiceParams.ServiceUpdateParams params = new ServiceParams.ServiceUpdateParams()
                    .setName("Test service")
                    .setReference("ref")
                    .setDuration(45);

            Service updatedService = mockAPI.admin().service().serviceUpdate(service, params);
            assertNotNull(updatedService);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceNewSchema() {
        try {
            //Paginated services
            SchemaForm schemaForm = defaultAPI.admin().service().getNewServiceSchema(company);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceEditSchema() {
        try {
            //Paginated services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            SchemaForm schemaForm = defaultAPI.admin().service().getEditServiceSchema(services.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    //TODO: Remove ignore when 401 Forbidden is solved
    @Test
    @Ignore
    public void serviceNewBookingSchema() {
        try {
            //Paginated services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            SchemaForm schemaForm = defaultAPI.admin().service().getNewBookingSchema(services.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

}
