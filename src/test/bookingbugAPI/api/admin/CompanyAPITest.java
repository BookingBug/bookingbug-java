package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.Currency;
import bookingbugAPI.models.SchemaForm;
import bookingbugAPI.services.CacheService;
import bookingbugAPI.services.HttpService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 09.06.2016.
 */
public class CompanyAPITest extends AbstractAPITest{

    private Company company;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    @Test
    public void companyRead() {
        assertNotNull(company);
    }

    @Test
    public void companySettings(){
        try {
            assertNotNull(company.getSettings());
            assertEquals(company.getSettings().getCurrency(), Currency.GBP);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
