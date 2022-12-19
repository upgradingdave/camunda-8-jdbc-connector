package io.camunda.connector.db;

import io.camunda.connector.JdbcConnectorRequest;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.params.JDBCParams;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Database extends Database {

  private Connection connection;

  public Connection getH2Connection(String jdbcUrl, String userName, String password) {
    //TODO: need to open connection based on each instance, or based on some other criteria?
    if(connection == null) {
      try {
        Class.forName("org.h2.Driver");
        connection = DriverManager.getConnection(jdbcUrl, userName, password);
      } catch (SQLException e) {
        throw new ConnectorException(e);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return connection;
  }

  public Connection getH2Connection(String connectionMode, String url, String dbName, String userName, String password) {

    String jdbcUrl = "jdbc:h2:";
    if(connectionMode != null && connectionMode.equals("mem")) {
      if(dbName == null || dbName.length() <= 0) {
        throw new ConnectorException("Database Name is required for in memory H2 database");
      }
      jdbcUrl += "mem:" + dbName;
    } else {
      throw new ConnectorException(new IllegalStateException("Only H2 in memory databases are implemented at the moment"));
    }

    return getH2Connection(jdbcUrl, userName, password);
  }

  public Boolean validate(JdbcConnectorRequest request) {
    JDBCParams jdbc = request.getJdbc();
    if(jdbc.getCustomJdbcUrl()) {
      String jdbcUrl = jdbc.getJdbcUrl();
      if(jdbcUrl == null || jdbcUrl.length() <= 0) {
        throw new ConnectorException(new IllegalStateException("JDBC Url is required"));
      } else {
        if(!jdbcUrl.startsWith("jdbc:h2")) {
          throw new ConnectorException(new IllegalStateException("H2 Database JDBC Url must start with `jdbc:h2`"));
        }
      }
    }
    return true;
  }

  @Override
  public Connection getConnection(JdbcConnectorRequest request) {
    JDBCParams jdbc = request.getJdbc();

    if(jdbc.getCustomJdbcUrl()) {
      return getH2Connection(jdbc.getJdbcUrl(), jdbc.getUserName(), jdbc.getPassword());
    } else {
      return getH2Connection(jdbc.getConnectionMode(), null, jdbc.getDbName(), jdbc.getUserName(), jdbc.getPassword());
    }
  }
}
