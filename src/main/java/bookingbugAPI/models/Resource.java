package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class Resource extends BBRoot{


    public Resource(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Resource(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }

    public Resource() {}
}
