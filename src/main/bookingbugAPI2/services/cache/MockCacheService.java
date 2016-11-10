package bookingbugAPI2.services.cache;

import bookingbugAPI2.services.http.PlainHttpService;

import javax.annotation.Nullable;

/**
 * Class used for mocking a cache service.
 * {@link #getDBResponse(String, String)} always returns null
 * {@link #storeResult(String, String, String, String)} does nothing at all
 */
public class MockCacheService extends AbstractCacheService {
    @Override
    public void storeResult(String url, String method, String resp, @Nullable String CACHE_KEY) {

    }

    @Override
    public PlainHttpService.NetResponse getDBResponse(String url, String method) {
        return null;
    }

    @Override
    public void invalidateResult(String url, String method) {

    }

    @Override
    public void invalidateResultsByTag(String CACHE_TAG) {

    }
}
