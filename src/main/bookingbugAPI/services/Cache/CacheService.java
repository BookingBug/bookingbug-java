package bookingbugAPI.services.Cache;

import bookingbugAPI.services.Http.PlainHttpService;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.logger.LocalLog;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Class used for caching HTTP Responses
 */
public class CacheService extends AbstractCacheService {

    SQLite db;
    boolean mock;

    static {
        //For OrmLite log garbage
        System.setProperty(LocalLog.LOCAL_LOG_LEVEL_PROPERTY, "ERROR");
    }

    public CacheService(SQLite db, boolean mock) {
        this.db = db;
        this.mock = mock;
    }

    public static CacheService JDBC() {
        return new CacheService(new JDBC_Sqlite(), false);
    }

    public static CacheService MOCK() {
        return new CacheService(new JDBC_Sqlite(), true);
    }

    @Override
    public void storeResult(String url, String method, String resp) {
        if(mock) return;

        Dao<PlainHttpService.NetResponse, Integer> respDao;

        try {
            db.createIfNotExists();
            respDao = db.getDao();

            PlainHttpService.NetResponse response = new PlainHttpService.NetResponse(url, method, resp);
            respDao.create(response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlainHttpService.NetResponse getDBResponse(String url, String method) {
        if(mock) return null;
        try {
            db.createIfNotExists();
            Dao<PlainHttpService.NetResponse, Integer> respDao = db.getDao();
            QueryBuilder<PlainHttpService.NetResponse, Integer> builder = respDao.queryBuilder();
            builder.where().eq("url", url).and().eq("method", method);
            List<PlainHttpService.NetResponse> responses = respDao.query(builder.prepare());
            if(responses.size() > 0)
                return responses.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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
