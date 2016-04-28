package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;


public class Event extends BBRoot{

    public Event(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Event(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Event() {}

    public SchemaForm getNewBookingSchema() throws IOException {
        if(getLink("new_booking") != null) {
            String link = getLink("new_booking");
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            return new SchemaForm(HttpService.api_GET(url, this.auth_token));
        }
        // Throw exception: link is missing
        throw new IOException("new_booking link missing");
    }

}
