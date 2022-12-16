package io.camunda.connector;

import io.camunda.connector.params.AuthenticationParams;
import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.H2Params;
import io.camunda.connector.params.JDBCParams;

import java.util.Objects;

public class JdbcConnectorRequest {

  private JDBCParams jdbc;
  static final String INPUT_JDBC = "jdbc";

  private CommandParams command;
  static final String INPUT_COMMAND = "command";

  private H2Params h2;
  static final String INPUT_H2 = "h2";

  //@Secret
  private AuthenticationParams authentication;

  public AuthenticationParams getAuthentication() {
    return authentication;
  }

  public void setAuthentication(AuthenticationParams authentication) {
    this.authentication = authentication;
  }

  public JDBCParams getJdbc() {
    return jdbc;
  }

  public void setJdbc(JDBCParams jdbc) {
    this.jdbc = jdbc;
  }

  public CommandParams getCommand() {
    return command;
  }

  public void setCommand(CommandParams command) {
    this.command = command;
  }

  public H2Params getH2() {
    return h2;
  }

  public void setH2(H2Params h2) {
    this.h2 = h2;
  }

  @Override
  public int hashCode() {
    return Objects.hash(authentication, jdbc, command, h2);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JdbcConnectorRequest other = (JdbcConnectorRequest) obj;
    return Objects.equals(authentication, other.authentication)
        && Objects.equals(jdbc, other.jdbc)
        && Objects.equals(h2, other.h2)
        && Objects.equals(command, other.command);
  }

  @Override
  public String toString() {
    return "JdbcConnectorRequest [jdbc=" + jdbc
        + ", command=" + command
        + ", authentication=" + authentication
        + ", h2=" + h2 + "]";
  }

}
