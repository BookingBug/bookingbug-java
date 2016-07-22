package bookingbugAPI.api;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import helpers.Config;


public class AdminURLS {

    public static class Company {

        public static UriTemplate companyRead() {
            return companyRead(new Config().serverUrl);
        }

        public static UriTemplate companyRead(String serverUrl) {
            return UriTemplate.buildFromTemplate(serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/company")
                    .build();
        }

        public static UriTemplate companyConfigRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/company_configuration")
                    .build();
        }
/*
        public static UriTemplate administratorCreate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .build();
        }
*/
    }

    public static class Address {
        public static UriTemplate addressList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/addresses")
                    .build();
        }

        public static UriTemplate addressRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/addresses")
                    .path(UriTemplateBuilder.var("addressId"))
                    .build();
        }
    }


    public static class Service {
        public static UriTemplate serviceNew(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/services/new")
                    .build();
        }

        public static UriTemplate serviceEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/services")
                    .path(UriTemplateBuilder.var("serviceId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate serviceList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/services")
                    .query(UriTemplateBuilder.var("page"), UriTemplateBuilder.var("per_page"))
                    .build();
        }

        public static UriTemplate serviceRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/services")
                    .path(UriTemplateBuilder.var("serviceId"))
                    .build();
        }

        public static UriTemplate serviceNewBooking() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/services")
                    .path(UriTemplateBuilder.var("serviceId"))
                    .literal("/new_booking")
                    .build();
        }
    }


    public static class Session {
        public static UriTemplate sessionList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/sessions")
                    .build();
        }

        public static UriTemplate sessionRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/sessions")
                    .path(UriTemplateBuilder.var("sessionId"))
                    .build();
        }
    }


    public static class Person {
        public static UriTemplate personList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .build();
        }

        public static UriTemplate personGetSchema(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate personRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .path(UriTemplateBuilder.var("personId"))
                    .build();
        }

        public static UriTemplate personCreate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .build();
        }

        public static UriTemplate personUpdate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .path(UriTemplateBuilder.var("personId"))
                    .build();
        }

        public static UriTemplate personDelete(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/people")
                    .path(UriTemplateBuilder.var("personId"))
                    .build();
        }
    }


    public static class Question {
        public static UriTemplate questionList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/questions")
                    .build();
        }
    }


    public static class Resource {
        public static UriTemplate resourceNew(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/resources")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate resourceEdit(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/resources")
                    .path(UriTemplateBuilder.var("resourceId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate resourceList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/resources")
                    .build();
        }

        public static UriTemplate resourceRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/resources")
                    .path(UriTemplateBuilder.var("resourceId"))
                    .build();
        }
    }


    public static class Slot {
        public static UriTemplate slotList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/slots")
                    .build();
        }

        public static UriTemplate slotRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/slots")
                    .path(UriTemplateBuilder.var("slotId"))
                    .build();
        }
    }


    public static class Bookings {
        public static UriTemplate bookingNew(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate bookingEdit(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("id"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate bookingList(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .query(UriTemplateBuilder.var("page"), UriTemplateBuilder.var("per_page"))
                    .build();
        }

        public static UriTemplate bookingRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("bookingId"))
                    .build();
        }

        public static UriTemplate bookingCreate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .build();
        }

        public static UriTemplate bookingUpdate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("id"))
                    .build();
        }

        public static UriTemplate bookingCancel(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("id"))
                    .build();
        }

        public static UriTemplate bookingCheckIn(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("id"))
                    .literal("/check_in")
                    .build();
        }

        public static UriTemplate bookingMarkStatus(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("id"))
                    .literal("/multi_status")
                    .build();
        }

        public static UriTemplate bookingAddPrivateNote(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("booking_id"))
                    .literal("/private_notes")
                    .build();
        }

        public static UriTemplate bookingUpdatePrivateNote(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("booking_id"))
                    .literal("/private_notes")
                    .path(UriTemplateBuilder.var("id"))
                    .build();
        }

        public static UriTemplate bookingDeletePrivateNote(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("booking_id"))
                    .literal("/private_notes")
                    .path(UriTemplateBuilder.var("id"))
                    .build();
        }

        public static UriTemplate bookingAnswerQuestion(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/bookings")
                    .path(UriTemplateBuilder.var("booking_id"))
                    .literal("/answers")
                    .build();
        }
    }


    public static class Client {
        public static UriTemplate clientList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/client")
                    .build();
        }

        public static UriTemplate clientCreate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/client")
                    .build();
        }

        public static UriTemplate clientRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/client")
                    .path(UriTemplateBuilder.var("clientId"))
                    .build();
        }

        public static UriTemplate clientReadUsingRefId() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/client")
                    .literal("/find_by_ref")
                    .path(UriTemplateBuilder.var("referenceId"))
                    .build();
        }

        public static UriTemplate clientReadUsingEmail() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/client")
                    .literal("/find_by_email")
                    .path(UriTemplateBuilder.var("email"))
                    .build();
        }
    }


    public static class User {
        public static UriTemplate userRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/user")
                    .path(UriTemplateBuilder.var("userId"))
                    .build();
        }
    }


    public static class Purchase {
        public static UriTemplate purchaseList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/purchases")
                    .build();
        }

        public static UriTemplate purchaseRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/purchases")
                    .path(UriTemplateBuilder.var("purchaseId"))
                    .build();
        }
    }


    public static class Deal {
        public static UriTemplate dealList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/deals")
                    .build();
        }

        public static UriTemplate dealReadByRef() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/deals")
                    .literal("/ref")
                    .path(UriTemplateBuilder.var("referenceId"))
                    .build();
        }

        public static UriTemplate dealCodes() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/deals")
                    .path(UriTemplateBuilder.var("dealId"))
                    .literal("/codes")
                    .build();
        }
    }


    public static class Coupons {
        public static UriTemplate couponList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/coupons")
                    .build();
        }
    }


    public static class Administrator {
        public static UriTemplate administratorCreate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .build();
        }

        public static UriTemplate administratorNew() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate administratorEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .path(UriTemplateBuilder.var("adminId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate administratorList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .build();
        }

        public static UriTemplate administratorRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/administrators")
                    .path(UriTemplateBuilder.var("adminId"))
                    .build();
        }
    }


    public static class Schedule {
        public static UriTemplate scheduleNew() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate scheduleEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .path(UriTemplateBuilder.var("scheduleId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate scheduleList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .build();
        }

        public static UriTemplate scheduleRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .path(UriTemplateBuilder.var("scheduleId"))
                    .build();
        }

        public static UriTemplate scheduleDelete(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .path(UriTemplateBuilder.var("scheduleId"))
                    .build();
        }

        public static UriTemplate scheduleUpdate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/schedules")
                    .path(UriTemplateBuilder.var("scheduleId"))
                    .build();
        }
    }


    public static class EventChain {
        public static UriTemplate eventChainNew() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate eventChainEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .path(UriTemplateBuilder.var("eventChainId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate eventChainList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .build();
        }

        public static UriTemplate eventChainRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .path(UriTemplateBuilder.var("eventChainId"))
                    .build();
        }

        public static UriTemplate eventChainReadUsingRefId() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .literal("/find_by_ref")
                    .path(UriTemplateBuilder.var("refId"))
                    .build();
        }

        public static UriTemplate eventChainEventsList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_chains")
                    .path(UriTemplateBuilder.var("eventChainId"))
                    .literal("/events")
                    .build();
        }
    }


    public static class EventGroup {
        public static UriTemplate eventGroupNew() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_groups")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate eventGroupEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_groups")
                    .path(UriTemplateBuilder.var("eventGroupId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate eventGroupList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_groups")
                    .build();
        }

        public static UriTemplate eventGroupRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/event_groups")
                    .path(UriTemplateBuilder.var("eventGroupId"))
                    .build();
        }
    }


    public static class Calendar {
        public static UriTemplate calendarData() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/cal")
                    .path(UriTemplateBuilder.var("id"))
                    .build();
        }
    }


    public static class BookableItem {
        public static UriTemplate bookableItemList() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/items")
                    .build();
        }
    }


    public static class Event {
        public static UriTemplate eventNew() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/events")
                    .literal("/new")
                    .build();
        }

        public static UriTemplate eventEdit() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/events")
                    .path(UriTemplateBuilder.var("eventId"))
                    .literal("/edit")
                    .build();
        }

        public static UriTemplate eventList(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/events")
                    .build();
        }

        public static UriTemplate eventRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl)
                    .literal("/admin")
                    .path(UriTemplateBuilder.var("companyId"))
                    .literal("/events")
                    .path(UriTemplateBuilder.var("eventId"))
                    .build();
        }
    }
}
