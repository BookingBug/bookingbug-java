package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class EventGroup extends BBRoot {


    public EventGroup(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public EventGroup(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public EventGroup() {}

    /**
     * Returns the id
     *
     * @return The id associated with the current EventGroup object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the name.
     *
     * @return The name associated with the current EventGroup object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the description.
     *
     * @return The description associated with the current EventGroup object
     */
    public String getDescription() {
        return get("description");
    }

    /**
     * Returns the imagesLink.
     *
     * @return The imagesLink associated with the current EventGroup object
     */
    public String getImagesLink() {
        return getLink("images");
    }
}
