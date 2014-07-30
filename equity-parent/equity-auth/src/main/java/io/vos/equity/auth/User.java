package io.vos.equity.auth;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.inject.Model;
import javax.management.relation.Role;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class User {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  private String name;

  @NotNull
  private String email;

  @ElementCollection(fetch = FetchType.EAGER)
  @Enumerated(EnumType.STRING)
  private List<Role> roles;

  public User() {
    this.roles = new ArrayList<>();
  }

  public int getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void addRole(Role... roles) {
    for (Role r : roles) {
      this.roles.add(r);
    }
  }
}
