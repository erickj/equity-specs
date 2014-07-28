package io.vos.equity.webapp.config;

import io.vos.equity.webapp.api.ApiModule;
import io.vos.equity.webapp.api.security.SecurityModule;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
        new ApiModule(),
        new SecurityModule());
  }
}
