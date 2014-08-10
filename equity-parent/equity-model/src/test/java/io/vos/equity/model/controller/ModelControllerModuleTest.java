package io.vos.equity.model.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.vos.equity.model.Equity;
import io.vos.equity.model.Model;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;
import com.google.inject.persist.PersistService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelControllerModuleTest {

  private Injector injector;

  @Before
  public void setUp() {
    injector = Guice.createInjector(
        new ModelControllerModule() {
          /** Expose the PersistService for testability. */
          @Override
          void postInstallJpaPersistModule() {
            expose(PersistService.class);
          }
        });

    // Starts up persistence.
    // @see https://github.com/google/guice/wiki/JPA
    injector.getInstance(PersistService.class).start();
  }

  @After
  public final void tearDown() {
    injector.getInstance(PersistService.class).stop();
  }

  @Test
  public void testEquityController_bound() {
    ModelController<Equity> controller =
        injector.getInstance(Key.get(new TypeLiteral<ModelController<Equity>>() {}));
    assertNotNull(controller);

    Equity e = controller.create();
    assertTrue(e instanceof Equity);
  }

}
