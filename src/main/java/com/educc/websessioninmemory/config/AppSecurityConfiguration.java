package com.educc.websessioninmemory.config;

import com.educc.websessioninmemory.auth.TokenAuthenticationProvider;
import com.educc.websessioninmemory.auth.TokenIdAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//public class AppSecurityConfiguration {
//
//}

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AppSecurityConfiguration extends WebSecurityConfigurerAdapter {



  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
        .and()
        .withUser("user2").password(passwordEncoder().encode("user2")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN");

    auth.authenticationProvider(new TokenAuthenticationProvider());
  }



  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/").permitAll()
        .antMatchers("/index.html").permitAll()
        .antMatchers("/login*").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
//        .loginPage("/login.html")
//        .loginProcessingUrl("/perform_login")
        .defaultSuccessUrl("/", true)
        //.failureUrl("/login.html?error=true")
//        .failureHandler(authenticationFailureHandler())
        .and()
        .logout()
        //.logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID");
        //.logoutSuccessHandler(logoutSuccessHandler());

//    http
//        .sessionManagement()
//            .sessionCreationPolicy(
//            SessionCreationPolicy.IF_REQUIRED) // No session will be created or used by spring security

    http.addFilterAfter(new TokenIdAuthenticationFilter(this.authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
