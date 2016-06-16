package bookingbugAPI.services;

import bookingbugAPI.api.AbstractAPI;
import bookingbugAPI.models.HttpException;
import helpers.Http;
import helpers.HttpServiceResponse;

import java.net.URL;
import java.util.Map;

/**
 * Created by sebi on 15.06.2016.
 */
public abstract class AbstractHttpService {
    AbstractAPI.ApiConfig config;

    public AbstractHttpService(AbstractAPI.ApiConfig config) {
        this.config = config;
    }

    /**
     * Get current service configuration
     * @return {@link bookingbugAPI.api.AbstractAPI.ApiConfig}
     */
    public AbstractAPI.ApiConfig getConfig() {
        return config;
    }


    /**
     * Makes a synchronous GET request
     * @param url URL to get
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_GET(URL url) throws HttpException {
        return callApi(url, "GET", PlainHttpService.urlEncodedContentType, null);
    }


    /**
     * Makes a synchronous POST request with {@link Http#urlEncodedContentType} contentType
     * @param url URL to post to
     * @param params Map, a generic Map containing data to post
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_POST(URL url, Map params) throws HttpException {
        return callApi(url, "POST", PlainHttpService.urlEncodedContentType, params);
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
        return callApi(url, "POST", contentType, params);
    }


    /**
     * Makes a synchronous PUT request with {@link Http#urlEncodedContentType} contentType
     * @param url URL to put to
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_PUT(URL url, Map params) throws HttpException {
        return callApi(url, "PUT", PlainHttpService.urlEncodedContentType, params);
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
        return callApi(url, "PUT", contentType, params);
    }


    /**
     * Makes a synchronous DELETE request
     * @param url URL to delete to
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, null);
    }


    /**
     * Makes a synchronous DELETE request with parameters and {@link Http#urlEncodedContentType} contentType
     * @param url URL to delete to
     * @param params Map, a generic Map containing data to put
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    public HttpServiceResponse api_DELETE(URL url, Map params) throws HttpException {
        return callApi(url, "DELETE", PlainHttpService.urlEncodedContentType, params);
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
        return callApi(url, "DELETE", contentType, params);
    }

    /**
     * Subclasses must implement this
     * @param url URL to call
     * @param method String, can be GET, POST, PUT, DELETE, UPDATE
     * @param contentType String, can be {@link Http#urlEncodedContentType} or {@link Http#jsonContentType}
     * @param params Map, a generic Map with parameters for POST, PUT or UPDATE. Will be serialized according
     *               to {@code contentType}
     * @return {@link HttpServiceResponse}
     * @throws HttpException
     */
    protected abstract HttpServiceResponse callApi(URL url, String method, String contentType, Map params) throws HttpException;

}
