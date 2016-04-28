package helpers;

import bookingbugAPI.models.params.Params;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;


public class Utils {


    /**
     * @param url
     * @param parent
     * @return
     */
    public static String absoluteURL(String url, String parent){
        if(url.startsWith("/")){
            if(parent != null) return parent + url;
            return new Config().serverUrl + url;
        }
        return url;
    }


    /**
     * @param url
     * @return
     */
    public static String absoluteURL(String url){
       return absoluteURL(url, null);
    }

    /**
     * Given a string url returns a templated url with pagination
     * @param fromTemplate String to convert to UriTemplate
     * @return
     */
    public static UriTemplate paginatedUriTemplate(String fromTemplate){
        return UriTemplate.buildFromTemplate(fromTemplate)
                .query(UriTemplateBuilder.var("page"), UriTemplateBuilder.var("per_page"))
                .build();
    }

    public static UriTemplate TemplateWithPagination(UriTemplate template, Params params){
        if(params != null){
            template.set("page", params.getPage());
            template.set("per_page", params.getPer_page());
        }
        return template;
    }

    /**
     * @param content
     * @return
     */
    public static ContentRepresentation stringToContent(String content) {
        JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
        InputStream ins = new ByteArrayInputStream(content.getBytes());
        Reader instr = new InputStreamReader(ins);
        return representationFactory.readRepresentation(HAL_JSON, instr);
    }


    /**
     * @param link
     * @return
     */
    public static boolean linkHasArgs(Link link) {
        UriTemplate template = UriTemplate.fromTemplate(link.getHref());
        String[] vars = template.getVariables();
        return vars.length > 0;
    }


    /**
     * @param link
     * @return
     */
    public static boolean linkHasArgs(String link) {
        UriTemplate template = UriTemplate.fromTemplate(link);
        String[] vars = template.getVariables();
        return vars.length > 0;
    }

    public static String inflateLink(UriTemplate template, Map args) {
        Map<String, Object> toInflate = new HashMap<String, Object>();

        for (Object key : args.keySet()) {
            final Object value = args.get(key);

            if(value == null || (value instanceof String && ((String) value).trim().isEmpty()))
                continue;

            if(value instanceof String[] && (((String[])value).length > 0 || ((String[])value)[0].trim().isEmpty()))
                continue;

            toInflate.put(key.toString(), value);
        }
        return template.expand(toInflate);
    }

    /**
     * @param link
     * @param args
     * @return
     */
    public static String inflateLink(String link, Map args) {
        return inflateLink(UriTemplate.fromTemplate(link), args);
    }


    /**
     * Function used to generate a schema and a form from a string array containing names for fields.
     * <BR>Used to generate forms from Link objects with parameters
     * @param link: Link object from which to extract form field names
     * @return ObjectNode: json node containing the schema and form
     */
    public static ObjectNode HyperSchemaGenerator(Link link) {
        final JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode root = factory.objectNode();
        ObjectNode schema = factory.objectNode();
        ArrayNode form = factory.arrayNode();

        UriTemplate template = UriTemplate.fromTemplate(link.getHref());
        String[] vars = template.getVariables();

        return HyperSchemaGenerator(vars, link.getRel());
    }


    /**
     * Function used to generate a schema and a form from a string array containing names for fields.
     * <BR>Used to generate forms from an Array of Strings
     * @param vars
     * @return ObjectNode: json node containing the schema and form
     */
    public static ObjectNode HyperSchemaGenerator(String[] vars, String schemaTitle) {
        final JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode root = factory.objectNode();
        ObjectNode schema = factory.objectNode();
        ArrayNode form = factory.arrayNode();

        //Generate the schema
        ArrayNode required = factory.arrayNode();
        ObjectNode properties = factory.objectNode();
        for (String var : vars) {
            properties.put(var, factory.objectNode().put("type", "string"));
        }
        schema.put("properties", properties);
        schema.put("required", required);
        schema.put("type", "object");
        schema.put("title", schemaTitle);

        form.add("*").add(factory.objectNode().put("type", "submit").put("title", "Submit"));

        root.put("form", form);
        root.put("schema", schema);
        return root;
    }


    public static class Messages {
        public static String logoutMessage = "logout_message";
        public static String createdPersonMessage = "created_person";
    }
}
