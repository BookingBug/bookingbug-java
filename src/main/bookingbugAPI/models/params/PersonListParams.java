package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class PersonListParams {

    String page;
    String per_page;


    public PersonListParams(){}


    public PersonListParams(Map<String, String[]> args){
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

        params.put("page",      new String[]{page});
        params.put("per_page",  new String[]{per_page});

        return params;
    }


    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPerPage() {
        return per_page;
    }

    public void setPerPage(String per_page) {
        this.per_page = per_page;
    }
}
