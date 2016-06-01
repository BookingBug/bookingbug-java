package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookingListParams extends Params {

    String start_date;
    String end_date;
    String include_cancelled;
    String event_id;
    String category_id;
    String start_time;
    String modified_since;
    String created_since;
    String email;
    String client_id;
    String order_by;


    public BookingListParams(){}


    public BookingListParams(int page){
        super(page);
    }


    public String getStart_time() {
        return start_time;
    }

    public BookingListParams setStart_time(String start_time) {
        this.start_time = start_time;
        return this;
    }

    public String getInclude_cancelled() {
        return include_cancelled;
    }

    public BookingListParams setInclude_cancelled(String include_cancelled) {
        this.include_cancelled = include_cancelled;
        return this;
    }

    public String getEnd_date() {
        return end_date;
    }

    public BookingListParams setEnd_date(String end_date) {
        this.end_date = end_date;
        return this;
    }

    public String getStart_date() {
        return start_date;
    }

    public BookingListParams setStart_date(String start_date) {
        this.start_date = start_date;
        return this;
    }

    public String getEvent_id() {
        return event_id;
    }

    public BookingListParams setEvent_id(String event_id) {
        this.event_id = event_id;
        return this;
    }

    public String getCategory_id() {
        return category_id;
    }

    public BookingListParams setCategory_id(String category_id) {
        this.category_id = category_id;
        return this;
    }

    public String getModified_since() {
        return modified_since;
    }

    public BookingListParams setModified_since(String modified_since) {
        this.modified_since = modified_since;
        return this;
    }

    public String getCreated_since() {
        return created_since;
    }

    public BookingListParams setCreated_since(String created_since) {
        this.created_since = created_since;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BookingListParams setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getClient_id() {
        return client_id;
    }

    public BookingListParams setClient_id(String client_id) {
        this.client_id = client_id;
        return this;
    }

    public String getOrder_by() {
        return order_by;
    }

    public BookingListParams setOrder_by(String order_by) {
        this.order_by = order_by;
        return this;
    }
}
