package bookingbugAPI.models.params;

import java.util.Map;

/**
 * Created by sebi on 02.02.2016.
 */
public class BookingUpdateParams extends Params {

    String date;
    String time;
    String datetime;
    String duration;
    String person_id;
    String resource_id;
    String email;
    String email_owner;
    String status;
    String client_name;
    String client_email;


    public BookingUpdateParams(){}


    public BookingUpdateParams(Map<String, String> args){
        setNotNullStringMap(args);
    }

    /**
     * Get parameters
     * @return the params in a map
     */
    public Map<String, String> getParams() {
        return getNotNullStringMap();
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date= date;
    }



    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_owner() {
        return email_owner;
    }

    public void setEmail_owner(String email_owner) {
        this.email_owner = email_owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getClient_email() {
        return client_email;
    }

    public void setClient_email(String client_email) {
        this.client_email = client_email;
    }

}
