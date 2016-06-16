package bookingbugAPI.models;



import com.damnhandy.uri.template.UriTemplate;
import bookingbugAPI.services.PlainHttpService;
import com.theoryinpractise.halbuilder.api.Link;
import helpers.HttpServiceResponse;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;


public class People extends BBRoot{

    private BBRoot schema;

    public People(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }

    public People(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }

    public People() {
        super();
    }

    //Getters and setters

    /**
     * Returns the person id.
     *
     * @return the person id associated with the current Person object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the person name.
     *
     * @return the person name associated with the current Person object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the person description.
     *
     * @return the person description associated with the current Person object
     */
    public String getDescription() {
        return get("description");
    }

    /**
     * Returns the type.
     *
     * @return the type associated with the current Person object
     */
    public String getType() {
        return get("type");
    }

    /**
     * Returns true if the person is deleted, false otherwise.
     *
     * @return the deleted attribute associated with the current Person object
     */
    public Boolean getDeleted() {
        return getBoolean("deleted", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns true if the person is disabled, false otherwise.
     *
     * @return the disabled attribute associated with the current Person object
     */
    public Boolean getDisabled() {
        return getBoolean("disabled", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current Person object
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the order.
     *
     * @return the order associated with the current Person object
     */
    public Integer getOrder() {
        return getInteger("order", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the email`.
     *
     * @return the email associated with the current Person object
     */
    public String getEmail() {
        return get("email");
    }

    /**
     * Returns the mobile.
     *
     * @return the mobile associated with the current Person object
     */
    public String getMobile() {
        return get("mobile");
    }

    /**
     * Returns true if the queuing is disabled, false otherwise.
     *
     * @return the queuing disabled attribute associated with the current Person object
     */
    public Boolean getQueuingDisabled() {
        return getBoolean("queuing_disabled", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the enabled services link array.
     *
     * @return the enabled services link array associated with the current Person object
     */
    public List<String> getEnabledServicesLinks() {
        return getLinks("enabled_services");
    }

    /**
     * Returns the enabled resources link array.
     *
     * @return the enabled resources link array associated with the current Person object
     */
    public List<String> getEnabledResourcesLinks() {
        return getLinks("enabled_resources");
    }

    /**
     * Returns the items link.
     *
     * @return the items link associated with the current Person object
     */
    public String getItemsLink() {
        return getLink("items");
    }

    /**
     * Returns the edit link.
     *
     * @return the edit link associated with the current Person object
     */
    public String getEditLink() {
        return getLink("edit");
    }

    /**
     * Returns the attendance link.
     *
     * @return the attendance link associated with the current Person object
     */
    public String getAttendanceLink() {
        return getLink("attendance");
    }

    /**
     * Returns the block link.
     *
     * @return the block link associated with the current Person object
     */
    public String getBlockLink() {
        return getLink("block");
    }

    /**
     * Returns the cal link.
     *
     * @return the cal link associated with the current Person object
     */
    public String getCalLink() {
        return getLink("cal");
    }

    /**
     * Returns the overlay cal link.
     *
     * @return the overlay cal link associated with the current Person object
     */
    public String getOverlayCalLink() {
        return getLink("overlay_cal");
    }

    /**
     * Returns the schedule link.
     *
     * @return the schedule link associated with the current Person object
     */
    public String getScheduleLink() {
        return getLink("schedule");
    }

    public BBRoot getSchema() throws IOException{
        if(schema == null){
            String link = getRep().getLinkByRel("new").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            schema = new BBRoot(PlainHttpService.api_GET(url, auth_token));
        }
        return schema;
    }

    public People getPerson(Link link) throws IOException{
        String absUrl = Utils.absoluteURL(link.getHref());
        URL url = new URL(UriTemplate.fromTemplate(absUrl).expand());
        return new People(PlainHttpService.api_GET(url, auth_token), auth_token);
    }

}
