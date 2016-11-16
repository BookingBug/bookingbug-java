package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class BookingQuestion extends BBRoot{


    public BookingQuestion(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public BookingQuestion(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse, auth_token);
    }


    public BookingQuestion() {}

}
