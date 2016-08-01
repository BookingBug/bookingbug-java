package helpers2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by sebi on 13.04.2016.
 */
public class Http {
    public final static String jsonContentType = "application/json";
    public final static String urlEncodedContentType = "application/x-www-form-urlencoded";

    public static Encoder getEncoder(String content_type) throws UnknownContentType {
        if(jsonContentType.equals(content_type))
            return new JsonEncoder();
        else if(urlEncodedContentType.equals(content_type))
            return new UrlEncoder();

        throw new UnknownContentType("Unknown content type: " + content_type +
                ". Possible values are " + jsonContentType + ", " + urlEncodedContentType);
    }

    public interface Encoder {
        String encode(Map map) throws EncodingException;
    }

    public static class UrlEncoder implements Encoder {

        private static final Set<Class<?>> PRIMITIVE_WRAPPER_TYPES;
        static {
            PRIMITIVE_WRAPPER_TYPES = new HashSet<Class<?>>();
            PRIMITIVE_WRAPPER_TYPES.add(Boolean.class);
            PRIMITIVE_WRAPPER_TYPES.add(Character.class);
            PRIMITIVE_WRAPPER_TYPES.add(String.class);
            PRIMITIVE_WRAPPER_TYPES.add(Short.class);
            PRIMITIVE_WRAPPER_TYPES.add(Integer.class);
            PRIMITIVE_WRAPPER_TYPES.add(Long.class);
            PRIMITIVE_WRAPPER_TYPES.add(Float.class);
            PRIMITIVE_WRAPPER_TYPES.add(Double.class);
        }

        private boolean ignoreNull = false;

        public UrlEncoder(){}

        public UrlEncoder(boolean ignoreNull) {
            this.ignoreNull = ignoreNull;
        }

        public String encode(Map map) throws EncodingException {
            try {
                ArrayList<String> encoded = new ArrayList<>();
                for (Object o : map.entrySet()) {
                    Map.Entry pair = (Map.Entry) o;

                    encoded.add(nest(pair.getKey().toString(), pair.getValue()));
                }
                return joinArray(encoded);
            } catch (UnsupportedEncodingException e) {
                throw new EncodingException("Exception when encoding to " + urlEncodedContentType, e);
            }
        }

        private String encode(String value) throws UnsupportedEncodingException {
            return URLEncoder.encode(value, "UTF-8");
        }

        private static String joinArray(ArrayList arr) {
            String res = "";
            for(int i = 0; i < arr.size(); i++) {
                if(arr.get(i).toString().isEmpty()) continue;
                res += arr.get(i).toString();
                //Last element
                if(i == arr.size() - 1) continue;
                res += '&';
            }
            return res;
        }

        private String arrayNest(String name, ArrayList value) throws UnsupportedEncodingException {
            if(value == null) return "";

            ArrayList<String> encoded = new ArrayList<>();
            for(Object obj : value) {
                encoded.add(nest(name + "[]", obj));
            }
            return joinArray(encoded);
        }

        private String objectNest(String name, Map value) throws UnsupportedEncodingException {
            if(value == null) return "";

            ArrayList<String> encoded = new ArrayList<>();
            for (Object o : value.entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                encoded.add(nest(name + '[' + pair.getKey().toString() + ']', pair.getValue()));
            }

            return joinArray(encoded);
        }

        private String nest(String name, Object value) throws UnsupportedEncodingException {
            String res = "";

            if(value == null) {
              res = ignoreNull ? "" : encode(name) + '=' + "null";
            } else if(isPrimitiveWrapper(value.getClass())) {
                res = encode(name) + '=' + encode(value.toString());
            } else if (value instanceof Collection) {
                //Array
                res = arrayNest(name, (ArrayList)value);
            } else if (value instanceof Map) {
                //Object
                res = objectNest(name, (Map)value);
            }

            return res;
        }

        public static boolean isPrimitiveWrapper(Class<?> clazz)
        {
            return PRIMITIVE_WRAPPER_TYPES.contains(clazz);
        }
    }

    public static class JsonEncoder implements Encoder {

        @Override
        public String encode(Map map) throws EncodingException {
            try {
                return new ObjectMapper().writeValueAsString(map);
            } catch (JsonProcessingException e) {
                throw new EncodingException("Exception when encoding to " + jsonContentType, e);
            }
        }
    }

    public static class EncodingException extends Exception {

        public EncodingException(String message) {
            super(message);
        }

        public EncodingException(String message, Throwable cause) {
            super(message, cause);
        }

        public EncodingException(Throwable cause) {
            super(cause);
        }
    }

    public static class UnknownContentType extends Exception {
        public UnknownContentType(String message) {
            super(message);
        }
    }

}
