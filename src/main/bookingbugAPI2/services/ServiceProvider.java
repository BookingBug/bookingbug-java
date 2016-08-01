package bookingbugAPI2.services;

import bookingbugAPI2.services.Cache.AbstractCacheService;
import bookingbugAPI2.services.Http.AbstractHttpService;
import bookingbugAPI2.services.Logger.AbstractLoggerService;

/**
 * Created by sebi on 07.07.2016.
 */
public interface ServiceProvider {

    AbstractHttpService httpService();
    AbstractLoggerService loggerService();
    AbstractCacheService cacheService();
    ConfigService configService();
}
