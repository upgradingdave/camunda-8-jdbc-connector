package io.camunda.connector;

import io.camunda.connector.api.annotation.Secret;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.Objects;

public class JdbcConnectorRequest {

  private JdbcConfig jdbc;

  private CommandConfig command;

  @Valid
  //@Secret
  @NotNull
  private Authentication authentication;

  public Authentication getAuthentication() {
    return authentication;
  }

  public void setAuthentication(Authentication authentication) {
    this.authentication = authentication;
  }

  public JdbcConfig getJdbc() {
    return jdbc;
  }

  public void setJdbc(JdbcConfig jdbc) {
    this.jdbc = jdbc;
  }

  public CommandConfig getCommand() {
    return command;
  }

  public void setCommand(CommandConfig command) {
    this.command = command;
  }

  @Override
  public int hashCode() {
    return Objects.hash(authentication, jdbc, command);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JdbcConnectorRequest other = (JdbcConnectorRequest) obj;
    return Objects.equals(authentication, other.authentication)
        && Objects.equals(jdbc, other.jdbc)
        && Objects.equals(command, other.command);
  }

  @Override
  public String toString() {
    return "JdbcConnectorRequest [jdbc=" + jdbc + ", command=" + command + ", authentication=" + authentication + "]";
  }

}
