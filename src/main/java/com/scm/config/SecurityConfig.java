package com.scm.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.scm.services.impl.SecurityCustomUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class SecurityConfig {



  // For in memory use
  /*
   * //User create and login using java code in memory service
   * 
   * @Bean
   * public UserDetailsService userDetailsService(){
   * 
   * // User from spring.framework.security not from enitities
   * UserDetails userDetails = User
   * .withDefaultPasswordEncoder()
   * .username("admin@123")
   * .password("@@@")
   * .roles("ADMIN","LEADER")
   * .build();
   * 
   * UserDetails userDetails2 = User
   * .withDefaultPasswordEncoder()
   * .username("ankit")
   * .password("1234")
   * .build();
   * 
   * var inMemoryUserDetailsManager = new
   * InMemoryUserDetailsManager(userDetails,userDetails2);
   * return inMemoryUserDetailsManager;
   * }
   */

   @Autowired
   private AuthFailureHandler authFailureHandler;


  @Autowired
  private SecurityCustomUserDetailService userDetailService;

  @Autowired
  private OAuthAuthenticationSuccessHandler handler;

  // Configuration of authentication provider spring security
  // For database configuration
  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    // User Details Service ka object
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    // Password encoder ka object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

    return daoAuthenticationProvider;
  }

  // kis me security lagega kisme nhi here
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

    // Configuration

    // Konsi URL private rahegi aur konsi public rahegi wo yahan dikhaya hai
    httpSecurity.authorizeHttpRequests(authorize -> {
      // authorize.requestMatchers("/home","register","services").permitAll();

      // User se suru hone wale sab pe lock laga do
      authorize.requestMatchers("/user/**").authenticated();
      // Baaki saari request ko kholdo
      authorize.anyRequest().permitAll();
    });

    // form default login
    // Agar form mein kuch bhi change karna hua to ham yaha karenge
    httpSecurity.formLogin(formLogin -> {

      // here we address our login page url
      formLogin.loginPage("/login");

      // Login pe data submit hone ke badh data kahan process hoga yahan de sakte ho .
      formLogin.loginProcessingUrl("/authenticate");

      // Agar Login success hone ke badh kahan forward hona hai .
      formLogin.successForwardUrl("/user/profile");

      // Agar Login error aata hai tab konsi page mein forward hona hai.
      // formLogin.failureForwardUrl("/login?error=true");

      formLogin.usernameParameter("email");
      formLogin.passwordParameter("password");

      // formLogin.failureHandler(new AuthenticationFailureHandler() {

      // @Override
      // public void onAuthenticationFailure(HttpServletRequest request,
      // HttpServletResponse response,
      // AuthenticationException exception) throws IOException, ServletException {
      // // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method
      // 'onAuthenticationFailure'");
      // }

      // });

      // formLogin.successHandler(new AuthenticationSuccessHandler() {

      // @Override
      // public void onAuthenticationSuccess(HttpServletRequest request,
      // HttpServletResponse response,
      // Authentication authentication) throws IOException, ServletException {
      // // TODO Auto-generated method stub
      // throw new UnsupportedOperationException("Unimplemented method
      // 'onAuthenticationSuccess'");
      // }

      // });

      /*
       * formLogin.failureHandler(new AuthenticationFailureHandler() {
       * 
       * @Override
       * public void onAuthenticationFailure(HttpServletRequest request,
       * HttpServletResponse response, AuthenticationException exception) throws
       * IOException, ServletException {
       * 
       * }
       * });
       * 
       * formLogin.successHandler(new AuthenticationSuccessHandler() {
       * 
       * @Override
       * public void onAuthenticationSuccess(HttpServletRequest request,
       * HttpServletResponse response, Authentication authentication) throws
       * IOException, ServletException {
       * 
       * }
       * });
       */

       formLogin.failureHandler(authFailureHandler);

    });

    httpSecurity.csrf(AbstractHttpConfigurer::disable);

    // oauth configuration

    httpSecurity.oauth2Login(oauth -> {
      oauth.loginPage("/login");
      oauth.successHandler(handler);
    });

    httpSecurity.logout(logoutForm -> {

      logoutForm.logoutUrl("/do-logout");
      logoutForm.logoutSuccessUrl("/login?logout=true");

    });

    return httpSecurity.build();

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();

  }

}
