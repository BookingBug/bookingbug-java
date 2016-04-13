package bookingbugAPI.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import helpers.HttpServiceResponse;

import java.util.Map;

/**
 * Created by sebi on 11.04.2016.
 */
public class SchemaForm extends BBRoot {
    public SchemaForm(HttpServiceResponse httpServiceResponse, String auth_token) {
        super(httpServiceResponse, auth_token);
    }

    public SchemaForm(HttpServiceResponse response) {
        super(response);
    }

    public String getSchemaStr() {
        return getSchemaJson().toString();
    }

    public JsonNode getSchemaJson() {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectMapper mapper = new ObjectMapper();

        // Defaults to empty json
        JsonNode schema = factory.objectNode();


        if(getRep().getValue("schema", null) != null) {
            schema = mapper.convertValue(getRep().getValue("schema"), JsonNode.class);
        }

        return schema;
    }

    public String getFormStr() {
        return getFormJson().toString();
    }

    public JsonNode getFormJson() {
        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectMapper mapper = new ObjectMapper();

        // Defaults to empty json
        JsonNode form = factory.objectNode();

        if(getRep().getValue("form", null) != null) {
            form = mapper.convertValue(getRep().getValue("form"), JsonNode.class);
        }

        return form;
    }
}
