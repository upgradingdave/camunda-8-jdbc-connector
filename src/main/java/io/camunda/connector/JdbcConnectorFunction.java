/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector;

import static io.camunda.connector.JdbcConnectorRequest.*;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.db.DatabaseManager;
import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.JDBCParams;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "JDBC",
    inputVariables = {INPUT_JDBC, INPUT_COMMAND},
    type = "io.camunda:connector-jdbc:1")
public class JdbcConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectorFunction.class);

  Map<JDBCParams, DatabaseManager> databaseManagers = new HashMap<>();

  @Override
  public Object execute(OutboundConnectorContext context) {
    final var request = context.bindVariables(JdbcConnectorRequest.class);

    LOGGER.info("Executing my connector with request {}", request);

    JDBCParams jdbc = request.getJdbc();
    DatabaseManager db = databaseManagers.get(jdbc);

    if (db == null) {
      db = new DatabaseManager(jdbc);
      databaseManagers.put(jdbc, db);
    }

    CommandParams command = request.getCommand();

    return switch (command.getCommandType()) {
      case "selectOne" -> db.selectOne(command.getSql(), command.getParams());
      case "selectList" -> db.selectList(command.getSql(), command.getParams());
      case "selectMap" -> db.selectMap(command.getSql(), command.getParams(), command.getMapKey());
      case "insert", "delete", "update" -> db.update(command.getSql(), command.getParams());
      default -> throw new UnsupportedOperationException(
          "The command type" + command.getCommandType() + " is not currently supported");
    };
  }
}
