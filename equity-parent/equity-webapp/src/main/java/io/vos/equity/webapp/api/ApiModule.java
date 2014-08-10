package io.vos.equity.webapp.api;

import io.vos.equity.ext.jpa.JpaBindingModule;
import io.vos.equity.model.controller.ModelControllerModule;
import io.vos.equity.webapp.api.session.SessionFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.servlet.RequestScoped;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ApiModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    installJpaBindingModule(new ModelControllerModule());

    bind(JsonMessageBodyWriter.class);
    bind(EquityResource.class);

    filter("/*").through(SessionFilter.class);

    serve("/api/*").with(GuiceContainer.class);
  }

  private void installJpaBindingModule(JpaBindingModule jpaBindingModule) {
    install(jpaBindingModule);
    filter("/*").through(jpaBindingModule.getPersistFilterKey());
  }

  @Provides
  @Singleton
  @ApiSerializer
  Gson provideGson() {
    return new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .setPrettyPrinting()
        .create();
  }
}
