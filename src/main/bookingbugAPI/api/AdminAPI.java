package bookingbugAPI.api;

import bookingbugAPI.models.*;
import bookingbugAPI.models.params.*;
import bookingbugAPI.services.AbstractHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.Http;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;


public class AdminAPI extends AbstractAPI {

    AdminAPI(AbstractHttpService httpService, ApiConfig builder) {
        super(httpService, builder);
    }

    /**
     * Accessor to create an instance of {@link BookingAPI} with current configuration
     * @return BookingAPI instance
     */
    public BookingAPI booking() {
        return new BookingAPI(httpService, newConfig());
    }

    public class BookingAPI extends AbstractAPI {

        BookingAPI(AbstractHttpService httpService, ApiConfig config) {
            super(httpService, config);
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
            return new SchemaForm(httpService.api_GET(url));
        }
    }


    /**
     * Accessor to create an instance of {@link CompanyAPI} with current configuration
     * @return CompanyAPI instance
     */
    public CompanyAPI company() {
        return new CompanyAPI(httpService, newConfig());
    }

    public class CompanyAPI extends AbstractAPI {

        public CompanyAPI(AbstractHttpService httpService, ApiConfig config) {
            super(httpService, config);
        }


        /**
         * Load All of the Links and Properties of a Company
         * @param companyId the id of company
         * @return Company
         * @throws IOException
         */
        public Company companyRead(String companyId) throws IOException {
            URL url = new URL(AdminURLS.Company.companyRead(config().serverUrl).set("companyId", companyId).expand());
            return new Company(httpService.api_GET(url));
        }

    }


    /**
     * Accessor to create an instance of {@link ServiceAPI} with current configuration
     * @return ServiceAPI instance
     */
    public ServiceAPI service() {
        return new ServiceAPI(httpService, newConfig());
    }

    public class ServiceAPI extends AbstractAPI {

        public ServiceAPI(AbstractHttpService httpService, ApiConfig config) {
            super(httpService, config);
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
            return new SchemaForm(httpService.api_GET(url));
        }

        /**
         * Create a service
         * @param company the company to own the service
         * @param sCParams Contains parameters for service creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Service
         * @throws IOException
         */
        public Service serviceCreate(Company company, ServiceParams.ServiceCreateParams sCParams) throws IOException {
            URL url = new URL (company.getServicesLink());
            return new Service(httpService.api_POST(url, sCParams.getParams()));
        }

        /**
         * Update a service
         * @param service the service to update
         * @param sUParams Contains parameters for service update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Service
         * @throws IOException
         */
        public Service serviceUpdate(Service service, ServiceParams.ServiceUpdateParams sUParams) throws IOException {
            URL url = new URL (service.getEditLink());
            return new Service(httpService.api_POST(url, sUParams.getParams()));
        }

        /**
         * Get a schema for creating a new booking with provided service
         * @param service The service
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewBookingSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.getNewBookingLink()).expand());
            return new SchemaForm(httpService.api_GET(url));
        }

        /**
         * Get a schema for editing a service
         * @param service The service to be edited
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditServiceSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.getEditLink()).expand());
            return new SchemaForm(httpService.api_GET(url));
        }
    }


    /**
     * Accessor to create an instance of {@link ServiceAPI} with current configuration
     * @return ServiceAPI instance
     */
    public ClientAPI client() {
        return new ClientAPI(httpService, newConfig());
    }

    public class ClientAPI extends AbstractAPI {

        public ClientAPI(AbstractHttpService httpService, ApiConfig config) {
            super(httpService, config);
        }


        /**
         * List of Clients for a company. Results are returned as a paginated list
         * @param company The owning company for clients
         * @param clParams Parameters for this call
         * @return Collection of Client
         * @throws IOException
         */
        public BBCollection<Client> clientList(Company company, Params clParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getClientLink(), clParams);
            URL url = new URL(template.expand());

            BBCollection<Client> clients = new BBCollection<Client>(httpService.api_GET(url), httpService.getConfig().auth_token,  "clients", Client.class);
            return clients;
        }

        /**
         * Load a specific client details
         * @param company  The owning company for client
         * @param clientId The client's id
         * @return Client
         * @throws IOException
         */
        public Client clientRead(Company company, String clientId) throws IOException {
            URL url = new URL(AdminURLS.Client.clientRead()
                    .set("companyId", company.id)
                    .set("serviceId", clientId)
                    .expand());
            return new Client(httpService.api_GET(url));
        }

        /**
         * Load a specific client details
         * @param company  The owning company for client
         * @param email The client's email
         * @return Client
         * @throws IOException
         */
        public Client clientReadByEmail(Company company, String email) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClientByEmailLink()).set("email", email).expand());
            return new Client(httpService.api_GET(url));
        }

        /**
         * Get the schema for editing a client
         * @param client The client to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditClientSchema(Client client) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(client.getEditLink()).expand());
            return new SchemaForm(httpService.api_GET(url));
        }

        /**
         * Enable/Disable specific client
         * @param company The company for the client
         * @param ctParams parameters for this call
         * @return Client TODO: check return type after 401 is solved
         * @throws IOException
         */
        public Client clientEnableDisable(Company company, ClientToggleParams ctParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClientLink()).expand());
            return new Client(httpService.api_PUT(url, Http.urlEncodedContentType, ctParams.getParams()));
        }

        /**
         * Update a client
         * @param client the client to update
         * @param cuParams Contains parameters for client update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Client
         * @throws IOException
         */
        public Client clientUpdate(Client client, ClientParams.Update cuParams) throws IOException {
            URL url = new URL (client.getSelf());
            return new Client(httpService.api_PUT(url, cuParams.getParams()));
        }

        /**
         * Create a client
         * @param company the company for client
         * @param clParams Contains parameters for client creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Client
         * @throws IOException
         */
        public Client clientCreate(Company company, ClientParams.Create clParams) throws IOException {
            URL url = new URL (UriTemplate.fromTemplate(company.getClientLink()).expand());
            return new Client(httpService.api_POST(url, clParams.getParams()));
        }

    }


    /**
     * Accessor to create an instance of {@link ResourceAPI} with current configuration
     * @return ResourceAPI instance
     */
    public ResourceAPI resource() {
        return new ResourceAPI(httpService, newConfig());
    }

    public class ResourceAPI extends AbstractAPI {

        public ResourceAPI(AbstractHttpService httpService, ApiConfig config) {
            super(httpService, config);
        }

        /**
         * Load specific resource details
         * @param company
         * @param resourceId
         * @return Resource
         * @throws IOException
         */
        public Resource resourceRead(Company company, String resourceId) throws IOException{
            URL url = new URL(AdminURLS.Resource.resourceRead()
                    .set("companyId", company.id)
                    .set("resourceId", resourceId)
                    .expand());
            return new Resource(httpService.api_GET(url));
        }

        /**
         * List of Resources for a company. Results are returned as a paginated list
         * @param company The owning company for services
         * @param rlParams Parameters for this call
         * @return Collection of Service
         * @throws IOException
         */
        public BBCollection<Resource> resourceList(Company company, Params rlParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getResourcesLink(), rlParams);
            URL url = new URL(template.expand());

            BBCollection<Resource> resources = new BBCollection<Resource>(httpService.api_GET(url), httpService.getConfig().auth_token,  "resources", Resource.class);
            return resources;
        }

        /**
         * Create a new resource
         * @param company the company for resource
         * @param rcParams Contains parameters for resource creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Resource
         * @throws IOException
         */
        public Resource resourceCreate(Company company, ResourceParams.Create rcParams) throws IOException {
            URL url = new URL (UriTemplate.fromTemplate(company.getResourcesLink()).expand());
            return new Resource(httpService.api_POST(url, rcParams.getParams()));
        }

        /**
         * Update a resource
         * @param resource the resource to update
         * @param ruParams Contains parameters for resource update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Resource
         * @throws IOException
         */
        public Resource resourceUpdate(Resource resource, ResourceParams.Update ruParams) throws IOException {
            URL url = new URL (resource.getSelf());
            return new Resource(httpService.api_PUT(url, ruParams.getParams()));
        }

        /**
         * Get the schema for creating a new resource
         * @param company The company to own the resource
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewResourceSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewResourceLink()).expand());
            return new SchemaForm(httpService.api_GET(url));
        }

        /**
         * Get the schema for editing a resource
         * @param resource The resource to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditResourceSchema(Resource resource) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(resource.getEditLink()).expand());
            return new SchemaForm(httpService.api_GET(url));
        }

        //TODO: Add block and schedule calls
    }
}
