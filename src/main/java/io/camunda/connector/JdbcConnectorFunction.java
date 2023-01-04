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

  private JdbcConnectorResult executeConnector(final JdbcConnectorRequest connectorRequest) {

    LOGGER.info("Executing my connector with request {}", connectorRequest);

    JDBCParams jdbc = connectorRequest.getJdbc();
    DatabaseManager db = databaseManagers.get(jdbc);

    if(db == null) {
      db = new DatabaseManager(jdbc);
      databaseManagers.put(jdbc, db);
    }

    CommandParams command = connectorRequest.getCommand();
    JdbcConnectorResult result = new JdbcConnectorResult();
    //TODO: implement params by getting some input from the process diagram. But for now, it's just empty
    //TODO: also should change from map to list
    Map<String, Object> params = new HashMap<>();

    if(command.getCommandType().equals("selectOne")) {
      result.setOneResult(db.selectOne(command.getSql(), params));
    } else if(command.getCommandType().equals("selectList")) {
      result.setListResult(db.selectList(command.getSql(), params));
    } else if(command.getCommandType().equals("selectMap")) {
      result.setMapResult(db.selectMap(command.getSql(), params, command.getMapKey()));
    } else if(command.getCommandType().equals("insert")) {
      result.setResultCount(db.update(command.getSql(), params));
    } else if(command.getCommandType().equals("update")) {
      result.setResultCount(db.update(command.getSql(), params));
    } else if(command.getCommandType().equals("delete")) {
      result.setResultCount(db.update(command.getSql(), params));
    } else {
      throw new UnsupportedOperationException("The command type" + command.getCommandType() + " is not currently supported");
    }
    return result;
  }
}
