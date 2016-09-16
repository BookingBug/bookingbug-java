package bookingbugAPI2.models;

import bookingbugAPI2.api.AdminURLS;
import bookingbugAPI2.api.PublicURLS;
import bookingbugAPI2.models.params.*;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import bookingbugAPI2.services.http.PlainHttpService;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import helpers2.HttpServiceResponse;
import helpers2.Utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

public class Company extends BBRoot {

    private Service servicesList;
    private Administrator administratorList;
    private BBRoot administratorSchema;


    public Company(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Company(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }


    public Company() {
    }


/*
    //TODO temp. until get the purchase from the content representation
    public Purchase getPurchase () {
        return new Purchase(this.getRep(), this.auth_token);
    }

    //TODO temp. until get the session from the content representation
    public Purchase getSession () {
        return new Purchase(this.getRep(), this.auth_token);
    }
*/
/*
    //TODO: kept for compatibility with ADMIN API until changed there
    public Service getServicesList() throws IOException {
        if(servicesList == null){
            String link = response.getRep().getLinkByRel("services").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            servicesList = new Service(HttpService.api_GET(url, auth_token), auth_token);
        }
        return servicesList;
    }
*/

    /**
     * Load All of the Links and Properties of a Company
     *
     * @return CompanyConfig
     * @throws IOException
     */
    public CompanyConfig companyRead_Admin() throws IOException {
        URL url = new URL(AdminURLS.Company.companyConfigRead().set("companyId", this.id).expand());
        BBCollection<CompanyConfig> config = new BBCollection<CompanyConfig>(PlainHttpService.api_GET(url, auth_token), auth_token, "config", CompanyConfig.class);
        return config.getObjectAtIndex(0);
    }


    /**
     * Get the Bookable Items Based on Another Item
     *
     * @param bilParams
     * @return BBCollection<BookableItem>
     * @throws IOException
     */
    public BBCollection<BookableItem> bookableItemsList_Admin(BookableItemListParams bilParams) throws IOException {
        String urlStr = AdminURLS.BookableItem.bookableItemList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, bilParams.getParams()));
        BBCollection<BookableItem> bookableItems = new BBCollection<BookableItem>(PlainHttpService.api_GET(url, auth_token), auth_token, "bookable_items", BookableItem.class);
        return bookableItems;
    }


    /**
     * Get All Bookable Services
     *
     * @return BBCollection<Service>
     * @throws IOException
     */
    public BBCollection<Service> serviceList() throws IOException {
        URL url = new URL(PublicURLS.Service.serviceList().set("companyId", this.id).expand());
        BBCollection<Service> services = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "services", Service.class);
        return services;
    }


    public Service serviceNew_Admin() throws IOException {
        URL url = new URL(AdminURLS.Service.serviceNew().set("companyId", this.id).expand());
        BBCollection<Service> services = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "service", Service.class);
        return services.getObjectAtIndex(0);
    }


    public Service serviceEdit_Admin(String serviceId) throws IOException {
        URL url = new URL(AdminURLS.Service.serviceEdit().set("companyId", this.id).set("serviceId", serviceId).expand());
        BBCollection<Service> services = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "service", Service.class);
        return services.getObjectAtIndex(0);
    }


    /**
     * List of Services for a Company.
     *
     * @return BBCollection<Service>
     * @throws IOException
     */
    public BBCollection<Service> serviceList_Admin(ServiceListParams slParams) throws IOException {
        UriTemplate template = Utils.TemplateWithPagination(
                AdminURLS.Service.serviceList().set("companyId", this.id),
                slParams);
        URL url = new URL(template.expand());
        BBCollection<Service> services = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "services", Service.class);
        return services;
    }


    /**
     * Get a Specific Service.
     *
     * @param serviceId Service Id
     * @return Service
     * @throws IOException
     */
    public Service serviceRead(String serviceId) throws IOException {
        URL url = new URL(PublicURLS.Service.serviceRead().set("companyId", this.id).set("serviceId", serviceId).expand());
        BBCollection<Service> services = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "service", Service.class);
        return services.getObjectAtIndex(0);
    }


    /**
     * Load a Specific Service Details
     *
     * @param serviceId
     * @return Service
     * @throws IOException
     */
    public Service serviceRead_Admin(String serviceId) throws IOException {
        URL url = new URL(AdminURLS.Service.serviceRead().set("companyId", this.id).set("serviceId", serviceId).expand());
        BBCollection<Service> service = new BBCollection<Service>(PlainHttpService.api_GET(url, auth_token), auth_token, "service", Service.class);
        return service.getObjectAtIndex(0);
    }


    //TODO: kept for compatibility with AdminController until checked there
    public Person getPeopleList() throws IOException {
        String link = response.getRep().getLinkByRel("people").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new Person(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Bookable People for a Company.
     *
     * @return BBCollection<People>
     * @throws IOException
     */
    public BBCollection<Person> personList() throws IOException {
        URL url = new URL(PublicURLS.Person.personList().set("companyId", this.id).expand());
        BBCollection<Person> people = new BBCollection<Person>(PlainHttpService.api_GET(url, auth_token), auth_token, "people", Person.class);
        return people;
    }


    public BBCollection<Person> personList_Admin(PersonListParams plParams) throws IOException {
        String urlStr = AdminURLS.Person.personList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, plParams.getParams()));
        BBCollection<Person> people = new BBCollection<Person>(PlainHttpService.api_GET(url, auth_token), auth_token, "people", Person.class);
        return people;
    }


    /**
     * Get a Specific Bookable Person’s Details.
     *
     * @param personId Person Id
     * @return People
     * @throws IOException
     */
    public Person personRead(String personId) throws IOException {
        URL url = new URL(PublicURLS.Person.personRead().set("companyId", this.id).set("personId", personId).expand());
        BBCollection<Person> people = new BBCollection<Person>(PlainHttpService.api_GET(url, auth_token), auth_token, "person", Person.class);
        return people.getObjectAtIndex(0);
    }


    /**
     * Get a Specific Person Details using a Reference ID
     *
     * @return People
     * @throws IOException
     */
    public Person personReadUsingReferenceId(String ref) throws IOException {
        URL url = new URL(PublicURLS.Person.personReadUsingReferenceId().set("companyId", this.id).set("ref", ref).expand());
        BBCollection<Person> people = new BBCollection<Person>(PlainHttpService.api_GET(url, auth_token), auth_token, "person", Person.class);
        return people.getObjectAtIndex(0);
    }


    /**
     * Get All Bookable Resources
     * Results are returned as a paginated list.
     *
     * @return BBCollection<Resource>
     * @throws IOException
     */
    public BBCollection<Resource> resourceList() throws IOException {
        URL url = new URL(PublicURLS.Resource.resourceList().set("companyId", this.id).expand());
        BBCollection<Resource> resources = new BBCollection<Resource>(PlainHttpService.api_GET(url, auth_token), auth_token, "resources", Resource.class);
        return resources;
    }


    /**
     * Get All Questions for a Company
     *
     * @param qlParams
     * @return BBCollection<Resource>
     * @throws IOException
     */
    public BBCollection<Question> questionList_Admin(QuestionListParams qlParams) throws IOException {
        String urlStr = AdminURLS.Question.questionList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, qlParams.getParams()));
        BBCollection<Question> questions = new BBCollection<Question>(PlainHttpService.api_GET(url, auth_token), auth_token, "questions", Question.class);
        return questions;
    }


    /**
     * resourceList_Admin
     *
     * @param rlParams
     * @return BBCollection<Resource>
     * @throws IOException
     */
    public BBCollection<Resource> resourceList_Admin(ResourceListParams rlParams) throws IOException {
        String urlStr = AdminURLS.Resource.resourceList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, rlParams.getParams()));
        BBCollection<Resource> resources = new BBCollection<Resource>(PlainHttpService.api_GET(url, auth_token), auth_token, "resources", Resource.class);
        return resources;
    }


    /**
     * Get a Specific Bookable Resource
     *
     * @param resourceId Resource Id
     * @return Resource
     * @throws IOException
     */
    public Resource resourceRead(String resourceId) throws IOException {
        URL url = new URL(PublicURLS.Resource.resourceRead().set("companyId", this.id).set("resourceId", resourceId).expand());
        BBCollection<Resource> resource = new BBCollection<Resource>(PlainHttpService.api_GET(url, auth_token), auth_token, "resources", Resource.class);
        return resource.getObjectAtIndex(0);
    }


    /**
     * Load a Specific Resource Details
     *
     * @param resourceId
     * @return Resource
     * @throws IOException
     */
    public Resource resourceRead_Admin(String resourceId) throws IOException {
        URL url = new URL(AdminURLS.Resource.resourceRead().set("companyId", this.id).set("resourceId", resourceId).expand());
        BBCollection<Resource> resource = new BBCollection<Resource>(PlainHttpService.api_GET(url, auth_token), auth_token, "resources", Resource.class);
        return resource.getObjectAtIndex(0);
    }


    /**
     * Search for a Range of Calendar Bookings for a Business.
     *
     * @param slParams SlotListParams
     * @return BBCollection<Slot>
     * @throws IOException
     */
    public BBCollection<Slot> slotList_Admin(SlotListParams slParams) throws IOException {
        String urlStr = AdminURLS.Slot.slotList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, slParams.getParams()));
        BBCollection<Slot> slots = new BBCollection<Slot>(PlainHttpService.api_GET(url, auth_token), auth_token, "slots", Slot.class);
        return slots;
    }


    /**
     * Get the Details and Links of a Specific Booked Slot
     *
     * @param slotId
     * @return Slot
     * @throws IOException
     */
    public Slot slotRead_Admin(String slotId) throws IOException {
        URL url = new URL(AdminURLS.Slot.slotRead().set("companyId", this.id).set("slotId", slotId).expand());
        BBCollection<Slot> slots = new BBCollection<Slot>(PlainHttpService.api_GET(url, auth_token), auth_token, "slots", Slot.class);
        return slots.getObjectAtIndex(0);
    }


    /**
     * Load All of the Links and Properties of a Company
     *
     * @param companyId Company Id
     * @return Resource
     * @throws IOException
     */
    public Resource companyDetails(String companyId) throws IOException {
        URL url = new URL(PublicURLS.Details.companyDetails().set("companyId", companyId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a List of Bookable Events.
     *
     * @return BBCollection<Event>
     * @throws IOException
     */
    public BBCollection<Event> eventList() throws IOException {
        return eventList(new Params());
    }

    /**
     * Get a List of Bookable Events.
     *
     * @param params Parameters for pagination
     * @return BBCollection<Event>
     * @throws IOException
     */
    public BBCollection<Event> eventList(Params params) throws IOException {
        UriTemplate template = Utils.TemplateWithPagination(
                PublicURLS.Event.eventList().set("companyId", this.id),
                params);
        URL url = new URL(template.expand());
        return new BBCollection<Event>(PlainHttpService.api_GET(url, auth_token), auth_token, "events", Event.class);
    }


    /**
     * Get a Specific Event.
     *
     * @return Event
     * @throws IOException
     */
    public Event eventRead(String eventId) throws IOException {
        URL url = new URL(PublicURLS.Event.eventRead().set("companyId", this.id).set("eventId", eventId).expand());
        BBCollection<Event> events = new BBCollection<Event>(PlainHttpService.api_GET(url), auth_token, "events", Event.class);
        return events.getObjectAtIndex(0);
    }


    /**
     * Get the Bookable Items Based on Another Item
     *
     * @return BBCollection<BookableItem>
     * @throws IOException
     */
    public BBCollection<BookableItem> bookableItemsList() throws IOException {
        URL url = new URL(PublicURLS.Bookable.bookableItemsList().set("companyId", this.id).expand());
        BBCollection<BookableItem> bookableItems = new BBCollection<BookableItem>(PlainHttpService.api_GET(url, auth_token), auth_token, "items", BookableItem.class);
        return bookableItems;
    }


    /**
     * This loads a list of bookable items for a particular date
     *
     * @return BBCollection<BookableItem>
     * @throws IOException
     */
    public BBCollection<BookableItem> bookableItemsByDate(String date) throws IOException {
        URL url = new URL(PublicURLS.Bookable.bookableItemsByDate().set("companyId", this.id).set("date", date).expand());
        BBCollection<BookableItem> bookableItems = new BBCollection<BookableItem>(PlainHttpService.api_GET(url, auth_token), auth_token, "bookable_items_by_date", BookableItem.class);
        return bookableItems;
    }


    /**
     * Get Data for a range of Days
     *
     * @return Resource
     * @throws IOException
     */
    public BBRoot availabilityDaysForBookableItem(TimeDataParams params) throws IOException {
        URL url = new URL(
                PublicURLS.Bookable.availabilityDaysForBookableItem()
                        .set("companyId", this.id)
                        .set((Map) params.getParams())
                        .expand());
        return new BBRoot(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get Data for availabile times for a day
     *
     * @return Resource
     * @throws IOException
     */
    public BBCollection<BookableAvailability> availabilityTimesForBookableItem(TimeDataParams params) throws IOException {
        URL url = new URL(
                PublicURLS.Bookable.availabilityTimesForBookableItem()
                        .set("companyId", this.id)
                        .set((Map) params.getParams())
                        .expand());
        return new BBCollection<BookableAvailability>(PlainHttpService.api_GET(url, auth_token), auth_token, "events", BookableAvailability.class);
    }


    /**
     * Check if available
     *
     * @return Resource
     * @throws IOException
     */
    public Resource availabilityCheck(String dateTime) throws IOException {
        URL url = new URL(PublicURLS.Bookable.availabilityCheck().set("companyId", this.id).set("dateTime", dateTime).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Details about how a client should book
     *
     * @return Resource
     * @throws IOException
     */
    public Resource memberBookingDetails() throws IOException {
        URL url = new URL(PublicURLS.Bookable.memberBookingDetails().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Event Groups.
     *
     * @return BBCollection<EventGroup>
     * @throws IOException
     */
    public BBCollection<EventGroup> eventGroupList() throws IOException {
        URL url = new URL(PublicURLS.EventGroup.eventGroupList().set("companyId", this.id).expand());
        BBCollection<EventGroup> eventGroups = new BBCollection<EventGroup>(PlainHttpService.api_GET(url, auth_token), auth_token, "event_groups", EventGroup.class);
        eventGroups.setCollectionNameSpace("");
        return eventGroups;
    }


    /**
     * Get a Specific Event Group.
     *
     * @return EventGroup
     * @throws IOException
     */
    public EventGroup eventGroupRead(String eventGroupId) throws IOException {
        URL url = new URL(PublicURLS.EventGroup.eventGroupRead().set("companyId", this.id).set("serviceId", eventGroupId).expand());
        BBCollection<EventGroup> eventGroups = new BBCollection<EventGroup>(PlainHttpService.api_GET(url, auth_token), auth_token, "event_group", EventGroup.class);
        return eventGroups.getObjectAtIndex(0);
    }


    /**
     * Get a List of Courses or Repeating Events for a Company.
     *
     * @return BBCollection<EventChain>
     * @throws IOException
     */
    public BBCollection<EventChain> eventChainList() throws IOException {
        return eventChainList(new Params());
    }

    /**
     * Get a List of Courses or Repeating Events for a Company.
     *
     * @param params Parameters for pagination
     * @return BBCollection<EventChain>
     * @throws IOException
     */
    public BBCollection<EventChain> eventChainList(Params params) throws IOException {
        UriTemplate template = Utils.TemplateWithPagination(
                AdminURLS.EventChain.eventChainList().set("companyId", this.id),
                params);
        URL url = new URL(template.expand());
        BBCollection<EventChain> eventChains = new BBCollection<EventChain>(PlainHttpService.api_GET(url, auth_token), auth_token, "event_chains", EventChain.class);
        return eventChains;
    }


    /**
     * Get a Specific Event Chain.
     *
     * @return EventChain
     * @throws IOException
     */
    public EventChain eventChainRead(String eventChainId) throws IOException {
        URL url = new URL(PublicURLS.EventChain.eventChainRead().set("companyId", this.id).set("eventChainId", eventChainId).expand());
        BBCollection<EventChain> eventChains = new BBCollection<EventChain>(PlainHttpService.api_GET(url, auth_token), auth_token, "event_chain", EventChain.class);
        return eventChains.getObjectAtIndex(0);
    }


    /**
     * Get All Questions for a Company.
     * <BR>Results are returned as a group of question for a specific question group.
     *
     * @return BBCollection<BookingQuestion>
     * @throws IOException
     */
    public BBCollection<BookingQuestion> bookingQuestionList(String detailGroupId) throws IOException {
        URL url = new URL(PublicURLS.BookingQuestion.bookingQuestionList().set("companyId", this.id).set("detailGroupId", detailGroupId).expand());
        BBCollection<BookingQuestion> bokingQuestions = new BBCollection<BookingQuestion>(PlainHttpService.api_GET(url, auth_token), auth_token, "booking_questions", BookingQuestion.class);
        return bokingQuestions;
    }


    /**
     * Question Read
     *
     * @param questionId Question Id
     * @return BookingQuestion
     * @throws IOException
     */
    public BookingQuestion bookingQuestionRead(String questionId) throws IOException {
        URL url = new URL(PublicURLS.BookingQuestion.bookingQuestionRead().set("companyId", this.id).set("questionId", questionId).expand());
        BBCollection<BookingQuestion> bokingQuestions = new BBCollection<BookingQuestion>(PlainHttpService.api_GET(url, auth_token), auth_token, "booking_question", BookingQuestion.class);
        return bokingQuestions.getObjectAtIndex(0);
    }


    /**
     * Get all Survey Questions for a Service
     *
     * @param detail_group_id Question Group Id
     * @return BBCollection<SurveyQuestion>
     * @throws IOException
     */
    public BBCollection<SurveyQuestion> surveyQuestionList(String detail_group_id) throws IOException {
        URL url = new URL(PublicURLS.SurveyQuestion.surveyQuestionList().set("companyId", this.id).set("detail_group_id", detail_group_id).expand());
        BBCollection<SurveyQuestion> surveyQuestions = new BBCollection<SurveyQuestion>(PlainHttpService.api_GET(url, auth_token), auth_token, "survey_questions", SurveyQuestion.class);
        return surveyQuestions;
    }


    /**
     * List of Categories for a Company.
     *
     * @return BBCollection<Category>
     * @throws IOException
     */
    public BBCollection<Category> categoryList() throws IOException {
        URL url = new URL(PublicURLS.Category.categoryList().set("companyId", this.id).expand());
        BBCollection<Category> categories = new BBCollection<Category>(PlainHttpService.api_GET(url, auth_token), auth_token, "survey_question", Category.class);
        categories.setCollectionNameSpace("categories");
        return categories;
    }


    /**
     * Load a Specific Category Details
     *
     * @param categoryId Category Id
     * @return Category
     * @throws IOException
     */
    public Category categoryRead(String categoryId) throws IOException {
        URL url = new URL(PublicURLS.Category.categoryRead().set("companyId", this.id).set("categoryId", categoryId).expand());
        BBCollection<Category> categories = new BBCollection<Category>(PlainHttpService.api_GET(url, auth_token), auth_token, "categories", Category.class);
        return (Category) categories.getObjectAtIndex(0);
    }


    /**
     * List of Categories for a Company
     *
     * @return Resource
     * @throws IOException
     */
    public Resource categoryNamedList() throws IOException {
        URL url = new URL(PublicURLS.Category.categoryNamedList().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Create a new client for a business
     *
     * @return Resource
     * @throws IOException
     */
    public Resource createClient() throws IOException {
        URL url = new URL(PublicURLS.Client.createClient().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Update a client.
     *
     * @param clientId
     * @return Resource
     * @throws IOException
     */
    public Resource updateClient(String clientId) throws IOException {
        URL url = new URL(PublicURLS.Client.updateClient().set("companyId", this.id).set("clientId", clientId).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Search for a client by email against the company that you are currently logged in as
     *
     * @param email Email
     * @return Resource
     * @throws IOException
     */
    public Client findByEmail(String email) throws IOException {
        URL url = new URL(PublicURLS.Client.findByEmail().set("companyId", this.id).set("email", email).expand());
        BBCollection<Client> clients = new BBCollection<Client>(PlainHttpService.api_GET(url, auth_token), auth_token, "clients", Client.class);
        return clients.getObjectAtIndex(0);
    }


    /**
     * Get all the child clients of a particular client
     *
     * @param clientId Client Id
     * @return Resource
     * @throws IOException
     */
    public BBCollection<Client> childClientsRead(String clientId) throws IOException {
        URL url = new URL(PublicURLS.Client.readChildClients().set("companyId", this.id).set("clientId", clientId).expand());
        BBCollection<Client> childClients = new BBCollection<Client>(PlainHttpService.api_GET(url, auth_token), auth_token, "clients", Client.class);
        childClients.setCollectionNameSpace("child_clients");
        return childClients;
    }


    /**
     * Get All Custom Booking Text for a Company
     *
     * @return
     * @throws IOException
     */
    public Resource bookingTextList() throws IOException {
        URL url = new URL(PublicURLS.BookingText.getBookingText().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all possible Space Statuses for a Company
     *
     * @return Resource
     * @throws IOException
     */
    public Resource spaceStatusList() throws IOException {
        URL url = new URL(PublicURLS.Company.spaceStatusList().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url));
    }


    /**
     * Loads all of the public settings for a company, this allows you to configure a booking widget,
     * and shows all of the details need to book and show an appropriate widget.
     *
     * @return CompanySettings
     * @throws IOException
     */
    public CompanySettings getSettings() throws IOException {
        if (getRep().getResourcesByRel("settings").size() > 0) {
            //Return settings from embedded
            return new CompanySettings(new HttpServiceResponse((ContentRepresentation) getRep().getResourcesByRel("settings").get(0)));
        } else {
            //Call API
            URL url = new URL(PublicURLS.Company.settingsDetails().set("companyId", this.id).expand());
            return new CompanySettings(PlainHttpService.api_GET(url));
        }
    }

    /**
     * You can either get all the company questions or pass a param to specifiy that you only want company questions
     * that apply to either a service, resource, person, company:
     * <BR>Service = 1, Resource = 2, Person = 3, Company = 4.
     *
     * @return Resource
     * @throws IOException
     */
    public Resource businessQuestions() throws IOException {
        URL url = new URL(PublicURLS.Company.businessQuestions().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url));
    }


    /**
     * Get All Addresses for a Company.
     * <BR>Provide a company id to retrieve a list of addresses associated with that company.
     * This is a list of company branch/store/resource addresses, not client addresses.
     * Results are returned as a paginated list.
     *
     * @return Resource
     * @throws IOException
     */
    public BBCollection<Address> addressList() throws IOException {
        URL url = new URL(PublicURLS.Address.addressList().set("companyId", this.id).expand());
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url), auth_token, "addresses", Address.class);
        return addresses;
    }


    /**
     * Get All Addresses for a Company.
     *
     * @param alParams AddressListParams
     * @return BBCollection<Address>
     * @throws IOException
     */
    public BBCollection<Address> addressList_Admin(AddressListParams alParams) throws IOException {
        String urlStr = AdminURLS.Address.addressList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, alParams.getParams()));
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url, auth_token), auth_token, "addresses", Address.class);
        return addresses;
    }


    /**
     * Get a Specific Address.
     * <BR>Provide a company id and address id to retrieve the details and links of that branch/store/resource address.
     *
     * @param addressId Address Id
     * @return Resource
     * @throws IOException
     */
    public Address addressRead(String addressId) throws IOException {
        URL url = new URL(AdminURLS.Address.addressRead().set("companyId", this.id).set("addressId", addressId).expand());
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url, auth_token), auth_token, "address", Address.class);
        return addresses.getObjectAtIndex(0);
    }


    public Address addressRead_Admin(String addressId) throws IOException {
        URL url = new URL(AdminURLS.Address.addressRead().set("companyId", this.id).set("id", addressId).expand());
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url, auth_token), auth_token, "address", Address.class);
        return addresses.getObjectAtIndex(0);
    }


    /**
     * Sessions. Results are returned as a paginated list.
     *
     * @param slParams
     * @return BBCollection<Session>
     * @throws IOException
     */
    public BBCollection<Session> sessionList_Admin(SessionListParams slParams) throws IOException {
        String urlStr = AdminURLS.Session.sessionList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, slParams.getParams()));
        BBCollection<Session> sessions = new BBCollection<Session>(PlainHttpService.api_GET(url, auth_token), auth_token, "sessions", Session.class);
        return sessions;
    }


    public Session sessionRead_Admin(String sessionId) throws IOException {
        URL url = new URL(AdminURLS.Session.sessionRead().set("companyId", this.id).set("sessionId", sessionId).expand());
        BBCollection<Session> session = new BBCollection<Session>(PlainHttpService.api_GET(url, auth_token), auth_token, "session", Session.class);
        return session.getObjectAtIndex(0);
    }


    /**
     * Get the address result for a particular customer
     *
     * @param customerId Customer Id
     * @return Resource
     * @throws IOException
     */
    public Address customerAddress(String customerId) throws IOException {
        URL url = new URL(PublicURLS.Address.customerAddress().set("companyId", this.id).set("customerId", customerId).expand());
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url, auth_token), auth_token, "addresses", Address.class);
        return addresses.getObjectAtIndex(0);
    }


    /**
     * Get a list of possible addresses from a postcode
     *
     * @param postcode Postcode
     * @return Resource
     * @throws IOException
     */
    public BBCollection<Address> postCodeAddress(String postcode) throws IOException {
        URL url = new URL(PublicURLS.Address.postCodeAddress().set("companyId", this.id).set("postcode", postcode).expand());
        BBCollection<Address> addresses = new BBCollection<Address>(PlainHttpService.api_GET(url, auth_token), auth_token, "addresses", Address.class);
        addresses.setCollectionNameSpace("address");
        return addresses;
    }


    /**
     * Get all Products for a Company
     *
     * @return Resource
     * @throws IOException
     */
    public BBCollection<Product> productsList() throws IOException {
        URL url = new URL(PublicURLS.Products.productsList().set("companyId", this.id).expand());
        BBCollection<Product> products = new BBCollection<Product>(PlainHttpService.api_GET(url), auth_token, "products", Product.class);
        return products;
    }


    /**
     * Get a specific Product
     *
     * @param productId Product Id
     * @return Resource
     * @throws IOException
     */
    public Product productsRead(String productId) throws IOException {
        URL url = new URL(PublicURLS.Products.productRead().set("companyId", this.id).set("productId", productId).expand());
        BBCollection<Product> products = new BBCollection<Product>(PlainHttpService.api_GET(url, auth_token), auth_token, "product", Product.class);
        return products.getObjectAtIndex(0);
    }


    /**
     * Slot List
     *
     * @return Resource
     * @throws IOException
     */
    public BBCollection<Slot> slotList() throws IOException {
        URL url = new URL(PublicURLS.Slot.slotList().set("companyId", this.id).expand());
        BBCollection<Slot> slots = new BBCollection<Slot>(PlainHttpService.api_GET(url), auth_token, "slots", Slot.class);
        return slots;
    }


    /**
     * Slot Read
     *
     * @param slotId Slot Id
     * @return Resource
     * @throws IOException
     */
    public Slot slotRead(String slotId) throws IOException {
        URL url = new URL(PublicURLS.Slot.slotRead().set("companyId", this.id).set("slotId", slotId).expand());
        BBCollection<Slot> slots = new BBCollection<Slot>(PlainHttpService.api_GET(url), auth_token, "slot", Slot.class);
        return slots.getObjectAtIndex(0);
    }


    /**
     * Get a list of images attached to an event group.
     *
     * @param eventGroupId Event Group Id
     * @return Resource
     * @throws IOException
     */
    public Resource eventGroupImagesList(String eventGroupId) throws IOException {
        URL url = new URL(PublicURLS.EventGroupImages.eventGroupImagesList().set("companyId", this.id).set("eventGroupId", eventGroupId).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * TODO: ???
     *
     * @param eventGroupId Event Group Id
     * @param id           Id
     * @return
     * @throws IOException
     */
    public Resource eventGroupImages_f(String eventGroupId, String id) throws IOException {
        URL url = new URL(PublicURLS.EventGroupImages.eventGroupImages_f().set("companyId", this.id).set("eventGroupId", eventGroupId).set("id", id).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Get all the deals for a Company
     *
     * @return Resource
     * @throws IOException
     */
    public Resource dealList() throws IOException {
        URL url = new URL(PublicURLS.Deal.dealList().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Get all the deals for a Company.
     *
     * @return BBCollection<Deal>
     * @throws IOException
     */
    public BBCollection<Deal> dealList_Admin() throws IOException {
        URL url = new URL(AdminURLS.Deal.dealList().set("companyId", this.id).expand());
        BBCollection<Deal> deals = new BBCollection<Deal>(PlainHttpService.api_GET(url, auth_token), auth_token, "deals", Deal.class);
        return deals;
    }


    /**
     * Get the deal from a company using the reference id.
     *
     * @param referenceId
     * @return Deal
     * @throws IOException
     */
    public Deal dealReadByRef_Admin(String referenceId) throws IOException {
        URL url = new URL(AdminURLS.Deal.dealReadByRef().set("companyId", this.id).set("referenceId", referenceId).expand());
        BBCollection<Deal> deals = new BBCollection<Deal>(PlainHttpService.api_GET(url, auth_token), auth_token, "deal", Deal.class);
        return deals.getObjectAtIndex(0);
    }


    /**
     * List all the deals codes for a deal
     *
     * @param dealId
     * @return BBCollection<DealCodes>
     * @throws IOException
     */
    public BBCollection<DealCodes> dealCodesList_Admin(String dealId) throws IOException {
        URL url = new URL(AdminURLS.Deal.dealCodes().set("companyId", this.id).set("dealId", dealId).expand());
        BBCollection<DealCodes> dealCodes = new BBCollection<DealCodes>(PlainHttpService.api_GET(url, auth_token), auth_token, "dealCodes", DealCodes.class);
        return dealCodes;
    }


    /**
     * Get A Deal
     *
     * @param dealId Deal Id
     * @return Resource
     * @throws IOException
     */
    public Resource dealRead(String dealId) throws IOException {
        URL url = new URL(PublicURLS.Deal.dealRead().set("companyId", this.id).set("dealId", dealId).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Get all the service groups for a Company
     *
     * @return Resource
     * @throws IOException
     */
    public Resource serviceGroupList() throws IOException {
        URL url = new URL(PublicURLS.ServiceGroup.serviceGroupList().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Get a Service Group
     *
     * @param groupId Group Id
     * @return Resource
     * @throws IOException
     */
    public Resource serviceGroupRead(String groupId) throws IOException {
        URL url = new URL(PublicURLS.ServiceGroup.serviceGroupRead().set("companyId", this.id).set("groupId", groupId).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Get Contents and Details of the Current Cached Basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource readBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.readBasket().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Add Shopping Item To Basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource createBasketItem() throws IOException {
        URL url = new URL(PublicURLS.Basket.createBasketItem().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Confirm and Pay for the Items in the Shopping Basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource checkoutBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.checkoutBasket().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Empty the Shopping Basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasket().set("companyId", this.id).expand());
        this.id = "";
        return new Resource(PlainHttpService.api_DELETE(url), auth_token);
    }


    /**
     * Get the Details of a Specific Item in the Shopping Basket
     *
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource readBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.readBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Remove an Item for the Shopping Basket
     *
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(PlainHttpService.api_DELETE(url), auth_token);
    }


    /**
     * Add a new file attachment to a basket item
     *
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource addFileAttachmentToBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.addFileAttachmentToBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(PlainHttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Update a file attachment for a basket item
     *
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource updateFileAttachmentToBasketItem(String basketItemId, Map<String, String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.updateFileAttachmentToBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(PlainHttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Get a file attachment for a basket
     *
     * @param basketId     Basket Id
     * @param attachmentId Attachment Id
     * @return
     * @throws IOException
     */
    public Resource getFileAttachmentForBasketItem(String basketId, String attachmentId) throws IOException {
        URL url = new URL(PublicURLS.Basket.getFileAttachmentForBasketItem().set("companyId", this.id).set("basketId", basketId).set("attachmentId", attachmentId).expand());
        return new Resource(PlainHttpService.api_GET(url), auth_token);
    }


    /**
     * Attempt to apply a deal to a basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource applyDeal() throws IOException {
        URL url = new URL(PublicURLS.Basket.applyDeal().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Delete a coupon from a basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource removeDeal(Map<String, String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.removeDeal().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Attempt to apply a discount coupon to a basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource applyBasketCoupon(Map<String, String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.applyBasketCoupon().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_POST(url, params, auth_token), auth_token);
    }


    /**
     * Delete a coupon from a basket
     *
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasketCoupon() throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasketCoupon().set("companyId", this.id).expand());
        return new Resource(PlainHttpService.api_DELETE(url), auth_token);
    }

    public Booking bookingCreate_Admin(BookingCreateParams bCParams) throws IOException {
        String urlStr = AdminURLS.Bookings.bookingCreate().set("companyId", this.id).expand();
        URL url = new URL(urlStr);

        return new Booking(PlainHttpService.api_POST(url, bCParams.getParams(), auth_token), auth_token);
    }

    /**
     * getBookingRead_Admin
     *
     * @param bookingId
     * @return Booking
     * @throws IOException
     */
    public Booking bookingRead_Admin(String bookingId) throws IOException {
        URL url = new URL(AdminURLS.Bookings.bookingRead().set("companyId", this.id).set("bookingId", bookingId).expand());
        BBCollection<Booking> bookings = new BBCollection<Booking>(PlainHttpService.api_GET(url, auth_token), auth_token, "booking", Booking.class);
        return bookings.getObjectAtIndex(0);
    }


    /**
     * Get all the coupons for a Company.
     *
     * @return BBCollection<Coupons>
     * @throws IOException
     */
    public BBCollection<Coupons> couponsList_Admin() throws IOException {
        URL url = new URL(AdminURLS.Coupons.couponList().set("companyId", this.id).expand());
        BBCollection<Coupons> coupons = new BBCollection<Coupons>(PlainHttpService.api_GET(url, auth_token), auth_token, "coupons", Coupons.class);
        return coupons;
    }


    /**
     * Read details about a logged in user, including details about which companies they have access to.
     *
     * @param userId
     * @return User
     * @throws IOException
     */
    public User userRead_Admin(String userId) throws IOException {
        URL url = new URL(AdminURLS.User.userRead().set("companyId", this.id).set("userId", userId).expand());
        BBCollection<User> users = new BBCollection<User>(PlainHttpService.api_GET(url, auth_token), auth_token, "user", User.class);
        return users.getObjectAtIndex(0);
    }


    /**
     * Get a list of Purchases.
     *
     * @param plParams
     * @return BBCollection<Purchase>
     * @throws IOException
     */
    public BBCollection<Purchase> purchaseList_Admin(PurchaseListParams plParams) throws IOException {
        String urlStr = AdminURLS.Purchase.purchaseList().set("companyId", this.id).expand();
        URL url = new URL(Utils.inflateLink(urlStr, plParams.getParams()));
        BBCollection<Purchase> purchases = new BBCollection<Purchase>(PlainHttpService.api_GET(url, auth_token), auth_token, "purchases", Purchase.class);
        return purchases;
    }


    /**
     * Get a Specific Purchase
     *
     * @param purchaseId
     * @return Purchase
     * @throws IOException
     */
    public Purchase purchaseRead_Admin(String purchaseId) throws IOException {
        URL url = new URL(AdminURLS.Purchase.purchaseRead().set("companyId", this.id).set("purchaseId", purchaseId).expand());
        BBCollection<Purchase> purchases = new BBCollection<Purchase>(PlainHttpService.api_GET(url, auth_token), auth_token, "purchase", Purchase.class);
        return purchases.getObjectAtIndex(0);
    }

    /**
     * Get the Client Schema
     *
     * @return BBRoot
     * @throws IOException
     */
    public BBRoot getClientSchema() throws IOException {
/*        String link = rep.getLinkByRel("client_details").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        HttpServiceResponse response = HttpService.api_GET(url, auth_token);
        return new BBRoot(response.getRep(), auth_token);*/
        ObjectMapper mapper = new ObjectMapper();
        SchemaFactoryWrapper visitor = new SchemaFactoryWrapper();
        mapper.acceptJsonFormatVisitor(Client.class, visitor);

        JsonSchema schema = visitor.finalSchema();

        final JsonNodeFactory factory = JsonNodeFactory.instance;
        ArrayNode form = factory.arrayNode();
        ObjectNode finalJson = factory.objectNode();

        form.add("*").add(factory.objectNode().put("type", "submit").put("title", "save"));

        finalJson.put("schema", mapper.readTree(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema)));
        finalJson.put("form", form);

        JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
        InputStream ins = new ByteArrayInputStream(finalJson.toString().getBytes());
        Reader insr = new InputStreamReader(ins);
        ContentRepresentation rep = representationFactory.readRepresentation(HAL_JSON, insr);
        HttpServiceResponse httpServiceResponse = new HttpServiceResponse(rep);
        return new BBRoot(httpServiceResponse, auth_token);
    }

    /**
     * Create a new Client
     *
     * @param data
     * @return BBRoot
     * @throws HttpException
     * @throws MalformedURLException
     */
    public BBRoot createClient(Map<String, String> data) throws HttpException, MalformedURLException {
        String link = response.getRep().getLinkByRel("client").getHref();
        URL url = new URL(link);
        return new BBRoot(PlainHttpService.api_POST(url, data, auth_token), auth_token);
    }

    /**
     * Create a new Person
     *
     * @param data
     * @return BBRoot
     * @throws HttpException
     * @throws MalformedURLException
     */
    public BBRoot createPerson(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personCreate().set("companyId", get("id")).expand();
        URL url = new URL(uri);
        return new BBRoot(PlainHttpService.api_POST(url, data, auth_token), auth_token);
    }

    /**
     * Update a person
     *
     * @param person
     * @param data
     * @return BBRoot
     * @throws HttpException
     * @throws MalformedURLException
     */
    public BBRoot updatePerson(Person person, Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personUpdate().set("companyId", get("id")).set("personId", person.get("id")).expand();
        URL url = new URL(uri);
        return new BBRoot(PlainHttpService.api_PUT(url, PlainHttpService.jsonContentType, data, auth_token), auth_token);
    }

    /**
     * Get the administrators
     *
     * @return Administrator
     * @throws IOException
     */
    public Administrator getAdministrators() throws IOException {
        if (administratorList == null) {
            String link = response.getRep().getLinkByRel("administrators").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            HttpServiceResponse response = PlainHttpService.api_GET(url, auth_token);
            administratorList = new Administrator(response, auth_token);
        }
        return administratorList;
    }

    /**
     * Get the administrator schema
     *
     * @return BBRoot
     * @throws IOException
     */
    public BBRoot getAdministratorSchema() throws IOException {
        if (administratorSchema == null) {
            String link = response.getRep().getLinkByRel("new_administrator").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            HttpServiceResponse response = PlainHttpService.api_GET(url, auth_token);
            administratorSchema = new BBRoot(response, auth_token);
        }
        return administratorSchema;
    }

    /**
     * Create a new administrator
     *
     * @param data
     * @return Administrator
     * @throws HttpException
     * @throws MalformedURLException
     */
    public Administrator createAdministrator(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Administrator.administratorCreate().set("companyId", get("id")).expand();
        URL url = new URL(uri);
        return new Administrator(PlainHttpService.api_POST(url, data, auth_token), auth_token);
    }

    /**
     * Update a person
     *
     * @param data
     * @return BBRoot
     * @throws HttpException
     * @throws MalformedURLException
     */
    public BBRoot updatePerson(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personCreate().set("companyId", get("id")).expand();
        URL url = new URL(uri);
        return new BBRoot(PlainHttpService.api_POST(url, data, auth_token), auth_token);
    }

    /**
     * Returns the company id.
     *
     * @return The company id associated with the current Company object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the company name.
     *
     * @return The company name associated with the current Company object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the company description.
     *
     * @return The company description associated with the current Company object
     */
    public String getDescription() {
        return get("description");
    }

    /**
     * Returns the company id address.
     *
     * @return The company id address associated with the current Company object
     */
    public Integer getAddressId() {
        return getInteger("address_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the company website.
     *
     * @return The company website associated with the current Company object
     */
    public String getWebsite() {
        return get("website");
    }

    /**
     * Returns the numeric widget id.
     *
     * @return The numeric widget id associated with the current Company object
     */
    public Integer getNumericWidgetId() {
        return getInteger("numeric_widget_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the company currency code.
     *
     * @return The company currency code associated with the current Company object
     */
    public String getCurrencyCode() {
        return get("currency_code");
    }

    /**
     * Returns the time zone.
     *
     * @return The time zone associated with the current Company object
     */
    public String getTimezone() {
        return get("timezone");
    }

    /**
     * Returns the country code.
     *
     * @return The country code associated with the current Company object
     */
    public String getCountryCode() {
        return get("country_code");
    }

    /**
     * Returns true if it is live, fasle otherwise.
     *
     * @return The live attribute associated with the current Company object
     */
    public Boolean getLive() {
        return getBoolean("live", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the settings link.
     *
     * @return The settings link associated with the current Company object
     */
    public String getSettingsLink() {
        return getLink("settings");
    }

    /**
     * Returns the services link.
     *
     * @return The services link associated with the current Company object
     */
    public String getServicesLink() {
        return getLink("services");
    }

    /**
     * Returns the categories link.
     *
     * @return The categories link associated with the current Company object
     */
    public String getCategoriesLink() {
        return getLink("categories");
    }

    /**
     * Returns the address link.
     *
     * @return The address link associated with the current Company object
     */
    public String getAddressLink() {
        return getLink("address");
    }

    /**
     * Returns the addresses link.
     *
     * @return The addresses link associated with the current Company object
     */
    public String getAddressesLink() {
        return getLink("addresses");
    }

    /**
     * Returns the book link.
     *
     * @return The book link associated with the current Company object
     */
    public String getBookLink() {
        return getLink("book");
    }

    /**
     * Returns the named categories link.
     *
     * @return The named categories link associated with the current Company object
     */
    public String getNamedCategoriesLink() {
        return getLink("named_categories");
    }

    /**
     * Returns the resources link.
     *
     * @return The resources link associated with the current Company object
     */
    public String getResourcesLink() {
        return getLink("resources");
    }

    /**
     * Returns the people link.
     *
     * @return The people link associated with the current Company object
     */
    public String getPeopleLink() {
        return getLink("people");
    }

    /**
     * Returns the clinics link.
     *
     * @return The clinics link associated with the current Company object
     */
    public String getClinicsLink() {
        return getLink("clinics");
    }

    /**
     * Returns the events link.
     *
     * @return The events link associated with the current Company object
     */
    public String getEventsLink() {
        return getLink("events");
    }

    /**
     * Returns the event chains link.
     *
     * @return The event chains link associated with the current Company object
     */
    public String getEventChainsLink() {
        return getLink("event_chains");
    }

    /**
     * Returns the event groups link.
     *
     * @return The event groups link associated with the current Company object
     */
    public String getEventGroupsLink() {
        return getLink("event_groups");
    }

    /**
     * Returns the client details link.
     *
     * @return The client details link associated with the current Company object
     */
    public String getClientDetailsLink() {
        return getLink("client_details");
    }

    /**
     * Returns the packages link.
     *
     * @return The packages link associated with the current Company object
     */
    public String getPackagesLink() {
        return getLink("packages");
    }

    /**
     * Returns the bulk purchases link.
     *
     * @return The bulk purchases link associated with the current Company object
     */
    public String getBulkPurchasesLink() {
        return getLink("bulk_purchases");
    }

    /**
     * Returns the checkout link.
     *
     * @return The checkout link associated with the current Company object
     */
    public String getCheckoutLink() {
        return getLink("checkout");
    }

    /**
     * Returns the total link.
     *
     * @return The total link associated with the current Company object
     */
    public String getTotalLink() {
        return getLink("total");
    }

    /**
     * Returns the login link.
     *
     * @return The login link associated with the current Company object
     */
    public String getLoginLink() {
        return getLink("login");
    }

    /**
     * Returns the client link.
     *
     * @return The client link associated with the current Company object
     */
    public String getClientLink() {
        return getLink("client");
    }

    /**
     * Returns the client by email link.
     *
     * @return The client by email link associated with the current Company object
     */
    public String getClientByEmailLink() {
        return getLink("client_by_email");
    }

    /**
     * Returns the booking text link.
     *
     * @return The booking text link associated with the current Company object
     */
    public String getBookingTextLink() {
        return getLink("booking_text");
    }

    /**
     * Returns the basket link.
     *
     * @return The basket link associated with the current Company object
     */
    public String getBasketLink() {
        return getLink("basket");
    }

    /**
     * Returns the days link.
     *
     * @return The days link associated with the current Company object
     */
    public String getDaysLink() {
        return getLink("days");
    }

    /**
     * Returns the times link.
     *
     * @return The times link associated with the current Company object
     */
    public String getTimesLink() {
        return getLink("times");
    }

    /**
     * Returns the coupon link.
     *
     * @return The coupon link associated with the current Company object
     */
    public String getCouponLink() {
        return getLink("coupon");
    }

    /**
     * Returns the company questions link.
     *
     * @return The company questions link associated with the current Company object
     */
    public String getCompanyQuestionsLink() {
        return getLink("company_questions");
    }

    /**
     * Returns the email password reset link.
     *
     * @return The email password reset link associated with the current Company object
     */
    public String getEmailPasswordResetLink() {
        return getLink("email_password_reset");
    }

    /**
     * Returns the deals link.
     *
     * @return The deals link associated with the current Company object
     */
    public String getDealsLink() {
        return getLink("deals");
    }

    /**
     * Returns the service groups link.
     *
     * @return The service groups link associated with the current Company object
     */
    public String getServiceGroupsLink() {
        return getLink("service_groups");
    }

    /**
     * Returns the member levels link.
     *
     * @return The member levels link associated with the current Company object
     */
    public String getMemberLevelsLink() {
        return getLink("member_levels");
    }

    /**
     * Returns the facebook login link.
     *
     * @return The facebook login link associated with the current Company object
     */
    public String getFacebookLoginLink() {
        return getLink("facebook_login");
    }

    /**
     * Returns the space statuses link.
     *
     * @return The space statuses link associated with the current Company object
     */
    public String getSpaceStatusesLink() {
        return getLink("space_statuses");
    }

    /**
     * Returns the new person link.
     *
     * @return The new person link associated with the current Company object
     */
    public String getNewPersonLink() {
        return getLink("new_person");
    }

    /**
     * Returns the new resource link.
     *
     * @return The new resource link associated with the current Company object
     */
    public String getNewResourceLink() {
        return getLink("new_resource");
    }

    /**
     * Returns the schedules link.
     *
     * @return The schedules link associated with the current Company object
     */
    public String getSchedulesLink() {
        return getLink("schedules");
    }

    /**
     * Returns the new schedule link.
     *
     * @return The new schedule link associated with the current Company object
     */
    public String getNewScheduleLink() {
        return getLink("new_schedule");
    }

    /**
     * Returns the administrators link.
     *
     * @return The administrators link associated with the current Company object
     */
    public String getAdministratorsLink() {
        return getLink("administrators");
    }

    /**
     * Returns the new person link.
     *
     * @return The new person link associated with the current Company object
     */
    public String getNewAdministratorLink() {
        return getLink("new_administrator");
    }

    /**
     * Returns the slots link.
     *
     * @return The slots link associated with the current Company object
     */
    public String getSlotsLink() {
        return getLink("slots");
    }

    /**
     * Returns the calendar events link.
     *
     * @return The calendar events link associated with the current Company object
     */
    public String getCalendarEventsLink() {
        return getLink("calendar_events");
    }

    /**
     * Returns the new service link.
     *
     * @return The new service link associated with the current Company object
     */
    public String getNewServiceLink() {
        return getLink("new_service");
    }

    /**
     * Returns the bookings link.
     *
     * @return The bookings link associated with the current Company object
     */
    public String getBookingsLink() {
        return getLink("bookings");
    }

    /**
     * Returns the queuers link.
     *
     * @return The queuers link associated with the current Company object
     */
    public String getQueuersLink() {
        return getLink("queuers");
    }

    /**
     * Returns the client queues link.
     *
     * @return The client queues link associated with the current Company object
     */
    public String getClientQueuesLink() {
        return getLink("client_queues");
    }

    /**
     * Returns the new queuer link.
     *
     * @return The new queuer link associated with the current Company object
     */
    public String getNewQueuerLink() {
        return getLink("new_queuer");
    }

    /**
     * Returns the pusher link.
     *
     * @return The pusher link associated with the current Company object
     */
    public String getPusherLink() {
        return getLink("pusher");
    }

    /**
     * Returns the external bookings link.
     *
     * @return The external bookings link associated with the current Company object
     */
    public String getExternalBookings() {
        return getLink("external_bookings");
    }

    /**
     * Check if company has resources configured
     *
     * @return true if the company has resources configured
     */
    public boolean hasResources() {
        //TODO: Use settings when BB updates the api
        return getResourcesLink() != null;
    }

    /**
     * Check if company has events configured
     *
     * @return true if the company has events configured
     */
    public boolean hasEvents() {
        //TODO: Use settings when BB updates the api
        return getEventsLink() != null;
    }

    /**
     * Check if company has services configured
     *
     * @return true if the company has services configured
     */
    public boolean hasServices() {
        //TODO: Use settings when BB updates the api
        return getServicesLink() != null;
    }

    /**
     * Check if company has people configured
     *
     * @return true if the company has people configured
     */
    public boolean hasPeople() {
        //TODO: Use settings when BB updates the api
        return getPeopleLink() != null;
    }

}
