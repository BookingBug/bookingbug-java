package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class Schedule extends BBRoot {
    public Schedule(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public Schedule(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
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
