package io.camunda.connector;

import io.camunda.connector.params.JDBCParams;

import java.util.Objects;

public class JdbcConnectorRequest {

  private JDBCParams jdbc;
  static final String INPUT_JDBC = "jdbc";

  public JDBCParams getJdbc() {
    return jdbc;
  }

  public void setJdbc(JDBCParams jdbc) {
    this.jdbc = jdbc;
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
    return Objects.equals(jdbc, other.jdbc);
  }

  @Override
  public String toString() {
    return "JdbcConnectorRequest [jdbc=" + jdbc + "]";
  }

}
