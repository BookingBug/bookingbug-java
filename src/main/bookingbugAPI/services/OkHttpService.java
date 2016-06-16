package bookingbugAPI.services;

import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.HttpException;
import helpers.Http;
import helpers.HttpServiceResponse;
import helpers.Utils;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;


/**
 * Created by sebi on 23.05.2016.
 */
public class OkHttpService extends AbstractHttpService {

    private final OkHttpClient client = new OkHttpClient();

    public OkHttpService(AbstractAPI.ApiConfig config) {
        super(config);
    }


    /**
     * Make a synchronous configurable network request. Uses headers and other config from {@link OkHttpService#config}
     * If the {@code method} is GET, then response caching will be enabled
     * @param url URL to call
     * @param method String, can be GET, POST, PUT, DELETE, UPDATE
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}
     * @param params Map, a generic Map with parameters for POST, PUT or UPDATE. Will be serialized according
     *               to {@code contentType}
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    protected HttpServiceResponse callApi(URL url, String method, String contentType, Map params) throws HttpException {
        PlainHttpService.NetResponse cache = null;

        if(Objects.equals(method, "GET"))
            cache = config.cacheService.getDBResponse(url.toString(), method);

        if(cache != null) {
            return new HttpServiceResponse(Utils.stringToContentRep(cache.getResp()), method, contentType, params, config.auth_token);
        }

        //http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0
        System.setProperty("jsse.enableSNIExtension", "false");

        Request.Builder builder = new Request.Builder()
                .header("User-Agent", config.userAgent)
                .header("App-Id", config.appId)
                .header("App-Key", config.appKey)
                .url(url.toString());

        if(config.auth_token != null)
            builder.header("Auth-Token", config.auth_token);

        String body = null;
        if(params != null) {
            try {
                body = Http.getEncoder(contentType).encode(params);
            } catch (Http.EncodingException | Http.UnknownContentType e) {
                throw new HttpException("Error", e) ;
            }
        }

        if(Objects.equals(method, "POST") && body != null) {
            builder.post(RequestBody.create(MediaType.parse(contentType), body));
        } else if(Objects.equals(method, "PUT") && body != null) {
            builder.put(RequestBody.create(MediaType.parse(contentType), body));
        } else if(Objects.equals(method, "DELETE") && body != null) {
            builder.delete(RequestBody.create(MediaType.parse(contentType), body));
        } else if(Objects.equals(method, "DELETE")) {
            builder.delete();
        }

        Request request = builder.build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new HttpException("Unexpected code " + response, response.message(), response.code());

            String raw_resp = response.body().string();

            if(Objects.equals(method, "GET"))
                config.cacheService.storeResult(url.toString(), method, raw_resp);

            return new HttpServiceResponse(Utils.stringToContentRep(raw_resp), method, contentType, params, config.auth_token);
        } catch (IOException e) {
            if(e instanceof HttpException) throw (HttpException)e;
            throw new HttpException("Error", e) ;
        }

    }
}
