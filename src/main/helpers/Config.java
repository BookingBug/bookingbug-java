package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Config {

    private final static Logger logger = Logger.getLogger(Config.class.getName());

    public String appId = null;
    public String appKey = null;
    public String userAgent = null;
    public String serverUrl = null;


    public Config() {
        try {
            Properties prop = new Properties();
            String propFileName = "config.properties";

            //InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(propFileName);
            InputStream inputStream = new FileInputStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            appId = prop.getProperty("application.auth.appid");
            appKey = prop.getProperty("application.auth.appkey");
            userAgent = prop.getProperty("application.auth.useragent");
            serverUrl = prop.getProperty("application.auth.serverurl");

            inputStream.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception @ Config(): " + e.getMessage());
        }

    }

}
