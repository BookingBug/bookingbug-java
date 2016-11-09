package bookingbugAPI2.models.params;

import java.util.HashMap;
import java.util.Map;


public class ClientListParams extends Params<ClientListParams> {
    String filter_by;
    String filter_by_fields;
    String order_by;
    String order_by_reverse;

    public ClientListParams() {
    }

    public ClientListParams(Map<String, String[]> args) {
        if (args == null || args.isEmpty()) {
            return;
        }

        String strValue;

        for (Map.Entry<String, String[]> entry : args.entrySet()) {
            final String[] value = entry.getValue();
            if (value[0] != null && !value[0].trim().isEmpty()) {
                strValue = null;
            } else {
                strValue = value[0];
            }

            switch (entry.getKey()) {
                case "page":
                    setPage(Integer.parseInt(strValue));
                    break;
                case "per_page":
                    setPerPage(Integer.parseInt(strValue));
                    break;
                case "filter_by":
                    filter_by = strValue;
                    break;
                case "filter_by_fields":
                    filter_by_fields = strValue;
                    break;
                case "order_by":
                    order_by = strValue;
                    break;
                case "order_by_reverse":
                    order_by_reverse = strValue;
                    break;
            }
        }
    }


    /**
     * getParams
     *
     * @return Map<String, String[]>
     */
    public Map<String, String[]> getParams() {
        Map<String, String[]> params = new HashMap<String, String[]>();

        params.put("page", new String[]{String.valueOf(getPage())});
        params.put("per_page", new String[]{String.valueOf(getPer_page())});
        params.put("filter_by", new String[]{filter_by});
        params.put("filter_by_fields", new String[]{filter_by_fields});
        params.put("order_by", new String[]{order_by});
        params.put("order_by_reverse", new String[]{order_by_reverse});

        return params;
    }

    public String getFilterBy() {
        return filter_by;
    }

    public void setFilterBy(String filter_by) {
        this.filter_by = filter_by;
    }

    public String getFilterByFields() {
        return filter_by_fields;
    }

    public void setFilterByFields(String filter_by_fields) {
        this.filter_by_fields = filter_by_fields;
    }

    public String getOrderBy() {
        return order_by;
    }

    public void setOrderBy(String order_by) {
        this.order_by = order_by;
    }

    public String getOrderByReverse() {
        return order_by_reverse;
    }

    public void setOrderByReverse(String order_by_reverse) {
        this.order_by_reverse = order_by_reverse;
    }
}
