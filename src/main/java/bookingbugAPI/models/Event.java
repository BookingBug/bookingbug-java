package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Event extends BBRoot{

    
    public Event(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Event(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Event() {}

}
