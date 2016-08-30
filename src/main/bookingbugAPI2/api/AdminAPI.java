package bookingbugAPI2.api;

import bookingbugAPI2.models.*;
import bookingbugAPI2.models.params.*;
import bookingbugAPI2.services.ServiceProvider;
import bookingbugAPI2.services.http.AbstractHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers2.Http;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
import rx.Observable;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class AdminAPI extends AbstractAPI {

    AdminURLS urls;

    public AdminAPI(ServiceProvider provider) {
        super(provider);
        urls = new AdminURLS(provider);
    }


    /**
     * Accessor to create an instance of {@link LoginAPI} with current configuration
     *
     * @return LoginAPI instance
     */
    public LoginAPI login() {
        return new LoginAPI(provider);
    }

    public class LoginAPI extends AbstractAPI<LoginAPI> {

        public LoginAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * Authenticate with email and password
         *
         * @param email    the user email
         * @param password the user password
         * @return Login instance
         * @throws IOException
         */
        public Login auth(String email, String password) throws IOException {
            URL url = new URL(urls.login().auth().expand());
            Map params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
            HttpServiceResponse resp;
            try {
                resp = httpService().api_POST(url, params);
            } catch (HttpException e) {
                if (e.getStatusCode() == 400) {
                    resp = new HttpServiceResponse(Utils.stringToContentRep(e.getRawResponse()), url.toString(), "POST", Http.urlEncodedContentType, params, provider.configService().auth_token);
                } else throw e;
            }

            return new Login(resp);
        }

        public Observable<Login> authObs(final String email, final String password) {
            return Observable.fromCallable(() -> auth(email, password));
        }

        /**
         * Authenticate the company administrator with provided credentials
         *
         * @param administrator The administrator to login with
         * @param email         the administrator email
         * @param password      the administrator password
         * @return Login instance
         * @throws IOException
         */
        public Login authWithCompanyAdministrator(Administrator administrator, String email, String password) throws IOException {
            URL url = new URL(administrator.getLoginLink());
            Map params = new HashMap<>();
            params.put("email", email);
            params.put("password", password);
            return new Login(httpService().api_POST(url, params));
        }

        public Observable<Login> authWithCompanyAdministratorObs(final Administrator administrator, final String email, final String password) {
            return Observable.fromCallable(() -> authWithCompanyAdministrator(administrator, email, password));
        }
    }


    /**
     * Accessor to create an instance of {@link BookingAPI} with current configuration
     *
     * @return BookingAPI instance
     */
    public BookingAPI booking() {
        return new BookingAPI(newProvider());
    }

    public class BookingAPI extends AbstractAPI<BookingAPI> {

        public BookingAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * Get a list of admin bookings for a company
         *
         * @param company  The owning company for booking
         * @param bLParams The parameters for this call
         * @return Collection of bookings
         * @throws IOException
         */
        public BBCollection<Booking> bookingList(Company company, BookingListParams bLParams) throws IOException {
            URL url = new URL(Utils.inflateLink(company.getBookingsLink(), bLParams.getParams()));
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), getAuthToken(), "bookings", Booking.class);
        }

        public Observable<BBCollection<Booking>> bookingListObs(final Company company, final BookingListParams bLParams) {
            return Observable.fromCallable(() -> bookingList(company, bLParams));
        }

        /**
         * Get all details about a specific booking
         *
         * @param company   the company owning the booking
         * @param bookingId the id of booking to read
         * @return Booking
         * @throws IOException
         */
        public Booking bookingRead(Company company, String bookingId) throws IOException {
            URL url = new URL(AdminURLS.Bookings.bookingRead()
                    .set("companyId", company.id)
                    .set("bookingId", bookingId)
                    .expand());
            return new Booking(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Booking> bookingReadObs(final Company company, final String bookingId) {
            return Observable.fromCallable(() -> bookingRead(company, bookingId));
        }

        /**
         * Get the edit schema for booking
         *
         * @param booking
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditBookingSchema(Booking booking) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(booking.getEditLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditBookingSchemaObs(final Booking booking) {
            return Observable.fromCallable(() -> getEditBookingSchema(booking));
        }

        /**
         * Create a booking for a company with provided parameters
         *
         * @param company  The company for booking
         * @param bCParams The parameters to create the booking with
         * @return Booking
         * @throws IOException
         */
        public Booking bookingCreate(Company company, BookingParams.Create bCParams) throws IOException {
            String urlStr = AdminURLS.Bookings.bookingCreate().set("companyId", company.id).expand();
            URL url = new URL(urlStr);

            return new Booking(httpService().api_POST(url, bCParams.getParams()));
        }

        public Observable<Booking> bookingCreateObs(final Company company, final BookingParams.Create bCParams) {
            return Observable.fromCallable(() -> bookingCreate(company, bCParams));
        }

        /**
         * Cancel a booking
         *
         * @param booking the booking to cancel
         * @param params  parameters for this call
         * @return Booking instance
         * @throws IOException
         */
        public Booking cancelBooking(Booking booking, BookingParams.Cancel params) throws IOException {
            URL url = new URL(booking.getSelf());
            return new Booking(httpService().api_DELETE(url, Http.jsonContentType, params.getParams(), CACHE_TAG));
        }

        public Observable<Booking> cancelBookingObs(final Booking booking, final BookingParams.Cancel params) {
            return Observable.fromCallable(() -> cancelBooking(booking, params));
        }
    }


    /**
     * Accessor to create an instance of {@link CompanyAPI} with current configuration
     *
     * @return CompanyAPI instance
     */
    public CompanyAPI company() {
        return new CompanyAPI(newProvider());
    }

    public class CompanyAPI extends AbstractAPI<CompanyAPI> {


        public CompanyAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * Load All of the Links and Properties of a Company
         *
         * @param companyId the id of company
         * @return Company
         * @throws IOException
         */
        public Company companyRead(String companyId) throws IOException {
            URL url = new URL(AdminURLS.Company.companyRead(configService().serverUrl).set("companyId", companyId).expand());
            return new Company(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Company> companyReadObs(final String companyId) {
            return Observable.fromCallable(() -> companyRead(companyId));
        }

        /**
         * Get the company for specified administrator
         *
         * @param administrator the administrator for the company
         * @return Company
         * @throws IOException
         */
        public Company getCompanyForAdministrator(Administrator administrator) throws IOException {
            URL url = new URL(administrator.getCompanyLink());
            return new Company(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Company> getCompanyForAdministratorObs(final Administrator administrator) {
            return Observable.fromCallable(() -> getCompanyForAdministrator(administrator));
        }

        /**
         * Return the settings for provided company
         *
         * @param company the company to retrieve settings for
         * @return CompanySettings instance
         * @throws IOException
         */
        public CompanySettings getSettingsForCompany(Company company) throws IOException {
            if (company.getResource("settings") != null)
                return new CompanySettings(new HttpServiceResponse(company.getResource("settings")));
            URL url = new URL(company.getSettingsLink());
            return new CompanySettings(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<CompanySettings> getSettingsForCompanyObs(final Company company) {
            return Observable.fromCallable(() -> getSettingsForCompany(company));
        }

        /**
         * Get all the events for a company with provided params. Returns as paginated list
         *
         * @param company The company owning the events
         * @param params  The params to filter the events
         * @return Collection of Event
         * @throws IOException
         */
        public BBCollection<Event> getEventsForCompany(Company company, CompanyParams.EventList params) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getEventsLink()).set(params.getParams()).expand());
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "events", Event.class);
        }

        public Observable<BBCollection<Event>> getEventsForCompanyObs(final Company company, final CompanyParams.EventList params) {
            return Observable.fromCallable(() -> getEventsForCompany(company, params));
        }
    }


    /**
     * Accessor to create an instance of {@link ServiceAPI} with current configuration
     *
     * @return ServiceAPI instance
     */
    public ServiceAPI service() {
        return new ServiceAPI(newProvider());
    }

    public class ServiceAPI extends AbstractAPI<ServiceAPI> {


        public ServiceAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * List of Services for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for services
         * @param slParams Parameters for this call
         * @return Collection of Service
         * @throws IOException
         */
        public BBCollection<Service> serviceList(Company company, ServiceListParams slParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getServicesLink(), slParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "services", Service.class);
        }

        public Observable<BBCollection<Service>> serviceListObs(final Company company, final ServiceListParams slParams) {
            return Observable.fromCallable(() -> serviceList(company, slParams));
        }

        /**
         * Load a Specific Service Details
         *
         * @param company   The owning company for service
         * @param serviceId the id of service to load
         * @return Service
         * @throws IOException
         */
        public Service serviceRead(Company company, String serviceId) throws IOException {
            URL url = new URL(AdminURLS.Service.serviceRead()
                    .set("companyId", company.id)
                    .set("serviceId", serviceId)
                    .expand());
            return new Service(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Service> serviceReadObs(final Company company, final String serviceId) {
            return Observable.fromCallable(() -> serviceRead(company, serviceId));
        }

        /**
         * Get schema for creating a new service
         *
         * @param company The owning company
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewServiceSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewServiceLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewServiceSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewServiceSchema(company));
        }

        /**
         * Create a service
         *
         * @param company  the company to own the service
         * @param sCParams Contains parameters for service creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Service
         * @throws IOException
         */
        public Service serviceCreate(Company company, ServiceParams.ServiceCreateParams sCParams) throws IOException {
            URL url = new URL(company.getServicesLink());
            return new Service(httpService().api_POST(url, sCParams.getParams(), CACHE_TAG));
        }

        public Observable<Service> serviceCreateObs(final Company company, final ServiceParams.ServiceCreateParams sCParams) {
            return Observable.fromCallable(() -> serviceCreate(company, sCParams));
        }

        /**
         * Update a service
         *
         * @param service  the service to update
         * @param sUParams Contains parameters for service update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Service
         * @throws IOException
         */
        public Service serviceUpdate(Service service, ServiceParams.ServiceUpdateParams sUParams) throws IOException {
            URL url = new URL(service.getEditLink());
            return new Service(httpService().api_POST(url, sUParams.getParams(), CACHE_TAG));
        }

        public Observable<Service> serviceUpdateObs(final Service service, final ServiceParams.ServiceUpdateParams sUParams) {
            return Observable.fromCallable(() -> serviceUpdate(service, sUParams));
        }

        /**
         * Get a schema for creating a new booking with provided service
         *
         * @param service The service
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewBookingSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.getNewBookingLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewBookingSchemaObs(final Service service) {
            return Observable.fromCallable(() -> getNewBookingSchema(service));
        }

        /**
         * Get a schema for editing a service
         *
         * @param service The service to be edited
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditServiceSchema(Service service) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(service.getEditLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditServiceSchemaObs(final Service service) {
            return Observable.fromCallable(() -> getEditServiceSchema(service));
        }
    }


    /**
     * Accessor to create an instance of {@link ServiceAPI} with current configuration
     *
     * @return ServiceAPI instance
     */
    public ClientAPI client() {
        return new ClientAPI(newProvider());
    }

    public class ClientAPI extends AbstractAPI<ClientAPI> {


        public ClientAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * List of Clients for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for clients
         * @param clParams Parameters for this call
         * @return Collection of Client
         * @throws IOException
         */
        public BBCollection<Client> clientList(Company company, Params clParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getClientLink(), clParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "clients", Client.class);
        }

        public Observable<BBCollection<Client>> clientListObs(final Company company, final Params clParams) {
            return Observable.fromCallable(() -> clientList(company, clParams));
        }

        /**
         * Load a specific client details
         *
         * @param company  The owning company for client
         * @param clientId The client's id
         * @return Client
         * @throws IOException
         */
        public Client clientRead(Company company, String clientId) throws IOException {
            URL url = new URL(urls.client().clientRead()
                    .set("companyId", company.id)
                    .set("clientId", clientId)
                    .expand());
            return new Client(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Client> clientReadObs(final Company company, final String clientId) {
            return Observable.fromCallable(() -> clientRead(company, clientId));
        }

        /**
         * Load a specific client details
         *
         * @param company The owning company for client
         * @param email   The client's email
         * @return Client
         * @throws IOException
         */
        public Client clientReadByEmail(Company company, String email) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClientByEmailLink()).set("email", email).expand());
            return new Client(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Client> clientReadByEmailObs(final Company company, final String email) {
            return Observable.fromCallable(() -> clientReadByEmail(company, email));
        }

        /**
         * Get the schema for editing a client
         *
         * @param client The client to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditClientSchema(Client client) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(client.getEditLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditClientSchemaObs(final Client client) {
            return Observable.fromCallable(() -> getEditClientSchema(client));
        }

        /**
         * Enable/Disable specific client
         *
         * @param company  The company for the client
         * @param ctParams parameters for this call
         * @return Client TODO: check return type after 401 is solved
         * @throws IOException
         */
        public Client clientEnableDisable(Company company, ClientToggleParams ctParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClientLink()).expand());
            return new Client(httpService().api_PUT(url, Http.urlEncodedContentType, ctParams.getParams(), CACHE_TAG));
        }

        public Observable<Client> clientEnableDisableObs(final Company company, final ClientToggleParams ctParams) {
            return Observable.fromCallable(() -> clientEnableDisable(company, ctParams));
        }

        /**
         * Update a client
         *
         * @param client   the client to update
         * @param cuParams Contains parameters for client update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Client
         * @throws IOException
         */
        public Client clientUpdate(Client client, ClientParams.Update cuParams) throws IOException {
            URL url = new URL(client.getSelf());
            return new Client(httpService().api_PUT(url, cuParams.getParams(), CACHE_TAG));
        }

        public Observable<Client> clientUpdateObs(final Client client, final ClientParams.Update cuParams) {
            return Observable.fromCallable(() -> clientUpdate(client, cuParams));
        }

        /**
         * Create a client
         *
         * @param company  the company for client
         * @param clParams Contains parameters for client creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Client
         * @throws IOException
         */
        public Client clientCreate(Company company, ClientParams.Create clParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClientLink()).expand());
            return new Client(httpService().api_POST(url, clParams.getParams(), CACHE_TAG));
        }

        public Observable<Client> clientCreateObs(final Company company, final ClientParams.Create clParams) {
            return Observable.fromCallable(() -> clientCreate(company, clParams));
        }

    }


    /**
     * Accessor to create an instance of {@link ResourceAPI} with current configuration
     *
     * @return ResourceAPI instance
     */
    public ResourceAPI resource() {
        return new ResourceAPI(newProvider());
    }

    public class ResourceAPI extends AbstractAPI<ResourceAPI> {

        public ResourceAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Load specific resource details
         *
         * @param company
         * @param resourceId
         * @return Resource
         * @throws IOException
         */
        public Resource resourceRead(Company company, String resourceId) throws IOException {
            URL url = new URL(AdminURLS.Resource.resourceRead()
                    .set("companyId", company.id)
                    .set("resourceId", resourceId)
                    .expand());
            return new Resource(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Resource> resourceReadObs(final Company company, final String resourceId) {
            return Observable.fromCallable(() -> resourceRead(company, resourceId));
        }

        /**
         * List of Resources for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for services
         * @param rlParams Parameters for this call
         * @return Collection of Resource
         * @throws IOException
         */
        public BBCollection<Resource> resourceList(Company company, Params rlParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getResourcesLink(), rlParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "resources", Resource.class);
        }

        public Observable<BBCollection<Resource>> resourceListObs(final Company company, final Params rlParams) {
            return Observable.fromCallable(() -> resourceList(company, rlParams));
        }

        /**
         * Create a new resource
         *
         * @param company  the company for resource
         * @param rcParams Contains parameters for resource creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Resource
         * @throws IOException
         */
        public Resource resourceCreate(Company company, ResourceParams.Create rcParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getResourcesLink()).expand());
            return new Resource(httpService().api_POST(url, rcParams.getParams(), CACHE_TAG));
        }

        public Observable<Resource> resourceCreateObs(final Company company, final ResourceParams.Create rcParams) {
            return Observable.fromCallable(() -> resourceCreate(company, rcParams));
        }

        /**
         * Update a resource
         *
         * @param resource the resource to update
         * @param ruParams Contains parameters for resource update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Resource
         * @throws IOException
         */
        public Resource resourceUpdate(Resource resource, ResourceParams.Update ruParams) throws IOException {
            URL url = new URL(resource.getSelf());
            return new Resource(httpService().api_PUT(url, ruParams.getParams(), CACHE_TAG));
        }

        public Observable<Resource> resourceUpdateObs(final Resource resource, final ResourceParams.Update ruParams) {
            return Observable.fromCallable(() -> resourceUpdate(resource, ruParams));
        }

        /**
         * Get the schema for creating a new resource
         *
         * @param company The company to own the resource
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewResourceSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewResourceLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewResourceSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewResourceSchema(company));
        }

        /**
         * Get the schema for editing a resource
         *
         * @param resource The resource to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditResourceSchema(Resource resource) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(resource.getEditLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditResourceSchemaObs(final Resource resource) {
            return Observable.fromCallable(() -> getEditResourceSchema(resource));
        }

        //TODO: Add block and schedule calls
    }


    /**
     * Accessor to create an instance of {@link EventChainAPI} with current configuration
     *
     * @return EventChainAPI instance
     */
    public EventChainAPI eventChain() {
        return new EventChainAPI(newProvider());
    }

    public class EventChainAPI extends AbstractAPI<EventChainAPI> {
        public EventChainAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Load specific event chain details
         *
         * @param company
         * @param eventChainId
         * @return EventChain
         * @throws IOException
         */
        public EventChain eventChainRead(Company company, String eventChainId) throws IOException {
            URL url = new URL(AdminURLS.EventChain.eventChainRead()
                    .set("companyId", company.id)
                    .set("eventChainId", eventChainId)
                    .expand());
            return new EventChain(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<EventChain> eventChainReadObs(final Company company, final String eventChainId) {
            return Observable.fromCallable(() -> eventChainRead(company, eventChainId));
        }

        /**
         * Load specific event chain details by reference
         *
         * @param company
         * @param refId   the reference to the event chain to read
         * @return EventChain
         * @throws IOException
         */
        public EventChain eventChainReadByRefId(Company company, String refId) throws IOException {
            URL url = new URL(AdminURLS.EventChain.eventChainReadUsingRefId()
                    .set("companyId", company.id)
                    .set("refId", refId)
                    .expand());
            return new EventChain(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<EventChain> eventChainReadByRefIdObs(final Company company, final String refId) {
            return Observable.fromCallable(() -> eventChainReadByRefId(company, refId));
        }

        /**
         * List of event chains for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for services
         * @param rlParams Parameters for this call
         * @return Collection of EventChain
         * @throws IOException
         */
        public BBCollection<EventChain> eventChainList(Company company, Params rlParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getEventChainsLink(), rlParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "event_chains", EventChain.class);
        }

        public Observable<BBCollection<EventChain>> eventChainListObs(final Company company, final Params rlParams) {
            return Observable.fromCallable(() -> eventChainList(company, rlParams));
        }

        /**
         * Create a new event chain
         *
         * @param company   the company for event chain
         * @param eccParams Contains parameters for event chain creation. If the schema is used, then set the json form output
         *                  to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                  in order to ignore declared fields
         * @return EventChain
         * @throws IOException
         */
        public EventChain eventChainCreate(Company company, EventChainParams.Create eccParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getEventChainsLink()).expand());
            return new EventChain(httpService().api_POST(url, eccParams.getParams(), CACHE_TAG));
        }

        public Observable<EventChain> eventChainCreateObs(final Company company, final EventChainParams.Create rcParams) {
            return Observable.fromCallable(() -> eventChainCreate(company, rcParams));
        }

        /**
         * Update an event chain
         *
         * @param eventChain the event chain to update
         * @param ecuParams  Contains parameters for event chain update. If the schema is used, then set the json form output
         *                   to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                   in order to ignore declared fields
         * @return EventChain
         * @throws IOException
         */

        public EventChain eventChainUpdate(EventChain eventChain, EventChainParams.Update ecuParams) throws IOException {
            URL url = new URL(eventChain.getSelf());
            return new EventChain(httpService().api_PUT(url, ecuParams.getParams(), CACHE_TAG));
        }

        public Observable<EventChain> eventChainUpdateObs(final EventChain eventChain, final EventChainParams.Update ruParams) {
            return Observable.fromCallable(() -> eventChainUpdate(eventChain, ruParams));
        }


        /**
         * Get a schema for editing a eventChain
         *
         * @param company
         * @param eventChainId the event chain to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditEventChainSchema(Company company, String eventChainId) throws IOException {
            URL url = new URL(AdminURLS.EventChain.eventChainEdit()
                    .set("companyId", company.id)
                    .set("eventChainId", eventChainId)
                    .expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditEventChainSchemaObs(final Company company, final String eventChainId) {
            return Observable.fromCallable(() -> getEditEventChainSchema(company, eventChainId));
        }

        /**
         * Get the schema for creating a new event chain
         *
         * @param company The company to own the event chain
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewEventChainSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getEventChainsLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewEventChainSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewEventChainSchema(company));
        }

        /**
         * Get the events for an eventChain
         *
         * @param eventChain The eventChain for events
         * @return Collection of Event
         * @throws IOException
         */
        public BBCollection<Event> getEventsForEventChain(EventChain eventChain) throws IOException {
            URL url = new URL(eventChain.getEventsLink());
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "events", Event.class);
        }

        public Observable<BBCollection<Event>> getEventsForEventChainObs(final EventChain eventChain) {
            return Observable.fromCallable(() -> getEventsForEventChain(eventChain));
        }
    }


    /**
     * Accessor to create an instance of {@link EventGroupAPI} with current configuration
     *
     * @return EventGroupAPI instance
     */
    public EventGroupAPI eventGroup() {
        return new EventGroupAPI(newProvider());
    }

    public class EventGroupAPI extends AbstractAPI<EventGroupAPI> {
        public EventGroupAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Load specific event group details
         *
         * @param company
         * @param eventGroupId
         * @return EventGroup
         * @throws IOException
         */
        public EventGroup eventGroupRead(Company company, String eventGroupId) throws IOException {
            URL url = new URL(AdminURLS.EventGroup.eventGroupRead()
                    .set("companyId", company.id)
                    .set("eventGroupId", eventGroupId)
                    .expand());
            return new EventGroup(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<EventGroup> eventGroupReadObs(final Company company, final String eventGroupId) {
            return Observable.fromCallable(() -> eventGroupRead(company, eventGroupId));
        }

        /**
         * List of event groups for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for services
         * @param rlParams Parameters for this call
         * @return Collection of EventGroup
         * @throws IOException
         */
        public BBCollection<EventGroup> eventGroupList(Company company, Params rlParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getEventGroupsLink(), rlParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "eventGroups", EventGroup.class);
        }

        public Observable<BBCollection<EventGroup>> eventGroupListObs(final Company company, final Params rlParams) {
            return Observable.fromCallable(() -> eventGroupList(company, rlParams));
        }

        /**
         * Get a schema for editing a eventGroup
         *
         * @param company
         * @param eventGroupId the event group to edit
         * @return
         * @throws IOException
         */
        public SchemaForm getEditEventGroupSchema(Company company, String eventGroupId) throws IOException {
            URL url = new URL(AdminURLS.EventGroup.eventGroupEdit()
                    .set("companyId", company.id)
                    .set("eventGroupId", eventGroupId)
                    .expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditEventGroupSchemaObs(final Company company, final String eventGroupId) {
            return Observable.fromCallable(() -> getEditEventGroupSchema(company, eventGroupId));
        }

        /**
         * Get the schema for creating a new event group
         *
         * @param company The company to own the event group
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewEventGroupSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getEventGroupsLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewEventGroupSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewEventGroupSchema(company));
        }
    }


    /**
     * Accessor to create an instance of {@link EventAPI} with current configuration
     *
     * @return EventAPI instance
     */
    public EventAPI event() {
        return new EventAPI(newProvider());
    }

    public class EventAPI extends AbstractAPI<EventAPI> {

        public EventAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * Get a schema for creating a new booking with provided event
         *
         * @param event The event
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewBookingSchema(Event event) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(event.getNewBookingLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewBookingSchemaObs(final Event event) {
            return Observable.fromCallable(() -> getNewBookingSchema(event));
        }
    }

    /**
     * Accessor to create an instance of {@link ScheduleAPI} with current configuration
     *
     * @return ScheduleAPI instance
     */
    public ScheduleAPI schedule() {
        return new ScheduleAPI(newProvider());
    }

    public class ScheduleAPI extends AbstractAPI<ScheduleAPI> {

        public ScheduleAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Get a list of admin schedules for a company
         *
         * @param company  The owning company for schedule
         * @param sLParams The parameters for this call
         * @return Collection of Schedule
         * @throws IOException
         */
        public BBCollection<Schedule> scheduleList(Company company, Params sLParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getSchedulesLink(), sLParams);
            URL url = new URL(template.expand());
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), getAuthToken(), "schedules", Schedule.class);
        }

        public Observable<BBCollection<Schedule>> scheduleListObs(final Company company, final Params sLParams) {
            return Observable.fromCallable(() -> scheduleList(company, sLParams));
        }

        /**
         * Create a schedule
         *
         * @param company  the company to own the schedule
         * @param sCParams Contains parameters for schedule creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Service
         * @throws IOException
         */
        public Schedule scheduleCreate(Company company, ScheduleParams.Create sCParams) throws IOException {
            URL url = new URL(company.getSchedulesLink());
            return new Schedule(httpService().api_POST(url, sCParams.getParams(), CACHE_TAG));
        }

        public Observable<Schedule> scheduleCreateObs(final Company company, final ScheduleParams.Create sCParams) {
            return Observable.fromCallable(() -> scheduleCreate(company, sCParams));
        }

        /**
         * Get schema for creating a new schedule
         *
         * @param company The owning company
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewScheduleSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewScheduleLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewScheduleSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewScheduleSchema(company));
        }

        /**
         * Delete a schedule
         *
         * @param company    The owning company
         * @param scheduleId The id of schedule
         * @return Schedule
         * @throws IOException
         */
        public Schedule deleteSchedule(Company company, String scheduleId) throws IOException {
            URL url = new URL(AdminURLS.Schedule.scheduleDelete()
                    .set("companyId", company.id)
                    .set("scheduleId", scheduleId)
                    .expand());
            return new Schedule(httpService().api_DELETE(url, CACHE_TAG));
        }

        public Observable<Schedule> deleteScheduleObs(final Company company, final String scheduleID) {
            return Observable.fromCallable(() -> deleteSchedule(company, scheduleID));
        }

        /**
         * Get all details about a specific schedule
         *
         * @param company    the company owning the schedule
         * @param scheduleId the id of schedule to read
         * @return Schedule
         * @throws IOException
         */
        public Schedule scheduleRead(Company company, String scheduleId) throws IOException {
            URL url = new URL(AdminURLS.Schedule.scheduleRead()
                    .set("companyId", company.id)
                    .set("scheduleId", scheduleId)
                    .expand());
            return new Schedule(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Schedule> scheduleReadObs(final Company company, final String scheduleId) {
            return Observable.fromCallable(() -> scheduleRead(company, scheduleId));
        }

        /**
         * Update a schedule
         *
         * @param company    the company owning the schedule
         * @param scheduleId the schedule to update
         * @param sUParams   Contains parameters for schedule update. If the schema is used, then set the json form output
         *                   to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                   in order to ignore declared fields
         * @return Schedule
         * @throws IOException
         */
        public Schedule scheduleUpdate(Company company, String scheduleId, ScheduleParams.Update sUParams) throws IOException {
            URL url = new URL(AdminURLS.Schedule.scheduleUpdate()
                    .set("companyId", company.id)
                    .set("scheduleId", scheduleId)
                    .expand());

            return new Schedule(httpService().api_PUT(url, sUParams.getParams(), CACHE_TAG));
        }

        public Observable<Schedule> scheduleUpdateObs(final Company company, final String scheduleId, final ScheduleParams.Update sUParams) {
            return Observable.fromCallable(() -> scheduleUpdate(company, scheduleId, sUParams));
        }

        /**
         * Get the edit schema for schedule
         *
         * @param company    the company owning the schedule
         * @param scheduleId the id of schedule to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditScheduleSchema(Company company, String scheduleId) throws IOException {
            URL url = new URL(AdminURLS.Schedule.scheduleEdit()
                    .set("companyId", company.id)
                    .set("scheduleId", scheduleId)
                    .expand());

            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditScheduleSchemaObs(Company company, String scheduleId) {
            return Observable.fromCallable(() -> getEditScheduleSchema(company, scheduleId));
        }
    }


    /**
     * Accessor to create an instance of {@link AddressAPI} with current configuration
     *
     * @return AddressAPI instance
     */
    public AddressAPI address() {
        return new AddressAPI(newProvider());
    }

    public class AddressAPI extends AbstractAPI<AddressAPI> {

        public AddressAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Get a list of addresses for a company
         *
         * @param company  The owning company for address
         * @param aLParams The parameters for this call
         * @return Collection of Address
         * @throws IOException
         */
        public BBCollection<Address> addressList(Company company, Params aLParams) throws IOException {
            URL url = new URL(Utils.inflateLink(company.getAddressesLink(), aLParams.getParams()));

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), getAuthToken(), "addresses", Address.class);
        }

        public Observable<BBCollection<Address>> addressListObs(final Company company, final Params aLParams) {
            return Observable.fromCallable(() -> addressList(company, aLParams));
        }

        /**
         * Create an address
         *
         * @param company  the company to own the address
         * @param aCParams Contains parameters for address creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Address
         * @throws IOException
         */
        public Address addressCreate(Company company, AddressParams.Create aCParams) throws IOException {
            URL url = new URL(company.getAddressLink());
            return new Address(httpService().api_POST(url, aCParams.getParams(), CACHE_TAG));
        }

        public Observable<Address> addressCreateObs(final Company company, final AddressParams.Create sCParams) {
            return Observable.fromCallable(() -> addressCreate(company, sCParams));
        }

        /**
         * Delete an address
         *
         * @param company   the company to own the address
         * @param addressId the address id
         * @return SchemaForm
         * @throws IOException
         */
        public Address deleteAddress(Company company, String addressId) throws IOException {
            URL url = new URL(AdminURLS.Address.addressDelete()
                    .set("companyId", company.id)
                    .set("addressId", addressId)
                    .expand());
            return new Address(httpService().api_DELETE(url, CACHE_TAG));
        }

        public Observable<Address> deleteAddressObs(final Company company, final String addressID) {
            return Observable.fromCallable(() -> deleteAddress(company, addressID));
        }

        /**
         * Get all details about a specific address
         *
         * @param company   the company owning the address
         * @param addressId the id of address to read
         * @return Address
         * @throws IOException
         */
        public Address addressRead(Company company, String addressId) throws IOException {
            URL url = new URL(AdminURLS.Address.addressRead()
                    .set("companyId", company.id)
                    .set("addressId", addressId)
                    .expand());
            return new Address(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Address> addressReadObs(final Company company, final String addressId) {
            return Observable.fromCallable(() -> addressRead(company, addressId));
        }

        /**
         * Update an address
         *
         * @param company  the company owning the address
         * @param sUParams Contains parameters for address update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Address
         * @throws IOException
         */
        public Address addressUpdate(Company company, AddressParams.Update sUParams) throws IOException {
            URL url = new URL(company.getAddressLink());

            return new Address(httpService().api_PUT(url, sUParams.getParams(), CACHE_TAG));
        }

        public Observable<Address> addressUpdateObs(final Company company, final AddressParams.Update sUParams) {
            return Observable.fromCallable(() -> addressUpdate(company, sUParams));
        }
    }


    /**
     * Accessor to create an instance of {@link AdministratorAPI} with current configuration
     *
     * @return AdministratorAPI instance
     */
    public AdministratorAPI administrator() {
        return new AdministratorAPI(newProvider());
    }

    public class AdministratorAPI extends AbstractAPI<AdministratorAPI> {

        public AdministratorAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * Get the administrator for a specific login. It searches in embedded objects and if not found calls
         * the administrator link
         *
         * @param login The login
         * @return Administrator instance
         * @throws IOException
         */
        public Administrator getAdministratorForLogin(Login login) throws IOException {
            String adminLink = login.getAdministratorLink();
            BBCollection<Administrator> admins = login.getAdministrators();
            //search embedded object
            for (Administrator admin : admins) {
                if (admin.getSelf().equals(adminLink))
                    return admin;
            }
            URL url = new URL(adminLink);
            return new Administrator(httpService().api_GET(url, CACHE_TAG), getAuthToken());
        }

        public Observable<Administrator> getAdministratorForLoginObs(Login login) {
            return Observable.fromCallable(() -> getAdministratorForLogin(login));
        }

        /**
         * Get a list of administrators for a company
         *
         * @param company  The owning company for administrator
         * @param aLParams The parameters for this call
         * @return Collection of Administrator
         * @throws IOException
         */
        public BBCollection<Administrator> administratorList(Company company, Params aLParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getAdministratorsLink(), aLParams);
            URL url = new URL(template.expand());
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), getAuthToken(), "administrators", Administrator.class);
        }

        public Observable<BBCollection<Administrator>> administratorListObs(final Company company, final Params aLParams) {
            return Observable.fromCallable(() -> administratorList(company, aLParams));
        }

        /**
         * Create an administrator
         *
         * @param company  the company to own the administrator
         * @param aCParams Contains parameters for administrator creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Administrator
         * @throws IOException
         */
        public Administrator administratorCreate(Company company, AdministratorParams.Create aCParams) throws IOException {
            URL url = new URL(AdminURLS.Administrator.administratorCreate()
                    .set("companyId", company.id)
                    .expand());

            return new Administrator(httpService().api_POST(url, aCParams.getParams(), CACHE_TAG));
        }

        public Observable<Administrator> administratorCreateObs(final Company company, final AdministratorParams.Create aCParams) {
            return Observable.fromCallable(() -> administratorCreate(company, aCParams));
        }

        /**
         * Delete an administrator
         *
         * @param company         The owning company
         * @param administratorId the id of administrator to be deleted
         * @return SchemaForm
         * @throws IOException
         */
        public Administrator deleteAdministrator(Company company, String administratorId) throws IOException {
            URL url = new URL(AdminURLS.Administrator.administratorDelete()
                    .set("companyId", company.id)
                    .set("administratorId", administratorId)
                    .expand());
            return new Administrator(httpService().api_DELETE(url, CACHE_TAG));
        }

        public Observable<Administrator> deleteAdministratorObs(final Company company, final String administratorID) {
            return Observable.fromCallable(() -> deleteAdministrator(company, administratorID));
        }

        /**
         * Get all details about a specific administrator
         *
         * @param company         the company owning the administrator
         * @param administratorId the id of administrator to read
         * @return Administrator
         * @throws IOException
         */
        public Administrator administratorRead(Company company, String administratorId) throws IOException {
            URL url = new URL(AdminURLS.Administrator.administratorRead()
                    .set("companyId", company.id)
                    .set("administratorId", administratorId)
                    .expand());
            return new Administrator(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Administrator> administratorReadObs(final Company company, final String administratorId) {
            return Observable.fromCallable(() -> administratorRead(company, administratorId));
        }

        /**
         * Update an administrator
         *
         * @param company  the company owning the administrator
         * @param aUParams Contains parameters for administrator update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Administrator
         * @throws IOException
         */
        public Administrator administratorUpdate(Company company, String adminId, AdministratorParams.Update aUParams) throws IOException {
            URL url = new URL(AdminURLS.Administrator.administratorUpdate()
                    .set("companyId", company.id)
                    .set("adminId", adminId)
                    .expand());
            return new Administrator(httpService().api_PUT(url, aUParams.getParams(), CACHE_TAG));
        }

        public Observable<Administrator> administratorUpdateObs(final Company company, final String adminId, final AdministratorParams.Update aUParams) {
            return Observable.fromCallable(() -> administratorUpdate(company, adminId, aUParams));
        }

        /**
         * Get the edit schema for administrator
         *
         * @param administrator
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditAdministratorSchema(Administrator administrator) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(administrator.getEditLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditAdministratorSchemaObs(final Administrator administrator) {
            return Observable.fromCallable(() -> getEditAdministratorSchema(administrator));
        }

        /**
         * Get schema for creating a new administrator
         *
         * @param company The owning company
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewAdministratorSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewAdministratorLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewAdministratorSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewAdministratorSchema(company));
        }
    }


    /**
     * Accessor to create an instance of {@link PersonAPI} with current configuration
     *
     * @return PersonAPI instance
     */
    public PersonAPI person() {
        return new PersonAPI(newProvider());
    }

    public class PersonAPI extends AbstractAPI<PersonAPI> {
        public PersonAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Load a specific person details by reference
         *
         * @param company
         * @param personId
         * @return
         * @throws IOException
         */
        public Person personRead(Company company, String personId) throws IOException {
            URL url = new URL(AdminURLS.Person.personRead()
                    .set("companyId", company.id)
                    .set("personId", personId)
                    .expand());
            return new Person(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Person> personReadObs(final Company company, final String refId) {
            return Observable.fromCallable(() -> personRead(company, refId));
        }

        /**
         * Load specific person details by reference
         *
         * @param company
         * @param refId   the reference to the person to read
         * @return People
         * @throws IOException
         */
        public Person personReadByRefId(Company company, String refId) throws IOException {
            URL url = new URL(AdminURLS.Person.personReadUsingRefId()
                    .set("companyId", company.id)
                    .set("refId", refId)
                    .expand());
            return new Person(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Person> personReadByRefIdObs(final Company company, final String refId) {
            return Observable.fromCallable(() -> personReadByRefId(company, refId));
        }

        /**
         * List of persons for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for people
         * @param plParams Parameters for this call
         * @return Collection of People
         * @throws IOException
         */
        public BBCollection<Person> personList(Company company, Params plParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getPeopleLink(), plParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "people", Person.class);
        }

        public Observable<BBCollection<Person>> personListObs(final Company company, final Params plParams) {
            return Observable.fromCallable(() -> personList(company, plParams));
        }

        /**
         * Create a new person
         *
         * @param company  the company for person
         * @param pcParams Contains parameters for person creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return People
         * @throws IOException
         */
        public Person personCreate(Company company, PersonParams.Create pcParams) throws IOException {
            URL url = new URL(AdminURLS.Person.personCreate()
                    .set("companyId", company.id)
                    .expand());
            return new Person(httpService().api_POST(url, pcParams.getParams(), CACHE_TAG));
        }

        public Observable<Person> personCreateObs(final Company company, final PersonParams.Create rcParams) {
            return Observable.fromCallable(() -> personCreate(company, rcParams));
        }

        /**
         * Update a person
         *
         * @param company  the company the person is part of.
         * @param personId the person to update
         * @param puParams Contains parameters for person update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return People
         * @throws IOException
         */

        public Person personUpdate(Company company, String personId, PersonParams.Update puParams) throws IOException {
            URL url = new URL(AdminURLS.Person.personUpdate()
                    .set("companyId", company.id)
                    .set("personId", personId)
                    .expand());
            return new Person(httpService().api_PUT(url, puParams.getParams(), CACHE_TAG));
        }

        public Observable<Person> personUpdateObs(final Company company, final String personId, final PersonParams.Update puParams) {
            return Observable.fromCallable(() -> personUpdate(company, personId, puParams));
        }


        /**
         * Get a schema for editing a person
         *
         * @param person the person to edit
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getEditPersonSchema(Person person) throws IOException {
            URL url = new URL(person.getEditLink());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getEditPersonSchemaObs(final Person person) {
            return Observable.fromCallable(() -> getEditPersonSchema(person));
        }

        /**
         * Get the schema for creating a new person
         *
         * @param company The company to own the person
         * @return SchemaForm
         * @throws IOException
         */
        public SchemaForm getNewPersonSchema(Company company) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getNewPersonLink()).expand());
            return new SchemaForm(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<SchemaForm> getNewPersonSchemaObs(final Company company) {
            return Observable.fromCallable(() -> getNewPersonSchema(company));
        }

        /**
         * Set a staff member attendance
         *
         * @param company  the company the person is part of.
         * @param personId the person to update
         * @return People
         * @throws IOException
         */

        public Person setPersonAttendance(Company company, String personId, PersonParams.Update puParams) throws IOException {
            URL url = new URL(new Person().getAttendanceLink());
            return new Person(httpService().api_PUT(url, puParams.getParams(), CACHE_TAG));
        }

        public Observable<Person> setPersonAttendanceObs(final Company company, final String personId, final PersonParams.Update puParams) {
            return Observable.fromCallable(() -> personUpdate(company, personId, puParams));
        }

        // TODO: 15.07.2016 Test setPersonAttendance

        // TODO: 15.07.2016 Implement getQueuersToAMember()
    }


    /**
     * Accessor to create an instance of {@link ClinicAPI} with current configuration
     *
     * @return ClinicAPI instance
     */
    public ClinicAPI clinic() {
        return new ClinicAPI(newProvider());
    }

    public class ClinicAPI extends AbstractAPI<ClinicAPI> {

        public ClinicAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * Load specific clinic details
         *
         * @param company
         * @param clinicId
         * @return Clinic
         * @throws IOException
         */
        public Clinic clinicRead(Company company, String clinicId) throws IOException {
            URL url = new URL(AdminURLS.Clinic.clinicRead()
                    .set("companyId", company.id)
                    .set("clinicId", clinicId)
                    .expand());
            return new Clinic(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Clinic> clinicReadObs(final Company company, final String clinicId) {
            return Observable.fromCallable(() -> clinicRead(company, clinicId));
        }

        /**
         * List of clinics for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for services
         * @param clParams Parameters for this call
         * @return Collection of Clinic
         * @throws IOException
         */
        public BBCollection<Clinic> clinicList(Company company, Params clParams) throws IOException {
            UriTemplate template = Utils.TemplateWithPagination(company.getClinicsLink(), clParams);
            URL url = new URL(template.expand());

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "clinics", Clinic.class);
        }

        public Observable<BBCollection<Clinic>> clinicListObs(final Company company, final Params rlParams) {
            return Observable.fromCallable(() -> clinicList(company, rlParams));
        }

        /**
         * Create a new clinic
         *
         * @param company  the company for clinic
         * @param ccParams Contains parameters for clinic creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Clinic
         * @throws IOException
         */
        public Clinic clinicCreate(Company company, ClinicParams.Create ccParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getClinicsLink()).expand());
            return new Clinic(httpService().api_POST(url, ccParams.getParams(), CACHE_TAG));
        }

        public Observable<Clinic> clinicCreateObs(final Company company, final ClinicParams.Create rcParams) {
            return Observable.fromCallable(() -> clinicCreate(company, rcParams));
        }

        /**
         * Cancel a clinic
         *
         * @param company  the company for clinic
         * @param clinicId the clinic to cancel
         * @return Clinic
         * @throws IOException
         */
        public Clinic clinicCancel(Company company, String clinicId, Params ccparams) throws IOException {
            URL url = new URL(AdminURLS.Clinic.clinicCancel()
                    .set("companyId", company.id)
                    .set("clinicID", clinicId)
                    .expand());
            return new Clinic(httpService().api_POST(url, ccparams.getParams(), CACHE_TAG));
        }

        public Observable<Clinic> clinicCancelObs(final Company company, String clinicId, Params ccParams) {
            return Observable.fromCallable(() -> clinicCancel(company, clinicId, ccParams));
        }

        /**
         * Update a clinic
         *
         * @param clinic   the clinic to update
         * @param cuParams Contains parameters for clinic update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Clinic
         * @throws IOException
         */
        public Clinic clinicUpdate(Clinic clinic, ClinicParams.Update cuParams) throws IOException {
            URL url = new URL(clinic.getSelf());
            return new Clinic(httpService().api_PUT(url, cuParams.getParams(), CACHE_TAG));
        }

        public Observable<Clinic> clinicUpdateObs(final Clinic clinic, final ClinicParams.Update cuParams) {
            return Observable.fromCallable(() -> clinicUpdate(clinic, cuParams));
        }
    }


    /**
     * Accessor to create an instance of {@link PurchaseAPI} with current configuration
     *
     * @return PurchaseAPI instance
     */
    public PurchaseAPI purchase() {
        return new PurchaseAPI(newProvider());
    }

    public class PurchaseAPI extends AbstractAPI<PurchaseAPI> {

        public PurchaseAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * List of purchases for a company
         *
         * @param company  The owning company for services
         * @param plParams Parameters for this call
         * @return Collection of Purchase
         * @throws IOException
         */
        public BBCollection<Purchase> purchaseList(Company company, PurchaseListParams plParams) throws IOException {
            UriTemplate template = AdminURLS.Purchase.purchaseList()
                    .set("companyId", company.id)
                    .set(plParams.getParams());
            URL url = new URL(template.expand());
            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "purchases", Purchase.class);
        }

        public Observable<BBCollection<Purchase>> purchaseListObs(final Company company, final PurchaseListParams plParams) {
            return Observable.fromCallable(() -> purchaseList(company, plParams));
        }

        /**
         * Get all details about a specific purchase
         *
         * @param company    the company that owns the purchase
         * @param purchaseId the purchase to read
         * @return Purchase
         * @throws IOException
         */
        public Purchase purchaseRead(Company company, String purchaseId) throws IOException {
            URL url = new URL(AdminURLS.Purchase.purchaseRead()
                    .set("companyId", company.id)
                    .set("purchaseId", purchaseId)
                    .expand());

            return new Purchase(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Purchase> purchaseReadObs(final Company company, String purchaseId) {
            return Observable.fromCallable(() -> purchaseRead(company, purchaseId));
        }

        /**
         * Make a purchase as paid
         *
         * @param company    the company that owns the purchase
         * @param purchaseId the purchase to mark as paid
         * @param ppParams
         * @return Purchase
         * @throws IOException
         */
        public Purchase purchasePay(Company company, String purchaseId, PurchaseParams ppParams) throws IOException {
            URL url = new URL(AdminURLS.Purchase.purchasePay()
                    .set("companyId", company.id)
                    .set("purchaseId", purchaseId)
                    .expand());

            return new Purchase(httpService().api_PUT(url, ppParams.getParams(), CACHE_TAG));
        }

        public Observable<Purchase> purchasePayObs(final Company company, final String purchaseId, final PurchaseParams ppParams) {
            return Observable.fromCallable(() -> purchasePay(company, purchaseId, ppParams));
        }
    }


    /**
     * Accessor to create an instance of {@link QuestionAPI} with current configuration
     *
     * @return QuestionAPI instance
     */
    public QuestionAPI question() {
        return new QuestionAPI(newProvider());
    }

    public class QuestionAPI extends AbstractAPI<QuestionAPI> {
        public QuestionAPI(ServiceProvider provider) {
            super(provider);
        }

        /**
         * List of questions for a company
         *
         * @param company  The owning company for questions
         * @param qlParams Parameters for this call
         * @return Collection of Question
         * @throws IOException
         */
        public BBCollection<Question> questionList(Company company, QuestionListParams qlParams) throws IOException {
            URL url = new URL(Utils.inflateLink(AdminURLS.Question.questionList()
                    .set("companyId", company.id).expand(), qlParams.getParams()));

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "questions", Question.class);
        }

        public Observable<BBCollection<Question>> questionListObs(final Company company, final QuestionListParams qlParams) {
            return Observable.fromCallable(() -> questionList(company, qlParams));
        }
    }


    /**
     * Accessor to create an instance of {@link SessionAPI} with current configuration
     *
     * @return SessionAPI instance
     */
    public SessionAPI session() {
        return new SessionAPI(newProvider());
    }

    public class SessionAPI extends AbstractAPI<SessionAPI> {
        public SessionAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * List of sessions for a company
         *
         * @param company  The owning company for sessions
         * @param slParams Parameters for this call
         * @return Collection of Session
         * @throws IOException
         */
        public BBCollection<Session> sessionList(Company company, SessionListParams slParams) throws IOException {
            URL url = new URL(Utils.inflateLink(AdminURLS.Session.sessionList()
                    .set("companyId", company.id)
                    .expand(), slParams.getParams()));

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "sessions", Session.class);
        }

        /**
         * Get all details about a specific session
         *
         * @param company   the company that owns the session
         * @param sessionId the session to read
         * @return Session
         * @throws IOException
         */
        public Session sessionRead(Company company, String sessionId) throws IOException {
            URL url = new URL(AdminURLS.Session.sessionRead()
                    .set("companyId", company.id)
                    .set("sessionId", sessionId)
                    .expand());

            return new Session(httpService().api_GET(url, CACHE_TAG));
        }
    }


    /**
     * Accessor to create an instance of {@link SlotAPI} with current configuration
     *
     * @return SlotAPI instance
     */
    public SlotAPI slot() {
        return new SlotAPI(newProvider());
    }

    public class SlotAPI extends AbstractAPI<SlotAPI> {
        public SlotAPI(ServiceProvider provider) {
            super(provider);
        }


        /**
         * List of slots for a company. Results are returned as a paginated list
         *
         * @param company  The owning company for slots
         * @param slParams Parameters for this call
         * @return Collection of Slot
         * @throws IOException
         */
        public BBCollection<Slot> slotList(Company company, SlotListParams slParams) throws IOException {
            URL url = new URL(Utils.inflateLink(AdminURLS.Slot.slotList()
                    .set("companyId", company.id)
                    .expand(), slParams.getParams()));

            return new BBCollection<>(httpService().api_GET(url, CACHE_TAG), configService().auth_token, "slots", Slot.class);
        }

        public Observable<BBCollection<Slot>> slotListObs(final Company company, final SlotListParams slParams) {
            return Observable.fromCallable(() -> slotList(company, slParams));
        }

        /**
         * Create a new slot
         *
         * @param company  the company for slot
         * @param scParams Contains parameters for slot creation. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Slot
         * @throws IOException
         */
        public Slot slotCreate(Company company, SlotParams.Create scParams) throws IOException {
            URL url = new URL(UriTemplate.fromTemplate(company.getSlotsLink()).expand());
            return new Slot(httpService().api_POST(url, scParams.getParams(), CACHE_TAG));
        }

        public Observable<Slot> slotCreateObs(final Company company, final SlotParams.Create scParams) {
            return Observable.fromCallable(() -> slotCreate(company, scParams));
        }

        /**
         * Cancel a slot
         *
         * @param company the company for slot
         * @param slotId  the slot to cancel
         * @return SchemaForm
         * @throws IOException
         */
        public Slot deleteSlot(Company company, String slotId) throws IOException {
            URL url = new URL(AdminURLS.Slot.slotDelete()
                    .set("companyId", company.id)
                    .set("slotID", slotId)
                    .expand());
            return new Slot(httpService().api_DELETE(url, CACHE_TAG));
        }

        public Observable<Slot> deleteSlotObs(final Company company, String slotId) {
            return Observable.fromCallable(() -> deleteSlot(company, slotId));
        }

        /**
         * Get all the details about a specific slot
         *
         * @param company the company that owns the slot
         * @param slotId  the slot to read
         * @return Slot
         * @throws IOException
         */
        public Slot slotRead(Company company, String slotId) throws IOException {
            URL url = new URL(AdminURLS.Slot.slotRead()
                    .set("companyId", company.id)
                    .set("slotId", slotId)
                    .expand());

            return new Slot(httpService().api_GET(url, CACHE_TAG));
        }

        public Observable<Slot> slotReadObs(final Company company, final String slotId) {
            return Observable.fromCallable(() -> slotRead(company, slotId));
        }

        /**
         * Update a slot
         *
         * @param slot     the slot to update
         * @param suParams Contains parameters for slot update. If the schema is used, then set the json form output
         *                 to this through {@link bookingbugAPI2.models.params.Params#setJson(String)}
         *                 in order to ignore declared fields
         * @return Slot
         * @throws IOException
         */
        public Slot slotUpdate(Slot slot, SlotParams.Update suParams) throws IOException {
            URL url = new URL(slot.getSelf());
            return new Slot(httpService().api_PUT(url, suParams.getParams(), CACHE_TAG));
        }

        public Observable<Slot> slotUpdateObs(final Slot slot, final SlotParams.Update suParams) {
            return Observable.fromCallable(() -> slotUpdate(slot, suParams));
        }
    }
}