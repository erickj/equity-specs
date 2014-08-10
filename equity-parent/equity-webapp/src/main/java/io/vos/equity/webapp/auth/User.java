package io.vos.equity.webapp.auth;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
class User {

  @Id
  private Integer id;
  private String email;

  public User() {}

  public User(Integer id, String email) {
    this.id = id;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }

  public User setId(Integer id) {
    this.id = id;
    return this;

  }

  public String getEmail() {
    return email;
  }

  public User setEmail(String email) {
    this.email = email;
    return this;
  }
}
