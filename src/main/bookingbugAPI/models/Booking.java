package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;


public class Booking extends BBRoot {


    public Booking(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public BBRoot getSchema() throws IOException {
        String link = getRep().getLinkByRel("edit").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new BBRoot(HttpService.api_GET(url, auth_token));
    }


    public String getFirstName() {
        return get("first_name");
    }

    public String getLastName() {
        return get("last_name");
    }

    public String getEmail() {
        return get("email");
    }

    public String getAddress1() {
        return get("address1");
    }

    public String getAddress2() {
        return get("address2");
    }

    public String getAddress3() {
        return get("address3");
    }

    public String getAddress4() {
        return get("address4");
    }

    public String getPostCode() {
        return get("postcode");
    }
}
