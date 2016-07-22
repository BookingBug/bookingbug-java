package bookingbugAPI.api.admin;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.AddressParams;
import bookingbugAPI.models.params.Params;
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
import static org.junit.Assert.assertTrue;


public class AddressAPITest extends AbstractAPITest {


    private Company company;

    //Dispatcher for create/update
    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post/put data
            if ((Objects.equals(request.getMethod(), "POST") || Objects.equals(request.getMethod(), "PUT")) && request.getBodySize() != 0) {
                JsonNode resp = ModelTest.getJSON("json/address.json");
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
    public void addressList() {
        try {
            BBCollection<Address> addresses;

            //All addresses
            addresses = defaultAPI.admin().address().addressList(company, new Params());
            assertNotNull(addresses);

            //Paginated addresses
            addresses = defaultAPI.admin().address().addressList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(addresses);
            assertTrue(addresses.size() <= 5);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void addressRead() {
        try {
            BBCollection<Address> addresses = defaultAPI.admin().address().addressList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(addresses);
            assertTrue(addresses.size() <= 5);

            //Read the first address by id
            Address address = defaultAPI.admin().address().addressRead(company, addresses.getObjectAtIndex(0).id);
            assertNotNull(address);

        } catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    // TODO: Fix Method not allowed
    @Ignore
    @Test
    public void addressUpdate() {
        try {
            BBCollection<Address> addresses = defaultAPI.admin().address().addressList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(addresses);

            AddressParams.Update params = new AddressParams.Update()
                    .setAddress1("Test1")
                    .setPostcode("12345");

            Address address = defaultAPI.admin().address().addressUpdate(company, params);
            assertNotNull(address);
            assertEquals(address.getAddress1(), params.getAddress1());
            assertEquals(address.getPostcode(), params.getPostcode());

            //TODO: expand here asserts

        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void addressCreate() {
        try {
            MockWebServer server = mockServer(dispatcher);
            AddressParams.Create params = new AddressParams.Create()
                    .setAddress1("Address1")
                    .setAddress2("Address2");

            Address address = mockAPI.admin().address().addressCreate(getCompany(), params);
            assertNotNull(address);
            server.shutdown();
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void addressDelete() {
        try {
            MockWebServer server = mockServer(dispatcher);

            BBCollection<Address> addresses = defaultAPI.admin().address().addressList(company, new Params().setPage(1).setPerPage(5));
            assertNotNull(addresses);

            // TODO: 13.07.2016 test delete address
        } catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }
    }
}
