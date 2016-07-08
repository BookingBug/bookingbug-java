package bookingbugAPI.models;

import bookingbugAPI.services.Http.PlainHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;
import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URL;

public class Event extends BBRoot {

    private int integerDefaultValue = 0;
    private boolean booleanDefaultValue = false;

    public Event(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public Event(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Event() {
    }

    public SchemaForm getNewBookingSchema() throws IOException {
        if (getLink("new_booking") != null) {
            String link = getLink("new_booking");
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            return new SchemaForm(PlainHttpService.api_GET(url, this.auth_token));
        }
        // Throw exception: link is missing
        throw new IOException("new_booking link missing");
    }

    /**
     * Returns the Event id.
     *
     * @return The id associated with the current Event object.
     */
    public Integer getId() {
        return getInteger("id", integerDefaultValue);
    }

    /**
     * Returns the Event date and time in {@link DateTime DateTime()} format.
     *
     * @return The date associated with the current Event object.
     */
    public DateTime getDatetime() {
        return getDate("datetime");
    }

    /**
     * Returns the Event description.
     *
     * @return The description associated with the current Event object.
     */
    public String getDescription() {
        return get("description");
    }

    /**
     * Returns the Event status.
     *
     * @return The status associated with the current Event object.
     */
    public Integer getStatus() {
        return getInteger("status", integerDefaultValue);
    }

    /**
     * Returns the Event spaces booked.
     *
     * @return The spaces booked associated with the current Event object.
     */
    public Integer getSpacesBooked() {
        return getInteger("spaces_booked", integerDefaultValue);
    }

    /**
     * Returns the Event spaces reserved.
     *
     * @return The spaces reserved associated with the current Event object.
     */
    public Integer getSpacesReserved() {
        return getInteger("spaces_reserved", integerDefaultValue);
    }

    /**
     * Returns the Event spaces blocked.
     *
     * @return The spaces blocked associated with the current Event object.
     */
    public Integer getSpacesBlocked() {
        return getInteger("spaces_blocked", integerDefaultValue);
    }

    /**
     * Returns the Event spaces held.
     *
     * @return The spaces held associated with the current Event object.
     */
    public Integer getSpacesHeld() {
        return getInteger("spaces_held", integerDefaultValue);
    }

    /**
     * Returns the Event number of spaces.
     *
     * @return The number of spaces associated with the current Event object.
     */
    public Integer getNumSpaces() {
        return getInteger("num_spaces", integerDefaultValue);
    }

    /**
     * Returns the Event wait spaces.
     *
     * @return The wait spaces associated with the current Event object.
     */
    public Integer getSpacesWait() {
        return getInteger("spaces_wait", integerDefaultValue);
    }

    /**
     * Returns the Event event chain id.
     *
     * @return The event chain id associated with the current Event object.
     */
    public Integer getEventChainId() {
        return getInteger("event_chain_id", integerDefaultValue);
    }

    /**
     * Returns the Event service id.
     *
     * @return The service id associated with the current Event object.
     */
    public Integer getServiceId() {
        return getInteger("service_id", integerDefaultValue);
    }

    /**
     * Returns the Event duration with unit provided by {@link #getDurationUnits() getDurationUnits()}
     *
     * @return The duration associated with the current Event object.
     */
    public Integer getDuration() {
        return getInteger("duration", integerDefaultValue);
    }

    /**
     * Returns the event duration size unit.
     *
     * @return The duration size unit associated with the current Event object.
     */
    public String getDurationUnits() {
        return get("units");
    }

    /**
     * Returns the Event price.
     *
     * @return The price associated with the current Event object.
     */
    public Integer getPrice() {
        return getInteger("price", integerDefaultValue);
    }

    /**
     * Returns the Event event groups link.
     *
     * @return The event groups link associated with the current Event object.
     */
    public String getEventGroupsLink() {
        return getLink("event_groups");
    }

    /**
     * Returns the Event event chains link.
     *
     * @return The event chains link associated with the current Event object.
     */
    public String getEventChainsLink() {
        return getLink("event_chains");
    }

    /**
     * Returns the Event book link.
     *
     * @return The book link associated with the current Event object.
     */
    public String getBookLink() {
        return getLink("book");
    }
}
