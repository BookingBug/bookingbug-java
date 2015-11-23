package helpers;

import com.theoryinpractise.halbuilder.api.ContentRepresentation;

import java.util.Map;


public class HttpServiceResponse {

    protected ContentRepresentation rep;

    protected String method;
    protected Map<String,String> params;
    protected String authToken;


    public HttpServiceResponse(ContentRepresentation rep) {
        this.rep = rep;
        this.method = null;
        this.params = null;
        this.authToken = null;
    }


    public HttpServiceResponse(ContentRepresentation rep, String method, Map<String, String> params) {
        this.rep = rep;
        this.method = method;
        this.params = params;
        this.authToken = null;
    }


    public HttpServiceResponse(ContentRepresentation rep, String method, Map<String, String> params, String auth_token) {
        this.rep = rep;
        this.method = method;
        this.params = params;
        this.authToken = auth_token;
    }


    public ContentRepresentation getRep() {
        return rep;
    }


    public void setRep(ContentRepresentation rep) {
        this.rep = rep;
    }


    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
    }


    public void setParams(Map<String, String> params) {
        this.params = params;
    }


    public Map<String, String> getParams() {
        return params;
    }

    public String getParamsStr() {

        Config config = new Config();
        String paramsStr = "";

        if (config.appId!=null) {
            paramsStr += " -H \"App-Id:" + config.appId + "\"";
        }

        if (config.appKey!=null) {
            paramsStr += " -H \"App-Key:" + config.appKey + "\"";
        }

        if (authToken!=null) {
            paramsStr += " -H \"Auth_Token:" + authToken + "\"";
        }

        if (params!=null) {
            paramsStr += " -d \"";
            int i=0;
            for (Map.Entry<String, String> param : params.entrySet()) {
                if (i>0) {
                    paramsStr += "&";
                }
                paramsStr += param.getKey() + "=" + param.getValue();
                i++;
            }
            paramsStr += "\"";
        }

        return paramsStr;
    }
}
