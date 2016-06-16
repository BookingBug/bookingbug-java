package bookingbugAPI.api;

import bookingbugAPI.services.AbstractHttpService;
import bookingbugAPI.services.CacheService;
import bookingbugAPI.services.OkHttpService;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract API class
 * Contains basic methods and members
 */
public abstract class AbstractAPI {

    protected AbstractHttpService httpService;

    public AbstractAPI(AbstractHttpService httpService, ApiConfig config){
        //httpService = new OkHttpService(config);
        this.httpService = httpService;
    }

    public ApiConfig newConfig() {
        return new ApiConfig(httpService.getConfig());
    }

    protected ApiConfig config() {
        return httpService.getConfig();
    }

    public String getAuthToken(){
        return httpService.getConfig().auth_token;
    }

    public void setAuthToken(String auth_token) {
        httpService.getConfig().withAuthToken(auth_token);
    }

    /**
     * Class which holds an API configuration
     * @param <T> Keep fluent interface for subclasses
     */
    public static class ApiConfig<T extends ApiConfig>  {

        static final String propFileName = "bb_sdk_config.properties";
        private final static Logger logger = Logger.getLogger(ApiConfig.class.getName());

        public String auth_token;
        public String appId;
        public String appKey;
        public String userAgent;
        public String serverUrl;
        public CacheService cacheService;

        public ApiConfig(ApiConfig config) {
            this.auth_token = config.auth_token;
            this.appId = config.appId;
            this.appKey = config.appKey;
            this.userAgent = config.userAgent;
            this.serverUrl = config.serverUrl;
            this.cacheService = config.cacheService;
        }

        public ApiConfig() {
            loadConfigFile(null);
            cacheService = CacheService.JDBC();
        }

        public T withNothing() {
            auth_token = null;
            appId = "";
            appKey = "";
            userAgent = "";
            serverUrl = null;
            cacheService = null;
            return (T) this;
        }

        public T withAuthToken(String token) {
            this.auth_token = token;
            return (T)this;
        }

        public T withCache(CacheService cacheService) {
            this.cacheService = cacheService;
            return (T)this;
        }

        public T withApp(String appId, String appKey) {
            this.appId = appId;
            this.appKey = appKey;
            return (T)this;
        }

        public T withUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return (T)this;
        }

        public T withServerUrl(String serverUrl) {
            this.serverUrl = serverUrl;
            return (T)this;
        }

        public T withConfigString(String configString) {
            loadConfigFile(configString);
            return (T)this;
        }

        private void loadConfigFile(String configString) {
            try{
                Properties prop = new Properties();

                if(configString != null) {
                    prop.load(new StringReader(configString));
                }
                else {
                    InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
                    if (inputStream != null) {
                        prop.load(inputStream);
                    } else {
                        throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                    }
                }

                appId = prop.getProperty("application.auth.appid");
                appKey = prop.getProperty("application.auth.appkey");
                userAgent = prop.getProperty("application.auth.useragent");
                serverUrl = prop.getProperty("application.auth.serverurl");
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Exception @ ApiConfig.withConfigFile(): " + e.getMessage());
            }
        }
    }
}
