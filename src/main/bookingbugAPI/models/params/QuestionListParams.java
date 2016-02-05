package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class QuestionListParams {

    String detail_group_id;


    public QuestionListParams(){}


    public QuestionListParams(Map<String, String[]> args){
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
                case "detail_group_id": detail_group_id = strValue;
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

        params.put("detail_group_id", new String[]{detail_group_id});

        return params;
    }


    public String getDetailGroupId() {
        return detail_group_id;
    }

    public void setDetailGroupId(String detail_group_id) {
        this.detail_group_id = detail_group_id;
    }

}
