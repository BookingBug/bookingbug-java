package helpers2.hal_addon;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.CharStreams;
import com.theoryinpractise.halbuilder.AbstractRepresentationFactory;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.api.RepresentationException;
import com.theoryinpractise.halbuilder.api.RepresentationReader;
import com.theoryinpractise.halbuilder.impl.api.Support;
import com.theoryinpractise.halbuilder.impl.representations.ContentBasedRepresentation;
import com.theoryinpractise.halbuilder.impl.representations.MutableRepresentation;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by sebi on 04.02.2016.
 */
public class CustomJsonRepresentationReader implements RepresentationReader {
    private final ObjectMapper mapper;
    private final AbstractRepresentationFactory representationFactory;

    public CustomJsonRepresentationReader(AbstractRepresentationFactory representationFactory) {
        this.representationFactory = representationFactory;
        this.mapper = CustomJsonDeserializer.getMapper();
    }

    public ContentRepresentation read(Reader reader) {
        try {
            String e = CharStreams.toString(reader);
            JsonNode rootNode = (JsonNode)this.mapper.readValue(new StringReader(e), JsonNode.class);
            return this.readResource(rootNode);
        } catch (Exception var4) {
            throw new RepresentationException(var4);
        }
    }

    private ContentRepresentation readResource(JsonNode rootNode) throws IOException {
        ContentBasedRepresentation resource = new ContentBasedRepresentation(this.representationFactory, rootNode.toString());
        this.readNamespaces(resource, rootNode);
        this.readLinks(resource, rootNode);
        this.readProperties(resource, rootNode);
        this.readResources(resource, rootNode);
        return resource;
    }

    private void readNamespaces(MutableRepresentation resource, JsonNode rootNode) {
        if(rootNode.has("_links")) {
            JsonNode linksNode = rootNode.get("_links");
            if(linksNode.has("curies")) {
                JsonNode curieNode = linksNode.get("curies");
                if(curieNode.isArray()) {
                    Iterator values = curieNode.elements();

                    while(values.hasNext()) {
                        JsonNode valueNode = (JsonNode)values.next();
                        resource.withNamespace(valueNode.get("name").asText(), valueNode.get("href").asText());
                    }
                } else {
                    resource.withNamespace(curieNode.get("name").asText(), curieNode.get("href").asText());
                }
            }
        }

    }

    private void readLinks(MutableRepresentation resource, JsonNode rootNode) {
        if(rootNode.has("_links")) {
            Iterator fields = rootNode.get("_links").fields();

            while(true) {
                while(true) {
                    Map.Entry keyNode;
                    do {
                        if(!fields.hasNext()) {
                            return;
                        }

                        keyNode = (Map.Entry)fields.next();
                    } while("curies".equals(keyNode.getKey()));

                    if(((JsonNode)keyNode.getValue()).isArray()) {
                        Iterator values = ((JsonNode)keyNode.getValue()).elements();

                        while(values.hasNext()) {
                            JsonNode valueNode = (JsonNode)values.next();
                            this.withJsonLink(resource, keyNode, valueNode);
                        }
                    } else {
                        this.withJsonLink(resource, keyNode, (JsonNode)keyNode.getValue());
                    }
                }
            }
        }
    }

    private void withJsonLink(MutableRepresentation resource, Map.Entry<String, JsonNode> keyNode, JsonNode valueNode) {
        String rel = (String)keyNode.getKey();
        String href = valueNode.get("href").asText();
        String name = this.optionalNodeValueAsText(valueNode, "name");
        String title = this.optionalNodeValueAsText(valueNode, "title");
        String hreflang = this.optionalNodeValueAsText(valueNode, "hreflang");
        String profile = this.optionalNodeValueAsText(valueNode, "profile");
        resource.withLink(rel, href, name, title, hreflang, profile);
    }

    String optionalNodeValueAsText(JsonNode node, String key) {
        JsonNode value = node.get(key);
        return value != null?value.asText():null;
    }

    private void readProperties(MutableRepresentation resource, JsonNode rootNode) throws IOException {
        Iterator fieldNames = rootNode.fieldNames();

        while(true) {
            while(true) {
                String fieldName;
                do {
                    if(!fieldNames.hasNext()) {
                        return;
                    }

                    fieldName = (String)fieldNames.next();
                } while(Support.RESERVED_JSON_PROPERTIES.contains(fieldName));

                JsonNode field = rootNode.get(fieldName);
                if(field.isArray()) {
                    ArrayList arrayValues = new ArrayList(field.size());
                    Iterator var7 = field.iterator();

                    while(var7.hasNext()) {
                        JsonNode arrayValue = (JsonNode)var7.next();
                        arrayValues.add(!arrayValue.isContainerNode()?arrayValue.asText(): ImmutableMap.copyOf((Map)this.mapper.readValue(arrayValue.toString(), Map.class)));
                    }

                    resource.withProperty(fieldName, arrayValues);
                } else {
                    resource.withProperty(fieldName, field.isNull()?null:(!field.isContainerNode()?field.asText():ImmutableMap.copyOf((Map)this.mapper.readValue(field.toString(), Map.class))));
                }
            }
        }
    }

    private void readResources(MutableRepresentation resource, JsonNode rootNode) throws IOException {
        if(rootNode.has("_embedded")) {
            Iterator fields = rootNode.get("_embedded").fields();

            while(true) {
                while(fields.hasNext()) {
                    Map.Entry keyNode = (Map.Entry)fields.next();
                    if(((JsonNode)keyNode.getValue()).isArray()) {
                        Iterator values = ((JsonNode)keyNode.getValue()).elements();

                        while(values.hasNext()) {
                            JsonNode valueNode = (JsonNode)values.next();
                            resource.withRepresentation((String)keyNode.getKey(), this.readResource(valueNode));
                        }
                    } else {
                        resource.withRepresentation((String)keyNode.getKey(), this.readResource((JsonNode)keyNode.getValue()));
                    }
                }

                return;
            }
        }
    }
}
