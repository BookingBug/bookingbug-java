package bookingbugAPI.models;

import helpers.HttpServiceResponse;

public class Client extends BBRoot {

    public String first_name;
    public String last_name;
    public String email;
    public String phone;

    public Client(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public Client(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }

    public Client() {}

}
