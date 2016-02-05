package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Slot extends BBRoot {


    public Slot(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Slot(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public Slot() { }

}
