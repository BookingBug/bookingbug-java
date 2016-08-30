package bookingbugAPI2.api.admin;

import bookingbugAPI2.api.API;
import bookingbugAPI2.api.AbstractAPI;
import bookingbugAPI2.api.AdminAPI;
import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.CompanyParams;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
            CompanySettings companySettings = defaultAPI.admin().company().getSettingsForCompany(company);
            assertNotNull(companySettings);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCompanyForAdministrator() {
        //TODO implement this
    }

    @Test
    public void getEventsForCompany() {
        String authToken = "TOoqPWGtDPa37YkJY8a9Iw";
        String companyURL = "/admin/37048/company";

        AbstractAPI.ApiConfig config = new AbstractAPI.ApiConfig().withAuthToken(authToken);
        AdminAPI.CompanyAPI api = new API(config).admin().company();

        try {
            Company company = new Company(api.httpService().api_GET(new URL(api.configService().serverUrl + companyURL)));

            BBCollection<Event> events = api.getEventsForCompany(company, new CompanyParams.EventList());
            assertNotNull(events);
            assertTrue(events.size() > 0);
        }
        catch (IOException e) {
            e.printStackTrace();
            assert false : e;
        }

    }
}
