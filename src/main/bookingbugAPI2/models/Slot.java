package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class Slot extends BBRoot {


    public Slot(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Slot(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public Slot() { }

}
