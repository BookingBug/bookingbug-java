package bookingbugAPI.services;

import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.HttpException;
import helpers.HttpServiceResponse;
import helpers.Utils;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.net.URL;
import java.util.Map;


/**
 * Created by sebi on 23.05.2016.
 */
public class OkHttpService {

    AbstractAPI.ApiConfig config;
    private final OkHttpClient client = new OkHttpClient();

    public OkHttpService(AbstractAPI.ApiConfig config) {
        this.config = config;
    }

    public AbstractAPI.ApiConfig getConfig() {
        return config;
    }

    public HttpServiceResponse api_GET(URL url) throws HttpException {
        return callApi(url, "GET", HttpService.urlEncodedContentType, null);
    }

    public HttpServiceResponse callApi(URL url, String method, String contentType, Map params) throws HttpException {
        HttpService.NetResponse cache = config.cacheService.getDBResponse(url.toString(), method);

        if(cache != null) {
            return new HttpServiceResponse(Utils.stringToContentRep(cache.getResp()), method, contentType, params, config.auth_token);
        }

        Request.Builder builder = new Request.Builder()
                .header("User-Agent", config.userAgent)
                .header("App-Id", config.appId)
                .header("App-Key", config.appKey)
                .url(url.toString());

        if(config.auth_token != null)
            builder.header("Auth-Token", config.auth_token);

        Request request = builder.build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful())
                throw new HttpException("Unexpected code " + response, response.message(), response.code());

            String raw_resp = response.body().string();
            config.cacheService.storeResult(url.toString(), method, raw_resp);

            return new HttpServiceResponse(Utils.stringToContentRep(raw_resp), method, contentType, params, config.auth_token);
        } catch (IOException e) {
            if(e instanceof HttpException) throw (HttpException)e;
            throw new HttpException("Error", e) ;
        }

    }
}
