package bookingbugAPI2.models.params;

import java.util.HashMap;
import java.util.Map;


public class BookableItemListParams {

    String resource_id;


    public BookableItemListParams(){}


    public BookableItemListParams(Map<String, String[]> args){
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
                case "resource_id": resource_id = strValue;
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

        params.put("resource_id", new String[]{resource_id});

        return params;
    }


    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }
}
