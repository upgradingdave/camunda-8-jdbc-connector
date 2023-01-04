package io.camunda.connector.params;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class JDBCParams {

  private String userName;

  //@Secret
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
    return Objects.hash(
        userName, password, jdbcUrl);
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
        + "jdbcUrl=" + jdbcUrl
        + ", userName=" + userName
        + ", password= (elided)"
        + "]";
  }
}
