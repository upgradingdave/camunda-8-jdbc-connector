package io.camunda.connector;

import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.error.ConnectorException;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "JDBC",
    inputVariables = {"jdbc"},
    type = "io.camunda:connector-jdbc:1")
public class JdbcConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectorFunction.class);

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
    var connectorRequest = context.getVariablesAsType(JdbcConnectorRequest.class);

    context.validate(connectorRequest);
    context.replaceSecrets(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private JdbcConnectorResult executeConnector(final JdbcConnectorRequest connectorRequest) {

    LOGGER.info("Executing my connector with request {}", connectorRequest);

    JdbcConfig jdbc = connectorRequest.getJdbc();

    //Class.forName ("org.h2.Driver");
    //Connection conn = DriverManager.getConnection ("jdbc:h2:~/test", "sa","");
    //Statement st = conn.createStatement();
    //Stmt.executeUpdate("sql statement");
    //conn.close();

    var result = new JdbcConnectorResult();
    result.setMyProperty("TODO: NOT IMPLEMENTED");
    return result;
  }
}
