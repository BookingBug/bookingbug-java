package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookingCreateParams {

    String datetime;
    String service_id;
    String person_id;
    String resource_id;
    String member_id;
    String notifications;


    public BookingCreateParams(){}


    public BookingCreateParams(Map<String, String[]> args){
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
                case "datetime": datetime = strValue;
                    break;
                case "service_id": service_id = strValue;
                    break;
                case "person_id": person_id = strValue;
                    break;
                case "resource_id": resource_id = strValue;
                    break;
                case "member_id": member_id = strValue;
                    break;
                case "notifications": notifications = strValue;
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

        params.put("datetime",      new String[]{datetime});
        params.put("service_id",    new String[]{service_id});
        params.put("person_id",     new String[]{person_id});
        params.put("resource_id",   new String[]{resource_id});
        params.put("member_id",     new String[]{member_id});
        params.put("notifications", new String[]{notifications});

        return params;
    }


    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
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

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public String getNotifications() {
        return notifications;
    }

    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }
}
