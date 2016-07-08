package bookingbugAPI.services.Cache;

import bookingbugAPI.services.Http.PlainHttpService;

/**
 * Created by sebi on 07.07.2016.
 */
public abstract class AbstractCacheService {

    public abstract void storeResult(String url, String method, String resp);
    public abstract PlainHttpService.NetResponse getDBResponse(String url, String method);

}
