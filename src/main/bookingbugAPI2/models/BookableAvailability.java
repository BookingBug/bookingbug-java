package bookingbugAPI2.models;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import helpers2.HttpServiceResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by sebi on 26.02.2016.
 */
public class BookableAvailability extends BBRoot {

    public BookableAvailability(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }


    public BookableAvailability(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }

    public BookableAvailability() {}

    public String getName(){
        return get("name");
    }

    public String getEvent_id(){
        return get("event_id");
    }

    public String getDate(){
        return get("date");
    }

    public Date getDateTimeObj(){
        Date datetime = null;
        try {
            datetime = new ISO8601DateFormat().parse(get("date"));
        } catch (ParseException | NullPointerException e) {
            //e.printStackTrace();
            log.warning("Cannot parse datetime format: " + e.toString());
        }
        return datetime;
    }

    public ArrayList<AvailTime> getAvailTimes(){

        ArrayList<ReadableRepresentation> times_reps = new ArrayList<>(getRep().getResourcesByRel("times"));
        ArrayList<AvailTime> times = new ArrayList<>();

        for (ReadableRepresentation rep : times_reps) {
            AvailTime timeObj = new AvailTime();
            timeObj.time_minutes = Integer.parseInt((String) rep.getValue("time"));
            timeObj.avail = Integer.parseInt((String) rep.getValue("avail")) == 1 ? true : false;
            timeObj.price = Integer.parseInt((String) rep.getValue("price"));
            times.add(timeObj);
        }
        return times;
    }

    public static class AvailTime {
        int time_minutes;
        boolean avail;
        int price;

        public int getTime_minutes() {
            return time_minutes;
        }

        public boolean isAvail() {
            return avail;
        }

        public int getPrice() {
            return price;
        }
    }
}
