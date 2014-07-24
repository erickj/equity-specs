package io.vos.equity.model;

import java.util.Date;

public class Equity {

  private final int id;
  private final String name;
  private final String value;
  private final Date now;

  public Equity(int id, String name, String value) {
    this.id = id;
    this.name = name;
    this.value = value;
    this.now = new Date();
  }
}
