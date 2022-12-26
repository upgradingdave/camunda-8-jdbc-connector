package io.camunda.connector.db;

import io.camunda.connector.JdbcConnectorRequest;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.params.JDBCParams;
import org.h2.jdbcx.JdbcDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database extends Database {

  private Connection connection;

  @Override
  public Connection getConnection(JdbcConnectorRequest request) {
    JDBCParams jdbc = request.getJdbc();
    //TODO: need to open connection based on each instance, or based on some other criteria?
    if(connection == null) {
      try {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(getJdbcUrl(request), jdbc.getUserName(), jdbc.getPassword());
      } catch (SQLException e) {
        throw new ConnectorException(e);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return connection;
  }

  public DataSource getDatasource(JdbcConnectorRequest request) {
    JDBCParams jdbc = request.getJdbc();
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL(getJdbcUrl(request));
    ds.setUser(jdbc.getUserName());
    ds.setPassword(jdbc.getPassword());
    return ds;
  }

  public String getJdbcUrl(JdbcConnectorRequest request) {
    JDBCParams jdbc = request.getJdbc();
    String jdbcUrl = null;

    // Jdbc Url is specified
    if(jdbc.getCustomJdbcUrl()) {
      jdbcUrl = jdbc.getJdbcUrl();
      if(jdbcUrl == null || jdbcUrl.length() <= 0) {
        throw new ConnectorException(new IllegalStateException("JDBC Url is required"));
      }
      if(!jdbcUrl.startsWith("jdbc:h2")) {
        throw new ConnectorException(new IllegalStateException("H2 Database JDBC Url must start with `jdbc:h2`"));
      }
    } else {
      jdbcUrl = "jdbc:h2:";
      String connectionMode = jdbc.getConnectionMode();
      if(connectionMode != null && connectionMode.equals("mem")) {
        String dbName = jdbc.getDbName();
        if(dbName == null || dbName.length() <= 0) {
          throw new ConnectorException("Database Name is required for in memory H2 database");
        }
        jdbcUrl += "mem:" + dbName;
      } else {
        throw new ConnectorException(new IllegalStateException("Only H2 in memory databases are implemented currently"));
      }
    }
    return jdbcUrl;
  }
}
