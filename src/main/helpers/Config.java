package helpers;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Config {

    private final static Logger logger = Logger.getLogger(Config.class.getName());

    public String appId = null;
    public String appKey = null;
    public String userAgent = null;
    public String serverUrl = null;

    private static boolean useCustomSettings = false;
    private static String cfgStream = null;


    /**
     * If this method is called with TRUE (first) parameter,
     * the settings will be overridden with the values parsed from the provided (second parameter) string.
     * @param willUseCustomSettings
     * @param configStream
     */
    public void setUseCustomSettings (boolean willUseCustomSettings, String configStream) {
        useCustomSettings = willUseCustomSettings;
        cfgStream = configStream;
    }


    /**
     * Default constructor.
     */
    public Config() {
        try {
            Properties prop = new Properties();

            if (useCustomSettings) {
                prop.load(new StringReader(cfgStream));
                logger.log(Level.ALL, "Settings loaded from the provided string.");
            } else {
                String propFileName = "config.properties";

                //InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(propFileName);
                InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName);
                //InputStream inputStream = new FileInputStream(propFileName);

                if (inputStream != null) {
                    prop.load(inputStream);
                } else {
                    throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
                }

                appId = prop.getProperty("application.auth.appid");
                appKey = prop.getProperty("application.auth.appkey");
                userAgent = prop.getProperty("application.auth.useragent");
                serverUrl = prop.getProperty("application.auth.serverurl");

                logger.log(Level.ALL, "Settings loaded from the properties file.");

                inputStream.close();
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception @ Config(): " + e.getMessage());
        }

    }

}
