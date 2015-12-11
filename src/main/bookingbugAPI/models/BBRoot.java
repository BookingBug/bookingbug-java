package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import helpers.Config;
import helpers.HttpServiceResponse;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

/**
 * Root class that holds a ContentRepresentation. Child classes should implement getters for relevant parts from
 * response
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


    /**
     * getLoginSchema
     * @return BBRoot
     * @throws IOException
     */
    public BBRoot getLoginSchema() throws IOException {
        URL url = new URL (UriTemplate.fromTemplate(response.getRep().getLinkByRel("new_login").getHref()).expand());
        HttpServiceResponse response = HttpService.api_GET(url);
        return new BBRoot(response);
    }


    /**
     * auth
     * @param params
     * @return Login
     */
    public Login auth(Map<String,String> params) throws MalformedURLException {
        HttpServiceResponse resp;

        try {
            URL url = new URL(UriTemplate.fromTemplate(new Config().serverUrl + "/login").expand());
            resp = HttpService.api_POST(url, params);
            auth_token = (String) resp.getRep().getValue("auth_token");
        } catch (HttpException e) {
            //e.printStackTrace();
            auth_token = null;
            JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
            InputStream ins = new ByteArrayInputStream(e.getRawResponse().getBytes());
            Reader inputStreamReader = new InputStreamReader(ins);
            ContentRepresentation representation = representationFactory.readRepresentation(HAL_JSON, inputStreamReader);
            resp = new HttpServiceResponse(representation);
        }

        return new Login(resp);
    }


    /**
     * auth
     * @param params
     * @param link
     * @return Login
     * @throws IOException
     */
    public Login auth(Map<String,String> params, Link link) throws IOException{
        URL url = new URL (link.getHref());
        HttpServiceResponse resp = HttpService.api_POST(url, params);
        auth_token = (String) resp.getRep().getValue("auth_token");
        return new Login(resp);
    }


    /**
     * get
     * @param key
     * @return String
     */
    public String get(String key){
        String val = null;
        try{
            val = (String)response.getRep().getValue(key);
        } catch (RepresentationException e) {
            //e.printStackTrace();
        }
        return val;
    }


    /**
     * getAuth_token
     * @return String
     */
    public String getAuth_token() {
        return auth_token;
    }


    /**
     * getLink
     * @param rel
     * @return String
     */
    public String getLink(String rel) {
        String link = null;
        try {
            link = response.getRep().getLinkByRel(rel).getHref();
        } catch (RepresentationException e) {
            //e.printStackTrace();
        }
        return link;
    }


    /**
     * getLinks
     * @return List<Link>
     */
    public List<Link> getLinks() {
        return response.getRep().getLinks();
    }


    /**
     * getRep
     * @return ContentRepresentation
     */
    public ContentRepresentation getRep() {
        return response.getRep();
    }


    /**
     * toString
     * @return String
     */
    public String toString() {
        return response.getRep().getContent();
    }


    /**
     * getCurl
     * @return String
     */
    public String getCurl() {
        curl = "curl \"" + getSelf() + "\" " + response.getParamsStr() + " -X " + response.getMethod();
        return curl;
    }


    /**
     * getSelf
     * @return String
     */
    public String getSelf() {
        if(response.getRep().getResourceLink() != null)
            return response.getRep().getResourceLink().getHref();
        return "";
    }


    /**
     * getResponse
     * @return HttpServiceResponse
     */
    public HttpServiceResponse getResponse() {
        return response;
    }

}
