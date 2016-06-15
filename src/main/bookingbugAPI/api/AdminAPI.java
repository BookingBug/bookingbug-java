package bookingbugAPI.api;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.BookingListParams;
import bookingbugAPI.models.params.ServiceListParams;
import bookingbugAPI.services.HttpService;
import bookingbugAPI.services.OkHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;


public class AdminAPI extends AbstractAPI {

    AdminAPI(ApiConfig builder) {
        super(builder);
    }

    /**
     * Accessor to create an instance of {@link BookingAPI} with current configuration
     * @return BookingAPI instance
     */
    public BookingAPI booking() {
        return new BookingAPI(newConfig());
    }

    public class BookingAPI extends AbstractAPI {

        BookingAPI(ApiConfig config) {
            super(config);
        }

        /**
         * Get a list of admin bookings for a company
         * @param company The owning company for bookin
         * @param bLParams The parameters for this call
         * @return Collection of bookings
         * @throws IOException
         */
        public BBCollection<Booking> bookingList(Company company, BookingListParams bLParams) throws IOException {
            URL url = new URL(Utils.inflateLink(company.getBookingsLink(), bLParams.getParams()));
            BBCollection<Booking> bookings = new BBCollection<Booking>(httpService.api_GET(url), getAuthToken(), "bookings", Booking.class);
            //BBCollection<Booking> bookings = new BBCollection<Booking>(HttpService.api_GET(url, getAuthToken()), getAuthToken(), "bookings", Booking.class);
            return bookings;
        }

        /**
         * Get all details about a specific booking
         * @param company the company owning the booking
         * @param bookingId the id of booking to read
         * @return Booking
         * @throws IOException
         */
        public Booking bookingRead(Company company, String bookingId) throws IOException {
            URL url = new URL(AdminURLS.Bookings.bookingRead()
                    .set("companyId", company.id)
                    .set("bookingId", bookingId)
                    .expand());
            return new Booking(httpService.api_GET(url));
        }

        /**
         * Get the edit schema for booking
         * @param booking
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditBookingSchema(Booking booking) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(booking.getEditLink()).expand());
            return new SchemaForm(HttpService.api_GET(url, httpService.getConfig().auth_token));
        }
    }


    /**
     * Accessor to create an instance of {@link CompanyAPI} with current configuration
     * @return CompanyAPI instance
     */
    public CompanyAPI company() {
        return new CompanyAPI(newConfig());
    }

    public class CompanyAPI extends AbstractAPI {

        public CompanyAPI(ApiConfig config) {
            super(config);
        }


        /**
         * Load All of the Links and Properties of a Company
         * @param companyId the id of company
         * @return Company
         * @throws IOException
         */
        public Company companyRead(String companyId) throws IOException {
            URL url = new URL(AdminURLS.Company.companyRead().set("companyId", companyId).expand());
            return new Company(httpService.api_GET(url));
        }

    }


    /**
     * Accessor to create an instance of {@link ServiceAPI} with current configuration
     * @return ServiceAPI instance
     */
    public ServiceAPI service() {
        return new ServiceAPI(newConfig());
    }

    public class ServiceAPI extends AbstractAPI {

        public ServiceAPI(ApiConfig config) {
            super(config);
        }

        /**
         * List of Services for a company. Results are returned as a paginated list
         * @param company The owning company for services
         * @param slParams Parameters for this call
         * @return Collection of Service
         * @throws IOException
         */
        public BBCollection<Service> serviceList(Company company, ServiceListParams slParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getServicesLink(), slParams);
            URL url = new URL(template.expand());

            BBCollection<Service> services = new BBCollection<Service>(httpService.api_GET(url), httpService.getConfig().auth_token,  "services", Service.class);
            return services;
        }

        /**
         * Load a Specific Service Details
         * @param company The owning company for service
         * @param serviceId the id of service to load
         * @return Service
         * @throws IOException
         */
        public Service serviceRead(Company company, String serviceId) throws IOException{
            URL url = new URL(AdminURLS.Service.serviceRead()
                    .set("companyId", company.id)
                    .set("serviceId", serviceId)
                    .expand());
            return new Service(httpService.api_GET(url));
        }

        /**
         * Get schema for creating a new service
         * @param company The owning company
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewServiceSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewServiceLink()).expand());
            return new SchemaForm(HttpService.api_GET(url, httpService.getConfig().auth_token));
        }

        /**
         * Get a schema for creating a new booking with provided service
         * @param service The service
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewBookingSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.get_newBookingLik()).expand());
            return new SchemaForm(HttpService.api_GET(url, httpService.getConfig().auth_token));
        }

        /**
         * Get a schema for editing a service
         * @param service The service to be edited
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditServiceSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.get_editServiceLik()).expand());
            return new SchemaForm(HttpService.api_GET(url, httpService.getConfig().auth_token));
        }
    }

}
