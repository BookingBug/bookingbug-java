package bookingbugAPI2.services.Cache;

import bookingbugAPI2.services.Http.PlainHttpService;
import bookingbugAPI2.services.Logger.AbstractLoggerService;
import bookingbugAPI2.services.ServiceProvider;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Class used for caching HTTP Responses using SQLite db implementation
 */
public class SQLiteCacheService extends AbstractCacheService {

    SQLite db;
    ServiceProvider provider;
    public static final int expiryDurationSec = 5 * 60;

    static {
        //For OrmLite log garbage
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
    }

    public SQLiteCacheService(ServiceProvider provider) {
        this.db = new JDBC_Sqlite();
        this.provider = provider;
    }

    /**
     * Constructor for custom SQLite driver implementation. See {@link SQLite}
     * @param provider the service provider
     * @param db the SQLite implementation
     */
    public SQLiteCacheService(ServiceProvider provider, SQLite db) {
        this.db = db;
        this.provider = provider;
    }

    /**
     * Cache a network result
     * @param url the url
     * @param method the http method (verb)
     * @param resp the response to cache
     */
    @Override
    public void storeResult(String url, String method, String resp) {
        Dao<PlainHttpService.NetResponse, Integer> respDao;
        AbstractLoggerService.Logger logger = provider.loggerService().getLogger(SQLiteCacheService.class.getName());
        try {
            logger.v("Caching result for {0} {1}", url, method);
            db.createIfNotExists();
            respDao = db.getDao();

            PlainHttpService.NetResponse response = new PlainHttpService.NetResponse(url, method, resp);
            respDao.create(response);

        } catch (SQLException e) {
            logger.e(e, "Error when caching result");
        }
    }

    /**
     * Restore from cache
     * @param url the url
     * @param method the http method (verb)
     * @return The response if found or null
     */
    @Override
    public PlainHttpService.NetResponse getDBResponse(String url, String method) {
        AbstractLoggerService.Logger logger = provider.loggerService().getLogger(SQLiteCacheService.class.getName());
        try {
            db.createIfNotExists();
            Dao<PlainHttpService.NetResponse, Integer> respDao = db.getDao();
            QueryBuilder<PlainHttpService.NetResponse, Integer> builder = respDao.queryBuilder();
            builder.where().eq("url", url).and().eq("method", method);
            List<PlainHttpService.NetResponse> responses = respDao.query(builder.prepare());
            if(responses.size() > 0) {

                PlainHttpService.NetResponse response = responses.get(0);

                //Check if response expired or if isFresh() and delete it if true
                if( (Calendar.getInstance().getTimeInMillis() - response.getTimestamp().getTime()) / 1000 > expiryDurationSec || isOneTimeFresh()) {
                    logger.v("Cache for {0} {1} is expired", url, method);
                    respDao.delete(response);
                    setOneTimeFresh(false);
                }
                else {
                    logger.v("Restoring from cache result for {0} {1}", url, method);
                    return responses.get(0);
                }
            }

        } catch (SQLException e) {
            logger.e(e, "Error when restoring from cache");
        }
        return null;
    }

    @Override
    public void invalidateResult(String url, String method) {
        AbstractLoggerService.Logger logger = provider.loggerService().getLogger(SQLiteCacheService.class.getName());
        try {
            db.createIfNotExists();
            Dao<PlainHttpService.NetResponse, Integer> respDao = db.getDao();
            QueryBuilder<PlainHttpService.NetResponse, Integer> builder = respDao.queryBuilder();
            builder.where().eq("url", url).and().eq("method", method);
            List<PlainHttpService.NetResponse> responses = respDao.query(builder.prepare());
            if(responses.size() > 0) {
                PlainHttpService.NetResponse response = responses.get(0);
                logger.v("Cache for {0} {1} invalidated", url, method);
                respDao.delete(response);
            }
        } catch (SQLException e) {
            logger.e(e, "Error when invalidating cache");
        }
    }


    /**
     * Classes should implement this interface to provide proper handling for SQLite db
     */
    public interface SQLite {

        Dao<PlainHttpService.NetResponse, Integer> getDao() throws SQLException;
        ConnectionSource getConnectionSource() throws SQLException;
        void createIfNotExists() throws SQLException;

    }

    /**
     * Implementation for SQLite with JDBC
     */
    public static final class JDBC_Sqlite implements SQLite {

        ConnectionSource connectionSource;
        Dao<PlainHttpService.NetResponse, Integer> dao;

        @Override
        public void createIfNotExists() throws SQLException {
            TableUtils.createTableIfNotExists(getConnectionSource(), PlainHttpService.NetResponse.class);
        }

        @Override
        public Dao<PlainHttpService.NetResponse, Integer> getDao() throws SQLException {
            if(dao == null)
                dao = DaoManager.createDao(getConnectionSource(), PlainHttpService.NetResponse.class);
            return dao;
        }

        @Override
        public ConnectionSource getConnectionSource() throws SQLException {
            if(connectionSource == null)
                connectionSource = new JdbcConnectionSource("jdbc:sqlite:test.db");
            return connectionSource;
        }
    }
}
