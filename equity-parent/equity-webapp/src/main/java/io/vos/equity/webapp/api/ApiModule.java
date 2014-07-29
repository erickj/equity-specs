package io.vos.equity.webapp.api;

import io.vos.equity.controller.EquityController;
import io.vos.equity.webapp.api.session.SessionFilter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.RequestScoped;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ApiModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    // @see https://github.com/google/guice/wiki/JPA
    install(new JpaPersistModule("entityModelStore"));
    filter("/*").through(PersistFilter.class);

    bind(EquityController.class).in(RequestScoped.class);

    bind(JsonMessageBodyWriter.class);
    bind(EquityResource.class);

    filter("/*").through(SessionFilter.class);

    serve("/api/*").with(GuiceContainer.class);
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
