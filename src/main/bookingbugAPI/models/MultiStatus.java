package bookingbugAPI.models;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Created by sergiu on 10.06.2016.
 */
public class MultiStatus extends BBRoot {
    @JsonProperty
    private String here;

    public String getHere() {
        return here;
    }
}
