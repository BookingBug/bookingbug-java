package helpers.hal_addon;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by sebi on 04.02.2016.
 */
public class CustomJsonDeserializer extends UntypedObjectDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if(jsonParser.getCurrentTokenId() == 11) {
            return "";
        }
        return super.deserialize(jsonParser, deserializationContext);
    }

    @Override
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        if(jsonParser.getCurrentTokenId() == 11) {
            return "";
        }
        return super.deserialize(jsonParser, deserializationContext, typeDeserializer);
    }

    @Override
    public Object getNullValue() {
        return "";
    }
}
