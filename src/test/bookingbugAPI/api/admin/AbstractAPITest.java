package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.HttpException;
import bookingbugAPI.models.Resource;
import bookingbugAPI.services.AbstractHttpService;
import bookingbugAPI.services.CacheService;
import bookingbugAPI.services.OkHttpService;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import helpers.HttpServiceResponse;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 10.06.2016.
 */
public abstract class AbstractAPITest {

    protected static final String companyId = "36990";
    protected static final String resourceId = "5";
    protected static final String token = "ro13e9jaWi3kvA4fMToU1w";

    protected API defaultAPI;
    protected API mockAPI;

    /**
     * Class used to mock all http calls to a predefined server. The root path of original URL is replaced with a predefined one
     * (For calls which have the url from model links)
     */
    private class MockHttpService  extends OkHttpService {

        public MockHttpService(AbstractAPI.ApiConfig config) {
            super(config);
        }

        @Override
        protected HttpServiceResponse callApi(URL url, String method, String contentType, Map params) throws HttpException {
            String strUrl = url.toString();
            strUrl = strUrl.replaceFirst("^(?:https?:\\/\\/)?(?:[^@\\n]+@)?(?:www\\.)?([^:\\/\\n]+)", getConfig().serverUrl);
            try {
                return super.callApi(new URL(strUrl), method, contentType, params);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new HttpException("Cannot replace url with mock", e);
            }
        }
    }

    @Before
    public void setUp() {
        defaultAPI = new API.APIBuilder()
                .withCache(CacheService.MOCK())
                .withAuthToken(token)
                .build();
    }

    @After
    public void tearDown() {
        defaultAPI = null;
    }

    /**
     * Starts a {@link MockWebServer} and initializes an api with the server's url
     * The api uses a custom HttpService {@link MockHttpService} which replaces all
     * @param dispatcher The dispatcher to mock http calls
     * @return the server
     * @throws IOException
     */
    public MockWebServer mockServer(Dispatcher dispatcher) throws IOException {
        MockWebServer server = new MockWebServer();
        server.setDispatcher(dispatcher);
        server.start();

        //Server url must not end in '/'
        String serverUrl = server.url("").toString();
        if(serverUrl.endsWith("/"))
            serverUrl = serverUrl.substring(0, serverUrl.length()-1);

        API.APIBuilder builder = new API.APIBuilder()
                .withCache(CacheService.MOCK())
                .withAuthToken(token)
                .withServerUrl(serverUrl);
        builder.withHttpService(new MockHttpService(builder));
        mockAPI = builder.build();

        return server;
    }

    public Company getCompany() {
        Company company = null;
        API.APIBuilder builder = new API.APIBuilder().withCache(CacheService.JDBC()).withAuthToken(token);
        API api = builder.build();
        return getCompany(api);
    }

    public Company getCompany(API api) {
        Company company = null;
        try {
            company = api.admin().company().companyRead(companyId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(company);
        return company;
    }

    public Resource getResource() {
        return getResource(new API.APIBuilder().withCache(CacheService.JDBC()).withAuthToken(token).build());
    }

    public Resource getResource(API api) {
        Resource resource = null;
        try {
            resource = api.admin().resource().resourceRead(getCompany(), resourceId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(resource);
        return resource;
    }
}
