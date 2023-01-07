package io.camunda.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.db.DatabaseManager;
import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.JDBCParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static io.camunda.connector.JdbcConnectorRequest.*;

@OutboundConnector(
    name = "JDBC",
    inputVariables = {INPUT_JDBC, INPUT_COMMAND},
    type = "io.camunda:connector-jdbc:1")
public class JdbcConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectorFunction.class);

  Map<JDBCParams, DatabaseManager> databaseManagers = new HashMap<>();

  @Override
  public Object execute(OutboundConnectorContext context) {
    var connectorRequest = context.getVariablesAsType(JdbcConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private Object executeConnector(final JdbcConnectorRequest connectorRequest) {

    LOGGER.info("Executing my connector with request {}", connectorRequest);

    JDBCParams jdbc = connectorRequest.getJdbc();
    DatabaseManager db = databaseManagers.get(jdbc);

    if(db == null) {
      db = new DatabaseManager(jdbc);
      databaseManagers.put(jdbc, db);
    }

    CommandParams command = connectorRequest.getCommand();

    if(command.getCommandType().equals("selectOne")) {
      return db.selectOne(command.getSql(), command.getParams());
    } else if(command.getCommandType().equals("selectList")) {
      return db.selectList(command.getSql(), command.getParams());
    } else if(command.getCommandType().equals("selectMap")) {
      return db.selectMap(command.getSql(), command.getParams(), command.getMapKey());
    } else if(command.getCommandType().equals("insert")) {
      return db.update(command.getSql(), command.getParams());
    } else if(command.getCommandType().equals("update")) {
      return db.update(command.getSql(), command.getParams());
    } else if(command.getCommandType().equals("delete")) {
      return db.update(command.getSql(), command.getParams());
    } else {
      throw new UnsupportedOperationException("The command type" + command.getCommandType() + " is not currently supported");
    }
  }
}
