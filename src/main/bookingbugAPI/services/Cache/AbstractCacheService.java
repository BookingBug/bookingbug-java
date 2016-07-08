package bookingbugAPI.services.Cache;

import bookingbugAPI.services.Http.PlainHttpService;

/**
 * Class used for caching HTTP Responses
 */
public abstract class AbstractCacheService {

    public abstract void storeResult(String url, String method, String resp);
    public abstract PlainHttpService.NetResponse getDBResponse(String url, String method);

}
