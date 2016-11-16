package bookingbugAPI2.api;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import helpers2.Config;


//@SuppressWarnings("unused") //use it just to highlight TODOs - comment it back afterwards
public class PublicURLS {

    public static class Company {

        public static String businessQuestionsLink = "company_questions";

        //TODO: replace it everywhere with Details.companyDetails() or vice versa. // check with sebi & team
//        static UriTemplate companyList() {
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            //return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/company").path(UriTemplateBuilder.var("companyId")).build();
//            return Details.companyDetails();
//        }

        /**
         * <B>GET</B><BR>
         * Get all possible Space Statuses for a Company.
         * @return UriTemplate
         */
        public static UriTemplate spaceStatusList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/space_statuses").build();
        }

        /**
         * <B>GET</B><BR>
         * Loads all of the public settings for a company, this allows you to configure a booking widget,
         * and shows all of the details need to book and show an approriate widget.
         * @return UriTemplate
         */
        public static UriTemplate settingsDetails() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/settings").build();
        }

        /**
         * <B>GET</B><BR>
         * You can either get all the company questions or pass a param to specifiy that you only want company questions
         * that apply to either a service, resource, person, company:
         * <BR>Service = 1, Resource = 2, Person = 3, Company = 4.
         * @return UriTemplate
         */
        public static UriTemplate businessQuestions() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + businessQuestionsLink).build();
        }
    }



    public static class Service {

        public static String servicesLink = "services";

        /**
         * <B>GET</B><BR>
         * Get All Bookable Services.
         * <BR><BR>Results are returned as a paginated list duration_unit may be minute, day, or week.
         * @return UriTemplate
         */
        public static UriTemplate serviceList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + servicesLink)
                    .query(UriTemplateBuilder.var("page"), UriTemplateBuilder.var("per_page"))
                    .build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Service.
         * <BR>duration_unit may be minute, day, or week.
         * @return UriTemplate
         */
        public static UriTemplate serviceRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + servicesLink).path("serviceId").build();
        }
    }



    public static class Person {

        public static String peopleLink = "people";

        /**
         * <B>GET</B><BR>
         * Get All Bookable People for a Company.
         * <BR><BR>Results are returned as a paginated list.
         * @return UriTemplate
         */
        public static UriTemplate personList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + peopleLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Bookable Person’s Details.
         * @return UriTemplate
         */
        public static UriTemplate personRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + peopleLink).path("personId").build();
        }


        /**
         * <B>GET</B><BR>
         * Get a Specific Person Details using a Reference ID.
         * <BR>If you create or update clients with a custom ref_id you can then use this id to get the client.
         * @return UriTemplate
         */
        public static UriTemplate personReadUsingReferenceId(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + peopleLink + "/find_by_ref").path("ref").build();
        }
    }



    public static class Resource {

        public static String resourcesLink = "resources";

        /**
         * <B>GET</B><BR>
         * Get All Bookable Resources.
         * <BR><BR>Results are returned as a paginated list.
         * @return UriTemplate
         */
        public static UriTemplate resourceList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + resourcesLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Bookable Resource.
         * @return UriTemplate
         */
        public static UriTemplate resourceRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + resourcesLink).path("resourceId").build();
        }
    }



    public static class Details {

        public static String companyLink = "company";

        /**
         * <B>GET</B><BR>
         * Load All of the Links and Properties of a Company.
         * @return UriTemplate
         */
        public static UriTemplate companyDetails() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/" + companyLink)
                    .path(UriTemplateBuilder.var("companyId")).build();
        }
    }



    public static class Event {

        public static String eventsLink = "events";

        /**
         * <B>GET</B><BR>
         * Get a List of Bookable Events.
         * @return UriTemplate
         */
        public static UriTemplate eventList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + eventsLink)
                    .query("page", "per_page", "event_chain_id",
                            "start_date", "end_date", "resource_id", "person_id",
                            "event_group_id", "summary", "member_level_id")
                    .build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Event.
         * @return UriTemplate
         */
        public static UriTemplate eventRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path("companyId").literal("/" + eventsLink).path("eventId").build();
        }
    }



    public static class Bookable {
        /**
         * <B>GET</B><BR>
         * Get the Bookable Items Based on Another Item.
         * <BR>Given a single, or combintation of service, resource, person, category, calculate what sub-items are bookable.
         * @return UriTemplate
         */
        public static UriTemplate bookableItemsList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/items").build();
        }

        /**
         * <B>GET</B><BR>
         * Loads a list of bookable items for a particular date.
         * @return UriTemplate
         */
        public static UriTemplate bookableItemsByDate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/day").path("date").build();
        }

        /**
         * <B>GET</B><BR>
         * Get Data for a range of Days.
         * <BR>This function retreives a set of day date for a bookable item. Bookable items in BookingBug are a service with
         * either or both of a Resource and a Person. These are combined into a single EventID.
         * <BR><BR>These can retrevie data for either:
         * <BR>* A date range - by submitting a start date and end date.
         * <BR>* A Week of the year - Returns 7 days of data.
         * <BR>* A Calendar Month - Retuns a presentable calender month - i.e. from the begining of the week for 6 weeks.
         * <BR>* A Full Month - An exact month, i.e st through to 31st.
         * <BR>* A Year.
         * <BR>You can also pass in a week_start - which is used to determine when a week, or a month_cal starts.
         * @return UriTemplate
         */
        public static UriTemplate availabilityDaysForBookableItem() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/day_data").build();
        }

        /**
         * <B>GET</B><BR>
         * Get Data for availabile times for a day.
         * <BR>You can also pass in a week_start - which is used to determine when a week, or a month_cal starts.
         * @return UriTemplate
         */
        public static UriTemplate availabilityTimesForBookableItem() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/time_data")
                    .query("event_id", "service_id", "resource_id","resource_ids", "person_id", "group_id", "location",
                    "date", "end_date", "duration", "num_resources").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate availabilityCheck() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/check_available").path(UriTemplateBuilder.var("dateTime")).build();
        }

        /**
         * <B>GET</B><BR>
         * This table contains details about how a member should book. This includes:
         * <BR>* Which fields are required and optional, such as phone number and address.
         * <BR>* If social logins are allowed.
         * <BR>* If existing members can login.
         * <BR>* If you should offer them the ability to set a password when creating a new account.
         * <BR>* Any Members questions for thing company.
         * @return UriTemplate
         */
        public static UriTemplate memberBookingDetails() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/client_details").build();
        }
    }



    public static class EventGroup {

        public static String eventGroupsLink = "event_groups";

        /**
         * <B>GET</B><BR>
         * Get All Event Groups.
         * <BR><BR>Results are returned as a paginated list.
         * @return UriTemplate
         */
        public static UriTemplate eventGroupList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + eventGroupsLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Event Group.
         * @return UriTemplate
         */
        public static UriTemplate eventGroupRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + eventGroupsLink).path("serviceId").build();
        }
    }



    public static class EventChain {

        public static String eventChainsLink = "event_chains";

        /**
         * <B>GET</B><BR>
         * Get a List of Courses or Repeating Events for a Company.
         * @return UriTemplate
         */
        public static UriTemplate eventChainList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + eventChainsLink)
                    .query(UriTemplateBuilder.var("page"), UriTemplateBuilder.var("per_page"))
                    .build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Event Chain.
         * @return UriTemplate
         */
        public static UriTemplate eventChainRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + eventChainsLink).path("eventChainId").build();
        }
    }



    public static class BookingQuestion {

        public static String questionsLink = "questions";

        /**
         * <B>GET</B><BR>
         * Get All Questions for a Company.
         * <BR>SAMPLE CALL: http://localhost:9000/public/36690/questions?detail_group_id=1
         * <BR><BR>Results are returned as a group of question for a specific question group.
         * @return UriTemplate
         */
        public static UriTemplate bookingQuestionList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + questionsLink).literal("?detail_group_id=").reserved("detailGroupId").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingQuestionRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + questionsLink).path("questionId").build();
        }
    }



    public static class SurveyQuestion {
        /**
         * <B>GET</B><BR>
         * Get all Survey Questions for a Service.
         * <BR><BR>Results are returned as a group of questions for a specific question group.
         * @return UriTemplate
         */
        public static UriTemplate surveyQuestionList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .path(UriTemplateBuilder.var("detail_group_id")).literal("/survey_questions").build();
        }
    }



    public static class Category {

        public static String categoriesLink = "categories";

        /**
         * <B>GET</B><BR>
         * List of Categories for a Company.
         * @return UriTemplate
         */
        public static UriTemplate categoryList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + categoriesLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Load a Specific Category Details.
         * @return UriTemplate
         */
        public static UriTemplate categoryRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + categoriesLink).path("categoryId").build();
        }

        /**
         * <B>GET</B><BR>
         * List of Categories for a Company.
         * @return UriTemplate
         */
        public static UriTemplate categoryNamedList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/named_categories").build();
        }
    }



    public static class Client {

        public static String clientLink = "client";

        /**
         * <B>POST</B><BR>
         * Create a new client for a business.
         * @return UriTemplate
         */
        public static UriTemplate createClient(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + clientLink).build();
        }

        /**
         * <B>PUT</B><BR>
         * Update and existing for a business.
         * <BR>You must be logged in as this client to update their details via the public API.
         * @return UriTemplate
         */
        public static UriTemplate updateClient(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + clientLink).path("clientId").build();
        }

        /**
         * <B>GET</B><BR>
         * Search for a client by email against the company that you are currently logged in as.
         * @return UriTemplate
         */
        public static UriTemplate findByEmail() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + clientLink + "/find_by_email").path("email").build();
        }

        /**
         * <B>GET</B><BR>
         * Get all the child clients of a particular client.
         * @return UriTemplate
         */
        public static UriTemplate readChildClients() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + clientLink).path("clientId").literal("/child_clients").build();
        }
    }



    public static class BookingText {
        /**
         * <B>GET</B><BR>
         * Get All Custom Booking Text for a Company.
         * <BR>Custom Booking Text is extra business text that should be shown in certain curcumstances.
         * This can be before a booking is made for a specific service, person or resource, or afterwards in the confirmation page.
         * @return UriTemplate
         */
        public static UriTemplate getBookingText() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/booking_text").build();
        }
    }



    public static class Address {

        public static String addressesLink = "addresses";

        /**
         * <B>GET</B><BR>
         * Get All Addresses for a Company.
         * <BR>Provide a company id to retrieve a list of addresses associated with that company.
         * This is a list of company branch/store/resource addresses, not client addresses.
         * <BR><BR>Results are returned as a paginated list.
         * @return UriTemplate
         */
        public static UriTemplate addressList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + addressesLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Address.
         * <BR>Provide a company id and address id to retrieve the details and links of that branch/store/resource address.
         * @return UriTemplate
         */
        public static UriTemplate addressRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + addressesLink).path("addressId").build();
        }

        /**
         * <B>GET</B><BR>
         * Load customers full address on selection.
         * @return UriTemplate
         */
        public static UriTemplate customerAddress() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/company").path(UriTemplateBuilder.var("companyId")).
                    literal("/" + addressesLink + "/address").path(UriTemplateBuilder.var("customerId")).build();
        }

        /**
         * <B>GET</B><BR>
         * Load possible addresses based on a customer postcode.
         * @return UriTemplate
         */
        public static UriTemplate postCodeAddress() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/company").path(UriTemplateBuilder.var("companyId")).
                    literal("/" + addressesLink).path(UriTemplateBuilder.var("postcode")).build();
        }
    }



    public static class Products {

        public static String productsLink = "products";

        /**
         * <B>GET</B><BR>
         * Get all Products for a Company.
         * @return UriTemplate
         */
        public static UriTemplate productsList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + productsLink).build();
        }

        /**
         * <B>GET</B><BR>
         * Get a specific Product.
         * @return UriTemplate
         */
        public static UriTemplate productRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + productsLink).path("productId").build();
        }
    }



    public static class Slot {

        public static String slotsLink = "slots";

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate slotList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + slotsLink).build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate slotRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/" + slotsLink).path("slotId").build();
        }
    }



    public static class EventGroupImages {
        /**
         * <B>GET</B><BR>
         * Get a list of images attached to an event group.
         * @return UriTemplate
         */
        public static UriTemplate eventGroupImagesList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/media/event_group_images").path("eventGroupId").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate eventGroupImages_f() {
            //TODO: check method naming
            //TODO: NO DOCUMENTATION for what the last ID stands for
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/media/event_group_images").path("eventGroupId").path("id").build();
        }
    }



    public static class Deal {
        /**
         * <B>GET</B><BR>
         * Get all the deals for a Company.
         * @return UriTemplate
         */
        public static UriTemplate dealList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/deals").build();
        }

        /**
         * <B>GET</B><BR>
         * Get A Deal.
         * @return UriTemplate
         */
        public static UriTemplate dealRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/deals").path("dealId").build();
        }
    }



    public static class ServiceGroup {
        /**
         * <B>GET</B><BR>
         * Get all the service groups for a Company.
         * @return UriTemplate
         */
        public static UriTemplate serviceGroupList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/service_groups").build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Service Group.
         * @return UriTemplate
         */
        public static UriTemplate serviceGroupRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/service_groups").path("groupId").build();
        }
    }



    public static class Basket {
        /**
         * <B>GET</B><BR>
         * Get Contents and Details of the Current Cached Basket.
         * @return UriTemplate
         */
        public static UriTemplate readBasket(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").build();
        }

        /**
         * <B>POST</B><BR>
         * This function adds a new shopping basket item to the current cached shopping basket.
         * If a shopping basket does not already exist then a new one is created.
         * <BR>The shopping basket is tied to the active session and is valid only as the session is.
         * Where possible basket items are “held” meaning that that space or slot is reserved until you checkout.
         * Reserved items are only held for a number of minutes unless another message is sent to keep the held item active,
         * otherwise it is released back to allow another customer to book it.
         * <BR>A shopping basket is not actaully required - it is possible to checkout in a single message via the Admin API,
         * passing in all of the details required to book an item, but a shopping basket can be used to cache and handle multiple items,
         * as well as deal with complicted issues such as applying and validating coupons, packages and discounts.
         * @return UriTemplate
         */
        public static UriTemplate createBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/add_item").build();
        }

        /**
         * <B>POST</B><BR>
         * Confirm and Pay for the Items in the Shopping Basket.
         * @return UriTemplate
         */
        public static UriTemplate checkoutBasket(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/checkout").build();
        }

        /**
         * <B>DELETE</B><BR>
         * Empty the Shopping Basket.
         * @return UriTemplate
         */
        public static UriTemplate deleteBasket(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").build();
        }

        /**
         * <B>GET</B><BR>
         * Get the Details of a Specific Item in the Shopping Basket.
         * @return UriTemplate
         */
        public static UriTemplate readBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").path("basketItemId").build();
        }

        /**
         * <B>DELETE</B><BR>
         * Remove an Item for the Shopping Basket.
         * @return UriTemplate
         */
        public static UriTemplate deleteBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").build();
        }

        /**
         * <B>POST</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate addFileAttachmentToBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").path("basketItemId").literal("/attach").build();
        }

        /**
         * <B>PUT</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate updateFileAttachmentToBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").path("basketItemId").literal("/attach").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate getFileAttachmentForBasketItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket").path("basketId").literal("/attach").path("attachmentId").build();
        }

        /**
         * <B>POST</B><BR>
         * Attempt to apply a deal to a basket.
         * @return UriTemplate
         */
        public static UriTemplate applyDeal(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/deal").build();
        }

        /**
         * <B>PUT</B><BR>
         * Delete a coupon from a basket.
         * @return UriTemplate
         */
        public static UriTemplate removeDeal(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/deal").build();
        }

        /**
         * <B>POST</B><BR>
         * Attempt to apply a discount coupon to a basket.
         * @return UriTemplate
         */
        public static UriTemplate applyBasketCoupon(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/coupon").build();
        }

        /**
         * <B>DELETE</B><BR>
         * Delete a coupon from a basket.
         * @return UriTemplate
         */
        public static UriTemplate deleteBasketCoupon(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/basket/coupon").build();
        }
    }



    static class Session {
        /**
         * <B>GET</B><BR>
         * Get the status of the current authenticated session.
         * <BR>This could include a logged in client, and a basket full of items.
         * This is useful if your user reloads the browser page and you are not maintaining the full session on the client.
         * @return UriTemplate
         */
        static UriTemplate sessionStatus() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/status").build();
        }
    }



    public static class Purchase {
        /**
         * <B>GET</B><BR>
         * Get a Specific Purchase Total.
         * @return UriTemplate
         */
        public static UriTemplate purchaseReadTotal(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/purchase_totals").path("purchaseTotalId").build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Purchase Item.
         * @return UriTemplate
         */
        public static UriTemplate purchaseReadItem(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).path(UriTemplateBuilder.var("companyId"))
                    .literal("/purchase_items").path("purchaseItemId").build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Purchase.
         * @return UriTemplate
         */
        public static UriTemplate purchaseRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId").build();
        }

        /**
         * <B>GET</B><BR>
         * Get a Specific Purchase from a booking ref.
         * @return UriTemplate
         */
        public static UriTemplate purchaseFindByBookingRef() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases/booking_ref").path("bookingRefId").build();
        }

        /**
         * <B>PUT</B><BR>
         * Update a number of bookings in a Specific Purchase.
         * @return UriTemplate
         */
        public static UriTemplate purchaseUpdate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId").build();
        }

        /**
         * <B>PUT</B><BR>
         * Update a booking’s status from WAITLIST to BOOKING if there is space available and the booking was on the waitlist originally.
         * @return UriTemplate
         */
        public static UriTemplate bookWaitlistItem() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/book_waitlist_item").build();
        }

        /**
         * <B>DELETE</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate deleteBookingsForPurchase() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                .literal("/bookings").path("bookingId").build();
        }

        /**
         * <B>PUT</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingUpdate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/bookings").path("bookingId").build();
        }

        /**
         * <B>DELETE</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingCancel() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/bookings").path("bookingId").build();
        }

        /**
         * <B>POST</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingAttachementAdd() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/bookings").path("bookingId").literal("/attach").build();
        }

        /**
         * <B>PUT</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingAttachementAddOrUpdate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/bookings").path("bookingId").literal("/attach").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate bookingAttachementList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/bookings").path("bookingId").literal("/attach").build();
        }

        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        public static UriTemplate purchaseDealsList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/purchases").path("purchaseId")
                    .literal("/deals").build();
        }
    }



    static class Affiliates {
        /**
         * <B>GET</B><BR>
         * Get an Affiliate Company by cookie.
         * @return UriTemplate
         */
        static UriTemplate getAffiliateDetailsByCookie() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId").build();
        }

        /**
         * <B>GET</B><BR>
         * Load the company of an affiliate link by reference.
         * @return UriTemplate
         */
        static UriTemplate getAffiliateDetailsByReference() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                .literal("/links").path("reference").build();
        }

        /**
         * <B>GET</B><BR>
         * Load the answer to an affilate question for an affiliate child.
         * @return UriTemplate
         */
        static UriTemplate getAnswerToAffiliateQuestion() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                    .literal("/links").path("reference").literal("/links").path(UriTemplateBuilder.var("companyId")).build();
        }

        /**
         * <B>GET</B><BR>
         * Load the company of an affiliate link by reference.
         * @return UriTemplate
         */
        static UriTemplate getAffiliateDetailsByReference2() {
            //TODO: check method naming
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                    .literal("/companies").build();
        }

        /**
         * <B>GET</B><BR>
         * Load the affiliate companies extension data for an object.
         * @return UriTemplate
         */
        static UriTemplate getExtensionDataForObject() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                    .literal("/extensions").path("object").path("extensionId").build();
        }

        /**
         * <B>POST</B><BR>
         * Load the affiliate companies extension data for an object.
         * @return UriTemplate
         */
        static UriTemplate createNewExtensionObjectForAffiliate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                    .literal("/extensions").path("object").path("extensionId").build();
        }

        /**
         * <B>GET</B><BR>
         * Load the affiliate companies extension data for an object.
         * @return UriTemplate
         */
        static UriTemplate getExtensionDataForObject2() {
            //TODO: check method naming
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/affiliates").path("affiliateId")
                    .literal("/extensions").path("object").literal("/reference").path("reference").build();
        }
    }


    //TODO: fill in the names of the functions
    static class Audit {
        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate f1() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/audit").path("companyId").build();
        }

        /**
         * <B>DELETE</B><BR>
         *
         * @return UriTemplate
         */
        static UriTemplate f2() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/audit").path("companyId").build();
        }
    }


    //TODO: fill in the names of the functions
    static class Push {
        /**
         * <B>POST</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate f1() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/push").path("companyId").literal("/auth").build();
        }

        /**
         * <B>POST</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate f2() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/push").path("companyId").literal("/pusher").build();
        }

        /**
         * <B>POST</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate f3() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/push").path("companyId").literal("/ios").build();
        }

        /**
         * <B>DELETE</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate f4() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/push").path("companyId").literal("/ios").build();
        }
    }



    static class Recaptcha {
        /**
         * <B>POST</B><BR>
         * Does a Post Request to the Google Recaptcha Verify Url.
         * @return UriTemplate
         */
        static UriTemplate verifyRecaptcha(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/recaptcha").build();
        }
    }



    static class NewLogin {
        /**
         * <B>GET</B><BR>
         * N/A
         * @return UriTemplate
         */
        static UriTemplate newLogin(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/new_login").build();
        }
    }
}
