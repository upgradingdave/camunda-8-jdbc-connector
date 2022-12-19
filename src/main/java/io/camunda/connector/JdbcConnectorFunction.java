package io.camunda.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.db.Database;
import io.camunda.connector.db.H2Database;
import io.camunda.connector.params.JDBCParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.camunda.connector.JdbcConnectorRequest.*;

@OutboundConnector(
    name = "JDBC",
    inputVariables = {INPUT_JDBC},
    type = "io.camunda:connector-jdbc:1")
public class JdbcConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectorFunction.class);

  Map<String, Database> databases;

  public JdbcConnectorFunction() {
    databases = new HashMap<>();
    databases.put("h2", new H2Database());
  }

  @Override
  public Object execute(OutboundConnectorContext context) {
    var connectorRequest = context.getVariablesAsType(JdbcConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private JdbcConnectorResult executeConnector(final JdbcConnectorRequest connectorRequest) {

    LOGGER.info("Executing my connector with request {}", connectorRequest);

    JDBCParams jdbc = connectorRequest.getJdbc();

    Database database = databases.get(jdbc.getDriverName());
    Connection connection = database.getConnection(connectorRequest);

    var connectorResult = new JdbcConnectorResult();

    if(jdbc.getCommandType().equals("select")) {
      String selectSql = jdbc.getSelectSql();
      try {
        List<Object> result = database.select(connection, selectSql);
        connectorResult.setResultSet(result);
      } catch (SQLException e) {
        throw new ConnectorException(e);
      }
    } else if(jdbc.getCommandType().equals("insert")) {

    }
    return connectorResult;
  }
}
