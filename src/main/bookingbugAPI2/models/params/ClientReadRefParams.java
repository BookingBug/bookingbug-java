package bookingbugAPI2.models.params;

import java.util.HashMap;
import java.util.Map;


public class ClientReadRefParams {

    String include_auth_token;
    String embed;


    public ClientReadRefParams(){}


    public ClientReadRefParams(Map<String, String[]> args){
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
                case "include_auth_token": include_auth_token = strValue;
                    break;
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

        params.put("include_auth_token",    new String[]{include_auth_token});
        params.put("embed",                 new String[]{embed});

        return params;
    }


    public String getInclude_auth_token() {
        return include_auth_token;
    }

    public void setInclude_auth_token(String include_auth_token) {
        this.include_auth_token = include_auth_token;
    }

    public String getEmbed() {
        return embed;
    }

    public void setEmbed(String embed) {
        this.embed = embed;
    }
}
