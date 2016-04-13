package bookingbugAPI.api;

import bookingbugAPI.models.*;
import bookingbugAPI.services.HttpService;
import org.junit.Ignore;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by sebi on 31.03.2016.
 */

@Ignore
public class CompanyTest {

    private static final String  companyId = "37028";
    private static final String token = "7gcmPMDS-G2gpNiPSUQA4A";

    @Test
    public void companySettings(){
        Company company = null;
        try {
            URL url = new URL(AdminURLS.Company.company().set("companyId", companyId).expand());
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
            URL url = new URL(AdminURLS.Company.company().set("companyId", companyId).expand());
            company = new Company(HttpService.api_GET(url, token), token);
            assertNotNull(company);

            schemaForm = company.getNewBookingSchema();
            assertNotNull(schemaForm);
            schemaForm.getSchemaJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
