package bookingbugAPI.models;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import helpers.HttpServiceResponse;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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

    public boolean isAvailable(Date datetime){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(datetime);
        int hour_to_check = calendar.get(Calendar.HOUR_OF_DAY);

        ArrayList<ReadableRepresentation> times = new ArrayList<>(getRep().getResourcesByRel("times"));
        for (ReadableRepresentation rep : times) {
            int time_min = Integer.parseInt((String) rep.getValue("time"));
            int avail = Integer.parseInt((String) rep.getValue("avail"));

            int hour = time_min / 60;
            if(hour_to_check >= hour && hour_to_check < hour + 1 && avail == 1)
                return true;
        }
        return false;
    }
}
