package io.vos.equity.webapp.config;

import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import io.vos.equity.webapp.api.EquityResource;

class ResourceModule extends JerseyServletModule {

    @Override
    protected void configureServlets() {
        bind(EquityResource.class);
        serve("/api/*").with(GuiceContainer.class);
    }
}
