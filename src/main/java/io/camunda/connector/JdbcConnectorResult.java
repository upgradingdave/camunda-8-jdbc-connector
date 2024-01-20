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
package io.camunda.connector;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class JdbcConnectorResult {

  private Map<String, Object> oneResult;
  private List<Map<String, Object>> listResult;
  private Map<Object, List<Map<String, Object>>> mapResult;
  private int resultCount;

  public Map<String, Object> getOneResult() {
    return oneResult;
  }

  public void setOneResult(Map<String, Object> oneResult) {
    this.oneResult = oneResult;
  }

  public List<Map<String, Object>> getListResult() {
    return listResult;
  }

  public void setListResult(List<Map<String, Object>> listResult) {
    this.listResult = listResult;
  }

  public Map<Object, List<Map<String, Object>>> getMapResult() {
    return mapResult;
  }

  public void setMapResult(Map<Object, List<Map<String, Object>>> mapResult) {
    this.mapResult = mapResult;
  }

  public int getResultCount() {
    return resultCount;
  }

  public void setResultCount(int resultCount) {
    this.resultCount = resultCount;
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
    return Objects.equals(oneResult, that.oneResult)
        && Objects.equals(listResult, that.listResult)
        && Objects.equals(mapResult, that.mapResult)
        && Objects.equals(resultCount, that.resultCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(oneResult, listResult, mapResult, resultCount);
  }

  @Override
  public String toString() {
    return "JdbcConnectorResult [oneResult="
        + oneResult
        + "listResult="
        + listResult
        + "mapResult="
        + mapResult
        + "resultCount="
        + resultCount
        + "]";
  }
}
