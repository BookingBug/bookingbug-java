package bookingbugAPI.services.Cache;

import bookingbugAPI.services.Http.PlainHttpService;

/**
 * Class used for mocking a cache service.
 * {@link #getDBResponse(String, String)} always returns null
 * {@link #storeResult(String, String, String)} does nothing at all
 */
public class MockCacheService extends AbstractCacheService {
    @Override
    public void storeResult(String url, String method, String resp) {

    }

    @Override
    public PlainHttpService.NetResponse getDBResponse(String url, String method) {
        return null;
    }
}
