package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.Administrator;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.HttpException;
import bookingbugAPI.models.Resource;
import bookingbugAPI.services.Cache.MockCacheService;
import bookingbugAPI.services.Cache.SQLiteCacheService;
import bookingbugAPI.services.Http.OkHttpService;
import bookingbugAPI.services.ServiceProvider;
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

        public MockHttpService(ServiceProvider provider) {
            super(provider);
        }

        @Override
        protected HttpServiceResponse callApi(URL url, String method, String contentType, Map params) throws HttpException {
            String strUrl = url.toString();
            strUrl = strUrl.replaceFirst("^(?:https?:\\/\\/)?(?:[^@\\n]+@)?(?:www\\.)?([^:\\/\\n]+)", provider.configService().serverUrl);
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
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig()
                .withCacheService(new MockCacheService())
                .withAuthToken(token);
        defaultAPI = new API(config);
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

        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig()
                .withCacheService(new MockCacheService())
                .withAuthToken(token)
                .withServerUrl(serverUrl);
        config.withHttpService(new MockHttpService(config));
        mockAPI = new API(config);

        return server;
    }

    public Company getCompany() {
        Company company = null;
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withAuthToken(token);
        config.withCacheService(new SQLiteCacheService(config));
        API api = new API(config);
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
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withAuthToken(token);
        config.withCacheService(new SQLiteCacheService(config));
        return getResource(new API(config));
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

    public Administrator getAdministrator() {
        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withAuthToken(token);
        config.withCacheService(new SQLiteCacheService(config));
        return getAdministrator(new API(config));
    }

    public Administrator getAdministrator(API api) {
        Administrator administrator = null;
        try {
            administrator = api.admin().administrator().administratorRead(getCompany(), resourceId);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertNotNull(administrator);
        return administrator;
    }
}
