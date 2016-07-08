package bookingbugAPI.services;

import bookingbugAPI.services.Cache.AbstractCacheService;
import bookingbugAPI.services.Http.AbstractHttpService;
import bookingbugAPI.services.Logger.AbstractLoggerService;

/**
 * Created by sebi on 07.07.2016.
 */
public interface ServiceProvider {

    AbstractHttpService httpService();
    AbstractLoggerService loggerService();
    AbstractCacheService cacheService();
    ConfigService configService();
}
