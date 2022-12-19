package io.camunda.connector.params;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class JDBCParams {

  @NotEmpty
  private String driverName;

  private String userName;

  //@Secret
  private String password;

  private Boolean customJdbcUrl;
  private String jdbcUrl;

  private String dbName;

  private String connectionMode;

  private String host;

  private String port;

  @NotEmpty
  private String commandType;

  private String selectSql;

  public String getDriverName() {
    return driverName;
  }

  public void setDriverName(String driverName) {
    this.driverName = driverName;
  }

  public String getJdbcUrl() {
    return jdbcUrl;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCommandType() {
    return commandType;
  }

  public void setCommandType(String commandType) {
    this.commandType = commandType;
  }

  public String getSelectSql() {
    return selectSql;
  }

  public void setSelectSql(String selectSql) {
    this.selectSql = selectSql;
  }

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  public Boolean getCustomJdbcUrl() {
    return customJdbcUrl;
  }

  public void setCustomJdbcUrl(Boolean customJdbcUrl) {
    this.customJdbcUrl = customJdbcUrl;
  }

  public String getDbName() {
    return dbName;
  }

  public void setDbName(String dbName) {
    this.dbName = dbName;
  }

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

  @Override
  public int hashCode() {
    return Objects.hash(
        driverName, userName, password, customJdbcUrl, jdbcUrl, dbName, host, port, connectionMode, commandType, selectSql);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JDBCParams other = (JDBCParams) obj;
    return Objects.equals(driverName, other.driverName)
        && Objects.equals(userName, other.userName)
        && Objects.equals(password, other.password)
        && Objects.equals(customJdbcUrl, other.customJdbcUrl)
        && Objects.equals(jdbcUrl, other.jdbcUrl)
        && Objects.equals(dbName, other.dbName)
        && Objects.equals(host, other.host)
        && Objects.equals(port, other.port)
        && Objects.equals(connectionMode, other.connectionMode)
        && Objects.equals(commandType, other.commandType)
        && Objects.equals(selectSql, other.selectSql);
  }

  @Override
  public String toString() {
    return "JdbcConfig ["
        + "driverName=" + driverName
        + ", userName=" + userName
        + ", password=" + password
        + ", password=" + customJdbcUrl
        + ", jdbcUrl=" + jdbcUrl
        + ", dbName=" + dbName
        + ", host=" + host
        + ", port=" + port
        + ", connectionMode=" + connectionMode
        + ", commandType=" + commandType
        + ", selectSql=" + selectSql
        + "]";
  }
}
