package io.vos.equity.model;

import com.google.common.base.Preconditions;

import java.util.Date;

public class Equity implements Model {

  private final int id;
  private final String name;
  private final String value;
  private final Date now;

  public Equity(int id, String name, String value) {
    this.id = id;
    this.name = Preconditions.checkNotNull(name);
    this.value = Preconditions.checkNotNull(value);
    this.now = new Date();
  }
}
