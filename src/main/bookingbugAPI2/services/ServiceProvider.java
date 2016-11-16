package bookingbugAPI2.services;

import bookingbugAPI2.services.cache.AbstractCacheService;
import bookingbugAPI2.services.http.AbstractHttpService;
import bookingbugAPI2.services.logger.AbstractLoggerService;

/**
 * Created by sebi on 07.07.2016.
 */
public interface ServiceProvider {

    AbstractHttpService httpService();
    AbstractLoggerService loggerService();
    AbstractCacheService cacheService();
    ConfigService configService();
}
