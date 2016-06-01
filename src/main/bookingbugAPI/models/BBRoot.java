package bookingbugAPI.models;

import bookingbugAPI.services.HttpService;
import com.damnhandy.uri.template.UriTemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import com.theoryinpractise.halbuilder.impl.representations.ContentBasedRepresentation;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import helpers.Config;
import helpers.HttpServiceResponse;
import helpers.hal_addon.CustomJsonDeserializer;
import org.joda.time.DateTime;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;


/**
 * Root class that holds a ContentRepresentation.
 * Child classes should implement getters for relevant parts from the response.
 */
@JsonIgnoreProperties({
        "rep", "auth_token", "id", "data", "links"
})
public class BBRoot {

    protected final Logger log = Logger.getLogger(this.getClass().getName());

    protected HttpServiceResponse response;
    protected String auth_token = null;
    public String id;
    public Map<String, String> data;
    public int INTEGER_DEFAULT_VALUE = 0;
    public double DOUBLE_DEFAULT_VALUE = 0.0;
    public boolean BOOLEAN_DEFAULT_VALUE = false;

    protected String curl = "N/A";


    public BBRoot(HttpServiceResponse httpServiceResponse) {
        response = httpServiceResponse;
        id = get("id");
    }


    public BBRoot(String auth_token) {
        this.auth_token = auth_token;
    }


    public BBRoot(HttpServiceResponse httpServiceResponse, String auth_token) {
        response = httpServiceResponse;
        this.auth_token = auth_token;
        id = get("id");
    }


    public BBRoot() {
    }


    public BBRoot getLoginSchema() throws IOException {
        URL url = new URL(UriTemplate.fromTemplate(response.getRep().getLinkByRel("new_login").getHref()).expand());
        HttpServiceResponse response = HttpService.api_GET(url);
        return new BBRoot(response);
    }


    public Login auth(Map<String, String> params) throws IOException {
        HttpServiceResponse resp;
        try {
            URL url = new URL(UriTemplate.fromTemplate(new Config().serverUrl + "/login").expand());
            resp = HttpService.api_POST(url, params);
            auth_token = (String) resp.getRep().getValue("auth_token");
        } catch (HttpException e) {
            //e.printStackTrace();
            if (e.getStatusCode() == 400) {
                auth_token = null;
                JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
                InputStream ins = new ByteArrayInputStream(e.getRawResponse().getBytes());
                Reader inputStreamReader = new InputStreamReader(ins);
                ContentRepresentation representation = representationFactory.readRepresentation(HAL_JSON, inputStreamReader);
                resp = new HttpServiceResponse(representation);
            } else {
                throw e;
            }
        }

        return new Login(resp);
    }


    public Login auth(Map<String, String> params, Link link) throws IOException {
        URL url = new URL(link.getHref());
        HttpServiceResponse resp = HttpService.api_POST(url, params);
        auth_token = (String) resp.getRep().getValue("auth_token");
        return new Login(resp);
    }


    public String get(String key) {
        String val = null;
        try {
            val = (String) response.getRep().getValue(key);
        } catch (RepresentationException e) {
            //e.printStackTrace();
        }
        return val;
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        String val = this.get(key);
        if (val != null) return Boolean.parseBoolean(val);
        return defaultValue;
    }

    public int getInteger(String key, int defaultValue) {
        String val = this.get(key);
        if (val != null) return Integer.parseInt(val);
        return defaultValue;
    }

    public double getDouble(String key, double defaultValue) {
        String val = this.get(key);
        if (val != null) return Double.parseDouble(val);
        return defaultValue;
    }

    public DateTime getDate(String key) {
        return new DateTime(get(key));
    }

    public List<String> getArray(String key) {
        List<String> val = null;
        try {
            val = (List<String>) (response.getRep().getValue(key));
        } catch (RepresentationException e) {
            e.printStackTrace();
        }
        return val;
    }

    //TODO: improve this
    public <T> T getObject(String key, Class<T> type) {

        try {
            ObjectMapper mapper = CustomJsonDeserializer.getMapper();
            String json_obj = mapper.writeValueAsString(response.getRep().getValue(key));
            return mapper.readValue(json_obj, type);
        } catch (RepresentationException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> List<T> getObjects(String key, Class<T> type) {
        List<T> val = null;

        try {
            ObjectMapper mapper = CustomJsonDeserializer.getMapper();
            String json_obj = mapper.writeValueAsString(response.getRep().getValue(key));
            val = mapper.readValue(json_obj, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return val;
    }

    //TODO: fix this
    public ContentRepresentation getResource(String key) {
        try {
            List<? extends ReadableRepresentation> entries = response.getRep().getResourcesByRel(key);

            for (ReadableRepresentation item : entries) {
                if (item instanceof ContentBasedRepresentation)
                    return (ContentRepresentation) item;
            }
        } catch (RepresentationException e) {
            e.printStackTrace();
        }
        return null;
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

    public String toPrettyString() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response.getRep());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return toString();
        }
    }


    public String getCurl() {
        curl = "curl \"" + getSelf() + "\" " + response.getParamsStr() + " -X " + response.getMethod();
        return curl;
    }


    public String getSelf() {
        if (response.getRep().getResourceLink() != null)
            return response.getRep().getResourceLink().getHref();
        return "";
    }


    public HttpServiceResponse getResponse() {
        return response;
    }

}
