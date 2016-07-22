package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Schedule extends BBRoot {
    public Schedule(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public String getName() {
        return get("name");
    }

    public String getDesc() {
        return get("desc");
    }

    public Integer getStyle() {
        return getInteger("style", INTEGER_DEFAULT_VALUE);
    }
}
