package bookingbugAPI.models;

import helpers.HttpServiceResponse;
import org.joda.time.DateTime;

import java.util.List;


public class Clinic extends BBRoot{
    public Clinic(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    /**
     * Returns the name of the clinic.
     * @return the name of the clinic associated with the current Clinic object.
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the starting time with {@link DateTime DateTime()} as format.
     * @return the starting time associated with the current Clinic object.
     */
    public DateTime getStartTime() {
        return getDate("start_time");
    }

    /**
     * Returns the ending time with {@link DateTime DateTime()} as format.
     * @return the ending time associated with the current Clinic object.
     */
    public DateTime getEndTime() {
        return getDate("end_time");
    }

    /**
     * Returns the resource ids.
     * @return the resource ids associated with the current Clinic object.
     */
    public List<String> getResourceIds() {
        return getStringArray("resource_ids");
    }

    /**
     * Returns the person ids.
     * @return the person ids associated with the current Clinic object.
     */
    public List<String> getPersonIds() {
        return getStringArray("person_ids");
    }

    /**
     * Returns the service ids.
     * @return the service ids associated with the current Clinic object.
     */
    public List<String> getServiceIds() {
        return getStringArray("service_ids");
    }
}
