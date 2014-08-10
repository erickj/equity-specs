package io.vos.equity.model.controller;

import io.vos.equity.ext.jpa.BaseJpaBindingModule;
import io.vos.equity.model.Equity;
import io.vos.equity.model.Model;

import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.PrivateModule;
import com.google.inject.Scopes;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

import java.util.logging.Logger;
import javax.persistence.EntityManager;

public class ModelControllerModule extends BaseJpaBindingModule {

  private static final String JPA_UNIT_NAME = "io.vos.equity.model";

  @Override
  public final String getJpaUnitName() {
    return JPA_UNIT_NAME;
  }

  @Override
  protected void configureInternal() {
    // After LOTS of attempts to find a generic way to bind ModelControllers the following is the
    // best I can find. Failed attempts included many variations of TypeLiterals with Providers and
    // assisted injection.
    // @see https://github.com/google/guice/wiki/Injections
    // @see https://github.com/google/guice/wiki/AssistedInject
    //
    // Final approach described here, I'm still not happy w/ needing to suppress
    // the unchecked cast in the ModelControllerImpl constructor.
    // @see http://jamolkhon.blogspot.com/2012/07/generic-typesafe-dao-with-guice-and.html
    bind(new TypeLiteral<ModelController<Equity>>() {})
        .to(new TypeLiteral<ModelControllerImpl<Equity>>() {})
        .in(Scopes.SINGLETON);
    expose(new TypeLiteral<ModelController<Equity>>() {});

    postInstallJpaPersistModule();
  }

  /**
   * Used by the {@link ModelControllerModuleTest} to expose the
   * PersistService.class for testing.
   */
  void postInstallJpaPersistModule() {};
}
