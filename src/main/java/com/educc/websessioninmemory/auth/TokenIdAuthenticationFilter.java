package com.educc.websessioninmemory.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class TokenIdAuthenticationFilter extends AbstractAuthenticationProcessingFilter {


  public TokenIdAuthenticationFilter(AuthenticationManager authenticationManager) {
    super(new AntPathRequestMatcher("/login-by-tokenid", "GET"));
    this.setAuthenticationManager(authenticationManager);
  }

  public Authentication attemptAuthentication(HttpServletRequest request,
                                              HttpServletResponse response) throws
      AuthenticationException {
    String token = request.getParameter("token");
    if (token == null) {
      throw new AuthenticationServiceException(
          "Authentication method not supported: " + request.getMethod());
    }

    var authRequest = new TokenAuthentication(null, token);
    Authentication authenticate = this.getAuthenticationManager().authenticate(authRequest);

    if (authenticate.isAuthenticated()) {
      response.setStatus(301);
      response.setHeader("Location", "/");
    }

    return authenticate;
  }

  @Override
  public void setAuthenticationManager(AuthenticationManager authenticationManager) {
    super.setAuthenticationManager(authenticationManager);
  }
}
