package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class EventChain extends BBRoot{


    public EventChain(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public EventChain(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public EventChain() {}

}
