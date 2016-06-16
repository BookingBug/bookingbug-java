package bookingbugAPI.services;

import bookingbugAPI.models.HttpException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTable;
import com.j256.ormlite.table.TableUtils;
import com.theoryinpractise.halbuilder.api.RepresentationFactory;
import helpers.Config;
import helpers.Http;
import helpers.HttpServiceResponse;
import helpers.hal_addon.CustomJsonRepresentationFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static com.theoryinpractise.halbuilder.api.RepresentationFactory.HAL_JSON;

public class PlainHttpService {

    private final static Logger log = Logger.getLogger(PlainHttpService.class.getName());

    public final static String jsonContentType = "application/json";
    public final static String urlEncodedContentType = "application/x-www-form-urlencoded";
    public final static String UTF8Encoding = "UTF-8";


    public static byte[] encodeUTF8(Map params, String contentType) throws Http.UnknownContentType, Http.EncodingException, UnsupportedEncodingException {
        return Http.getEncoder(contentType)
                .encode(params)
                .getBytes(UTF8Encoding);
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

    public static HttpServiceResponse api_POST(URL url, Map params) throws HttpException {
        return callApi(url, null, "POST", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_POST(URL url, Map params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "POST", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, Map params) throws HttpException {
        return callApi(url, null, "PUT", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, Map params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "PUT", urlEncodedContentType, params);
    }

    public static HttpServiceResponse api_PUT(URL url, String contentType, Map params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "PUT", contentType, params);
    }

    public static HttpServiceResponse api_DELETE(URL url) throws HttpException {
        return callApi(url, null, "DELETE", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_DELETE(URL url, String auth_token) throws HttpException {
        return callApi(url, auth_token, "DELETE", urlEncodedContentType, null);
    }

    public static HttpServiceResponse api_DELETE(URL url, String contentType, Map params, String auth_token) throws HttpException {
        return callApi(url, auth_token, "DELETE", contentType, params);
    }

    private static HttpServiceResponse callApi(URL url, String auth_token, String method, String contentType, Map params) throws HttpException {
        return callApi(url, auth_token, method, contentType, params, false);
    }

    private static HttpServiceResponse callApi(URL url, String auth_token, String method, String contentType, Map params, boolean testingMode) throws HttpException {
        String returnString = "";
        String errorMessage = "";
        byte[] bodyBytes = new byte[0];
        int responseCode = 200;
        boolean throwError = false;
        HttpURLConnection urlConnection = null;
        NetResponse cache = getDBResponse(url.toString(), method);

        try {

            if(cache != null) {
                CustomJsonRepresentationFactory representationFactory = new CustomJsonRepresentationFactory();
                representationFactory.withFlag(RepresentationFactory.STRIP_NULLS);
                Reader reader = new InputStreamReader(new ByteArrayInputStream(cache.resp.getBytes()));
                return new HttpServiceResponse(representationFactory.readRepresentation(HAL_JSON, reader), method, contentType, params, auth_token);
            }

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
                bodyBytes = encodeUTF8(params, contentType);
                urlConnection.getOutputStream().write(bodyBytes);
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

            CustomJsonRepresentationFactory representationFactory = new CustomJsonRepresentationFactory();
            representationFactory.withFlag(RepresentationFactory.STRIP_NULLS);

            BufferedReader in = new BufferedReader( new InputStreamReader(inputStream) );

            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            returnString = response.toString();

            storeResult(url.toString(), method, returnString);

            if (!testingMode) {
                if(throwError) {
                    errorMessage = "The call to " + url.toString()
                            + "with parameters " + new String(bodyBytes, UTF8Encoding) + " returned "
                            + urlConnection.getResponseCode() + " . Error message: " + returnString;
                    //System.out.println("Error message: "+ errorMessage);
                } else {
                    Reader reader = new InputStreamReader(new ByteArrayInputStream(returnString.getBytes()));
                    return new HttpServiceResponse(representationFactory.readRepresentation(HAL_JSON, reader), method, contentType, params, auth_token);
                }
            } else {
                Reader reader = new InputStreamReader(new ByteArrayInputStream(returnString.getBytes()));
                return new HttpServiceResponse(representationFactory.readRepresentation(HAL_JSON, reader), method, contentType, params, auth_token);
            }

        } catch (IOException e) {
            throw new HttpException("Error", returnString, e) ;
        } catch (Http.EncodingException | Http.UnknownContentType e) {
            throw new HttpException("Error when writing body params", e) ;
        } finally {
            if(urlConnection != null) urlConnection.disconnect();
        } throw new HttpException(errorMessage, returnString, responseCode);
    }

    private static NetResponse getDBResponse(String url, String method) {
        Dao<NetResponse, Integer> respDao;
        ConnectionSource connectionSource = null;
        try {

            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:test.db");
            respDao = DaoManager.createDao(connectionSource, NetResponse.class);
            TableUtils.createTableIfNotExists(connectionSource, NetResponse.class);

            QueryBuilder<NetResponse, Integer> builder = respDao.queryBuilder();
            builder.where().eq("url", url).and().eq("method", method);
            List<NetResponse> responses = respDao.query(builder.prepare());
            if(responses.size() > 0)
                return responses.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static void storeResult(String url, String method, String str) {

        Dao<NetResponse, Integer> respDao;
        ConnectionSource connectionSource = null;
        try {

            // create our data-source for the database
            connectionSource = new JdbcConnectionSource("jdbc:sqlite:test.db");
            respDao = DaoManager.createDao(connectionSource, NetResponse.class);
            TableUtils.createTableIfNotExists(connectionSource, NetResponse.class);

            NetResponse response = new NetResponse(url, method, str);
            respDao.create(response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // destroy the data source which should close underlying connections
            if (connectionSource != null) {
                try {
                    connectionSource.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @DatabaseTable(tableName = "net_response")
    public static class NetResponse {
        @DatabaseField(generatedId = true)
        private int id;

        @DatabaseField
        private String url;

        @DatabaseField
        private String method;

        @DatabaseField
        private String resp;

        public NetResponse(){}

        public NetResponse(String url, String method, String resp) {
            this.url = url;
            this.method = method;
            this.resp = resp;
        }

        public String getResp() {
            return resp;
        }
    }
}