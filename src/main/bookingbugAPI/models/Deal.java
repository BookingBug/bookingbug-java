package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Deal extends BBRoot {

    public Deal(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Deal(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Deal() {}

}
