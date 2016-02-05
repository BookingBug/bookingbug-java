package bookingbugAPI.models;



import com.damnhandy.uri.template.UriTemplate;
import bookingbugAPI.services.HttpService;
import com.theoryinpractise.halbuilder.api.Link;
import helpers.HttpServiceResponse;
import helpers.Utils;

import java.io.IOException;
import java.net.URL;


public class People extends BBRoot{

    private BBRoot schema;

    public People(HttpServiceResponse httpServiceResponse){
        super(httpServiceResponse);
    }

    public People(HttpServiceResponse httpServiceResponse, String auth_token){
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }

    public People() {
        super();
    }

    //Getters and setters
    public String getEmail() {
        return get("email");
    }

    public String getName() {
         return get("name");
     }

    public String getDescription() {
        return get("description");
    }

    public String getPhone() {
        return get("mobile");
    }

    public String getCal() {
        return getLink("cal");
    }

    public BBRoot getSchema() throws IOException{
        if(schema == null){
            String link = getRep().getLinkByRel("new").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            schema = new BBRoot(HttpService.api_GET(url, auth_token));
        }
        return schema;
    }

    public People getPerson(Link link) throws IOException{
        String absUrl = Utils.absoluteURL(link.getHref());
        URL url = new URL(UriTemplate.fromTemplate(absUrl).expand());
        return new People(HttpService.api_GET(url, auth_token), auth_token);
    }

}
