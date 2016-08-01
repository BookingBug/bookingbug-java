package bookingbugAPI2.models;

import helpers2.HttpServiceResponse;


public class BookableItem extends BBRoot {


    public BookableItem(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public BookableItem(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public BookableItem() { }

}
