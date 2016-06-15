package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.models.Company;
import bookingbugAPI.services.CacheService;
import org.junit.After;
import org.junit.Before;

import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 10.06.2016.
 */
public abstract class AbstractAPITest {

    protected static final String companyId = "37025";
    protected static final String token = "x2_5PcI15mq7sEWm70JazA";

    protected API defaultAPI;

    @Before
    public void setUp() {
        defaultAPI = new API.APIBuilder()
                .withCache(CacheService.MOCK())
                .withAuthToken(token)
                .build();
    };

    @After
    public void tearDown() {
        defaultAPI = null;
    };

    public Company getCompany() {
        Company company = null;
        API.APIBuilder builder = new API.APIBuilder().withCache(CacheService.JDBC()).withAuthToken(token);
        API api = builder.build();

        try {
            company = api.admin().company().companyRead(companyId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(company);
        return company;
    }
}
