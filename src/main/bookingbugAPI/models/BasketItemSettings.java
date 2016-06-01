package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import org.joda.time.DateTime;

/**
 * Created by sergiu on 31.05.2016.
 */
public class BasketItemSettings extends BBRoot{

    public BasketItemSettings(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }
    /**
     * Returns the resource.
     *
     * @return The resource associated with the current BasketItemSettings object
     */
    public Integer getResource() {
        return getInteger("resource", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the person.
     *
     * @return The person associated with the current BasketItemSettings object
     */
    public Integer getPerson() {
        return getInteger("person", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the earliest date and time, with {@link DateTime DateTime()} as format.
     *
     * @return The earliest date and time  associated with the current BasketItemSettings object
     */
    public DateTime getEarliestTime() {
        return getDate("earliest_rime");
    }
}
