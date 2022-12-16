package io.camunda.connector;

import javax.validation.constraints.NotEmpty;

import java.util.Objects;

public class Authentication {

  @NotEmpty
  private String userName;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @NotEmpty
  //@Secret
  private String password;

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
    Authentication other = (Authentication) obj;
    return Objects.equals(password, other.password) && Objects.equals(userName, other.userName);
  }

  @Override
  public String toString() {
    return "Authentication [userName=" + userName + ", password=" + password + "]";
  }
}
