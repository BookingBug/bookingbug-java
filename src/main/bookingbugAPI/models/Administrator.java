package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import helpers.HttpServiceResponse;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


public class Administrator extends BBRoot {

    private Company company;
    private BBRoot schema;


    public Administrator(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse);
        this.auth_token = auth_token;
    }


    /**
     * getCompany
     * @return Company
     * @throws IOException
     */
    public Company getCompany() throws IOException {
        if(company == null){
            String link = response.getRep().getLinkByRel("company").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            company = new Company(HttpService.api_GET(url, auth_token), auth_token);
        }
        return company;
    }


    /**
     * getEditSchema
     * @return BBRoot
     * @throws IOException
     */
    public BBRoot getEditSchema() throws IOException {
        if(schema == null) {
            String link = response.getRep().getLinkByRel("edit").getHref();
            URL url = new URL(UriTemplate.fromTemplate(link).expand());
            schema = new BBRoot(HttpService.api_GET(url, auth_token), auth_token);
        }
        return schema;
    }


    /**
     * login
     * @param params
     * @return Login
     * @throws IOException
     */
    public Login login(Map<String,String> params) throws  IOException {
        return auth(params, response.getRep().getLinkByRel("login"));
    }

}
