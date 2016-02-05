package bookingbugAPI.models;

import helpers.HttpServiceResponse;


public class CompanyConfig extends BBRoot{


    public CompanyConfig(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public CompanyConfig(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public CompanyConfig() {}

}
