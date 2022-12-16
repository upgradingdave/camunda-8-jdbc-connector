package io.camunda.connector;

import java.util.List;
import java.util.Objects;

public class JdbcConnectorResult {

  private List<Object> resultSet;

  public List<Object> getResultSet() {
    return resultSet;
  }

  public void setResultSet(List<Object> resultSet) {
    this.resultSet = resultSet;
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    final JdbcConnectorResult that = (JdbcConnectorResult) o;
    return Objects.equals(resultSet, that.resultSet);
  }

  @Override
  public int hashCode() {
    return Objects.hash(resultSet);
  }

  @Override
  public String toString() {
    return "JdbcConnectorResult [resultSet=" + resultSet + "]";
  }

}
