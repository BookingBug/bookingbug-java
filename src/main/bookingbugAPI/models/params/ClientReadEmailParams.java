package bookingbugAPI.models.params;

import java.util.HashMap;
import java.util.Map;


public class ClientReadEmailParams {

    String embed;


    public ClientReadEmailParams(){}


    public ClientReadEmailParams(Map<String, String[]> args){
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
                case "embed": embed = strValue;
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

        params.put("embed",                 new String[]{embed});

        return params;
    }


    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }
}
