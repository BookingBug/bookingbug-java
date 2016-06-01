package bookingbugAPI.models;

import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.models.params.EventListParams;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;
import helpers.Utils;
import org.joda.time.DateTime;
import rx.Observable;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;


public class EventChain extends BBRoot {

    public EventChain(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public EventChain(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public EventChain() {
    }

    public SchemaForm getNewBookingSchema() throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(this.getRep().getLinkByRel("new_booking").getHref()).expand());
        return new SchemaForm(HttpService.api_GET(url, this.auth_token));
    }

    /**
     * Get a List of Bookable Events for an EventChain.
     *
     * @param params Parameters for pagination
     * @return BBCollection<Event>
     * @throws IOException
     */
    public BBCollection<Event> eventList(EventListParams params) throws IOException {
        UriTemplate template;
        if (getLink("events") != null) {
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

    public Observable<BBCollection<Event>> eventListObs(final EventListParams params) {
        return Observable.fromCallable(new Callable<BBCollection<Event>>() {
            @Override
            public BBCollection<Event> call() throws Exception {
                return eventList(params);
            }
        });
    }

    /**
     * Returns the id.
     *
     * @return The id associated with the current EventChain object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the name.
     *
     * @return The name associated with the current EventChain object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the description.
     *
     * @return The description associated with the current EventChain object
     */
    public String getDescription() {
        return get("description");
    }

    /**
     * Returns the duration expressed in minutes.
     *
     * @return The duration associated with the current EventChain object
     */
    public Integer getDuration() {
        return getInteger("duration", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the event group.
     *
     * @return The group associated with the current EventChain object
     */
    public String getGroup() {
        return get("group");
    }

    /**
     * Returns the date and time, with {@link DateTime DateTime()} as format.
     *
     * @return The date and time associated with the current EventChain object
     */
    public DateTime getTime() {
        return getDate("time");
    }

    /**
     * Returns the long description of the event.
     *
     * @return The long description associated with the current EventChain object
     */
    public String getLongDescription() {
        return get("long_description");
    }

    /**
     * Returns the capacity view.
     *
     * @return The capacity view associated with the current EventChain object
     */
    public Integer getCapacityView() {
        return getInteger("capacity_view", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the event start date and time, with {@link DateTime DateTime()} as format.
     *
     * @return The start date and time associated with the current EventChain object
     */
    public DateTime getStartDate() {
        return getDate("start_date");
    }

    /**
     * Returns the event end date and time.
     *
     * @return The end date and time associated with the current EventChain object
     */
    public DateTime getEndDate() {
        return getDate("end_date");
    }

    /**
     * Returns the event spaces.
     *
     * @return The spaces associated with the current EventChain object
     */
    public Integer getSpaces() {
        return getInteger("spaces", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the event price.
     *
     * @return The price associated with the current EventChain object
     */
    public Integer getPrice() {
        return getInteger("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the maximum number of bookings.
     *
     * @return The maximum number of bookings associated with the current EventChain object
     */
    public Integer getMaxNumBookings() {
        return getInteger("max_num_bookings", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the date and time of the minimum advance time, with {@link DateTime DateTime()} as format.
     *
     * @return The date and time of the minimum advance time associated with the current EventChain object
     */
    public DateTime getMinAdvanceTime() {
        return getDate("min_advance_time");
    }

    /**
     * Returns the event ticket space.
     *
     * @return The ticket space associated with the current EventChain object
     */
    public String getTicketType() {
        return get("ticket_type");
    }

    /**
     * Returns the email per ticket attribute.
     *
     * @return The email per ticket attribute associated with the current EventChain object
     */
    public Boolean getEmailPerTicket() {
        return getBoolean("email_per_ticket", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question per ticket attribute.
     *
     * @return The question per ticket attribute associated with the current EventChain object
     */
    public Boolean getQuestionPerTicket() {
        return getBoolean("questions_per_ticket", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the event course.
     *
     * @return The course associated with the current EventChain object
     */
    public Boolean getCourse() {
        return getBoolean("course", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the questions link.
     *
     * @return The question link associated with the current EventChain object
     */
    public String getQuestionsLink() {
        return getLink("questions");
    }

    /**
     * Returns the child event chains.
     *
     * @return The child event chains assoiciated with the currnet EventChain object
     */
    public BBCollection<EventChain> getChildEventChains() {
        return new BBCollection<>(new HttpServiceResponse(getResource("child_event_chains")), auth_token, EventChain.class);
    }

}
