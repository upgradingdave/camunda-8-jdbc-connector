package io.camunda.connector.params;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class JDBCParams {

  @NotEmpty
  private String driverName;

  private String jdbcUrl;

  private String dbName;

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  @Override
  public int hashCode() {
    return Objects.hash(driverName, jdbcUrl);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JDBCParams other = (JDBCParams) obj;
    return Objects.equals(driverName, other.driverName)
        && Objects.equals(jdbcUrl, other.jdbcUrl);
  }

  @Override
  public String toString() {
    return "JdbcConfig [driverName=" + driverName
        + ", jdbcUrl=" + jdbcUrl
        + "]";
  }
}
