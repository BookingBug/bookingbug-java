package bookingbugAPI.api;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import helpers.Config;


public class AdminURLS {

    public static class Administrator {
        public static UriTemplate administratorCreate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/administrators").build();
        }

        public static UriTemplate administratorUpdate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/administrators").path(UriTemplateBuilder.var("id")).build();
        }
    }


    public static class Company {
        public static UriTemplate companyList() {
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").literal("/company").path(UriTemplateBuilder.var("companyId")).build();
        }

        public static UriTemplate administratorCreate() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/administrators").build();
        }
    }


    public static class Person {
        public static UriTemplate personList(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").build();
        }

        public static UriTemplate personGetSchema(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").literal("/new").build();
        }

        public static UriTemplate personRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").path(UriTemplateBuilder.var("personId")).build();
        }

        public static UriTemplate personCreate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").build();
        }

        public static UriTemplate personUpdate(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").path(UriTemplateBuilder.var("personId")).build();
        }

        public static UriTemplate personDelete(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/people").path(UriTemplateBuilder.var("personId")).build();
        }
    }


    public static class Service {
        public static UriTemplate serviceList(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/services").build();
        }

        public static UriTemplate serviceRead() {
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/services").path(UriTemplateBuilder.var("serviceId")).build();
        }
    }


    public static class Resource {
        public static UriTemplate resourceList(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/resources").build();
        }

        public static UriTemplate resourceRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/resources").path(UriTemplateBuilder.var("resourceId")).build();
        }
    }


    public static class Event {
        public static UriTemplate eventList(){
            //use companyId instead of company_id due to execution error: named capturing group is missing trailing > near (BUG IN DAMNHANDY)
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/events").build();
        }

        public static UriTemplate eventRead(){
            return UriTemplate.buildFromTemplate(new Config().serverUrl).literal("/admin").path(UriTemplateBuilder.var("companyId")).literal("/events").path(UriTemplateBuilder.var("eventId")).build();
        }
    }
}
