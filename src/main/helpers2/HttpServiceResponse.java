package helpers2;

import bookingbugAPI2.services.Http.PlainHttpService;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;

import java.util.Map;


public class HttpServiceResponse {

    protected ContentRepresentation rep;
    protected String url;
    protected String method;
    protected String contentType = PlainHttpService.jsonContentType;
    protected Map params;
    protected String authToken;


    public HttpServiceResponse(ContentRepresentation rep) {
        this.rep = rep;
        this.method = null;
        this.params = null;
        this.authToken = null;
    }


    public HttpServiceResponse(ContentRepresentation rep, String method, Map params) {
        this.rep = rep;
        this.method = method;
        this.params = params;
        this.authToken = null;
    }


    public HttpServiceResponse(ContentRepresentation rep, String url, String method, String contentType, Map params, String auth_token) {
        this.rep = rep;
        this.url = url;
        this.method = method;
        this.contentType = contentType;
        this.params = params;
        this.authToken = auth_token;
    }


    public ContentRepresentation getRep() {
        return rep;
    }


    public void setRep(ContentRepresentation rep) {
        this.rep = rep;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
    }


    public void setParams(Map params) {
        this.params = params;
    }


    public Map getParams() {
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

        try {
            paramsStr += " -H \"Content-Type: " + contentType + "\"";
            paramsStr += " -d \"";
            paramsStr += Http.getEncoder(contentType).encode(params);
            paramsStr += "\"";
        }
        catch (Http.EncodingException | Http.UnknownContentType | NullPointerException e) {
            e.printStackTrace();
        }

        return paramsStr;
    }
}
