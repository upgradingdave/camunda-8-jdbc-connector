package io.camunda.connector;

import io.camunda.connector.api.annotation.Secret;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class JdbcConfig {

  @NotEmpty
  private String driverName;

  private String driverSubProtocol;

  private String host;

  private String port;

  private String dbName;

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String getDriverSubProtocol() {
    return driverSubProtocol;
  }

  public void setDriverSubProtocol(String driverSubProtocol) {
    this.driverSubProtocol = driverSubProtocol;
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
    return Objects.hash(driverName, driverSubProtocol, host, port, dbName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JdbcConfig other = (JdbcConfig) obj;
    return Objects.equals(driverName, other.driverName)
        && Objects.equals(driverSubProtocol, other.driverSubProtocol)
        && Objects.equals(host, other.host)
        && Objects.equals(port, other.port)
        && Objects.equals(dbName, other.dbName);
  }

  @Override
  public String toString() {
    return "JdbcConfig [driverName=" + driverName
        + ", driverSubProtocol=" + driverSubProtocol
        + ", host=" + host
        + ", port=" + port
        + ", dbName=" + dbName
        + "]";
  }
}
