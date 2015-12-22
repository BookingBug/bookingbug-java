package bookingbugAPI.models;

import bookingbugAPI.api.PublicURLS;
import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.theoryinpractise.halbuilder.api.Link;
import helpers.HttpServiceResponse;
import helpers.SdkUtils;

import java.io.IOException;
import java.net.URL;


public class Service extends BBRoot {


    public Service(HttpServiceResponse httpServiceResponse) {
        super(httpServiceResponse);
    }


    public Service(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }


    public Service() {
    }


    public Service getService(Link link) throws IOException {
        String absUrl = SdkUtils.absoluteURL(link.getHref());
        URL url = new URL(UriTemplate.fromTemplate(absUrl).expand());
        Service service = new Service(HttpService.api_GET(url, auth_token), auth_token);
        return service;
    }

}

