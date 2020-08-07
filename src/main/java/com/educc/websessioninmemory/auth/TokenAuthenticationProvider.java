package com.educc.websessioninmemory.auth;

import java.util.Arrays;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public class TokenAuthenticationProvider implements AuthenticationProvider {

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    TokenAuthentication auth = (TokenAuthentication) authentication;

    //if (auth.getCredentials() == null) return auth;

    TokenAuthentication result = auth;
    String token = (String) auth.getCredentials();

    if (auth.getCredentials().toString().equals("123")) {
      var list = Arrays.asList(new GrantedAuthority() {
        @Override
        public String getAuthority() {
          return "ROLE_ADMIN";
        }
      });
      UserDetails userDetails = new User("edu", token, list);
      result = new TokenAuthentication(userDetails, token, list);
    }

    return result;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    if(authentication == null) return false;
    return TokenAuthentication.class.equals(authentication);
  }
}
