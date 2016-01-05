package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookingCancelParams {

    String notify = "true";
    String cancel_reason;


    public BookingCancelParams(){}


    public BookingCancelParams(Map<String, String[]> args){
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
                case "notify": notify = strValue;
                    break;
                case "cancel_reason": cancel_reason = strValue;
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

        params.put("notify",        new String[]{notify});
        params.put("cancel_reason", new String[]{cancel_reason});

        return params;
    }


    public String getNotify() {
        return notify;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getCancelReason() {
        return cancel_reason;
    }

    public void setCancelReason(String cancel_reason) {
        this.cancel_reason = cancel_reason;
    }
}
