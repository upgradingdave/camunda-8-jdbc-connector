package io.camunda.connector.params;

import javax.validation.constraints.NotEmpty;
import java.util.Map;
import java.util.Objects;

public class CommandParams {

  @NotEmpty
  private String commandType;

  private String sql;

  private Map<Integer, Object> params;

  private String mapKey;

  public String getSql() {
    return sql;
  }

  public void setSql(String sql) {
    this.sql = sql;
  }

  public Map<Integer, Object> getParams() {
    return params;
  }

  public void setParams(Map<Integer, Object> params) {
    this.params = params;
  }

  public String getCommandType() {
    return commandType;
  }

  public void setCommandType(String commandType) {
    this.commandType = commandType;
  }

  public String getMapKey() {
    return mapKey;
  }

  public void setMapKey(String mapKey) {
    this.mapKey = mapKey;
  }

  @Override
  public int hashCode() {
    return Objects.hash(commandType, sql, mapKey);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CommandParams other = (CommandParams) obj;
    return Objects.equals(commandType, other.commandType)
        && Objects.equals(sql, other.sql)
        && Objects.equals(params, other.params)
        && Objects.equals(mapKey, other.mapKey);
  }

  @Override
  public String toString() {
    return "JdbcConfig ["
        + "commandType=" + commandType
        + ", sql=" + sql
        + ", params=" + params
        + ", mapKey=" + mapKey
        + "]";
  }

}
