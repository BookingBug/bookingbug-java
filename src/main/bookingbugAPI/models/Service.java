package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import helpers.HttpServiceResponse;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;


public class Service extends BBRoot {

    private ArrayList<Integer> durations;
    private ArrayList<Integer> prices;

    public Service(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Service(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public Service() {
    }

    public SchemaForm getNewBookingSchema() throws IOException {
        if(getLink("new_booking") != null) {
            String link = getLink("new_booking");
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            return new SchemaForm(HttpService.api_GET(url, this.auth_token));
        }
        // Throw exception: link is missing
        throw new IOException("new_booking link missing");
    }

    public Service getService(Link link) throws IOException {
        String absUrl = Utils.absoluteURL(link.getHref());
        URL url = new URL(UriTemplate.fromTemplate(absUrl).expand());
        Service service = new Service(HttpService.api_GET(url, auth_token), auth_token);
        return service;
    }

    public ArrayList<Integer> getDurations() {
        if(durations == null) {
            durations = new ArrayList<>();
            Object duration_reps = getRep().getValue("durations");
            if(duration_reps instanceof Collection<?>) {
                for (Object rep : (Collection) duration_reps) {
                    durations.add(Integer.parseInt(rep.toString()));
                }
            }
        }
        return durations;
    }

    public ArrayList<Integer> getPrices() {
        if(prices == null) {
            prices = new ArrayList<>();
            Object prices_reps = getRep().getValue("prices");
            if(prices_reps instanceof Collection<?>) {
                for (Object rep : (Collection) prices_reps) {
                    prices.add(Integer.parseInt(rep.toString()));
                }
            }
        }
        return prices;
    }

}

