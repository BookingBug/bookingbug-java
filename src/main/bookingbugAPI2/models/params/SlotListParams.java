package bookingbugAPI2.models.params;

import java.util.HashMap;
import java.util.Map;


public class SlotListParams {

    String start_date;
    String end_date;
    String date;
    String resource_id;
    String service_id;
    String person_id;
    String page;
    String per_page;


    public SlotListParams(){}


    public SlotListParams(Map<String, String[]> args){
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
                case "date": date = strValue;
                    break;
                case "resource_id": resource_id = strValue;
                    break;
                case "service_id": service_id = strValue;
                    break;
                case "person_id": person_id = strValue;
                    break;
                case "page": page = strValue;
                    break;
                case "per_page": per_page = strValue;
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

        params.put("start_date",    new String[]{start_date});
        params.put("end_date",      new String[]{end_date});
        params.put("date",          new String[]{date});
        params.put("resource_id",   new String[]{resource_id});
        params.put("service_id",    new String[]{service_id});
        params.put("person_id",     new String[]{person_id});
        params.put("page",          new String[]{page});
        params.put("per_page",      new String[]{per_page});

        return params;
    }


    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }
}
