package io.camunda.connector.params;

import java.util.Objects;

public class H2Params {

  private String connectionMode;

  private String host;

  private String port;

  private String dbName;

  public String getConnectionMode() {
    return connectionMode;
  }

  public void setConnectionMode(String connectionMode) {
    this.connectionMode = connectionMode;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, port, dbName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    H2Params other = (H2Params) obj;
    return Objects.equals(connectionMode, other.connectionMode)
        && Objects.equals(host, other.host)
        && Objects.equals(port, other.port)
        && Objects.equals(dbName, other.dbName);
  }

  @Override
  public String toString() {
    return "JdbcConfig [connectionMode=" + connectionMode
        + ", host=" + host
        + ", port=" + port
        + ", dbName=" + dbName
        + "]";
  }
}
