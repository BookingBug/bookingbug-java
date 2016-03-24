package bookingbugAPI.models;

import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.models.params.Params;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;
import helpers.Utils;
import rx.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;


public class EventChain extends BBRoot{


    public EventChain(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public EventChain(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public EventChain() {}

    /**
     * Get a List of Bookable Events for an EventChain.
     * @param params Parameters for pagination
     * @return BBCollection<Event>
     * @throws IOException
     */
    public BBCollection<Event> eventList(Params params) throws IOException {
        UriTemplate template = Utils.TemplateWithPagination(
                Utils.paginatedUriTemplate(getLink("events")),
                params);
        URL url = new URL(template.expand());
        return new BBCollection<Event>(HttpService.api_GET(url, auth_token), auth_token, "events", Event.class);
    }

    public Observable<BBCollection<Event>> eventListObs(final Params params){
        return Observable.fromCallable(new Callable<BBCollection<Event>>() {
            @Override
            public BBCollection<Event> call() throws Exception {
                return eventList(params);
            }
        });
    }
}
