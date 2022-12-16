package io.camunda.connector.params;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class CommandParams {

  @NotEmpty
  private String commandType;

  private String selectSql;

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

  @Override
  public int hashCode() {
    return Objects.hash(commandType, selectSql);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    CommandParams other = (CommandParams) obj;
    return Objects.equals(commandType, other.commandType)
        && Objects.equals(selectSql, other.selectSql);
  }

  @Override
  public String toString() {
    return "Command [commandType=" + commandType  + ", selectSql=" + selectSql + "]";
  }
}
