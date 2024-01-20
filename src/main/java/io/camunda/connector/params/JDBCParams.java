/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. Licensed under a proprietary license.
 * See the License.txt file for more information. You may not use this file
 * except in compliance with the proprietary license.
 */
package io.camunda.connector.params;

import java.util.Objects;

public class JDBCParams {

  private String userName;

  private String password;

  private String jdbcUrl;

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

  public void setJdbcUrl(String jdbcUrl) {
    this.jdbcUrl = jdbcUrl;
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, password, jdbcUrl);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    JDBCParams other = (JDBCParams) obj;
    return Objects.equals(userName, other.userName)
        && Objects.equals(password, other.password)
        && Objects.equals(jdbcUrl, other.jdbcUrl);
  }

  @Override
  public String toString() {
    return "JdbcConfig ["
        + "jdbcUrl="
        + jdbcUrl
        + ", userName="
        + userName
        + ", password= (elided)"
        + "]";
  }
}
