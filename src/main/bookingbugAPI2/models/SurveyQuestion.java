package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class SurveyQuestion extends BBRoot {


    public SurveyQuestion(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public SurveyQuestion(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public SurveyQuestion() { }
}
