package io.camunda.connector;

import io.camunda.connector.params.CommandParams;
import io.camunda.connector.params.JDBCParams;

import java.util.Objects;

public class JdbcConnectorRequest {

  private JDBCParams jdbc;
  static final String INPUT_JDBC = "jdbc";

  private CommandParams command;
  static final String INPUT_COMMAND = "command";

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

  @Override
  public int hashCode() {
    return Objects.hash(jdbc);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JdbcConnectorRequest other = (JdbcConnectorRequest) obj;
    return Objects.equals(jdbc, other.jdbc)
        && Objects.equals(command, other.command);
  }

  @Override
  public String toString() {
    return "JdbcConnectorRequest [jdbc=" + jdbc + ", command=" + command + "]";
  }

}
