package bookingbugAPI2.services.Cache;

import bookingbugAPI2.services.Http.PlainHttpService;

/**
 * Class used for caching HTTP Responses
 */
public abstract class AbstractCacheService {

    private boolean oneTimeFresh = false;

    public boolean isOneTimeFresh() {
        return oneTimeFresh;
    }

    public void setOneTimeFresh(boolean oneTimeFresh) {
        this.oneTimeFresh = oneTimeFresh;
    }

    public abstract void storeResult(String url, String method, String resp);
    public abstract PlainHttpService.NetResponse getDBResponse(String url, String method);
    public abstract void invalidateResult(String url, String method);

}
