package bookingbugAPI.models;

import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.services.HttpService;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;


public class EventGroup extends BBRoot {


    public EventGroup(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public EventGroup(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public EventGroup() {}

}
