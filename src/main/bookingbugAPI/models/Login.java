package bookingbugAPI.models;

import bookingbugAPI.api.AdminURLS;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.Link;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Login extends BBRoot {

    private String email;
    private String password;

    private Administrator administrator;


    public Login(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
        auth_token = get("auth_token");
    }


    public Login(HttpServiceResponse httpServiceResponse, Map<String, String> credentials) {
        super(httpServiceResponse);
        auth_token = get("auth_token");
        email = credentials.get("email");
        password = credentials.get("password");
    }


    /**
     * getCredentials
     * @return Map<String, String>
     */
    public Map<String, String> getCredentials() {
        Map<String, String> credentials = new HashMap<String, String>();
        credentials.put("email", email);
        credentials.put("password", password);
        return credentials;
    }


    /**
     * getEmail
     * @return String
     */
    public String getEmail() {
        return email;
    }


    /**
     * getPassword
     * @return String
     */
    public String getPassword() {
        return password;
    }


    /**
     * getAdministrator
     * @return Administrator
     * @throws IOException
     */
    public Administrator getAdministrator() throws IOException {
        if(administrator == null){
            List<ContentRepresentation> admin_reps =
                    (List<ContentRepresentation>) response.getRep().getResourcesByRel("administrators");
            Link getAdministratorLink = response.getRep().getLinkByRel("administrator");

            if (admin_reps.size() > 0) {
                //search matching link
                for(ContentRepresentation representation : admin_reps) {
                    if(representation.getLinkByRel("self").getHref().equals(getAdministratorLink.getHref()))
                        administrator = new Administrator(new HttpServiceResponse(representation), auth_token);
                }
            }
            //If not found
            if(admin_reps.size() == 0 || administrator == null){
                String link = getRep().getLinkByRel("administrator").getHref();
                URL url = new URL(UriTemplate.fromTemplate(link).expand());
                administrator = new Administrator(HttpService.api_GET(url, auth_token), auth_token);
            }
        }
        return administrator;
    }


    /**
     * getAdministrator
     * @param link
     * @return Administrator
     * @throws IOException
     */
    public Administrator getAdministrator(Link link) throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Administrator(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * getAdministrators
     * @return ArrayList<Administrator>
     */
    public ArrayList<Administrator> getAdministrators() {
        ArrayList<Administrator> admins = new ArrayList<Administrator>();
        List<ContentRepresentation> admin_reps =
                (List<ContentRepresentation>) getRep().getResourcesByRel("administrators");

        for(ContentRepresentation representation : admin_reps) {
            admins.add(new Administrator(response, auth_token));
        }
        return admins;
    }


    /**
     * createAdministrator
     * @param data
     * @return Administrator
     * @throws HttpException
     * @throws MalformedURLException
     */
    public Administrator createAdministrator(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Administrator.administratorCreate().set("companyId", get("company_id")).expand();
        URL url = new URL (uri);
        return new Administrator(HttpService.api_POST(url, data, auth_token), auth_token);
    }


    /**
     * updateAdministrator
     * @param link
     * @param data
     * @return Administrator
     * @throws HttpException
     * @throws MalformedURLException
     */
    public Administrator updateAdministrator(Link link, Map<String, String> data) throws HttpException, MalformedURLException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Administrator(HttpService.api_PUT(url, HttpService.jsonContentType, data, auth_token), auth_token);
    }


    /**
     * getMember
     * @return Member
     * @throws IOException
     */
    public Member getMember() throws IOException {
        String link = getRep().getLinkByRel("member").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new Member(HttpService.api_GET(url, auth_token), auth_token);
    }


    /**
     * getMember
     * @param link
     * @return Member
     * @throws IOException
     */
    public Member getMember(Link link) throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Member(HttpService.api_GET(url, auth_token), auth_token);
    }

}
