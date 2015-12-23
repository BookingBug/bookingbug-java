package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookingListParams {

    String start_date;
    String end_date;
    String include_cancelled;
    String event_id;
    String category_id;
    String start_time;
    String modified_since;
    String created_since;
    String email;
    String page;
    String per_page;
    String client_id;


    public BookingListParams(){}


    public BookingListParams(Map<String, String[]> args){
        if (args==null || args.isEmpty()) {
            return;
        }

        String strValue;

        for (Map.Entry<String, String[]> entry : args.entrySet()) {
            final String[] value = entry.getValue();
            if (value[0]!=null && !value[0].trim().isEmpty()) {
                strValue = null;
            } else {
                strValue = value[0];
            }

            switch(entry.getKey()) {
                case "start_date": start_date = strValue;
                    break;
                case "end_date": end_date = strValue;
                    break;
                case "include_cancelled": include_cancelled = strValue;
                    break;
                case "event_id": event_id = strValue;
                    break;
                case "category_id": category_id = strValue;
                    break;
                case "start_time": start_time = strValue;
                    break;
                case "modified_since": modified_since = strValue;
                    break;
                case "created_since": created_since = strValue;
                    break;
                case "email": email = strValue;
                    break;
                case "page": page = strValue;
                    break;
                case "per_page": per_page = strValue;
                    break;
                case "client_id": client_id = strValue;
                    break;
            }
        }
    }


    /**
     * getParams
     * @return Map<String, String[]>
     */
    public Map<String, String[]> getParams() {
        Map<String, String[]> params = new HashMap<String, String[]>();

        params.put("start_date",        new String[]{start_date});
        params.put("end_date",          new String[]{end_date});
        params.put("include_cancelled", new String[]{include_cancelled});
        params.put("event_id",          new String[]{event_id});
        params.put("category_id",       new String[]{category_id});
        params.put("start_time",        new String[]{start_time});
        params.put("modified_since",    new String[]{modified_since});
        params.put("created_since",     new String[]{created_since});
        params.put("email",             new String[]{email});
        params.put("page",              new String[]{page});
        params.put("per_page",          new String[]{per_page});
        params.put("client_id",         new String[]{client_id});

        return params;
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

    public String getPage() {
        return page;
    }

    public BookingListParams setPage(String page) {
        this.page = page;
        return this;
    }

    public String getPer_page() {
        return per_page;
    }

    public BookingListParams setPer_page(String per_page) {
        this.per_page = per_page;
        return this;
    }

    public String getClient_id() {
        return client_id;
    }

    public BookingListParams setClient_id(String client_id) {
        this.client_id = client_id;
        return this;
    }
}
