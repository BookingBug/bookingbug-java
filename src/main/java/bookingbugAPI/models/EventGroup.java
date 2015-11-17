package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class EventGroup extends BBRoot{


    public EventGroup(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public EventGroup(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public EventGroup() {}

}
