package bookingbugAPI.api.admin;

import bookingbugAPI.models.BBCollection;
import bookingbugAPI.models.Company;
import bookingbugAPI.models.SchemaForm;
import bookingbugAPI.models.Service;
import bookingbugAPI.models.params.ServiceListParams;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by sebi on 10.06.2016.
 */
public class ServiceAPITest extends AbstractAPITest {

    private Company company;

    @Override
    @Before
    public void setUp() {
        super.setUp();
        company = getCompany();
    }

    @Test
    public void serviceList() {
        try {
            BBCollection<Service> services;

            //All services
            services = defaultAPI.admin().service().serviceList(company, null);
            assertNotNull(services);

            //Paginated services
            services = defaultAPI.admin().service().serviceList(company, new ServiceListParams().setPage(1).setPerPage(5));
            assertNotNull(services);
            assertEquals(services.size(), 5);

        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceRead() {
        try {
            //All services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            Service service = defaultAPI.admin().service().serviceRead(company, services.getObjectAtIndex(0).id);
            assertNotNull(service);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;


         }
    }

    @Test
    public void serviceNewSchema() {
        try {
            //Paginated services
            SchemaForm schemaForm = defaultAPI.admin().service().getNewServiceSchema(company);
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceEditSchema() {
        try {
            //Paginated services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            SchemaForm schemaForm = defaultAPI.admin().service().getEditServiceSchema(services.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

    @Test
    public void serviceNewBookingSchema() {
        try {
            //Paginated services
            BBCollection<Service> services = defaultAPI.admin().service().serviceList(company, null);
            SchemaForm schemaForm = defaultAPI.admin().service().getNewBookingSchema(services.getObjectAtIndex(0));
            assertNotNull(schemaForm);
        }catch (Exception e) {
            e.printStackTrace();
            assert false : e;
        }
    }

}
