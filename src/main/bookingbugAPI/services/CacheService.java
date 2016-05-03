package bookingbugAPI.services;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Class used for caching HTTP Responses
 */
public class CacheService  {

    SQLite db;

    public CacheService() {
        db = new JDBC_Sqlite();
    }

    public void storeResult(String url, String method, String resp) {
        Dao<HttpService.NetResponse, Integer> respDao;

        try {
            db.createIfNotExists();
            respDao = db.getDao();

            HttpService.NetResponse response = new HttpService.NetResponse(url, method, resp);
            respDao.create(response);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HttpService.NetResponse getDBResponse(String url, String method) {
        try {
            db.createIfNotExists();
            Dao<HttpService.NetResponse, Integer> respDao = db.getDao();
            QueryBuilder<HttpService.NetResponse, Integer> builder = respDao.queryBuilder();
            builder.where().eq("url", url).and().eq("method", method);
            List<HttpService.NetResponse> responses = respDao.query(builder.prepare());
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

        Dao<HttpService.NetResponse, Integer> getDao() throws SQLException;
        ConnectionSource getConnectionSource() throws SQLException;
        void createIfNotExists() throws SQLException;

    }

    /**
     * Implementation for SQLite with JDBC
     */
    public final class JDBC_Sqlite implements SQLite {

        ConnectionSource connectionSource;
        Dao<HttpService.NetResponse, Integer> dao;

        @Override
        public void createIfNotExists() throws SQLException {
            TableUtils.createTableIfNotExists(getConnectionSource(), HttpService.NetResponse.class);
        }

        @Override
        public Dao<HttpService.NetResponse, Integer> getDao() throws SQLException {
            if(dao == null)
                dao = DaoManager.createDao(getConnectionSource(), HttpService.NetResponse.class);
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
