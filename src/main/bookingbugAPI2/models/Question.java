package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;

import java.util.List;
import java.util.Map;


public class Question extends BBRoot {

    public Question(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Question(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public Question() {
    }

    /**
     * Returns the question's id.
     *
     * @return The id associated with the current Question object.
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the question's name.
     *
     * @return The name associated with the current Question object.
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the question's required attribute.
     *
     * @return The required attribute associated with the current Question object.
     */
    public Boolean getRequired() {
        return getBoolean("required", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question's important attribute.
     *
     * @return The important attribute associated with the current Question object.
     */
    public Boolean getImportant() {
        return getBoolean("important", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question's admin only attribute.
     *
     * @return The admin only attribute associated with the current Question object.
     */
    public Boolean getAdminOnly() {
        return getBoolean("admin_only", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question's apllies to attribute.
     *
     * @return The apllies to attribute associated with the current Question object.
     */
    public Integer getAppliesTo() {
        return getInteger("applies_to", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the question's ask member attribute.
     *
     * @return The ask member attribute associated with the current Question object.
     */
    public Boolean getAskMember() {
        return getBoolean("ask_member", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question's detail type.
     *
     * @return The detail type associated with the current Question object.
     */
    public String getDetailType() {
        return get("detail_type");
    }

    /**
     * Returns the question's option.
     *
     * @return The option associated with the current Question object.
     */
    public List<Option> getOptions() {
        return getObjects("options", Option.class);
    }

    /**
     * Returns the question's settings.
     *
     * @return The settings associated with the current Question object.
     */
    public Map getSettings() {
        return getObject("settings", Map.class);
    }

    /**
     * Returns the question's price.
     *
     * @return The price associated with the current Question object.
     */
    public Integer getPrice() {
        return getInteger("price", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the question's price per booking.
     *
     * @return The price per booking associated with the current Question object.
     */
    public Boolean getPricePerBooking() {
        return getBoolean("price_per_booking", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns the question's outcome.
     *
     * @return The outcome associated with the current Question object.
     */
    public Boolean getOutcome() {
        return getBoolean("outcome", BOOLEAN_DEFAULT_VALUE);
    }
}
