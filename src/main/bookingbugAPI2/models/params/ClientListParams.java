package bookingbugAPI2.models.params;

import java.util.HashMap;
import java.util.Map;


public class ClientListParams {
    String page;
    String per_page;
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
                    page = strValue;
                    break;
                case "per_page":
                    per_page = strValue;
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
    public Map<String, String> getParams() {
        Map<String, String> params = new HashMap<>();

        params.put("page", page);
        params.put("per_page", per_page);
        params.put("filter_by", filter_by);
        params.put("filter_by_fields", filter_by_fields);
        params.put("order_by", order_by);
        params.put("order_by_reverse", order_by_reverse);

        return params;
    }

    public String getPage() {
        return page;
    }

    public ClientListParams setPage(String page) {
        this.page = page;
        return this;
    }

    public String getPer_page() {
        return per_page;
    }

    public ClientListParams setPer_page(String per_page) {
        this.per_page = per_page;
        return this;
    }

    public String getFilterBy() {
        return filter_by;
    }

    public ClientListParams setFilterBy(String filter_by) {
        this.filter_by = filter_by;
        return this;
    }

    public String getFilterByFields() {
        return filter_by_fields;
    }

    public ClientListParams setFilterByFields(String filter_by_fields) {
        this.filter_by_fields = filter_by_fields;
        return this;
    }

    public String getOrderBy() {
        return order_by;
    }

    public ClientListParams setOrderBy(String order_by) {
        this.order_by = order_by;
        return this;
    }

    public String getOrderByReverse() {
        return order_by_reverse;
    }

    public ClientListParams setOrderByReverse(String order_by_reverse) {
        this.order_by_reverse = order_by_reverse;
        return this;
    }
}
