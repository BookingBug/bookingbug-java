package bookingbugAPI.models;

import bookingbugAPI.models.BBRoot;
import helpers.HttpServiceResponse;


public class Question extends BBRoot{


    public Question(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Question(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public Question() {}

}
