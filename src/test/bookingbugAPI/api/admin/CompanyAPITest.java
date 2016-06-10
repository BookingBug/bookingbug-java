package bookingbugAPI.api.admin;

import bookingbugAPI.api.API;
import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.Currency;
import bookingbugAPI.models.SchemaForm;
import bookingbugAPI.services.CacheService;
import bookingbugAPI.services.HttpService;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 09.06.2016.
 */
public class CompanyAPITest {

    private static final String companyId = "37025";
    private static final String token = "x2_5PcI15mq7sEWm70JazA";

    @Test
    public void companySettings(){
        Company company = null;
        try {
            URL url = new URL(AdminURLS.Company.companyRead().set("companyId", companyId).expand());
            company = new Company(HttpService.api_GET(url, token), token);
            assertNotNull(company);
            assertNotNull(company.getSettings());
            assertEquals(company.getSettings().getCurrency(), Currency.GBP);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void companyBookingSchema(){
        Company company = null;
        SchemaForm schemaForm = null;
        try {
            URL url = new URL(AdminURLS.Company.companyRead().set("companyId", companyId).expand());
            company = new Company(HttpService.api_GET(url, token), token);
            assertNotNull(company);

            schemaForm = company.getNewBookingSchema();
            assertNotNull(schemaForm);
            schemaForm.getSchemaJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCompany() {
        Company company = null;
        API.APIBuilder builder = new API.APIBuilder().withCache(CacheService.MOCK()).withAuthToken(token);
        API api = builder.build();

        try {
            company = api.admin().company().companyRead(companyId);
        }catch (Exception e) {
            e.printStackTrace();
        }
        assertNotNull(company);
    }
}
