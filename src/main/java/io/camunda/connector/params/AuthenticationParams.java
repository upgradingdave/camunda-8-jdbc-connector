package io.camunda.connector.params;

import java.util.Objects;

public class AuthenticationParams {

  private String userName;

  //@Secret
  private String password;

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

  @Override
  public int hashCode() {
    return Objects.hash(password, userName);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    AuthenticationParams other = (AuthenticationParams) obj;
    return Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
  }

  @Override
  public String toString() {
    return "Authentication [userName=" + userName + ", password=" + password + "]";
  }
}
