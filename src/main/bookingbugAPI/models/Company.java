package bookingbugAPI.models;

import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.api.PublicURLS;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.module.jsonSchema.JsonSchema;
import com.fasterxml.jackson.module.jsonSchema.factories.SchemaFactoryWrapper;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import bookingbugAPI.services.HttpService;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import helpers.HttpServiceResponse;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

public class Company extends BBRoot{

    private Service servicesList;
    private Administrator administratorList;
    private BBRoot administratorSchema;


    public Company(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Company(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }


    public Company() {
        super();
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

    //TODO: kept for compatibility with ADMIN API until changed there
    public Service getServicesList() throws IOException {
        if(servicesList == null){
            String link = response.getRep().getLinkByRel("services").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            servicesList = new Service(HttpService.api_GET(url, auth_token), auth_token);
        }
        return servicesList;
    }


    /**
     * Get All Bookable Services
     * @return Resource
     * @throws IOException
     */
    public Resource serviceList() throws IOException {
        URL url = new URL(PublicURLS.Service.serviceList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Service.
     * @param serviceId Service Id
     * @return Resource
     * @throws IOException
     */
    public Resource serviceRead(String serviceId) throws IOException {
        URL url = new URL(PublicURLS.Service.serviceRead().set("companyId", this.id).set("serviceId", serviceId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    //TODO: kept for compatibility with AdminController untill checked there
    public People getPeopleList() throws IOException {
        String link = response.getRep().getLinkByRel("people").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new People(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Bookable People for a Company.
     * @return Resource
     * @throws IOException
     */
    public Resource personList() throws IOException {
        URL url = new URL(PublicURLS.Person.personList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Bookable Person’s Details.
     * @param personId Person Id
     * @return Resource
     * @throws IOException
     */
    public Resource personRead(String personId) throws IOException {
        URL url = new URL(PublicURLS.Person.personRead().set("companyId", this.id).set("personId", personId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Person Details using a Reference ID
     * @return
     * @throws IOException
     */
    public Resource personReadUsingReferenceId(String ref) throws IOException {
        URL url = new URL(PublicURLS.Person.personReadUsingReferenceId().set("companyId", this.id).set("ref", ref).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Bookable Resources
     * Results are returned as a paginated list.
     * @return Resource
     * @throws IOException
     */
    public Resource resourceList() throws IOException {
        URL url = new URL(PublicURLS.Resource.resourceList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Bookable Resource
     * @param resourceId Resource Id
     * @return Resource
     * @throws IOException
     */
    public Resource resourceRead(String resourceId) throws IOException {
        URL url = new URL(PublicURLS.Resource.resourceRead().set("companyId", this.id).set("resourceId", resourceId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Load All of the Links and Properties of a Company
     * @param companyId Company Id
     * @return Resource
     * @throws IOException
     */
    public Resource companyDetails(String companyId) throws IOException {
        URL url = new URL(PublicURLS.Details.companyDetails().set("companyId", companyId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a List of Bookable Events.
     * @return Resource
     * @throws IOException
     */
    public Resource eventList() throws IOException {
        URL url = new URL(PublicURLS.Event.eventList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Event.
     * @return Resource
     * @throws IOException
     */
    public Resource eventRead(String eventId) throws IOException {
        URL url = new URL(PublicURLS.Event.eventRead().set("companyId", this.id).set("eventId", eventId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get the Bookable Items Based on Another Item
     * @return Resource
     * @throws IOException
     */
    public Resource bookableItemsList() throws IOException {
        URL url = new URL(PublicURLS.Bookable.bookableItemsList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * This loads a list of bookable items for a particular date
     * @return Resource
     * @throws IOException
     */
    public Resource bookableItemsByDate(String date) throws IOException {
        URL url = new URL(PublicURLS.Bookable.bookableItemsByDate().set("companyId", this.id).set("date", date).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get Data for a range of Days
     * @return Resource
     * @throws IOException
     */
    public Resource availabilityDaysForBookableItem() throws IOException {
        URL url = new URL(PublicURLS.Bookable.availabilityDaysForBookableItem().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get Data for availabile times for a day
     * @return Resource
     * @throws IOException
     */
    public Resource availabilityTimesForBookableItem() throws IOException {
        URL url = new URL(PublicURLS.Bookable.availabilityTimesForBookableItem().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Check if available
     * @return Resource
     * @throws IOException
     */
    public Resource availabilityCheck(String dateTime) throws IOException {
        URL url = new URL(PublicURLS.Bookable.availabilityCheck().set("companyId", this.id).set("dateTime", dateTime).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Details about how a client should book
     * @return Resource
     * @throws IOException
     */
    public Resource memberBookingDetails() throws IOException {
        URL url = new URL(PublicURLS.Bookable.memberBookingDetails().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Event Groups.
     * @return Resource
     * @throws IOException
     */
    public Resource eventGroupList() throws IOException {
        URL url = new URL(PublicURLS.EventGroup.eventGroupList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Event Group.
     * @return Resource
     * @throws IOException
     */
    public Resource eventGroupRead(String eventGroupId) throws IOException {
        URL url = new URL (PublicURLS.EventGroup.eventGroupRead().set("companyId", this.id).set("serviceId", eventGroupId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a List of Courses or Repeating Events for a Company.
     * @return Resource
     * @throws IOException
     */
    public Resource eventChainList() throws IOException {
        URL url = new URL (PublicURLS.EventChain.eventChainList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a Specific Event Chain.
     * @return Resource
     * @throws IOException
     */
    public Resource eventChainRead(String eventChainId) throws IOException {
        URL url = new URL (PublicURLS.EventChain.eventChainRead().set("companyId", this.id).set("eventChainId", eventChainId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Questions for a Company.
     * <BR>Results are returned as a group of question for a specific question group.
     * @return Resource
     * @throws IOException
     */
    public Resource bookingQuestionList(String detailGroupId) throws IOException {
        URL url = new URL(PublicURLS.BookingQuestion.bookingQuestionList().set("companyId", this.id).set("detailGroupId", detailGroupId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Question Read
     * @param questionId Question Id
     * @return Resource
     * @throws IOException
     */
    public Resource bookingQuestionRead(String questionId) throws IOException {
        URL url = new URL(PublicURLS.BookingQuestion.bookingQuestionRead().set("companyId", this.id).set("questionId", questionId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all Survey Questions for a Service
     * @param detail_group_id Question Group Id
     * @return Result
     * @throws IOException
     */
    public Resource surveyQuestionList(String detail_group_id) throws IOException {
        URL url = new URL(PublicURLS.SurveyQuestion.surveyQuestionList().set("companyId", this.id).set("detail_group_id", detail_group_id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * List of Categories for a Company.
     * @return Resource
     * @throws IOException
     */
    public Resource categoryList() throws IOException {
        URL url = new URL(PublicURLS.Category.categoryList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Load a Specific Category Details
     * @param categoryId Category Id
     * @return Resource
     * @throws IOException
     */
    public Resource categoryRead(String categoryId) throws IOException {
        URL url = new URL(PublicURLS.Category.categoryRead().set("companyId", this.id).set("categoryId", categoryId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * List of Categories for a Company
     * @return Resource
     * @throws IOException
     */
    public Resource categoryNamedList() throws IOException {
        URL url = new URL(PublicURLS.Category.categoryNamedList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Create a new client for a business
     * @return Resource
     * @throws IOException
     */
    public Resource createClient() throws IOException {
        URL url = new URL(PublicURLS.Client.createClient().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Update a client.
     * @param clientId
     * @return Resource
     * @throws IOException
     */
    public Resource updateClient(String clientId) throws IOException {
        URL url = new URL(PublicURLS.Client.updateClient().set("companyId", this.id).set("clientId", clientId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Search for a client by email against the company that you are currently logged in as
     * @param email Email
     * @return Resource
     * @throws IOException
     */
    public Resource findByEmail(String email) throws IOException {
        URL url = new URL(PublicURLS.Client.findByEmail().set("companyId", this.id).set("email", email).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all the child clients of a particular client
     * @param clientId Client Id
     * @return Resource
     * @throws IOException
     */
    public Resource readChildClients(String clientId) throws IOException {
        URL url = new URL(PublicURLS.Client.readChildClients().set("companyId", this.id).set("clientId", clientId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get All Custom Booking Text for a Company
     * @return
     * @throws IOException
     */
    public Resource bookingTextList() throws IOException {
        URL url = new URL(PublicURLS.BookingText.getBookingText().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all possible Space Statuses for a Company
     * @return Resource
     * @throws IOException
     */
    public Resource spaceStatusList() throws IOException {
        URL url = new URL(PublicURLS.Company.spaceStatusList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url));
    }


    /**
     * Loads all of the public settings for a company, this allows you to configure a booking widget,
     * and shows all of the details need to book and show an appropriate widget.
     * @return Resource
     * @throws IOException
     */
    public Resource settingsDetails() throws IOException {
        URL url = new URL(PublicURLS.Company.settingsDetails().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url));
    }


    /**
     * You can either get all the company questions or pass a param to specifiy that you only want company questions
     * that apply to either a service, resource, person, company:
     * <BR>Service = 1, Resource = 2, Person = 3, Company = 4.
     * @return Resource
     * @throws IOException
     */
    public Resource businessQuestions() throws IOException {
        URL url = new URL(PublicURLS.Company.businessQuestions().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url));
    }


    /**
     * Get All Addresses for a Company.
     * <BR>Provide a company id to retrieve a list of addresses associated with that company.
     * This is a list of company branch/store/resource addresses, not client addresses.
     * Results are returned as a paginated list.
     * @return Resource
     * @throws IOException
     */
    public Resource addressList() throws IOException {
        URL url = new URL(PublicURLS.Address.addressList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get a Specific Address.
     * <BR>Provide a company id and address id to retrieve the details and links of that branch/store/resource address.
     * @param addressId Address Id
     * @return Resource
     * @throws IOException
     */
    public Resource addressRead(String addressId) throws IOException {
        URL url = new URL (PublicURLS.Address.addressRead().set("companyId", this.id).set("addressId", addressId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get the address result for a particular customer
     * @param customerId Customer Id
     * @return Resource
     * @throws IOException
     */
    public Resource customerAddress(String customerId) throws IOException {
        URL url = new URL(PublicURLS.Address.customerAddress().set("companyId", this.id).set("customerId", customerId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get a list of possible addresses from a postcode
     * @param postcode Postcode
     * @return Resource
     * @throws IOException
     */
    public Resource postCodeAddress(String postcode) throws IOException {
        URL url = new URL(PublicURLS.Address.postCodeAddress().set("companyId", this.id).set("postcode", postcode).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Get all Products for a Company
     * @return Resource
     * @throws IOException
     */
    public Resource productsList() throws IOException {
        URL url = new URL(PublicURLS.Products.productsList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get a specific Product
     * @param productId Product Id
     * @return Resource
     * @throws IOException
     */
    public Resource productsRead(String productId) throws IOException {
        URL url = new URL (PublicURLS.Products.productRead().set("companyId", this.id).set("productId", productId).expand());
        return new Resource(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * Slot List
     * @return Resource
     * @throws IOException
     */
    public Resource slotList() throws IOException {
        URL url = new URL(PublicURLS.Slot.slotList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Slot Read
     * @param slotId Slot Id
     * @return Resource
     * @throws IOException
     */
    public Resource slotRead(String slotId) throws IOException {
        URL url = new URL(PublicURLS.Slot.slotRead().set("companyId", this.id).set("slotId", slotId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get a list of images attached to an event group.
     * @param eventGroupId Event Group Id
     * @return Resource
     * @throws IOException
     */
    public Resource eventGroupImagesList(String eventGroupId) throws IOException {
        URL url = new URL(PublicURLS.EventGroupImages.eventGroupImagesList().set("companyId", this.id).set("eventGroupId", eventGroupId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * TODO: ???
     * @param eventGroupId Event Group Id
     * @param id Id
     * @return
     * @throws IOException
     */
    public Resource eventGroupImages_f(String eventGroupId, String id) throws IOException {
        URL url = new URL(PublicURLS.EventGroupImages.eventGroupImages_f().set("companyId", this.id).set("eventGroupId", eventGroupId).set("id", id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get all the deals for a Company
     * @return Resource
     * @throws IOException
     */
    public Resource dealList() throws IOException {
        URL url = new URL(PublicURLS.Deal.dealList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get A Deal
     * @param dealId Deal Id
     * @return Resource
     * @throws IOException
     */
    public Resource dealRead(String dealId) throws IOException {
        URL url = new URL(PublicURLS.Deal.dealRead().set("companyId", this.id).set("dealId", dealId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get all the service groups for a Company
     * @return Resource
     * @throws IOException
     */
    public Resource serviceGroupList() throws IOException {
        URL url = new URL(PublicURLS.ServiceGroup.serviceGroupList().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get a Service Group
     * @param groupId Group Id
     * @return Resource
     * @throws IOException
     */
    public Resource serviceGroupRead(String groupId) throws IOException {
        URL url = new URL(PublicURLS.ServiceGroup.serviceGroupRead().set("companyId", this.id).set("groupId", groupId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Get Contents and Details of the Current Cached Basket
     * @return Resource
     * @throws IOException
     */
    public Resource readBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.readBasket().set("companyId", this.id).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }

/*
    public BBRoot getBasket() throws IOException {
        String link = rep.getLinkByRel("basket").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        HttpServiceResponse response = HttpService.api_GET(url);
        return new BBRoot(response.getRep(), auth_token);
    }
*/

    /**
     * Add Shopping Item To Basket
     * @return Resource
     * @throws IOException
     */
    public Resource createBasketItem() throws IOException {
        URL url = new URL(PublicURLS.Basket.createBasketItem().set("companyId", this.id).expand());
        return new Resource(HttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Confirm and Pay for the Items in the Shopping Basket
     * @return Resource
     * @throws IOException
     */
    public Resource checkoutBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.checkoutBasket().set("companyId", this.id).expand());
        return new Resource(HttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Empty the Shopping Basket
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasket() throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasket().set("companyId", this.id).expand());
        this.id = "";
        return new Resource(HttpService.api_DELETE(url), auth_token);
    }


    /**
     * Get the Details of a Specific Item in the Shopping Basket
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource readBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.readBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Remove an Item for the Shopping Basket
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(HttpService.api_DELETE(url), auth_token);
    }


    /**
     * Add a new file attachment to a basket item
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource addFileAttachmentToBasketItem(String basketItemId) throws IOException {
        URL url = new URL(PublicURLS.Basket.addFileAttachmentToBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(HttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Update a file attachment for a basket item
     * @param basketItemId Basket Item Id
     * @return Resource
     * @throws IOException
     */
    public Resource updateFileAttachmentToBasketItem(String basketItemId, Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.updateFileAttachmentToBasketItem().set("companyId", this.id).set("basketItemId", basketItemId).expand());
        return new Resource(HttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Get a file attachment for a basket
     * @param basketId Basket Id
     * @param attachmentId Attachment Id
     * @return
     * @throws IOException
     */
    public Resource getFileAttachmentForBasketItem(String basketId, String attachmentId) throws IOException {
        URL url = new URL(PublicURLS.Basket.getFileAttachmentForBasketItem().set("companyId", this.id).set("basketId", basketId).set("attachmentId", attachmentId).expand());
        return new Resource(HttpService.api_GET(url), auth_token);
    }


    /**
     * Attempt to apply a deal to a basket
     * @return Resource
     * @throws IOException
     */
    public Resource applyDeal() throws IOException {
        URL url = new URL(PublicURLS.Basket.applyDeal().set("companyId", this.id).expand());
        return new Resource(HttpService.api_POST(url, auth_token), auth_token);
    }


    /**
     * Delete a coupon from a basket
     * @return Resource
     * @throws IOException
     */
    public Resource removeDeal(Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.removeDeal().set("companyId", this.id).expand());
        return new Resource(HttpService.api_PUT(url, params, auth_token), auth_token);
    }


    /**
     * Attempt to apply a discount coupon to a basket
     * @return Resource
     * @throws IOException
     */
    public Resource applyBasketCoupon(Map<String,String> params) throws IOException {
        URL url = new URL(PublicURLS.Basket.applyBasketCoupon().set("companyId", this.id).expand());
        return new Resource(HttpService.api_POST(url, params, auth_token), auth_token);
    }


    /**
     * Delete a coupon from a basket
     * @return Resource
     * @throws IOException
     */
    public Resource deleteBasketCoupon() throws IOException {
        URL url = new URL(PublicURLS.Basket.deleteBasketCoupon().set("companyId", this.id).expand());
        return new Resource(HttpService.api_DELETE(url), auth_token);
    }


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

    public BBRoot createClient(Map<String, String> data) throws HttpException, MalformedURLException {
        String link = response.getRep().getLinkByRel("client").getHref();
        URL url = new URL (link);
        return new BBRoot(HttpService.api_POST(url, data, auth_token), auth_token);
    }

    public BBRoot createPerson(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personCreate().set("companyId", get("id")).expand();
        URL url = new URL (uri);
        return new BBRoot(HttpService.api_POST(url, data, auth_token), auth_token);
    }

    public BBRoot updatePerson(People person, Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personUpdate().set("companyId", get("id")).set("personId", person.get("id")).expand();
        URL url = new URL (uri);
        return new BBRoot(HttpService.api_PUT(url, HttpService.jsonContentType, data, auth_token), auth_token);
    }

    public Administrator getAdministrators() throws IOException {
        if(administratorList == null) {
            String link = response.getRep().getLinkByRel("administrators").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            HttpServiceResponse response = HttpService.api_GET(url, auth_token);
            administratorList = new Administrator(response, auth_token);
        }
        return administratorList;
    }

    public BBRoot getAdministratorSchema() throws IOException {
        if(administratorSchema == null) {
            String link = response.getRep().getLinkByRel("new_administrator").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            HttpServiceResponse response = HttpService.api_GET(url, auth_token);
            administratorSchema = new BBRoot(response, auth_token);
        }
        return administratorSchema;
    }

    public Administrator createAdministrator(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Company.administratorCreate().set("companyId", get("id")).expand();
        URL url = new URL (uri);
        return new Administrator(HttpService.api_POST(url, data, auth_token), auth_token);
    }

    public BBRoot updatePerson(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Person.personCreate().set("companyId", get("id")).expand();
        URL url = new URL (uri);
        return new BBRoot(HttpService.api_POST(url, data, auth_token), auth_token);
    }

}
