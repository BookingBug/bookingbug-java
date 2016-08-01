package bookingbugAPI2.services;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;

/**
 * Created by sebi on 07.07.2016.
 */
public class ConfigService {

    static final String propFileName = "bb_sdk_config.properties";

    public String auth_token;
    public String appId;
    public String appKey;
    public String userAgent;
    public String serverUrl;

    public ConfigService(){
        auth_token = null;
        appId = "";
        appKey = "";
        userAgent = "";
        serverUrl = null;
    }

    public ConfigService(ConfigService configService) {
        auth_token = configService.auth_token;
        appId = configService.appId;
        appKey = configService.appKey;
        userAgent = configService.userAgent;
        serverUrl = configService.serverUrl;
    }

    public ConfigService setAuth_token(String auth_token) {
        this.auth_token = auth_token;
        return this;
    }

    public ConfigService setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public ConfigService setAppKey(String appKey) {
        this.appKey = appKey;
        return this;
    }

    public ConfigService setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public ConfigService setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
        return this;
    }

    public void loadConfigFile(String configString) {
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
            //logger.log(Level.SEVERE, "Exception @ ApiConfig.withConfigFile(): " + e.getMessage());
        }
    }
}
