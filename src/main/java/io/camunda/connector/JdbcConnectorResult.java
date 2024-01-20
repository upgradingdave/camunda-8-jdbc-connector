/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
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
