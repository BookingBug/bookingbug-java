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


    public Login login(Map<String, String> params) throws IOException {
        return auth(params, response.getRep().getLinkByRel("login"));
    }

    /**
     * Returns the admin credentials.
     *
     * @return the credentials associated with the current Login object.
     */
    public Map<String, String> getCredentials() {
        Map<String, String> credentials = new HashMap<>();
        credentials.put("email", email);
        credentials.put("password", password);
        return credentials;
    }

    /**
     * Returns the email.
     *
     * @return the email associated with the current Login object.
     */
    public String getEmail() {
        return get("email");
    }

    /**
     * Returns the authentication token.
     *
     * @return the authentication token associated with the current Login object.
     */
    public String getAuthToken() {
        return get("auth_token");
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current Login object.
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the path.
     *
     * @return the path associated with the current Login object.
     */
    public String getPath() {
        return get("path");
    }

    /**
     * Returns the administrator link.
     *
     * @return the administrator link associated with the current Login object.
     */
    public String getAdministratorLink() {
        return getLink("administrator");
    }

    /**
     * Returns the members collection.
     *
     * @return the members collection associated with the current Login object.
     */
    public BBCollection<Member> getMembers() {
        return new BBCollection<Member>(new HttpServiceResponse(getResource("members")), auth_token, Member.class);
    }

    /**
     *Returns the administrator.
     *
     * @return the administrator associated with the current login object.
     * @throws IOException
     */
    public Administrator getAdministrator() throws IOException {
        if (administrator == null) {
            List<ContentRepresentation> admin_reps =
                    (List<ContentRepresentation>) response.getRep().getResourcesByRel("administrators");
            Link getAdministratorLink = response.getRep().getLinkByRel("administrator");

            if (admin_reps.size() > 0) {
                //search matching link
                for (ContentRepresentation representation : admin_reps) {
                    if (representation.getLinkByRel("self").getHref().equals(getAdministratorLink.getHref()))
                        administrator = new Administrator(new HttpServiceResponse(representation), auth_token);
                }
            }
            //If not found
            if (admin_reps.size() == 0 || administrator == null) {
                String link = getRep().getLinkByRel("administrator").getHref();
                URL url = new URL(UriTemplate.fromTemplate(link).expand());
                administrator = new Administrator(HttpService.api_GET(url, auth_token), auth_token);
            }
        }
        return administrator;
    }

    /**
     *Returns the administrator from inserted link.
     * @param link
     * @return the administrator associated with the current login object.
     * @throws IOException
     */
    public Administrator getAdministrator(Link link) throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Administrator(HttpService.api_GET(url, auth_token), auth_token);
    }

    /**Returns the list of administrators
     *
     * @return
     */
    public BBCollection<Administrator> getAdministrators() {
        return new BBCollection<Administrator>(new HttpServiceResponse(getResource("administrators")), auth_token, Administrator.class);
    }

    /**
     *Returns a new administrator from inserted data.
     * @param data
     * @return a new administrator
     * @throws HttpException
     * @throws MalformedURLException
     */
    public Administrator createAdministrator(Map<String, String> data) throws HttpException, MalformedURLException {
        String uri = AdminURLS.Administrator.administratorCreate().set("companyId", get("company_id")).expand();
        URL url = new URL(uri);
        return new Administrator(HttpService.api_POST(url, data, auth_token), auth_token);
    }

    /**
     *Returns an updated administrator from inserted data.
     * @param link
     * @param data
     * @return the administrator updated
     * @throws HttpException
     * @throws MalformedURLException
     */
    public Administrator updateAdministrator(Link link, Map<String, String> data) throws HttpException, MalformedURLException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Administrator(HttpService.api_PUT(url, HttpService.jsonContentType, data, auth_token), auth_token);
    }

    /**
     *Returns a member from inserted link.
     * @param link
     * @return the member by link from the current Login object
     * @throws IOException
     */
    public Member getMember(Link link) throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(link.getHref()).expand());
        return new Member(HttpService.api_GET(url, auth_token), auth_token);
    }
}
