package io.vos.equity.model;

import com.google.common.base.Preconditions;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "equity", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class Equity implements Model {

  @Id
  @GeneratedValue
  private int id;

  @NotNull
  private String name;

  @NotNull
  private String value;

  public Equity() {}

  public Equity setName(String name) {
    this.name = Preconditions.checkNotNull(name);
    return this;
  }

  public Equity setValue(String value) {
    this.value = Preconditions.checkNotNull(value);
    return this;
  }
}
