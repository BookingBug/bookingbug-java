package bookingbugAPI.services;

import bookingbugAPI.models.HttpException;
import bookingbugAPI.models.PublicRoot;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theoryinpractise.halbuilder.api.ContentRepresentation;
import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;
import helpers.Config;
import bookingbugAPI.models.BBRoot;
import helpers.HttpServiceResponse;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.logging.Logger;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

public class HttpService {

    private final static Logger log = Logger.getLogger(HttpService.class.getName());

    public final static String jsonContentType = "application/json";
    public final static String urlEncodedContentType = "application/x-www-form-urlencoded";

    public static String encodeParams(Map<String,String> params) {
        StringBuilder postData = new StringBuilder();
        try{

            for (Map.Entry<String,String> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
        } catch(UnsupportedEncodingException e){
            log.warning(e.getMessage());
        }
        return postData.toString();
    }

    public static String encodeParamsJson(Map<String, String> params) {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(params);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static byte[] encodeUTF8(Map<String,String> params, String contentType) {
        byte[] data = new byte[0];
        String encodedParams = "";

        //Convert to json instead
        if(contentType == urlEncodedContentType)
            encodedParams = encodeParams(params);
        else if(contentType == jsonContentType)
            encodedParams = encodeParamsJson(params);

        try {
            data = encodedParams.getBytes("UTF-8");
            //data = json.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.warning(e.getMessage());
        }
        return data;
    }

    public static HttpServiceResponse api_GET(URL url, boolean testingMode) throws HttpException {
        return callApi(url, null, "GET", urlEncodedContentType, null, testingMode);
    }

    public static HttpServiceResponse api_GET(URL url) throws HttpException {
        return callApi(url, null, "GET", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_GET(URL url, String auth_token) throws HttpException {
        return callApi(url, auth_token, "GET", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_POST(URL url, boolean testingMode) throws HttpException {
        return callApi(url, null, "POST", urlEncodedContentType, null, testingMode);
    }

    public static HttpServiceResponse api_POST(URL url) throws HttpException {
        return callApi(url, null, "POST", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_POST(URL url, String auth_token) throws HttpException {
        return callApi(url, auth_token, "POST", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_POST(URL url, Map<String,String> params) throws HttpException {
        return callApi(url, null, "POST", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_POST(URL url, Map<String,String> params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "POST", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, Map<String,String> params) throws HttpException {
        return callApi(url, null, "PUT", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, Map<String,String> params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "PUT", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, String contentType, Map<String,String> params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "PUT", contentType, params);
    }

    public static HttpServiceResponse api_DELETE(URL url) throws HttpException {
        return callApi(url, null, "DELETE", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_DELETE(URL url, String auth_token) throws HttpException {
        return callApi(url, auth_token, "DELETE", urlEncodedContentType, null);
    }

    private static HttpServiceResponse callApi(URL url, String auth_token, String method, String contentType, Map<String,String> params) throws HttpException {
        return callApi(url, auth_token, method, contentType, params, false);
    }

    private static HttpServiceResponse callApi(URL url, String auth_token, String method, String contentType, Map<String,String> params, boolean testingMode) throws HttpException {
        String returnString = "";
        String errorMessage = "";
        int responseCode = 200;
        boolean throwError = false;
        HttpURLConnection urlConnection = null;
        try {
            //http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0
            System.setProperty("jsse.enableSNIExtension", "false");
            Config config = new Config();

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setRequestProperty("User-Agent", config.userAgent);
            urlConnection.setRequestProperty("App-Id", config.appId);
            urlConnection.setRequestProperty("App-Key", config.appKey);

            if(auth_token != null)
                urlConnection.setRequestProperty("Auth-Token", auth_token);

            if(params != null) {
                //Set params in body
                urlConnection.setDoOutput(true);
                urlConnection.getOutputStream().write(encodeUTF8(params, contentType));
            }

            responseCode = urlConnection.getResponseCode();
            if (!testingMode) {
                log.info("Response code: " + responseCode);
            }
            //System.out.println("Response code: "+responseCode);

            InputStream inputStream = urlConnection.getErrorStream();
            if(responseCode >= 400) {
                throwError = true;
            }
            if(inputStream == null){
                inputStream = urlConnection.getInputStream();
            }

            JsonRepresentationFactory representationFactory = new JsonRepresentationFactory();
            BufferedReader in = new BufferedReader( new InputStreamReader(inputStream) );

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            returnString = response.toString();

            if (!testingMode) {
                if(throwError) {
                    errorMessage = "The call to " + url.toString() + " returned " + urlConnection.getResponseCode() + " . Error message: " + returnString;
                    //System.out.println("Error message: "+ errorMessage);
                } else {
                    Reader reader = new InputStreamReader(new ByteArrayInputStream(returnString.getBytes()));
                    return new HttpServiceResponse(representationFactory.readRepresentation(HAL_JSON, reader), method, params, auth_token);
                }
            } else {
                Reader reader = new InputStreamReader(new ByteArrayInputStream(returnString.getBytes()));
                return new HttpServiceResponse(representationFactory.readRepresentation(HAL_JSON, reader), method, params, auth_token);
            }


        } catch (IOException e) {
            throw new HttpException("Error", returnString, e) ;
        } finally {
            if(urlConnection != null) urlConnection.disconnect();
        } throw new HttpException(errorMessage, returnString, responseCode);
    }

    public PublicRoot start() throws IOException {
        URL url = new URL(new Config().serverUrl);
        return new PublicRoot(api_GET(url));
    }

    public BBRoot start(Class type) throws IOException {
        URL url = new URL(new Config().serverUrl);
        ContentRepresentation representation = api_GET(url).getRep();
        Object obj = null;

        Class[] args = new Class[2];
        args[0] = representation.getClass();
        args[1] = this.getClass();

        try {
            obj = type.getConstructor(ContentRepresentation.class, HttpService.class).newInstance(representation, this);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (BBRoot)obj;
    }
}