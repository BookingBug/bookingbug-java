package bookingbugAPI.models;

import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.models.params.BookingCancelParams;
import bookingbugAPI.models.params.BookingUpdateParams;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;

import java.util.Calendar;
import java.util.Date;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Map;



public class Booking extends BBRoot {

    public Booking(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Booking(HttpServiceResponse response) {
        super(response);
    }

    public Booking(){}

    public BBRoot getSchema() throws IOException {
        String link = getRep().getLinkByRel("edit").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new BBRoot(HttpService.api_GET(url, auth_token));
    }

    /**
     * Returns a new Booking - update the current booking with provided params
     * @param bParams
     * @throws IOException
     */
    public Booking bookingUpdate_Admin(BookingUpdateParams bParams) throws IOException {
        URL url = new URL (AdminURLS.Bookings.bookingUpdate().set("companyId", getCompany_id()).set("id", this.id).expand());
        return new Booking(HttpService.api_PUT(url, bParams.getParams(), auth_token), auth_token);
    }

    /**
     * Deletes the booking
     * @param bcParams
     * @return
     * @throws IOException
     */
    public Booking bookingCancel_Admin(BookingCancelParams bcParams) throws IOException {
        URL url = new URL(AdminURLS.Bookings.bookingCancel().set("companyId", getCompany_id()).set("id", this.id).expand());
        return new Booking(HttpService.api_DELETE(url, HttpService.jsonContentType, bcParams.getParams(), auth_token), auth_token);
    }

    /**
     * Returns the duration.
     *
     * @return The duration associated with the current Booking object.
     */
    public Integer getDuration() {
        return getInteger("duration", INTEGER_DEFAULT_VALUE);
    }

    public String getUpdated_at() {
        return get("updated_at");
    }

    public String getCompany_id() {
        return get("company_id");
    }


    /**
     * Returns the settings. Settings consists of an obfuscated id.
     * @return The settings associated with the current Booking object.
     */
    public Map getSettings() {
        return getObject("settings", Map.class);
    }

    public String getQuestions() {
        return get("questions");
    }

    public String getOn_waitlist() {
        return get("on_waitlist");
    }

    public String getClient_email() {
        return get("client_email");
    }

    public String getId() {
        return get("id");
    }

    public String getSlot_id() {
        return get("slot_id");
    }

    public String getIs_cancelled() {
        return get("is_cancelled");
    }

    public String getClient_name() {
        return get("client_name");
    }

    public String getClient_mobile() {
        return get("client_mobile");
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
     * Returns the full_describe of the booking.
     *
     * @return The full_describe associated with the current Booking object.
     */
    public String getFull_describe() {
        return get("full_describe");
    }

    public String getChannel() {
        return get("channel");
    }

    /**
     * Returns the notes map.
     *
     * @return The notes associated with the current Booking object.
     */
    public Notes getNotes() {
        return getObject("notes", Notes.class);
    }

    public String getService_id() {
        return get("service_id");
    }

    public String getClient_id() {
        return get("client_id");
    }

    public String getService_name() {
        return get("service_name");
    }

    public String getStatus() {
        return get("status");
    }

    /**
     * Returns the multi-status map.
     *
     * @return The multi-status map associated with the current Booking object.
     */
    public Map getMulti_status() {
        return getObject("multi_status", Map.class);
    }

    public String getCreated_at() {
        return get("created_at");
    }

    public String getPrice() {
        return get("price");
    }

    public String getDatetime() {
        return get("datetime");
    }

    public Date getDateTimeObj(){
        Date datetime = null;
        try {
            datetime = new ISO8601DateFormat().parse(get("datetime"));
        } catch (ParseException | NullPointerException e) {
            //e.printStackTrace();
            log.warning("Cannot parse datetime format: " + e.toString());
        }
        return datetime;
    }

    public Date getEndDateTimeObj(){
        Date endDateTime = null;
        Date startDateTime = getDateTimeObj();
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(startDateTime);
            cal.add(Calendar.MINUTE, getDuration());
            endDateTime = cal.getTime();
        }catch (Exception e){
            log.warning("Cannot get booking end datetime: " + e.toString());
        }
        return endDateTime;
    }

    public String getResource_name() {
        return get("resource_name");
    }

    /**
     * Returns the slot settings.
     *
     * @return The slot settings associated with the current Booking object.
     */
    public Map getSlot_settings() {
        return getObject("slot_settings", Map.class);
    }

    public String getQuantity() {
        return get("quantity");
    }

    public String getBooking_updated() {
        return get("booking_updated");
    }

    public String getClient_phone() {
        return get("client_phone");
    }

    public String getResource_id() {
        return get("resource_id");
    }

    public String getPurchase_id() {
        return get("purchase_id");
    }

    public String getMember_id() {
        return get("member_id");
    }

    public String getPaid() {
        return get("paid");
    }

    public String getPurchase_ref() {
        return get("purchase_ref");
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
     * Returns the client resource.
     *
     * @return The client resource associated with the current Booking object.
     */
    public Client getClient() {
        return new Client(new HttpServiceResponse(getResource("client")));
    }

    /**
     * Returns the answer array resource.
     *
     * @return The answers resource associated with the current Booking object.
     */
    public BBCollection<Answer> getAnswers() {
        return new BBCollection<>(new HttpServiceResponse(getResource("answers")), auth_token, Answer.class);
    }


    /**
     * Returns the survey answers summary resource.
     *
     * @return The survey answers summary resource associated with the current Booking object.
     */
    public List<String> getSurveyAnswersSummary() {
        return getArray("survey_answers_summary");
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
