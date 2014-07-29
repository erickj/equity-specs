package io.vos.equity.controller;

import io.vos.equity.model.Equity;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class EquityController {

  private static final Logger LOGGER = Logger.getLogger(EquityController.class.getName());

  private Provider<EntityManager> emProvider;

  @Inject
  EquityController(Provider<EntityManager> emProvider) {
    LOGGER.info("Constructed new EquityController");
    this.emProvider = Preconditions.checkNotNull(emProvider);;
  }

  @Transactional
  public Equity find(int id) {
    return emProvider.get().find(Equity.class, id);
  }

  @Transactional
  public void save(Equity equity) {
    emProvider.get().persist(equity);
  }
}
