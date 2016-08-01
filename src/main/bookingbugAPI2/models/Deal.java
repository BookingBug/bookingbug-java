package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;
import org.joda.time.DateTime;


public class Deal extends BBRoot {

    public Deal(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Deal(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Deal() {}

    /**
     * Returns the deal id.
     *
     * @return The deal id associated with the current Deal object
     */
    public Integer getId() {
        return getInteger("id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the deal name.
     *
     * @return The deal name associated with the current Deal object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the number deal.
     *
     * @return The number deal associated with the current Deal object
     */
    public Integer getNum() {
        return getInteger("num", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the service id.
     *
     * @return The service id associated with the current Deal object
     */
    public Integer getService_id() {
        return getInteger("service_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the date and time the deal was created, with {@link DateTime DateTime()} as format.
     *
     * @return The date and time associated with the current Deal object
     */
    public DateTime getCreated_at() {
        return getDate("created_at");
    }

    /**
     * Returns the deal redemption.
     *
     * @return The deal redemption associated with the current Deal object
     */
    public Integer getRedemption() {
        return getInteger("redemption", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the deal total codes.
     *
     * @return The deal total codes associated with the current Deal object
     */
    public Integer getTotalCodes() {
        return getInteger("total_codes", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the deal used codes.
     *
     * @return The deal used codes associated with the current Deal object
     */
    public Integer getUsedCodes() {
        return getInteger("used_codes", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the deal vendor.
     *
     * @return The deal vendor associated with the current Deal object
     */
    public String getVendor() {
        return get("vendor");
    }

    /**
     * Returns true if mixed booking are allowed, false otherwise.
     *
     * @return The allow mixed bookings attribute associated with the current Deal object
     */
    public Boolean getAllowMixedBookings() {
        return getBoolean("allow_mixed_bookings", BOOLEAN_DEFAULT_VALUE);
    }

    /**
     * Returns true to show to widget, false otherwise.
     *
     * @return The widget show attribute associated with the current Deal object
     */
    public Boolean getWidgetShow() {
        return getBoolean("widget_show", BOOLEAN_DEFAULT_VALUE);
    }
}
