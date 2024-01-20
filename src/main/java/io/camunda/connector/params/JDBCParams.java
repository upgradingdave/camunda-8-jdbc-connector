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

import io.camunda.connector.api.annotation.Secret;
import java.util.Objects;

public class JDBCParams {

  private String userName;

  @Secret private String password;

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
