package bookingbugAPI.models;

import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.models.params.BookingCancelParams;
import bookingbugAPI.models.params.BookingUpdateParams;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.j256.ormlite.stmt.query.In;
import helpers.HttpServiceResponse;
import org.joda.time.DateTime;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;


public class Booking extends BBRoot {

    public Booking(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Booking(HttpServiceResponse response) {
        super(response);
    }

    public Booking() {
    }

    public BBRoot getSchema() throws IOException {
        String link = getRep().getLinkByRel("edit").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new BBRoot(HttpService.api_GET(url, auth_token));
    }

    /**
     * Returns a new Booking - update the current booking with provided params
     *
     * @param bParams
     * @throws IOException
     */
    public Booking bookingUpdate_Admin(BookingUpdateParams bParams) throws IOException {
        URL url = new URL(AdminURLS.Bookings.bookingUpdate().set("companyId", getCompanyId()).set("id", this.id).expand());
        return new Booking(HttpService.api_PUT(url, bParams.getParams(), auth_token), auth_token);
    }

    /**
     * Deletes the booking
     *
     * @param bcParams
     * @return
     * @throws IOException
     */
    public Booking bookingCancel_Admin(BookingCancelParams bcParams) throws IOException {
        URL url = new URL(AdminURLS.Bookings.bookingCancel().set("companyId", getCompanyId()).set("id", this.id).expand());
        return new Booking(HttpService.api_DELETE(url, HttpService.jsonContentType, bcParams.getParams(), auth_token), auth_token);
    }

    /**
     * Returns the booking id.
     *
     * @return The id associated with the current Booking object.
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the the full_describe of the booking.
     *
     * @return The full_describe associated with the current Booking object.
     */
    public String getFullDescribe() {
        return get("full_describe");
    }

    /**
     * Returns the resource name.
     *
     * @return The resource name associated with the current Booking object.
     */
    public String getResourceName() {
        return get("resource_name");
    }

    /**
     * Returns the person name.
     *
     * @return The person name associated with the current Booking object.
     */
    public String getPersonName() {
        return get("person_name");
    }

    /**
     * Returns the service name.
     *
     * @return The service name associated with the current Booking object.
     */
    public String getServiceName() {
        return get("service_name");
    }

    /**
     * Returns the resource id.
     *
     * @return The resource id associated with the current Booking object.
     */
    public Integer getResourceId() {
        return getInteger("resource_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the member id.
     *
     * @return The member id associated with the current Booking object.
     */
    public Integer getMemberId() {
        return getInteger("member_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the client name.
     *
     * @return The client name associated with the current Booking object.
     */
    public String getClientName() {
        return get("client_name");
    }

    /**
     * Returns the client email.
     *
     * @return The client email associated with the current Booking object.
     */
    public String getClientEmail() {
        return get("client_email");
    }

    /**
     * Returns the client phone.
     *
     * @return The client phone associated with the current Booking object.
     */
    public String getClientPhone() {
        return get("client_phone");
    }

    /**
     * Returns the client mobile.
     *
     * @return The client mobile associated with the current Booking object.
     */
    public String getClientMobile() {
        return get("client_mobile");
    }

    /**
     * Returns the service id.
     *
     * @return The service id associated with the current Booking object.
     */
    public Integer getServiceId() {
        return getInteger("service_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the date and time when the booking starts, with {@link  DateTime DateTime()} as format.
     *
     * @return The starting date and time associated with the current Booking object.
     */
    public DateTime getStartDatetime() {
        return getDate("datetime");
    }

    /**
     * Returns the date and time when the booking ends, with {@link  DateTime DateTime()} as format.
     *
     * @return The ending date and time associated with the current Booking object.
     */
    public DateTime getEndDateTime() {
        return getStartDatetime().plusMinutes(getDuration());
    }

    /**
     * Returns the duration of the booking.
     *
     * @return The duration associated with the current Booking object.
     */
    public Integer getDuration() {
        return getInteger("duration", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if it's on the wait list, false otherwise.
     *
     * @return The on wait list attribute associated with the current Booking object.
     */
    public Boolean getOnWaitlist() {
        return getBoolean("on_waitlist", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the company id.
     *
     * @return The company id associated with the current Booking object.
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if it's attended, false otherwise.
     *
     * @return The attended attribute associated with the current Booking object.
     */
    public Boolean getAttended() {
        return getBoolean("attended", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the new date and time of the updated booking, with {@link DateTime DateTime()} as format.
     *
     * @return new date and time of the updated booking, associated with current Booking object
     */
    public DateTime getBookingUpdated() {
        return getDate("booking_updated");
    }

    /**
     * Returns the date and time when the booking was updated at, with {@link DateTime DateTime()} as format.
     *
     * @return date and time when the booking was updated at, associated with current Booking object
     */
    public DateTime getUpdatedAt() {
        return getDate("updated_at");
    }

    /**
     * Returns the date and time when the booking was created at, with {@link DateTime DateTime()} as format.
     *
     * @return date and time when the booking was created at, associated with current Booking object
     */
    public DateTime getCreatedAt() {
        return getDate("created_at");
    }

    /**
     * Returns the client id.
     *
     * @return The client id associated with the current Booking object.
     */
    public Integer getClientId() {
        return getInteger("client_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the person id.
     *
     * @return The person id associated with the current Booking object.
     */
    public Integer getPersonId() {
        return getInteger("person_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the price of the booking.
     *
     * @return The price of the booking associated with the current Booking object.
     */
    public Integer getPrice() {
        return getInteger("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the paid attribute.
     *
     * @return The paid attribute associated with the current Booking object.
     */
    public Integer getPaid() {
        return getInteger("paid", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the quantity.
     *
     * @return The quantity associated with the current Booking object.
     */
    public Integer getQuantity() {
        return getInteger("quantity", INTEGER_DEFAULT_VALUE);
    }


    /**
     * Returns true if the booking is cancelled, false otherwise.
     *
     * @return The is canceled attribute associated with the current Booking object.
     */
    public Boolean getIsCancelled() {
        return getBoolean("is_cancelled", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the multi-status.
     *
     * @return The multi-status associated with the current Booking object.
     */
    public MultiStatus getMultiStatus() {
        return getObject("multi_status", MultiStatus.class);
    }

    /**
     * Returns purchase id.
     *
     * @return The purchase id associated with the current Booking object.
     */
    public Integer getPurchaseId() {
        return getInteger("purchase_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns purchase reference.
     *
     * @return The purchase reference associated with the current Booking object.
     */
    public String getPurchaseRef() {
        return get("purchase_ref");
    }

    /**
     * Returns the notes.
     *
     * @return The notes associated with the current Booking object.
     */
    public Notes getNotes() {
        return getObject("notes", Notes.class);
    }

    /**
     * Returns channel.
     *
     * @return The channel associated with the current Booking object.
     */
    public String getChannel() {
        return get("channel");
    }

    /**
     * Returns status of the booking.
     *
     * @return The status associated with the current Booking object.
     */
    public Integer getStatus() {
        return getInteger("status", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the client.
     *
     * @return The client associated with the current Booking object.
     */
    public Client getClient() {
        return new Client(new HttpServiceResponse(getResource("client")));
    }

    /**
     * Returns the answers array.
     *
     * @return The answers associated with the current Booking object.
     */
    public BBCollection<Answer> getAnswers() {
        return new BBCollection<>(new HttpServiceResponse(getResource("answers")), auth_token, Answer.class);
    }

    /**
     * Returns slot id.
     *
     * @return The slot id associated with the current Booking object.
     */
    public Integer getSlotId() {
        return getInteger("slot_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the settings. Settings consists of an obfuscated id.
     *
     * @return The settings associated with the current Booking object.
     */
    public Map getSettings() {
        return getObject("settings", Map.class);
    }

    /**
     * Returns the slot settings.
     *
     * @return The slot settings associated with the current Booking object.
     */
    public Map getSlotSettings() {
        return getObject("slot_settings", Map.class);
    }

    /**
     * Returns the client link.
     *
     * @return The client link associated with the current Booking object.
     */
    public String getClientLink() {
        return getLink("client");
    }

    /**
     * Returns the check in link.
     *
     * @return The check in link associated with the current Booking object.
     */
    public String getCheckInLink() {
        return getLink("check_in");
    }

    /**
     * Returns the questions link.
     *
     * @return The questions link associated with the current Booking object.
     */
    public String getQuestionsLink() {
        return getLink("questions");
    }

    /**
     * Returns the edit link.
     *
     * @return The edit link associated with the current Booking object.
     */
    public String getEditLink() {
        return getLink("edit");
    }

    /**
     * Returns the event groups link.
     *
     * @return The event groups link associated with the current Booking object.
     */
    public String getEventGroupsLink() {
        return getLink("event_groups");
    }

    /**
     * Returns the event chain link.
     *
     * @return The event chain link associated with the current Booking object.
     */
    public String getEventChainLink() {
        return getLink("event_chain");
    }

    /**
     * Returns the cancel link.
     *
     * @return The cancel link associated with the current Booking object.
     */
    public String getCancelLink() {
        return getLink("cancel");
    }

    /**
     * Returns the address link.
     *
     * @return The address link associated with the current Booking object.
     */
    public String getAddressLink() {
        return getLink("address");
    }

    /**
     * Returns the event chain id.
     *
     * @return The event chain id associated with the current Booking object.
     */
    public Integer getEventChainId() {
        return getInteger("event_chain_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the minimum date and time of the booking cancel, with {@link DateTime DateTime()} as date format.
     *
     * @return The minimum date and time of the booking cancel associated with the current Booking object.
     */
    public DateTime getMinCancellationTime() {
        return getDate("min_cancellation_time");
    }

}
