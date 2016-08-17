package bookingbugAPI2.services.Cache;

import bookingbugAPI2.services.Http.PlainHttpService;

import javax.annotation.Nullable;

/**
 * Class used for caching HTTP Responses
 */
public abstract class AbstractCacheService {

    private boolean oneTimeFresh = false;
    protected int expiryDurationSec = 5 * 60;

    public boolean isOneTimeFresh() {
        return oneTimeFresh;
    }

    public void setOneTimeFresh(boolean oneTimeFresh) {
        this.oneTimeFresh = oneTimeFresh;
    }

    public int getExpiryDurationSec() {
        return expiryDurationSec;
    }

    /**
     * Sets the expiry duration in seconds. <b>Default is 5 minutes</b>
     * @param expiryDurationSec the duration in seconds
     */
    public void setExpiryDurationSec(int expiryDurationSec) {
        this.expiryDurationSec = expiryDurationSec;
    }

    public abstract void storeResult(String url, String method, String resp, @Nullable String CACHE_TAG);
    public abstract PlainHttpService.NetResponse getDBResponse(String url, String method);
    public abstract void invalidateResult(String url, String method);
    public abstract void invalidateResultsByTag(String CACHE_TAG);

}
