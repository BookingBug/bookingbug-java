package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class User extends BBRoot{


    public User(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public User(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public User() {}

}
