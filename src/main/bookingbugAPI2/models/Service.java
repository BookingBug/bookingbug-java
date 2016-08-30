package bookingbugAPI2.models;

import bookingbugAPI2.services.http.PlainHttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import helpers2.HttpServiceResponse;
import helpers2.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;


public class Service extends BBRoot {

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
            return new SchemaForm(PlainHttpService.api_GET(url, this.auth_token));
        }
        // Throw exception: link is missing
        throw new IOException("new_booking link missing");
    }

    public Service getService(Link link) throws IOException {
        String absUrl = Utils.absoluteURL(link.getHref());
        URL url = new URL(UriTemplate.fromTemplate(absUrl).expand());
        Service service = new Service(PlainHttpService.api_GET(url, auth_token), auth_token);
        return service;
    }

    /**
     * Returns the service id.
     *
     * @return the service id associated with the current Service object.
     */
    public Integer getId(){
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the service name.
     *
     * @return the service name associated with the current Service object.
     */
    public String getName(){
        return get("name");
    }

    /**
     * Returns the service durations. If there is no durations key return a list containing 1
     *
     * @return the service durations associated with the current Service object.
     */
    public List<Integer> getDurations(){
        try {
            return getObjects("durations", Integer.class);
        } catch (RepresentationException e) {
            List<Integer> res = new LinkedList<>();
            res.add(1);
            return res;
        }
    }

    /**
     * Returns the service prices.
     *
     * @return the service prices associated with the current Service object.
     */
    public List<Integer> getPrices(){
        try {
            return getIntegerArray("prices");
        } catch (RepresentationException e) {
            return new LinkedList<>();
        }
    }

    /**
     * Returns the detail group id.
     *
     * @return the detail group id associated with the current Service object.
     */
    public Integer getDetailGroupId(){
        return getInteger("detail_group_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the booking time step.
     *
     * @return the booking time step associated with the current Service object.
     */
    public Integer getBookingTimeStep(){
        return getInteger("booking_time_step", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if it is event group, false otherwise.
     *
     * @return the is event group attribute associated with the current Service object.
     */
    public Boolean getIsEventGroup(){
        return getBoolean("is_event_group", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the service type.
     *
     * @return the service type associated with the current Service object.
     */
    public String getType(){
        return get("type");
    }

    /**
     * Returns true if service get deleted, false otherwise.
     *
     * @return the get deleted attribute associated with the current Service object.
     */
    public Boolean getDeleted(){
        return getBoolean("deleted", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current Service object.
     */
    public Integer getCompanyId(){
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the minimum advance period.
     *
     * @return the minimum advance period associated with the current Service object.
     */
    public Integer getMinAdvancePeriod(){
        return getInteger("min_advance_period", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the maximum advance period.
     *
     * @return the maximum advance period associated with the current Service object.
     */
    public Integer getMaxAdvancePeriod(){
        return getInteger("max_advance_period", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the minimum cancel period.
     *
     * @return the minimum cancel period associated with the current Service object.
     */
    public Integer getMinCancelPeriod(){
        return getInteger("min_cancel_period", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the booking type public.
     *
     * @return the booking type public associated with the current Service object.
     */
    public String getBookingTypePublic(){
        return get("booking_type_public");
    }

    /**
     * Returns the booking type member.
     *
     * @return the booking type member associated with the current Service object.
     */
    public String getBookingTypeMember(){
        return get("booking_type_member");
    }

    /**
     * Returns the minimum number of bookings.
     *
     * @return the minimum number of bookings associated with the current Service object.
     */
    public Integer getMinBookings(){
        return getInteger("min_bookings", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the maximum number of booking.
     *
     * @return the maximum number of booking associated with the current Service object.
     */
    public Integer getMaxBookings(){
        return getInteger("max_bookings", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the service groups.
     *
     * @return the service groups associated with the current Service object.
     */
    public List<String> getGroups(){
        return getStringArray("groups");
    }

    /**
     * Returns the service order.
     *
     * @return the service order associated with the current Service object.
     */
    public Integer getOrder(){
        return getInteger("order", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns true if is child level service, false otherwise.
     *
     * @return the child level service attribute associated with the current Service object.
     */
    public Boolean getChildLevelService(){
        return getBoolean("child_level_service", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the edit link
     * @return the link to edit this service
     */
    public String getEditLink() {
        return getLink("edit");
    }

    /**
     * Returns the new booking link
     * @return the link to create a new booking
     */
    public String getNewBookingLink() {
        return getLink("new_booking");
    }

    /**
     * Returns the items link.
     *
     * @return the items link associated with the current Service object.
     */
    public String getItemsLink(){
        return getLink("items");
    }

    /**
     * Returns the questions link.
     *
     * @return the questions link associated with the current Service object.
     */
    public String getQuestionsLink(){
        return getLink("questions");
    }

    /**
     * Returns the days link.
     *
     * @return the days link associated with the current Service object.
     */
    public String getDaysLink(){
        return getLink("days");
    }

    /**
     * Returns the times link.
     *
     * @return the times link associated with the current Service object.
     */
    public String getTimesLink(){
        return getLink("times");
    }

    /**
     * Returns the book link.
     *
     * @return the book link associated with the current Service object.
     */
    public String getBookLink(){
        return getLink("book");
    }

    /**
     * Returns the children link.
     *
     * @return the children link associated with the current Service object.
     */
    public String getAllChildrenLink(){
        return getLink("all_children");
    }
}

