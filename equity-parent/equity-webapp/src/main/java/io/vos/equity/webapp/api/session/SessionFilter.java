package io.vos.equity.webapp.api.session;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Singleton
public class SessionFilter implements Filter {

  private static final Logger LOGGER = Logger.getLogger(SessionFilter.class.getName());

  private FilterConfig filterConfig;

  @Override
  public void destroy() {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    Preconditions.checkArgument(request instanceof HttpServletRequest);
    Preconditions.checkArgument(response instanceof HttpServletResponse);

    Preconditions.checkState(checkRequestSession((HttpServletRequest) request));
    filterChain.doFilter(request, response);
  }

  private boolean checkRequestSession(HttpServletRequest request) {
    HttpSession session = request.getSession(false /* create */);
    return true;
  }

  @Override
  public void init(FilterConfig filterConfig) {
    this.filterConfig = filterConfig;
  }
}
