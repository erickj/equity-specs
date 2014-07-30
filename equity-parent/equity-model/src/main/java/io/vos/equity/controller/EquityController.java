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

  private EntityManager em;

  @Inject
  EquityController(EntityManager em) {
    LOGGER.info("Constructed new EquityController");
    this.em = em;
  }

  @Transactional
  public Equity find(int id) {
    return em.get().find(Equity.class, id);
  }

  @Transactional
  public void save(Equity equity) {
    em.get().persist(equity);
  }
}
