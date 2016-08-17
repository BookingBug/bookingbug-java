package bookingbugAPI2.services;

import bookingbugAPI2.api.AbstractAPI;
import bookingbugAPI2.services.Cache.MockCacheService;
import bookingbugAPI2.services.Http.OkHttpService;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import helpers2.HttpServiceResponse;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by sebi on 17.08.2016.
 */
public class SQLiteCacheServiceTest {

    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check post data
            if(request.getPath().equals("/post/") && Objects.equals(request.getMethod(), "POST") && request.getBodySize() != 0) {
                return new MockResponse().setResponseCode(201).setBody("{\"status\":\"201\"}");
            }

            //Check put data
            if(request.getPath().equals("/put/") && Objects.equals(request.getMethod(), "PUT") && request.getBodySize() != 0) {
                return new MockResponse().setResponseCode(201).setBody("{\"status\":\"updated\"}");
            }

            //Check delete data
            if(request.getPath().equals("/delete/") && Objects.equals(request.getMethod(), "DELETE")) {
                return new MockResponse().setResponseCode(201).setBody("{\"status\":\"deleted\"}");
            }

            return new MockResponse().setResponseCode(200).setBody("{}");
        }
    };

    @Test
    public void testExpiry() throws InterruptedException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig();
        config.cacheService().setExpiryDurationSec(1);
        String dummyURL = "dummy_url";
        String dummyResp = "{\"dummy\":\"dummy\"}";
        String dummyTag = "MY_TAG";

        config.cacheService().invalidateResultsByTag(dummyTag);
        config.cacheService().storeResult(dummyURL, "GET", dummyResp, dummyTag);

        Thread.sleep(500);
        assertNotNull(config.cacheService().getDBResponse(dummyURL, "GET"));

        Thread.sleep(600);
        assertNull(config.cacheService().getDBResponse(dummyURL, "GET"));
    }

    @Test
    public void testCacheGET() throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig();

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        URL dummyGET = server.url("/get/").url();
        String dummyTag = "MY_TAG";

        config.cacheService().invalidateResultsByTag(dummyTag);
        HttpServiceResponse response = config.httpService().api_GET(dummyGET, dummyTag);
        assertNotNull(response);
        assertNotNull(config.cacheService().getDBResponse(dummyGET.toString(), "GET"));
        server.shutdown();
    }

    private void testInvalidationOnVerb(String method) throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig();

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        URL dummyGET1 = server.url("/get/").url();
        URL dummyGET2 = server.url("/get2/").url();
        URL dummyPOST = server.url("/post/").url();
        String dummyTag1 = "MY_TAG1";
        String dummyTag2 = "MY_TAG2";
        HttpServiceResponse response = null;

        config.cacheService().invalidateResultsByTag(dummyTag1);
        config.cacheService().invalidateResultsByTag(dummyTag2);

        //Trigger get to cache
        response = config.httpService().api_GET(dummyGET1, dummyTag1);
        response = config.httpService().api_GET(dummyGET2, dummyTag2);
        assertNotNull(response);
        assertNotNull(config.cacheService().getDBResponse(dummyGET1.toString(), "GET"));

        //Post with tag1 and check cache for tag1 and tag2
        switch (method) {
            case "POST":
                response = config.httpService().api_POST(dummyPOST, new HashMap(), dummyTag1);
                break;
            case "PUT":
                response = config.httpService().api_PUT(dummyPOST, new HashMap(), dummyTag1);
                break;
            case "DELETE":
                response = config.httpService().api_DELETE(dummyPOST, new HashMap(), dummyTag1);
                break;
            default: response = null;
        }

        assertNotNull(response);
        assertNull(config.cacheService().getDBResponse(dummyGET1.toString(), "GET"));
        assertNotNull(config.cacheService().getDBResponse(dummyGET2.toString(), "GET"));
        server.shutdown();
    }

    @Test
    public void testInvalidationOnPOST() throws IOException {
        testInvalidationOnVerb("POST");
    }

    @Test
    public void testInvalidationOnPUT() throws IOException {
        testInvalidationOnVerb("PUT");
    }

    @Test
    public void testInvalidationOnDELETE() throws IOException {
        testInvalidationOnVerb("DELETE");
    }
}
