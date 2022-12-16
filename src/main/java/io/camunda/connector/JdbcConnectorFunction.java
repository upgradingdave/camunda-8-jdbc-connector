package io.camunda.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.params.AuthenticationParams;
import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.H2Params;
import io.camunda.connector.params.JDBCParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static io.camunda.connector.JdbcConnectorRequest.*;

@OutboundConnector(
    name = "JDBC",
    inputVariables = {INPUT_JDBC, INPUT_COMMAND, INPUT_H2},
    type = "io.camunda:connector-jdbc:1")
public class JdbcConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectorFunction.class);

  private Connection h2Connection;

  @Override
  public Object execute(OutboundConnectorContext context) {
    var connectorRequest = context.getVariablesAsType(JdbcConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  public Connection getH2Connection(String jdbcUrl, String userName, String password) {
    //TODO: need to open connection based on each instance, or based on some other criteria?
    if(h2Connection == null) {
      try {
        Class.forName("org.h2.Driver");
        h2Connection = DriverManager.getConnection(jdbcUrl, userName, password);
      } catch (SQLException e) {
        throw new ConnectorException(e);
      } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
      }
    }
    return h2Connection;
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

  public List<Object> convertResult(ResultSet resultSet) throws SQLException {
    final List<Object> result = new ArrayList<>();
    while (resultSet.next()) {
      final List<Object> row = new ArrayList<>();
      int colCount = resultSet.getMetaData().getColumnCount();
      for (int i = 0; i < colCount; i++) {
        row.add(resultSet.getObject(i+1));
      }
      result.add(row);
    }
    return result;
  }

  public List<Object> select(Connection connection, final String query) throws ConnectorException, SQLException {
    Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    ResultSet resultSet = statement.executeQuery(query);
    return convertResult(resultSet);
  }

  private JdbcConnectorResult executeConnector(final JdbcConnectorRequest connectorRequest) {

    LOGGER.info("Executing my connector with request {}", connectorRequest);

    JDBCParams jdbc = connectorRequest.getJdbc();
    AuthenticationParams auth = connectorRequest.getAuthentication();
    CommandParams command = connectorRequest.getCommand();

    Connection connection = null;

    if(jdbc.getDriverName().equals("h2")) {
      H2Params h2 = connectorRequest.getH2();
      connection = getH2Connection(h2.getConnectionMode(), null, h2.getDbName(), auth.getUserName(), auth.getPassword());
    } else if(jdbc.getDriverName().equals("jdbc")) {
      String jdbcUrl = jdbc.getJdbcUrl();
      if(jdbcUrl == null || jdbcUrl.length() < 0) {
        throw new ConnectorException(new IllegalStateException("JDBC Url is required"));
      } else {
        if(jdbcUrl.startsWith("jdbc:h2")) {
          connection = getH2Connection(jdbcUrl, auth.getUserName(), auth.getPassword());
        }
      }
    }

    var connectorResult = new JdbcConnectorResult();

    String selectSql = command.getSelectSql();
    try {
      List<Object> result = select(connection, selectSql);
      connectorResult.setResultSet(result);
    } catch (SQLException e) {
      throw new ConnectorException(e);
    }

    return connectorResult;
  }
}
