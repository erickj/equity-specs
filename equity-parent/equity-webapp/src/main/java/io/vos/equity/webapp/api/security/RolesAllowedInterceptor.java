package io.vos.equity.webapp.api.security;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.inject.Provider;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

class RolesAllowedInterceptor implements MethodInterceptor {

  private static final Logger LOGGER = Logger.getLogger(RolesAllowedInterceptor.class.getName());

  private final Provider<HttpServletRequest> requestProvider;

  RolesAllowedInterceptor(Provider<HttpServletRequest> requestProvider) {
    this.requestProvider = Preconditions.checkNotNull(requestProvider);
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    LOGGER.info("entered #invoke");
    Method m = invocation.getMethod();
    RolesAllowed rolesAllowed = Preconditions.checkNotNull(
        m.getAnnotation(RolesAllowed.class));
    String[] roles = rolesAllowed.value();

    boolean hasRole = Iterables.any(Lists.newArrayList(roles), createIsInRolePredicate());
    if (!hasRole) {
      throw new IllegalStateException("User not in role " + roles[0]);
    }

    return invocation.proceed();
  }

  private Predicate<String> createIsInRolePredicate() {
    return new Predicate<String>() {
      @Override
      public boolean apply(@Nullable String role) {
        return requestProvider.get().isUserInRole(role);
      }
    };
  }
}
