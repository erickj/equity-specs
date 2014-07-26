package io.vos.equity.webapp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import io.vos.equity.webapp.api.EquityResource;

public class ApiModule extends JerseyServletModule {

  @Override
  protected void configureServlets() {
    bind(JsonMessageBodyWriter.class);

    bind(EquityResource.class);
    serve("/api/*").with(GuiceContainer.class);
  }

  @Provides
  Gson provideGson() {
    return new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();
  }
}
