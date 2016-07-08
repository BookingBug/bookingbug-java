package bookingbugAPI.api;

import bookingbugAPI.services.Http.AbstractHttpService;
import bookingbugAPI.services.Cache.AbstractCacheService;
import bookingbugAPI.services.Cache.CacheService;
import bookingbugAPI.services.ConfigService;
import bookingbugAPI.services.Logger.AbstractLoggerService;
import bookingbugAPI.services.Logger.JavaLoggerService;
import bookingbugAPI.services.Http.OkHttpService;
import bookingbugAPI.services.ServiceProvider;

/**
 * Abstract API class
 * Contains basic methods and members
 */
public abstract class AbstractAPI implements ServiceProvider {

    ServiceProvider provider;

    public AbstractAPI(ServiceProvider provider){
        this.provider = provider;
    }

    /**
     * Returns an ApiConfig with the same configuration as the current one, except that the <b>ConfigService is a clone</b>
     * @return current configuration with ConfigService clone
     */
    public ApiConfig newConfig() {
        ApiConfig clone = new ApiConfig(provider);
        ConfigService newConfig = new ConfigService(provider.configService());
        return clone.withConfigService(newConfig);
    }

    public ServiceProvider newProvider() {
        return (ServiceProvider)newConfig();
    }

    public String getAuthToken(){
        return provider.configService().auth_token;
    }

    @Override
    public AbstractHttpService httpService() {
        return provider.httpService();
    }

    @Override
    public AbstractLoggerService loggerService() {
        return provider.loggerService();
    }

    @Override
    public AbstractCacheService cacheService() {
        return provider.cacheService();
    }

    @Override
    public ConfigService configService() {
        return provider.configService();
    }

    /**
     * Class which holds an API configuration
     * @param <T> Keep fluent interface for subclasses
     */
    public static class ApiConfig<T extends ApiConfig> implements ServiceProvider {

        //Services
        public AbstractCacheService cacheService;
        public AbstractLoggerService loggerService;
        public AbstractHttpService httpService;
        public ConfigService configService;

        public ApiConfig(ServiceProvider provider) {
            this.cacheService = provider.cacheService();
            this.loggerService = provider.loggerService();
            this.httpService = provider.httpService();
            this.configService = provider.configService();
        }

        public ApiConfig() {
            //Load default services
            configService = new ConfigService();
            configService.loadConfigFile(null);

            httpService = new OkHttpService(this);
            cacheService = CacheService.JDBC();
            loggerService = new JavaLoggerService();
        }

        public T withNothing() {
            cacheService = null;
            loggerService = null;
            httpService = null;
            configService = new ConfigService();

            return (T) this;
        }

        public T withAuthToken(String token) {
            this.configService.auth_token = token;
            return (T)this;
        }

        public T withApp(String appId, String appKey) {
            this.configService.appId = appId;
            this.configService.appKey = appKey;
            return (T)this;
        }

        public T withUserAgent(String userAgent) {
            this.configService.userAgent = userAgent;
            return (T)this;
        }

        public T withServerUrl(String serverUrl) {
            this.configService.serverUrl = serverUrl;
            return (T)this;
        }

        public T withConfigString(String configString) {
            configService.loadConfigFile(configString);
            return (T)this;
        }

        public T withCacheService(CacheService cacheService) {
            this.cacheService = cacheService;
            return (T)this;
        }

        public T withConfigService(ConfigService configService) {
            this.configService = configService;
            return (T)this;
        }

        public T withHttpService(AbstractHttpService httpService) {
            this.httpService = httpService;
            return (T)this;
        }

        @Override
        public AbstractHttpService httpService() {
            return httpService;
        }

        @Override
        public AbstractLoggerService loggerService() {
            return loggerService;
        }

        @Override
        public AbstractCacheService cacheService() {
            return cacheService;
        }

        @Override
        public ConfigService configService() {
            return configService;
        }
    }
}
