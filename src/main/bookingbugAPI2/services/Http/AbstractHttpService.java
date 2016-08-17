package bookingbugAPI2.services.Http;

import bookingbugAPI2.models.HttpException;
import bookingbugAPI2.services.ServiceProvider;
import helpers2.Http;
import helpers2.HttpServiceResponse;

import javax.annotation.Nullable;
import java.net.URL;
import java.util.Map;

/**
 * Created by sebi on 15.06.2016.
 */
public abstract class AbstractHttpService {
    protected final ServiceProvider provider;

    public AbstractHttpService(ServiceProvider provider) {
        this.provider = provider;
    }

    /**
     * Makes a synchronous GET request
     * @param url URL to get
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_GET(URL url) throws HttpException {
        return callApi(url, "GET", PlainHttpService.urlEncodedContentType, null, null);
    }

    /**
     * Makes a synchronous GET request
     * @param url URL to get
     * @param CACHE_TAG the TAG for cache record
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_GET(URL url, String CACHE_TAG) throws HttpException {
        return callApi(url, "GET", PlainHttpService.urlEncodedContentType, null, CACHE_TAG);
    }


    /**
     * Makes a synchronous POST request with {@link Http#urlEncodedContentType} contentType
     * @param url URL to post to
     * @param params Map, a generic Map containing data to post
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_POST(URL url, Map params) throws HttpException {
        return callApi(url, "POST", PlainHttpService.urlEncodedContentType, params, null);
    }

    /**
     * Makes a synchronous POST request with {@link Http#urlEncodedContentType} contentType.
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to post to
     * @param params Map, a generic Map containing data to post
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_POST(URL url, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "POST", PlainHttpService.urlEncodedContentType, params, CACHE_TAG);
    }


    /**
     * Makes a synchronous POST request with specific contentType
     * @param url URL to post to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to post
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_POST(URL url, String contentType, Map params) throws HttpException {
        return callApi(url, "POST", contentType, params, null);
    }

    /**
     * Makes a synchronous POST request with specific contentType
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to post to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to post
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_POST(URL url, String contentType, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "POST", contentType, params, CACHE_TAG);
    }


    /**
     * Makes a synchronous PUT request with {@link Http#urlEncodedContentType} contentType
     * @param url URL to put to
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_PUT(URL url, Map params) throws HttpException {
        return callApi(url, "PUT", PlainHttpService.urlEncodedContentType, params, null);
    }

    /**
     * Makes a synchronous PUT request with {@link Http#urlEncodedContentType} contentType
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to put to
     * @param params Map, a generic Map containing data to put
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_PUT(URL url, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "PUT", PlainHttpService.urlEncodedContentType, params, CACHE_TAG);
    }


    /**
     * Makes a synchronous PUT request with specific contentType
     * @param url URL to put to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_PUT(URL url, String contentType, Map params) throws HttpException {
        return callApi(url, "PUT", contentType, params, null);
    }

    /**
     * Makes a synchronous PUT request with specific contentType
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to put to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to put
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_PUT(URL url, String contentType, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "PUT", contentType, params, CACHE_TAG);
    }


    /**
     * Makes a synchronous DELETE request
     * @param url URL to delete to
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, null, null);
    }

    /**
     * Makes a synchronous DELETE request
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to delete to
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, null, CACHE_TAG);
    }


    /**
     * Makes a synchronous DELETE request with parameters and {@link Http#urlEncodedContentType} contentType
     * @param url URL to delete to
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, Map params) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, params, null);
    }

    /**
     * Makes a synchronous DELETE request with parameters and {@link Http#urlEncodedContentType} contentType
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to delete to
     * @param params Map, a generic Map containing data to put
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, params, CACHE_TAG);
    }


    /**
     * Makes a synchronous DELETE request with parameters and specific contentType
     * @param url URL to delete to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, String contentType, Map params) throws HttpException {
        return callApi(url, "DELETE", contentType, params, null);
    }

    /**
     * Makes a synchronous DELETE request with parameters and specific contentType
     * <i>Will invalidate all caches with {@code tag} if not null</i>
     * @param url URL to delete to
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}.
     * @param params Map, a generic Map containing data to put
     * @param CACHE_TAG the cache TAG to invalidate
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, String contentType, Map params, @Nullable String CACHE_TAG) throws HttpException {
        return callApi(url, "DELETE", contentType, params, CACHE_TAG);
    }

    /**
     * Subclasses must implement this
     * @param url URL to call
     * @param method String, can be GET, POST, PUT, DELETE, UPDATE
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}
     * @param params Map, a generic Map with parameters for POST, PUT or UPDATE. Will be serialized according
     *               to {@code contentType}
     * @param CACHE_TAG the TAG for cache record, used only by GET
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    protected abstract HttpServiceResponse callApi(URL url, String method, String contentType, Map params, @Nullable String CACHE_TAG) throws HttpException;

}
