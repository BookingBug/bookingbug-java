package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import helpers.Config;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;
import java.util.*;


/**
 * Root class that holds a ContentRepresentation.
 * Child classes should implement getters for relevant parts from the response.
 */
@JsonIgnoreProperties({
    "rep", "auth_token", "id", "data", "links"
})
public class BBRoot {

    protected HttpServiceResponse response;
    protected String auth_token = null;
    public String id;
    public Map<String, String> data;

    protected String curl = "N/A";


    public BBRoot(HttpServiceResponse httpServiceResponse) {
        response = httpServiceResponse;
        id = get("id");
    }


    public BBRoot(String auth_token){
        this.auth_token = auth_token;
    }


    public BBRoot(HttpServiceResponse httpServiceResponse, String auth_token) {
        response = httpServiceResponse;
        this.auth_token = auth_token;
        id = get("id");
    }


    public BBRoot() {}


    public BBRoot getLoginSchema() throws IOException {
        URL url = new URL (UriTemplate.fromTemplate(response.getRep().getLinkByRel("new_login").getHref()).expand());
        HttpServiceResponse response = HttpService.api_GET(url);
        return new BBRoot(response);
    }


    public Login auth(Map<String,String> params) throws IOException{
        URL url = new URL (UriTemplate.fromTemplate(new Config().serverUrl + "/login").expand());
        response = HttpService.api_POST(url, params);
        auth_token = (String) response.getRep().getValue("auth_token");
        return new Login(response);

    }


    public Login auth(Map<String,String> params, Link link) throws IOException{
        URL url = new URL (link.getHref());
        HttpServiceResponse resp = HttpService.api_POST(url, params);
        auth_token = (String) resp.getRep().getValue("auth_token");
        return new Login(resp);
    }


    public String get(String key){
        String val = null;
        try{
            val = (String)response.getRep().getValue(key);
        } catch (RepresentationException e) {
            //e.printStackTrace();
        }
        return val;
    }


    public String getAuth_token() {
        return auth_token;
    }


    public String getLink(String rel) {
        String link = null;
        try {
            link = response.getRep().getLinkByRel(rel).getHref();
        } catch (RepresentationException e) {
            //e.printStackTrace();
        }
        return link;
    }


    public List<Link> getLinks() {
        return response.getRep().getLinks();
    }


    public ContentRepresentation getRep() {
        return response.getRep();
    }


    public String toString() {
        return response.getRep().getContent();
    }


    public String getCurl() {
        curl = "curl \"" + getSelf() + "\" " + response.getParamsStr() + " -X " + response.getMethod();
        return curl;
    }


    public String getSelf() {
        if(response.getRep().getResourceLink() != null)
            return response.getRep().getResourceLink().getHref();
        return "";
    }


    public HttpServiceResponse getResponse() {
        return response;
    }

}
