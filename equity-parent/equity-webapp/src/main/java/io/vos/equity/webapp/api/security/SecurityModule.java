package io.vos.equity.webapp.security;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matcher;
import com.google.inject.matcher.Matchers;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

public class SecurityModule extends AbstractModule {

  @Override
  protected void configure() {
    bindInterceptor(
        Matchers.any(),
        Matchers.annotatedWith(RolesAllowed.class),
        new RolesAllowedInterceptor(getProvider(HttpServletRequest.class)));
  }
}
