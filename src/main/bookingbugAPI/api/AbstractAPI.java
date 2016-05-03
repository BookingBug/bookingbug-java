package bookingbugAPI.api;

import bookingbugAPI.services.CacheService;

/**
 * Abstract API class
 * Contains basic methods and members
 */
public abstract class AbstractAPI {

    String token;
    CacheService cacheService;

    //TODO: Add HTTPService instance here

    AbstractAPI(ApiConfig config){
        // Copy configuration
        this.token = config.token;
        this.cacheService = config.cacheService;
    }

    ApiConfig newConfig() {
        return new ApiConfig()
                .withToken(token)
                .withCache(cacheService);
    }

    /**
     * Class which holds an API configuration
     * @param <T> Keep fluent interface for subclasses
     */
    public static class ApiConfig<T extends ApiConfig>  {

        String token;
        CacheService cacheService;

        public ApiConfig() {}

        public ApiConfig(ApiConfig config) {
            this.token = config.token;
            this.cacheService = config.cacheService;
        }

        public T withToken(String token) {
            this.token = token;
            return (T)this;
        }

        public T withCache(CacheService cacheService) {
            this.cacheService = cacheService;
            return (T)this;
        }
    }
}
