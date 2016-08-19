package bookingbugAPI2.api.admin;

import bookingbugAPI2.models.Company;
import org.junit.Before;
import org.junit.Test;

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
            //assertEquals(company.getSettings().getCurrency(), Currency.GBP);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCompanyForAdministrator() {
        //TODO implement this
    }
}
