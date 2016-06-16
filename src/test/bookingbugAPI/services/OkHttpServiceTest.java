package bookingbugAPI.services;

import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.HttpException;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import helpers.HttpServiceResponse;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;

/**
 * Created by sebi on 25.05.2016.
 */
public class OkHttpServiceTest {

    String appId = "edb1ef31";
    String appKey = "7cda5a59e91113e7a0f1b3654dadca86";
    String auth_token = "x2_5PcI15mq7sEWm70JazA";


    final Dispatcher dispatcher = new Dispatcher() {

        @Override
        public MockResponse dispatch(RecordedRequest request) throws InterruptedException {

            //Check appId and appKey headers
            if (!request.getHeader("App-Id").equals(appId) || !request.getHeader("App-Key").equals(appKey))
                return new MockResponse().setResponseCode(401);

            //Check auth token on protected path
            if(request.getPath().equals("/admin/") &&  !auth_token.equals(request.getHeader("Auth-Token")))
                return new MockResponse().setResponseCode(401);

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

    private AbstractAPI.ApiConfig getConfig() {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig();

        config.withApp(appId, appKey).withCache(CacheService.MOCK());

        return config;
    }

    private HttpException tryGet(OkHttpService httpService, URL url) {
        HttpException ex = null;
        try {
            httpService.api_GET(url);
        } catch (HttpException e) {
            ex = e;
        }
        return ex;
    }

    /**
     * Tests that HttpService correctly sets-up headers from config
     * @throws IOException
     */
    @Test
    public void headerTest() throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withNothing().withCache(CacheService.MOCK());
        OkHttpService httpService = new OkHttpService(config);

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        HttpException ex;
        URL rootURL = server.url("").url();
        URL adminURL = server.url("/admin/").url();

        //Test appId and appKey missing
        ex = tryGet(httpService, rootURL);
        assertNotNull("HttpService should raise exception when headers are missing", ex);
        assertEquals("Status code should be 401 when headers are missing", ex.getStatusCode(), 401);

        //Test basic headers (appId and appKey included)
        config.withApp(appId, appKey);
        ex = tryGet(httpService, rootURL);
        assertNull("No exception should be raised when headers are included", ex);

        //Test admin path without auth_token
        ex = tryGet(httpService, adminURL);
        assertNotNull("HttpService should raise exception when auth_token is missing on admin", ex);
        assertEquals("Status code should be 401 when auth_token is missing on admin path", ex.getStatusCode(), 401);

        //Test admin path with auth_token
        config.withAuthToken(auth_token);
        ex = tryGet(httpService, adminURL);
        assertNull("No exception should be raised when auth_token is included on admin path", ex);

        server.shutdown();
    }

    /**
     * Test POST api
     * @throws IOException
     */
    @Test
    public void testPOST() throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withCache(CacheService.MOCK());
        OkHttpService httpService = new OkHttpService(config);

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        HttpException ex = null;
        HttpServiceResponse response = null;
        URL postURL = server.url("/post/").url();

        Map params = new HashMap();
        params.put("param1", "val1");

        try {
            //With specific encoding
            response = httpService.api_POST(postURL, PlainHttpService.urlEncodedContentType, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"201\"}");

            //With default encoding
            response = httpService.api_POST(postURL, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"201\"}");
        } catch (HttpException e) {
            ex = e;
        }
        assertNull(ex);
        server.shutdown();
    }

    /**
     * Test PUT api
     * @throws IOException
     */
    @Test
    public void testPUT() throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withCache(CacheService.MOCK());
        OkHttpService httpService = new OkHttpService(config);

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        HttpException ex = null;
        HttpServiceResponse response = null;
        URL putURL = server.url("/put/").url();

        Map params = new HashMap();
        params.put("param1", "val1");

        try {
            //With specific encoding
            response = httpService.api_PUT(putURL, PlainHttpService.urlEncodedContentType, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"updated\"}");

            //With default encoding
            response = httpService.api_PUT(putURL, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"updated\"}");
        } catch (HttpException e) {
            ex = e;
        }
        assertNull(ex);
        server.shutdown();
    }

    /**
     * Test DELETE api
     * @throws IOException
     */
    @Test
    public void testDELETE() throws IOException {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withCache(CacheService.MOCK());
        OkHttpService httpService = new OkHttpService(config);

        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        HttpException ex = null;
        HttpServiceResponse response = null;
        URL deleteURL = server.url("/delete/").url();

        Map params = new HashMap();
        params.put("param1", "val1");

        try {
            //Without params
            response = httpService.api_DELETE(deleteURL);
            assertEquals("Received status should be 200", response.getRep().getContent(), "{\"status\":\"deleted\"}");

            //With specific encoding
            response = httpService.api_DELETE(deleteURL, PlainHttpService.urlEncodedContentType, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"deleted\"}");

            //With default encoding
            response = httpService.api_DELETE(deleteURL, params);
            assertEquals("Received status should be 201", response.getRep().getContent(), "{\"status\":\"deleted\"}");
        } catch (HttpException e) {
            ex = e;
        }
        assertNull(ex);
        server.shutdown();
    }

}
