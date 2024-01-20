/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.camunda.connector.params;

import java.util.Map;
import java.util.Objects;
import javax.validation.constraints.NotEmpty;

public class CommandParams {

  @NotEmpty private String commandType;

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
        + "commandType="
        + commandType
        + ", sql="
        + sql
        + ", params="
        + params
        + ", mapKey="
        + mapKey
        + "]";
  }
}
