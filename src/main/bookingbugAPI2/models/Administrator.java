package bookingbugAPI2.models;

import bookingbugAPI2.services.http.PlainHttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers2.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


public class Administrator extends BBRoot {

    public Administrator(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }

    public Administrator(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }

    public Company getCompany() throws IOException {
        String link = response.getRep().getLinkByRel("company").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new Company(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    public BBRoot getEditSchema() throws IOException {
        String link = response.getRep().getLinkByRel("edit").getHref();
        URL url = new URL(UriTemplate.fromTemplate(link).expand());
        return new BBRoot(PlainHttpService.api_GET(url, auth_token), auth_token);
    }


    public Login login(Map<String, String> params) throws IOException {
        return auth(params, response.getRep().getLinkByRel("login"));
    }

    /**
     * Returns the name of the admin.
     *
     * @return the name associated with the current Administrator object
     */
    public String getName() {
        return get("name");
    }

    /**
     * Returns the email of the admin.
     *
     * @return the email associated with the current Administrator object
     */
    public String getEmail() {
        return get("email");
    }

    /**
     * Returns the role of the admin.
     *
     * @return the associated with the current administrator object
     */
    public String getRole() {
        return get("role");
    }

    /**
     * Returns the company id.
     *
     * @return the company id associated with the current administrator object
     */
    public Integer getCompanyId() {
        return getInteger("company_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the company name.
     *
     * @return the company name associated with the current administrator object
     */
    public String getCompanyName() {
        return get("company_name");
    }

    /**
     * Returns the person id.
     *
     * @return the person id associated with the current administrator object
     */
    public Integer getPersonId() {
        return getInteger("person_id", INTEGER_DEFAULT_VALUE);
    }

    /**
     * Returns the edit link.
     *
     * @return the edit link associated with the current administrator object
     */
    public String getEditLink() {
        return getLink("edit");
    }

    /**
     * Returns the company link.
     *
     * @return the company link associated with the current administrator object
     */
    public String getCompanyLink() {
        return getLink("company");
    }

    /**
     * Returns the person link.
     *
     * @return the person link associated with the current administrator object
     */
    public String getPersonLink() {
        return getLink("person");
    }

    /**
     * Returns the login link.
     *
     * @return the login link associated with the current administrator object
     */
    public String getLoginLink() {
        return getLink("login");
    }

    /**
     * Returns the base_login.
     *
     * @return the base_login associated with the current administrator object
     */
    public String getBaseLoginLink() {
        return getLink("base_login");
    }
}
