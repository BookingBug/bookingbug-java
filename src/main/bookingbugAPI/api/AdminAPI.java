package bookingbugAPI.api;

import bookingbugAPI.models.*;
import bookingbugAPI.services.HttpService;

import java.io.IOException;
import java.net.URL;


public class AdminAPI {

    static public class CompanyAPI extends AuthedAPI {

        public CompanyAPI(String token) {
            super(token);
        }

        public Company companyList(String companyId) throws IOException {
            String uri = AdminURLS.Company.companyList().set("companyId", companyId).expand();
            URL url = new URL(uri);

            return new Company(HttpService.api_GET(url, auth_token));
        }
    }

    static public class PersonAPI extends AuthedAPI {

        public PersonAPI(String token) {
            super(token);
        }

        public People personList(String companyId) throws IOException {
            String uri = AdminURLS.Person.personList().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_GET(url, auth_token));
        }

        public People personRead(String companyId, String personId) throws IOException {
            String uri = AdminURLS.Person.personRead().set("companyId", companyId).set("personId", personId).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_GET(url, auth_token));
        }

        public People personGetSchema(String companyId) throws IOException{
            String uri = AdminURLS.Person.personGetSchema().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_GET(url, auth_token));
        }

        public People personCreate(String companyId, People person) throws IOException{
            String uri = AdminURLS.Person.personCreate().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_POST(url, person.data, auth_token));
        }

        public People personUpdate(String companyId, People person) throws IOException{
            String uri = AdminURLS.Person.personUpdate().set("companyId", companyId).set("personId", person.id).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_PUT(url, person.data, auth_token));
        }

        public People personDelete(String companyId, String personId) throws IOException{
            String uri = AdminURLS.Person.personDelete().set("companyId", companyId).set("personId", personId).expand();
            URL url = new URL (uri);

            return new People(HttpService.api_DELETE(url, auth_token));
        }
    }

    static public class ServiceAPI extends AuthedAPI {

        public ServiceAPI(String token) {
            super(token);
        }

        public Service serviceList(String companyId) throws IOException {
            String uri = AdminURLS.Service.serviceList().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new Service(HttpService.api_GET(url, auth_token));
        }

        public Service serviceRead(String companyId, String serviceId) throws IOException {
            String uri = AdminURLS.Service.serviceRead().set("companyId", companyId).set("serviceId", serviceId).expand();
            URL url = new URL (uri);

            return new Service(HttpService.api_GET(url, auth_token));
        }
    }


    static public class ResourceAPI extends AuthedAPI {

        public ResourceAPI(String token) {
            super(token);
        }

        public Resource getResources(String companyId) throws IOException {
            String uri = AdminURLS.Resource.resourceList().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new Resource(HttpService.api_GET(url, auth_token));
        }

        public Resource getResource(String companyId, String resourceId) throws IOException {
            String uri = AdminURLS.Resource.resourceRead().set("companyId", companyId).set("resourceId", resourceId).expand();
            URL url = new URL (uri);

            return new Resource(HttpService.api_GET(url, auth_token));
        }
    }


    static public class EventAPI extends AuthedAPI{

        public EventAPI(String token) {
            super(token);
        }

        public Event eventList(String companyId) throws IOException {
            String uri = AdminURLS.Event.eventList().set("companyId", companyId).expand();
            URL url = new URL (uri);

            return new Event(HttpService.api_GET(url, auth_token));
        }

        public Event eventRead(String companyId, String eventId) throws IOException {
            String uri = AdminURLS.Event.eventRead().set("companyId", companyId).set("eventId", eventId).expand();
            URL url = new URL (uri);

            return new Event(HttpService.api_GET(url, auth_token));
        }
    }

}
