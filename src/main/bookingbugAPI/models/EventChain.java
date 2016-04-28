package bookingbugAPI.models;

import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.models.params.EventListParams;
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

    public SchemaForm getNewBookingSchema() throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(this.getRep().getLinkByRel("new_booking").getHref()).expand());
        return new SchemaForm(HttpService.api_GET(url, this.auth_token));
    }

    /**
     * Get a List of Bookable Events for an EventChain.
     * @param params Parameters for pagination
     * @return BBCollection<Event>
     * @throws IOException
     */
    public BBCollection<Event> eventList(EventListParams params) throws IOException {
        UriTemplate template;
        if(getLink("events") != null) {
            template = Utils.TemplateWithPagination(
                    Utils.paginatedUriTemplate(getLink("events")),
                    params);
        } else {
            params.setEvent_chain_id(this.id);
            template = PublicURLS.Event.eventList().set("companyId", get("company_id"));
        }

        URL url = new URL(template.expand(params.getParamsMapObj()));
        return new BBCollection<Event>(HttpService.api_GET(url, auth_token), auth_token, "events", Event.class);
    }

    public Observable<BBCollection<Event>> eventListObs(final EventListParams params){
        return Observable.fromCallable(new Callable<BBCollection<Event>>() {
            @Override
            public BBCollection<Event> call() throws Exception {
                return eventList(params);
            }
        });
    }
}
