package bookingbugAPI2.services.Http;

import bookingbugAPI2.models.HttpException;
import bookingbugAPI2.services.ConfigService;
import bookingbugAPI2.services.Logger.AbstractLoggerService;
import bookingbugAPI2.services.ServiceProvider;
import helpers2.Http;
import helpers2.HttpServiceResponse;
import helpers2.Utils;
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

    public OkHttpService(ServiceProvider provider) {
        super(provider);
    }


    /**
     * Make a synchronous configurable network request. Uses headers and other config from {@link ServiceProvider#configService()}
     * If the {@code method} is GET, then response caching will be enabled
     * @param url URL to call
     * @param method String, can be GET, POST, PUT, DELETE, UPDATE
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}
     * @param params Map, a generic Map with parameters for POST, PUT or UPDATE. Will be serialized according
     *               to {@code contentType}
     * @param CACHE_TAG the TAG for cache record
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    protected HttpServiceResponse callApi(URL url, String method, String contentType, Map params, String CACHE_TAG) throws HttpException {
        ConfigService config = provider.configService();
        AbstractLoggerService.Logger logger = provider.loggerService().getLogger(OkHttpService.class.getName());
        PlainHttpService.NetResponse cache = null;

        if(Objects.equals(method, "GET"))
            cache = provider.cacheService().getDBResponse(url.toString(), method);

        if(cache != null) {
            logger.v("Response for {0} {1} loaded from cache", method, url);
            return new HttpServiceResponse(Utils.stringToContentRep(cache.getResp()), url.toString(), method, contentType, params, config.auth_token);
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
                logger.e(e, "Unknown Error");
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
            if (!response.isSuccessful()) {
                logger.e("Failed request: {0} {1} {2} {3}", response.code(), method, url, response.message());
                String message = response.message() + response.body().string();
                throw new HttpException("Unexpected code " + response, message, response.code());
            }
            else logger.d("{0} {1} {2}", response.code(), method, url);

            String raw_resp = response.body().string();

            if(Objects.equals(method, "GET"))
                provider.cacheService().storeResult(url.toString(), method, raw_resp, CACHE_TAG);
            else if(CACHE_TAG != null) {
                //POST / PUT / UPDATE / DELETE methods => invalidate cache with provided tag
                provider.cacheService().invalidateResultsByTag(CACHE_TAG);
            }

            return new HttpServiceResponse(Utils.stringToContentRep(raw_resp), url.toString(), method, contentType, params, config.auth_token);
        } catch (IOException e) {
            logger.e(e, "Unknown Error");
            if(e instanceof HttpException) throw (HttpException)e;
            throw new HttpException("Error", e) ;
        }

    }
}
