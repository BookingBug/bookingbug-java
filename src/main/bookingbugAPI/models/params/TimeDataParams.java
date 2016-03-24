package bookingbugAPI.models.params;

import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by sebi on 27.02.2016.
 */
public class TimeDataParams extends Params {

    String event_id;
    String service_id;
    String resource_id;
    String resource_ids;
    String person_id;
    String group_id;
    String location;
    String date;
    String end_date;
    String duration;
    String num_resources;

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getNum_resources() {
        return num_resources;
    }

    public void setNum_resources(String num_resources) {
        this.num_resources = num_resources;
    }

    public int getDuration() {
        return Integer.parseInt(duration);
    }

    public void setDuration(int duration) {
        this.duration = String.valueOf(duration);
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = new ISO8601DateFormat().format(end_date);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = new ISO8601DateFormat().format(date);
    }



    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public ArrayList<String> getResource_ids() {
        ArrayList<String> res = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(resource_ids, ",");
        while (tokenizer.hasMoreTokens())
            res.add(tokenizer.nextToken());
        return res;
    }

    public void setResource_ids(ArrayList<String> resource_ids) {
        this.resource_ids = "";
        for(int i = 0; i < resource_ids.size(); i++) {
            this.resource_ids += resource_ids.get(i);
            if(i + 1 < resource_ids.size())
                this.resource_ids += ",";
        }
    }
}
