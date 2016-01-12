package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class PurchaseListParams {

    String created_from;
    String created_to;
    String admin_bookings_only;
    String order_by;
    String order_by_reverse;


    public PurchaseListParams(){}


    public PurchaseListParams(Map<String, String[]> args){
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
                case "created_from": created_from = strValue;
                    break;
                case "created_to": created_to = strValue;
                    break;
                case "admin_bookings_only": admin_bookings_only = strValue;
                    break;
                case "order_by": order_by = strValue;
                    break;
                case "order_by_reverse": order_by_reverse = strValue;
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

        params.put("created_from", new String[]{created_from});
        params.put("created_to", new String[]{created_to});
        params.put("admin_bookings_only", new String[]{admin_bookings_only});
        params.put("order_by", new String[]{order_by});
        params.put("order_by_reverse", new String[]{order_by_reverse});

        return params;
    }


    public String getCreated_from() {
        return created_from;
    }

    public void setCreated_from(String created_from) {
        this.created_from = created_from;
    }

    public String getCreated_to() {
        return created_to;
    }

    public void setCreated_to(String created_to) {
        this.created_to = created_to;
    }

    public String getAdmin_bookings_only() {
        return admin_bookings_only;
    }

    public void setAdmin_bookings_only(String admin_bookings_only) {
        this.admin_bookings_only = admin_bookings_only;
    }

    public String getOrder_by() {
        return order_by;
    }

    public void setOrder_by(String order_by) {
        this.order_by = order_by;
    }

    public String getOrder_by_reverse() {
        return order_by_reverse;
    }

    public void setOrder_by_reverse(String order_by_reverse) {
        this.order_by_reverse = order_by_reverse;
    }
}
